package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import bloodbank.db.pojos.BloodBank;
import bloodbank.db.pojos.BloodRequest;
import bloodbank.db.pojos.Donation;
import bloodbank.ifaces.BloodBankManager;

public class BloodBankManagerImpl implements BloodBankManager {

	@Override
	public void newBloodBank(BloodBank bloodbank) {
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Please, input the BloodBank info:");
	        System.out.print("Name: ");
	        String name = reader.readLine();
            System.out.print("Address: ");
	        String address = reader.readLine();
	        System.out.print("City: ");
	        String city = reader.readLine();
	        System.out.print("Contact Number: ");
	        String contact = reader.readLine();
	        System.out.print("Person Responsible: ");
	        String person = reader.readLine();
	        System.out.print("Capacity Stock (integer): ");
	        String capacity = reader.readLine();

			
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO BloodBank (name, address, city, contact_number, person_responsible, capacity_stock) "
			           + "VALUES ('" 
			           + name + "', '"
			           + address + "', '"
			           + city + "', '"
			           + contact + "', '"
			           + person + "', "
			           + capacity
			           + ");";

			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("BloodBank info processed");
			System.out.println("Records inserted.");
			

			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void linkDonation(BloodBank bloodbank, Donation donation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBloodBank(BloodBank bloodbank) {
		//TODO rellenar metodo

	}

	@Override
	public List<BloodBank> searchBloodBank(String name, String city) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:bloodbank_database.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Name to search (use % for wildcard): ");
            String searchName = reader.readLine();
            System.out.print("City to search (use % for wildcard): ");
            String searchCity = reader.readLine();

            String sql = "SELECT * FROM BloodBank WHERE name LIKE ? AND city LIKE ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, searchName);
            prep.setString(2, searchCity);
            ResultSet rs = prep.executeQuery();
			
         // print results
            while (rs.next()) {
                int    id             = rs.getInt("ID");
                String name           = rs.getString("name");
                String address        = rs.getString("address");
                String city           = rs.getString("city");
                int    contactNumber  = rs.getInt("contact_number");
                String personResp     = rs.getString("person_responsible");
                int    capacityStock  = rs.getInt("capacity_stock");

                //BloodBank bb = new BloodBank(id, name, address, city, contactNumber, personResp, capacityStock);
                //System.out.println(bb);
            }

            
            rs.close();
            prep.close();
            System.out.println("Search finished.");
		
			rs.close();
			prep.close();
			System.out.println("Search finished.");

			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public void linkBloodRequest(BloodBank bloodbank, BloodRequest bloodrequest) {
		// TODO Auto-generated method stub

	}

}
