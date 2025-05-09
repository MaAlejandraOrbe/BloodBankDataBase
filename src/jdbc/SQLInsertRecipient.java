package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertRecipient {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");
            
            System.out.println("1: New recipient");
            System.out.println("2: Existing recipient");
            System.out.print("Your choice (1 or 2): ");
            int choice = Integer.parseInt(reader.readLine().trim());

            switch(choice) {
            
            case 1:
            	 System.out.println("Please, input the Recipient info:");
                 System.out.print("First name: ");
                 String firstName = reader.readLine();
                 System.out.print("Last name: ");
                 String lastName = reader.readLine();
                 System.out.print("DOB (YYYY-MM-DD): ");
                 String dob = reader.readLine();
                 System.out.print("Blood type: ");
                 String bloodType = reader.readLine();
                 System.out.print("Country: ");
                 String country = reader.readLine();
                 System.out.print("Contact number: ");
                 String contact = reader.readLine();
                 System.out.print("Emergency contact number: ");
                 String emergency = reader.readLine();

                 Statement stmt = c.createStatement();
                 String sql = "INSERT INTO Recipient "
                            + "(first_name, last_name, DOB, blood_type, country, contact_number, emergency_contact_number) "
                            + "VALUES ('"
                            + firstName  + "', '"
                            + lastName   + "', '"
                            + dob        + "', '"
                            + bloodType  + "', '"
                            + country    + "', '"
                            + contact    + "', '"
                            + emergency  + "');";
                 stmt.executeUpdate(sql);
                 stmt.close();
                 }

                 System.out.println("Recipient info processed");
                 System.out.println("Record inserted.");

                 c.close();
            	
           
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
