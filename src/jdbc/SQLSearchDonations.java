package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bloodbank.db.pojos.Donation;

public class SQLSearchDonations {

    public static void main(String[] args) {
        try {
            
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:./db/bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

           
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Status to search (use % for wildcard): ");
            String searchStatus = reader.readLine();

            String sql = "SELECT * FROM Donations WHERE status LIKE ?";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, searchStatus);
            ResultSet rs = prep.executeQuery();

        
            while (rs.next()) {
                int    id             = rs.getInt("ID");
                int    donorId        = rs.getInt("donor_id");
                int    bloodBankId    = rs.getInt("bloodBank_id");
                String status         = rs.getString("status");
                Date   dateDonation   = rs.getDate("date_donation");
                int    quantity       = rs.getInt("quantity");
                Date   expirationDate = rs.getDate("expiration_date");

                //Donation donation = new Donation(id, donorId, bloodBankId, status, dateDonation, quantity, expirationDate);
                //System.out.println(donation);
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
