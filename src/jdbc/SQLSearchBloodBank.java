package jdbc;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bloodbank.db.pojos.BloodBank;

public class SQLSearchBloodBank {
	//by name and city
	
	public static void main(String args[]) {
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
                Integer    id             = rs.getInt("ID");
                String name           = rs.getString("name");
                String address        = rs.getString("address");
                String city           = rs.getString("city");
                String    contactNumber  = rs.getString("contact_number");
                String personResp     = rs.getString("person_responsible");
                int    capacityStock  = rs.getInt("capacity_stock");

                BloodBank bb = new BloodBank(id, name, address, city,  contactNumber, personResp, capacityStock);
                System.out.println(bb);
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
	}
}