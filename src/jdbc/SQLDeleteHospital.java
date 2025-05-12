package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bloodbank.db.pojos.Hospital;  // your POJO for the Hospitals table

public class SQLDeleteHospital {
    // Shared connection
    private static Connection c;

    // Prints all rows in the Hospitals table
    private static void printHospitals() throws Exception {
        Statement stmt = c.createStatement();
        String sql = "SELECT * FROM Hospitals";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int    id               = rs.getInt("ID");
            String name             = rs.getString("name");
            String city             = rs.getString("city");
            String address          = rs.getString("address");
            String personResponsible= rs.getString("person_responsible");
            String contactNumber    = rs.getString("contact_number");
            Hospital h = new Hospital(
                id,
                name,
                city,
                address,
                personResponsible,
                contactNumber
            );
            System.out.println(h);
        }
        rs.close();
        stmt.close();
    }

    public static void main(String[] args) {
        try {
            // 1) Open database connection
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

       
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Choose a hospital to delete by ID:");
            printHospitals();
            int hospitalId = Integer.parseInt(reader.readLine().trim());
           
            String deleteSql = "DELETE FROM Hospitals WHERE ID = ?";
            PreparedStatement prep = c.prepareStatement(deleteSql);
            prep.setInt(1, hospitalId);
            int count = prep.executeUpdate();
            if (count > 0) {
                System.out.println("Successfully deleted Hospital with ID=" + hospitalId);
            } else {
                System.out.println("No Hospital found with ID=" + hospitalId);
            }
            prep.close();

            
            c.close();
            System.out.println("Database connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
