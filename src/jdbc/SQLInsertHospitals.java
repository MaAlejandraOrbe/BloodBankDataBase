package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertHospitals {

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager
                .getConnection("jdbc:sqlite:bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please, input the Hospitals info:");
            System.out.print("Name: ");
            String name = reader.readLine();
            System.out.print("City: ");
            String city = reader.readLine();
            System.out.print("Address: ");
            String address = reader.readLine();
            System.out.print("Person Responsible: ");
            String person = reader.readLine();
            System.out.print("Contact Number: ");
            String contact = reader.readLine();

            Statement stmt = c.createStatement();
            String sql = "INSERT INTO Hospitals "
                       + "(name, city, address, person_responsible, contact_number) "
                       + "VALUES ('"
                       + name    + "', '"
                       + city    + "', '"
                       + address + "', '"
                       + person  + "', '"
                       + contact + "');";
            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Hospitals info processed");
            System.out.println("Record inserted.");

            c.close();
            System.out.println("Database connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
