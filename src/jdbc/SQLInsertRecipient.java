package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;  // Added import for ResultSet
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class SQLInsertRecipient {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            conn.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

            // Declare variables before switch for proper scope
            int recipientId = -1;
            System.out.print("Hospital ID (integer): ");
            int hospitalId = Integer.parseInt(reader.readLine().trim());  // Moved prompt outside switch

            System.out.println("1: New recipient");
            System.out.println("2: Existing recipient");
            System.out.print("Your choice (1 or 2): ");
            int choice = Integer.parseInt(reader.readLine().trim());

            switch(choice) {
                case 1:
                    // Input and insert new recipient
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

                    Statement stmt = conn.createStatement();
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

                    // Retrieve the ID of the newly inserted recipient
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid() AS lastId");
                    if (rs.next()) {
                        recipientId = rs.getInt("lastId");  // Extract the ID
                    }
                    rs.close();
                    stmt.close();
                    System.out.println("Created Recipient with ID=" + recipientId);

                    // Link new recipient to hospital
                    stmt = conn.createStatement();
                    String linkSql =
                        "INSERT INTO Hospital_Recipient (recipient_id, hospital_id) VALUES (" + recipientId + ", " + hospitalId + ");";
                    stmt.executeUpdate(linkSql);
                    stmt.close();
                    System.out.println("Linked new Recipient " + recipientId + " to Hospital " + hospitalId);
                    break;  // Prevent fall-through

                case 2:
                    // Find and link existing recipient
                    System.out.println("Please, input partial Recipient info to find ID:");
                    System.out.print("Last name keyword: ");
                    String keyword = reader.readLine();

                    stmt = conn.createStatement();
                    String searchSql =
                        "SELECT ID FROM Recipient WHERE last_name LIKE '%" + keyword + "%';";
                    rs = stmt.executeQuery(searchSql);
                    List<Integer> ids = new ArrayList<>();
                    while (rs.next()) {
                        int id = rs.getInt("ID");
                        ids.add(id);
                        System.out.println("Found ID: " + id);
                    }
                    rs.close();
                    stmt.close();

                    if (ids.isEmpty()) {
                        System.err.println("No recipients found. Exiting.");
                        conn.rollback();  // Rollback on no match
                        return;
                    }
                    System.out.print("Enter chosen Recipient ID: ");
                    recipientId = Integer.parseInt(reader.readLine().trim());

                    // Link existing recipient to hospital
                    stmt = conn.createStatement();
                    linkSql =
                        "INSERT OR IGNORE INTO Hospital_Recipient (recipient_id, hospital_id) VALUES (" + recipientId + ", " + hospitalId + ");";
                    stmt.executeUpdate(linkSql);
                    stmt.close();
                    System.out.println("Linked existing Recipient " + recipientId + " to Hospital " + hospitalId);
                    break;  // Prevent fall-through

                default:
                    System.err.println("Invalid option selected. Exiting.");
                    break;
            }

            conn.close();  // Close connection
            System.out.println("Database connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
