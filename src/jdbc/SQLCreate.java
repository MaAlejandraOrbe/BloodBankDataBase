package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLCreate {
	
	public static void main(String args[]) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
			c.createStatement().execute("PRAGMA foreign_keys = ON");
			System.out.println("Database connection opened");

			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE IF NOT EXISTS BloodBank ("
			           + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "name TEXT NOT NULL, "
			           + "address TEXT NOT NULL, "
			           + "city TEXT NOT NULL, "
			           + "contact_number TEXT NOT NULL, "
			           + "person_responsible TEXT, "
			           + "capacity_stock INTEGER NOT NULL"
			           + ");";
			stmt1.executeUpdate(sql1);
			stmt1.close();

			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE IF NOT EXISTS Donor ("
			           + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "first_name TEXT NOT NULL, "
			           + "last_name TEXT NOT NULL, "
			           + "DOB DATE NOT NULL, "
			           + "blood_type TEXT NOT NULL, "
			           + "eligible_to_donate BOOLEAN NOT NULL, "
			           + "country TEXT, "
			           + "contact_number TEXT NOT NULL, "
			           + "emergency_contact_number TEXT NOT NULL"
			           + ");";
			stmt2.executeUpdate(sql2);
			stmt2.close();

			
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE IF NOT EXISTS Donations ("
			           + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "donor_id INTEGER NOT NULL, "
			           + "bloodBank_id INTEGER NOT NULL, "
			           + "status TEXT NOT NULL, "
			           + "date_donation DATE NOT NULL, "
			           + "quantity INTEGER NOT NULL, "
			           + "expiration_date DATE NOT NULL, "
			           + "FOREIGN KEY (donor_id) REFERENCES Donor(ID) ON DELETE CASCADE, "
			           + "FOREIGN KEY (bloodBank_id) REFERENCES BloodBank(ID) ON DELETE CASCADE"
			           + ");";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE IF NOT EXISTS Recipient ("
			           + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "first_name TEXT NOT NULL, "
			           + "last_name TEXT NOT NULL, "
			           + "DOB DATE NOT NULL, "
			           + "blood_type TEXT NOT NULL, "
			           + "country TEXT, "
			           + "contact_number TEXT, "
			           + "emergency_contact_number TEXT"
			           + ");";
			stmt4.executeUpdate(sql4);
			stmt4.close();

			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE IF NOT EXISTS Hospitals ("
			           + "ID INTEGER PRIMARY KEY, "
			           + "name TEXT NOT NULL, "
			           + "city TEXT NOT NULL, "
			           + "address TEXT NOT NULL, "
			           + "person_responsible TEXT, "
			           + "contact_number TEXT NOT NULL"
			           + ");";
			stmt5.executeUpdate(sql5);
			stmt5.close();

			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE IF NOT EXISTS Hospital_Recipient ("
			           + "recipient_id INTEGER NOT NULL, "
			           + "hospital_id INTEGER NOT NULL, "
			           + "PRIMARY KEY (recipient_id, hospital_id), "
			           + "FOREIGN KEY (recipient_id) REFERENCES Recipient(ID) ON DELETE CASCADE, "
			           + "FOREIGN KEY (hospital_id) REFERENCES Hospitals(ID) ON DELETE CASCADE"
			           + ");";
			stmt6.executeUpdate(sql6);
			stmt6.close();

			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE IF NOT EXISTS BloodRequest ("
			           + "ID INTEGER PRIMARY KEY, "
			           + "recipient_id INTEGER NOT NULL, "
			           + "bloodBank_id INTEGER NOT NULL, "
			           + "quantity_order INTEGER NOT NULL, "
			           + "status TEXT NOT NULL, "
			           + "donation_id INTEGER, "
			           + "FOREIGN KEY (bloodBank_id) REFERENCES BloodBank(ID) ON DELETE CASCADE, "
			           + "FOREIGN KEY (recipient_id) REFERENCES Recipient(ID) ON DELETE CASCADE, "
			           + "FOREIGN KEY (donation_id) REFERENCES Donations(ID) ON DELETE SET NULL"
			           + ");";
			stmt7.executeUpdate(sql7);
			stmt7.close();

			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE IF NOT EXISTS donationsWorker ("
			           + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "name TEXT NOT NULL, "
			           + "phone INTEGER, "
			           + "email TEXT NOT NULL"
			           + ");";
			stmt8.executeUpdate(sql8);
			stmt8.close();
			

			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE IF NOT EXISTS requestsWorker ("
			           + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "name TEXT NOT NULL, "
			           + "phone INTEGER, "
			           + "email TEXT NOT NULL"
			           + ");";
			stmt9.executeUpdate(sql9);
			stmt9.close();
			
			Statement stmt10 = c.createStatement();
			String sql10 = "CREATE TABLE IF NOT EXISTS bloodbankWorker ("
			           + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "name TEXT NOT NULL, "
			           + "phone INTEGER, "
			           + "email TEXT NOT NULL"
			           + ");";
			stmt10.executeUpdate(sql10);
			stmt10.close();
			
			Statement stmt11 = c.createStatement();
			String sql11 = "CREATE TABLE IF NOT EXISTS roles ("
			           + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
			           + "name TEXT NOT NULL, "
			           + ");";
			stmt11.executeUpdate(sql11);
			stmt11.close();

			


			c.close();
			System.out.println("Database connection closed");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
