package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertDonor {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please, input the Donor info:");
            System.out.print("First name: ");
            String firstName = reader.readLine();
            System.out.print("Last name: ");
            String lastName = reader.readLine();
            System.out.print("DOB (YYYY-MM-DD): ");
            String dob = reader.readLine();
            System.out.print("Blood type: ");
            String bloodType = reader.readLine();
            System.out.print("Eligible to donate? (1 = yes, 0 = no): ");
            String eligible = reader.readLine();
            System.out.print("Country: ");
            String country = reader.readLine();
            System.out.print("Contact number: ");
            String contact = reader.readLine();
            System.out.print("Emergency contact number: ");
            String emergency = reader.readLine();

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO Donor "
                       + "(first_name, last_name, DOB, blood_type, eligible_to_donate, country, contact_number, emergency_contact_number) "
                       + "VALUES ('"
                       + firstName + "', '"
                       + lastName  + "', '"
                       + dob       + "', '"
                       + bloodType + "', "
                       + eligible  + ", '"
                       + country   + "', '"
                       + contact   + "', '"
                       + emergency + "');";

            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Donor info processed");
            System.out.println("Record inserted.");

            c.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
