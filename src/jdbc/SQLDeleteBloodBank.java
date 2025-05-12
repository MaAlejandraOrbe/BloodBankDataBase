package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bloodbank.db.pojos.BloodBank;

public class SQLDeleteBloodBank {
	private static Connection c;

	private static void printBloodBanks() throws Exception {
        Statement stmt = c.createStatement();
        String sql = "SELECT * FROM BloodBank";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id   = rs.getInt("ID");
            String name  = rs.getString("name");
            String addr  = rs.getString("address");
            String city  = rs.getString("city");
            int contact   = rs.getInt("contact_number");
            String person = rs.getString("person_responsible");
            int capacity  = rs.getInt("capacity_stock");
            BloodBank bb = new BloodBank(id, name, addr, city, contact, person, capacity);
            System.out.println(bb);
        }
        rs.close();
        stmt.close();
    }


	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			// Note that we are using the class' connection
			c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Remove an employee: beginning
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose a blood bank to delete, type its ID: ");
			printBloodBanks();
			
			int bankId = Integer.parseInt(reader.readLine());
			String sql = "DELETE FROM BloodBank WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, bankId);
			prep.executeUpdate();
			System.out.println("Deletion finished.");
			// Remove an employee: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}