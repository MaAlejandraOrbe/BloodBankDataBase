package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    private Connection c;

    public ConnectionManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:bloodbank_database.db");
            c.createStatement().execute("PRAGMA foreign_keys=ON");
            System.out.println("Database connection opened.");
            
            createTables();
        } catch (Exception e) {
            System.out.println("Database access error");
            e.printStackTrace();
        }
    }

    private void createTables() {
        try {
            Statement s = c.createStatement();

            // BloodBank table
            String table1 = 
                "CREATE TABLE BloodBank ("
              + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
              + " name TEXT NOT NULL,"
              + " address TEXT NOT NULL,"
              + " city TEXT NOT NULL,"
              + " contact_number INTEGER NOT NULL,"
              + " person_responsible TEXT,"
              + " capacity_stock INTEGER NOT NULL"
              + ")";
            s.executeUpdate(table1);

            // Donor table
            String table2 =
                "CREATE TABLE Donor ("
              + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
              + " first_name TEXT NOT NULL,"
              + " last_name TEXT NOT NULL,"
              + " DOB DATE NOT NULL,"
              + " blood_type TEXT NOT NULL,"
              + " eligible_to_donate BOOLEAN NOT NULL,"
              + " country TEXT,"
              + " contact_number TEXT NOT NULL,"
              + " emergency_contact_number INTEGER NOT NULL"
              + ")";
            s.executeUpdate(table2);

            // Donations table
            String table3 =
                "CREATE TABLE Donations ("
              + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
              + " donor_id INTEGER NOT NULL,"
              + " bloodBank_id INTEGER NOT NULL,"
              + " status TEXT NOT NULL,"
              + " date_donation DATE NOT NULL,"
              + " quantity INTEGER NOT NULL,"
              + " expiration_date DATE NOT NULL,"
              + " FOREIGN KEY (donor_id) REFERENCES Donor(ID) ON DELETE CASCADE,"
              + " FOREIGN KEY (bloodBank_id) REFERENCES BloodBank(ID) ON DELETE CASCADE"
              + ")";
            s.executeUpdate(table3);

            // Recipient table
            String table4 =
                "CREATE TABLE Recipient ("
              + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
              + " first_name TEXT NOT NULL,"
              + " last_name TEXT NOT NULL,"
              + " DOB DATE NOT NULL,"
              + " blood_type TEXT NOT NULL,"
              + " country TEXT,"
              + " contact_number TEXT NOT NULL,"
              + " emergency_contact_number INTEGER NOT NULL"
              + ")";
            s.executeUpdate(table4);

            // Hospitals table
            String table5 =
                "CREATE TABLE Hospitals ("
              + " ID INTEGER PRIMARY KEY,"
              + " name TEXT NOT NULL,"
              + " city TEXT NOT NULL,"
              + " address TEXT NOT NULL,"
              + " person_responsible TEXT,"
              + " contact_number TEXT NOT NULL"
              + ")";
            s.executeUpdate(table5);

            // Hospital_Recipient join table
            String table6 =
                "CREATE TABLE Hospital_Recipient ("
              + " recipient_id INTEGER,"
              + " hospital_id INTEGER,"
              + " FOREIGN KEY (recipient_id) REFERENCES Recipient(ID) ON DELETE CASCADE,"
              + " FOREIGN KEY (hospital_id) REFERENCES Hospitals(ID) ON DELETE CASCADE"
              + ")";
            s.executeUpdate(table6);

            // BloodRequest table
            String table7 =
                "CREATE TABLE BloodRequest ("
              + " ID INTEGER PRIMARY KEY,"
              + " recipient_id INTEGER NOT NULL,"
              + " bloodBank_id INTEGER NOT NULL,"
              + " quantity_order INTEGER NOT NULL,"
              + " status TEXT NOT NULL,"
              + " donation_id INTEGER,"
              + " FOREIGN KEY (bloodBank_id) REFERENCES BloodBank(ID) ON DELETE CASCADE,"
              + " FOREIGN KEY (recipient_id) REFERENCES Recipient(ID) ON DELETE CASCADE,"
              + " FOREIGN KEY (donation_id) REFERENCES Donations(ID) ON DELETE SET NULL"
              + ")";
            s.executeUpdate(table7);

            s.close();
        } catch (SQLException e) {
            // If tables already exist, just ignore
            if (e.getMessage().contains("already exist")) {
                return;
            }
            System.out.println("Database error.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }

    public void closeConnection() {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println("Database error.");
            e.printStackTrace();
        }
    }
}
