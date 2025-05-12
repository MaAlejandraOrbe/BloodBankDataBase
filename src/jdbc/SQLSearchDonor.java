package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bloodbank.db.pojos.Donor;

public class SQLSearchDonor {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:./db/bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("First name to search (use % for wildcard): ");
            String searchFirst = reader.readLine();
            System.out.print("Last name to search (use % for wildcard): ");
            String searchLast = reader.readLine();
            System.out.print("DOB to search (YYYY-MM-DD): ");
            String searchDob = reader.readLine();

            // 3) Prepare and execute query
            String sql = "SELECT * FROM Donor WHERE first_name LIKE ? AND last_name LIKE ? AND DOB = ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, searchFirst);
            prep.setString(2, searchLast);
            prep.setString(3, searchDob);
            ResultSet rs = prep.executeQuery();

            // 4) Iterate results and print each Donor
            while (rs.next()) {
                int    id                  = rs.getInt("ID");
                String firstName           = rs.getString("first_name");
                String lastName            = rs.getString("last_name");
                String dob                 = rs.getString("DOB");
                String bloodType           = rs.getString("blood_type");
                boolean eligibleToDonate   = rs.getBoolean("eligible_to_donate");
                String country             = rs.getString("country");
                String contactNumber       = rs.getString("contact_number");
                int    emergencyContactNum = rs.getInt("emergency_contact_number");

              //  Donor donor = new Donor(id, firstName, lastName, dob, bloodType, eligibleToDonate, country, contactNumber, emergencyContactNum);
               // System.out.println(donor);
            }

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
