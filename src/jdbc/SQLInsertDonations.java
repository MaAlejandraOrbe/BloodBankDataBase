package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertDonations {

    public static void main(String[] args) {
        try {
            
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");
         
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please, input the Donations info:");
            System.out.print("Donor ID (integer): ");
            String donorId = reader.readLine();
            System.out.print("BloodBank ID (integer): ");
            String bloodBankId = reader.readLine();
            System.out.print("Status: ");
            String status = reader.readLine();
            System.out.print("Date of Donation (YYYY-MM-DD): ");
            String dateDonation = reader.readLine();
            System.out.print("Quantity (integer): ");
            String quantity = reader.readLine();
            System.out.print("Expiration Date (YYYY-MM-DD): ");
            String expirationDate = reader.readLine();

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO Donations "
                       + "(donor_id, bloodBank_id, status, date_donation, quantity, expiration_date) "
                       + "VALUES ("
                       +   donorId + ", "
                       +   bloodBankId + ", '"
                       +   status + "', '"
                       +   dateDonation + "', "
                       +   quantity + ", '"
                       +   expirationDate + "');";

            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Donations info processed");
            System.out.println("Record inserted.");

            c.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
