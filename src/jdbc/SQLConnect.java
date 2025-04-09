package jdbc;
import java.sql.*;

public class SQLConnect {

	public static void main(String args[]) {
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.\n");
			
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM Donor";
			
			ResultSet rs = stmt.executeQuery(sql);
		
			while(rs.next()) {
				int id = rs.getInt("ID");
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				System.out.println("ID: " + id + ", Name: " + first_name + ", Surname: " + last_name); 
				
			}
			
			rs.close();
			stmt.close();
			c.close();
			System.out.println("\nDatabase connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}