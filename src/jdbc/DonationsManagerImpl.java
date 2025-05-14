package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bloodbank.db.pojos.Donor;
import bloodbank.db.pojos.BloodBank;
import bloodbank.db.pojos.Donation;
import bloodbank.ifaces.DonationsManager;

public class DonationsManagerImpl implements DonationsManager {

    private Connection c;

    public DonationsManagerImpl(Connection c) {
        this.c = c;
    }

    public void newDonation(Donation donation) {
        try {
            String sql = "INSERT INTO Donations (donor_id, bloodBank_id, status, date_donation, quantity, expiration_date) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, donation.getDonor().getId());
            p.setInt(2, donation.getBloodbank().getId());
            p.setString(3, donation.getStatus());
            p.setDate(4, Date.valueOf(donation.getDonation_date()));
            p.setInt(5, donation.getQuantity());
            p.setDate(6, Date.valueOf(donation.getExpiration_date()));
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in newDonation.");
            e.printStackTrace();
        }
    }

    @Override
    
    //TODO and why is there capacity stock in donor??????????? and address and city and we dont have the ones we neeed
    public void newDonor(Donor donor) {
        try {
            String sql = "INSERT INTO Donor (first_name, last_name, DOB, blood_type, eligible_to_donate, country, contact_number, emergency_contact_number) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            p.setString(1, donor.getFirst_name());
            p.setString(2, donor.getLast_name());
            p.setDate(3,Date.valueOf(donor.getDOB()));
            p.setString(4, donor.getBlood_type());
            p.setBoolean(5, donor.getEligible_donate());
            p.setString(6, donor.getCountry());
            p.setString(7, donor.getContact_number());
            p.setString(8, donor.getEmergency_contact_number());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in newDonor.");
            e.printStackTrace();
        }
    }

    //TODO: COMPROBAR QUE SEA CORRECTO
    @Override
    public void linkDonorToDonation(int donorId, int donationId) {
    	try {
    		String sql="INSERT INTO donorsToDonation(donorId, donationId) VALUES(?,?)";
    		PreparedStatement p=c.prepareStatement(sql);
    		p.setInt(1, donorId);
    		p.setInt(2, donationId);
    		p.executeUpdate();
    		p.close();
    		
    	}catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
    	}
        
    }
    

    @Override
    public List<Donor> searchDonor(String firstName, String lastName,Date DOB) {
        List<Donor> list = new ArrayList<Donor>();
        try {
            String sql = "SELECT * FROM Donor WHERE first_name LIKE ? AND last_name LIKE ? AND DOB = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, "%" + firstName + "%");
            p.setString(2, "%" + lastName + "%");
            p.setDate(3, DOB);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                java.time.LocalDate dobLd = rs.getDate("DOB").toLocalDate();
                String bloodType = rs.getString("blood_type");
                Boolean eligible = rs.getBoolean("eligible_to_donate");
                String country = rs.getString("country");
                String contact   = rs.getString("contact_number");
                String emergency = rs.getString("emergency_contact_number");
                Donor d = new Donor(id, fn, ln, dobLd, bloodType, country,eligible,  contact, emergency);
                	
                list.add(d);
            }
            rs.close();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in searchDonor.");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Donation> searchDonation(String status) {
        List<Donation> list = new ArrayList<Donation>();
        try {
            String sql = "SELECT * FROM Donations WHERE status LIKE ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, "%" + status + "%");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                Integer bbid           = rs.getInt("bloodBank_id");
                Integer donorid           = rs.getInt("donor_id");
                Integer quantity     = rs.getInt("quantity");
                String  st           = rs.getString("status");
                java.time.LocalDate dateDon   = rs.getDate("date_donation").toLocalDate();
                java.time.LocalDate expDate   = rs.getDate("expiration_date").toLocalDate();
                Donation d = new Donation(donorid,st,dateDon,quantity,expDate,bbid,donorid);
                list.add(d);
            }
            
            /*public Donation(Integer id, String status, LocalDate donation_date, Integer quantity, LocalDate expiration_date,
			BloodBank bloodbank, Donor donor) {
	*/
            rs.close();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in searchDonation.");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateDonation(Donation donation) {
        try {
            String sql = "UPDATE Donations SET status = ?, date_donation = ?, quantity = ?, expiration_date = ? WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, donation.getStatus());
            p.setDate(2, Date.valueOf(donation.getDonation_date()));
            p.setInt(3, donation.getQuantity());
            p.setDate(4, Date.valueOf(donation.getExpiration_date()));
            p.setInt(5, donation.getId());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in updateDonation.");
            e.printStackTrace();
        }
    }
}
