package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertBloodRequest {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager
                .getConnection("jdbc:sqlite:bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please, input the BloodRequest info:");
            System.out.print("ID (integer): ");
            String id = reader.readLine();
            System.out.print("Recipient ID (integer): ");
            String recipientId = reader.readLine();
            System.out.print("BloodBank ID (integer): ");
            String bloodBankId = reader.readLine();
            System.out.print("Quantity ordered (integer): ");
            String quantityOrder = reader.readLine();
            System.out.print("Status: ");
            String status = reader.readLine();
            System.out.print("Donation ID (integer or NULL): ");
            String donationId = reader.readLine();
            if (donationId == null || donationId.trim().isEmpty()) {
                donationId = "NULL";
            }

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO BloodRequest "
                       + "(ID, recipient_id, bloodBank_id, quantity_order, status, donation_id) "
                       + "VALUES ("
                       +   id             + ", "
                       +   recipientId    + ", "
                       +   bloodBankId    + ", "
                       +   quantityOrder  + ", '"
                       +   status         + "', "
                       +   donationId
                       + ");";
            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("BloodRequest info processed");
            System.out.println("Record inserted.");

            c.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
