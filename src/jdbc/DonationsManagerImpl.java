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
            p.setDate(4, donation.getDonation_date());
            p.setInt(5, donation.getQuantity());
            p.setDate(6, donation.getExpiration_date());
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
            p.setDate(3,donor.getDOB());
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
                Date dobLd = rs.getDate("DOB");
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
    
    private Donor getDonorById(int id) {
    	try {
    		String sql="SELECT * FROM  Donor WHERE ID = ?";
    		PreparedStatement p=c.prepareStatement(sql);
    		p.setInt(1,id);
    		ResultSet rs=p.executeQuery();
    		
    		Donor d=null;
    		if(rs.next()) {
    			d= new Donor(rs.getInt("ID"),
    					rs.getString("first_name"),
    	                rs.getString("last_name"),
    	                rs.getDate("DOB"),
    	                rs.getString("blood_type"),
    	                rs.getString("country"),
    	                rs.getBoolean("eligible_to_donate"),
    	                rs.getString("contact_number"),
    	                rs.getString("emergency_contact_number")
    	            );
    			}
    		rs.close();
    		p.close();
    		return d;
    		}
    	catch (SQLException e) {
            System.out.println("Error retrieving Donor with ID " + id);
            e.printStackTrace();
        }
        return null;
    }
    
    //!!!!! I DID THIS ONE TO USE IN MENU FOLLOWING THE EXAMPLE OD DOGSCLNIC
    public Donor getDonorByEmail(String email) {
    	try {
    		String sql="SELECT * FROM  Donor WHERE email = ?";
    		PreparedStatement p=c.prepareStatement(sql);
    		p.setString(1,email);
    		ResultSet rs=p.executeQuery();
    		
    		Donor d=null;
    		if(rs.next()) {
    			d= new Donor(rs.getInt("id"),
    					rs.getString("first_name"),
    	                rs.getString("last_name"),
    	                rs.getDate("DOB"),
    	                rs.getString("blood_type"),
    	                rs.getString("country"),
    	                rs.getBoolean("eligible_to_donate"),
    	                rs.getString("contact_number"),
    	                rs.getString("emergency_contact_number")
    	            );
    			}
    		rs.close();
    		p.close();
    		return d;
    		}
    	catch (SQLException e) {
            System.out.println("Error retrieving Donor with email" + email);
            e.printStackTrace();
        }
        return null;
    }
    
    
    private BloodBank getBloodBankById(int id) {
        try {
            String sql = "SELECT * FROM BloodBank WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, id);
            ResultSet rs = p.executeQuery();
            BloodBank b=null;
            if (rs.next()) {
            
                return new BloodBank(
                    rs.getInt("ID"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("contactNumber"),
                    rs.getString("personResponsible"),
                    rs.getInt("current_stock")
                );
            }
            rs.close();
            p.close();
            return b;
        } catch (SQLException e) {
        	 System.out.println("Error retrieving BloodBank with ID " + id);
            e.printStackTrace();
        }
        return null;
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
            	Integer id           = rs.getInt("ID");
            	String  st           = rs.getString("status");
                Date dateDon   = rs.getDate("date_donation");
                Integer quantity     = rs.getInt("quantity");
                Date expDate   = rs.getDate("expiration_date");
                Integer bbid           = rs.getInt("bloodBank_id");
                Integer donorid           = rs.getInt("donor_id");
                
                BloodBank bloodBank=getBloodBankById(bbid);
                Donor donor=getDonorById(donorid);
                
                Donation d = new Donation(id,st,dateDon,quantity,expDate,bloodBank,donor);
                            
                list.add(d);
                
            }
            
            
            rs.close();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in searchDonation.");
            e.printStackTrace();
        }
        return list;
    }
    
    /*method get id bb y get id donor.*/

    @Override
    public void updateDonation(Donation donation) {
        try {
            String sql = "UPDATE Donations SET status = ?, date_donation = ?, quantity = ?, expiration_date = ? WHERE ID = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, donation.getStatus());
            p.setDate(2, donation.getDonation_date());
            p.setInt(3, donation.getQuantity());
            p.setDate(4,donation.getExpiration_date());
            p.setInt(5, donation.getId());
            p.executeUpdate();
            p.close();
        } catch (SQLException e) {
            System.out.println("Database error in updateDonation.");
            e.printStackTrace();
        }
    }
}
