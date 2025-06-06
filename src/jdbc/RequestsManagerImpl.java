package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bloodbank.db.pojos.*;
import bloodbank.ifaces.RequestsManager;

public class RequestsManagerImpl implements RequestsManager {
	
	private Connection c;

	    public RequestsManagerImpl(Connection c) {
	        this.c = c;
	    }
	    
	
	    //TODO CHECK TABLES NAMES IN DATABASE: 
	    public void insertRequestsWorker(RequestsWorker requestsWorker) {
	    	try {
	    		Statement s=c.createStatement();
	    		String sql="INSERT INTO requestsWorker (name, phone, email) VALUES ('" + requestsWorker.getName() + "', "
						+ requestsWorker.getPhone() + ", '" + requestsWorker.getEmail() + "')";
				s.executeUpdate(sql);
				s.close();
			} catch (SQLException e) {
				System.out.println("Database exception.");
				e.printStackTrace();
			}	
	    }
	    
	    
	    public RequestsWorker getRequestsWorkerByEmail(String email) {
	    	try {
				String sql = "SELECT * FROM requestsWorker WHERE email = ?";
				PreparedStatement p = c.prepareStatement(sql);
				p.setString(1, email);
				ResultSet rs = p.executeQuery();
				rs.next();
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				Integer phone = rs.getInt("phone");
				RequestsWorker rW = new RequestsWorker(id, name, phone, email);
				rs.close();
				p.close();
				return rW;
			} catch (SQLException e) {
				System.out.println("Database error.");
				e.printStackTrace();
			}
			return null;
	    }
	    
	    public RequestsWorker getRequestsWorker(int id) {
	      	try {
				String sql = "SELECT * FROM requestsWorker WHERE id = ?";
				PreparedStatement p = c.prepareStatement(sql);
				p.setInt(1, id);
				ResultSet rs = p.executeQuery();
				rs.next();
				String name = rs.getString("name");
				Integer phone = rs.getInt("phone");
				String email = rs.getString("email");
				RequestsWorker rW = new RequestsWorker(id, name, phone, email);
				rs.close();
				p.close();
				return rW;
			} catch (SQLException e) {
				System.out.println("Database error.");
				e.printStackTrace();
			}
			return null;
	    }
	    

	
	    //TODO: COMPROBAR QUE ESTO SEA CORRECTO
	@Override
	public void fullfillBloodRequest(BloodRequest bloodRequest) {
		try {
			
			String sql="UPDATE bloodRequest SET" +" quantity order = ?, " + "status = ?, " + "WHERE id = ? ";
			PreparedStatement p;
			p=c.prepareStatement(sql);
			p.setInt(1,bloodRequest.getQuantity_order());
			p.setString(2,bloodRequest.getStatus());
			p.executeUpdate();
			p.close();
			
		}catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}

	}
	
	

	//TODO: COMPROBAR QUE SEA CORRECTO
	@Override
	public void newBloodRequest(BloodRequest bloodRequest) {
		try {
		String sql= "INSERT INTO BloodRequest (quantity_order,status,bloodBank_id,recipient_id,donation_id) VALUES(?,?,?,?,?) ";
		PreparedStatement p=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		p.setInt(1, bloodRequest.getQuantity_order());;
		p.setString(2,bloodRequest.getStatus());
		p.setInt(3,bloodRequest.getBloodBank().getId());
		p.setInt(4,bloodRequest.getRecipient().getId());
		p.setInt(5,bloodRequest.getDonation().getId());
		
		p.executeUpdate();
		p.close();
		
		}catch(SQLException e) {
			System.out.println("Database error with new BloodRequest.");
			e.printStackTrace();
		}
	}
	

	@Override
	public void newRecipient(Recipient recipient) {
		try {
		String sql = "INSERT INTO Recipient "
                + "(first_name, last_name, DOB, blood_type, country, contact_number, emergency_contact_number) "
                + "VALUES (?,?,?,?,?,?,?)";
		
		PreparedStatement p=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		p.setString(1, recipient.getFirst_name());
		p.setString(2, recipient.getLast_name());
		p.setDate(3,recipient.getDOB());
		p.setString(4, recipient.getBlood_type());
		p.setString(5,recipient.getCountry());
		p.setString(6, recipient.getContact_number());
		p.setString(7, recipient.getEmergency_contact_number());
		p.executeUpdate();
		p.close();
		}catch(SQLException e) {
			System.out.println("Database error with new Recipient.");
			e.printStackTrace();
		}
		
		
    }

	@Override
	public void newHospital(Hospital hospital) {
		try {
			String sql = "INSERT INTO Hospitals "
                    + "(name, city, address, person_responsible, contact_number) "
                    + "VALUES (?,?,?,?,?)";
			
			PreparedStatement p=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			p.setString(1, hospital.getName());
			p.setString(2,hospital.getCity());
			p.setString(3, hospital.getAddress());
			p.setString(4,hospital.getPerson_responsible());
			p.setString(5,hospital.getContact_number());
			p.executeUpdate();
			p.close();
			}catch(SQLException e) {
				System.out.println("Database error with new Hospital.");
				e.printStackTrace();
		}

	}

	//TODO: COMPROBAR QUE SEA CORRECTO
	
	@Override
	public void linkRecipientToBloodRequest(int recipientId, int bloodRequestId) {
		try {
			String sql="INSERT INTO recipientsBloodRequest(recipientId, bloodRequestId) VALUES (?,?)";
			PreparedStatement p=c.prepareStatement(sql);
			p.setInt(1, recipientId);
			p.setInt(2, bloodRequestId);
			p.executeUpdate();
			p.close();
		}catch (SQLException e) {
			System.out.println("Database error.");
			e.printStackTrace();
		}

	}

	@Override
	public BloodRequest getBloodRequestById(int id) {
	    try {
	        String sql = "SELECT * FROM BloodRequest WHERE ID = ?";
	        PreparedStatement p = c.prepareStatement(sql);
	        p.setInt(1, id);
	        ResultSet rs = p.executeQuery();

	        BloodRequest bloodRequest = null;
	        if (rs.next()) {
	            int quantityOrder = rs.getInt("quantity_order");
	            String status = rs.getString("status");

	            bloodRequest = new BloodRequest(id, quantityOrder, status);
	        }
	        rs.close();
	        p.close();
	        return bloodRequest;
	    } catch (SQLException e) {
	        System.out.println("Error retrieving BloodRequest with ID " + id);
	        e.printStackTrace();
	    }
	    return null;
	}

	@Override
	public List<BloodRequest> getAllBloodRequests() {
	    List<BloodRequest> list = new ArrayList<>();
	    try {
	        String sql = "SELECT * FROM BloodRequest";
	        PreparedStatement p = c.prepareStatement(sql);
	        ResultSet rs = p.executeQuery();

	        while (rs.next()) {
	            BloodRequest bloodRequest = new BloodRequest(
	                rs.getInt("ID"),
	                rs.getInt("quantity_order"),
	                rs.getString("status")
	            );
	            list.add(bloodRequest);
	        }
	        rs.close();
	        p.close();
	    } catch (SQLException e) {
	        System.out.println("Database error in getAllBloodRequests.");
	        e.printStackTrace();
	    }
	    return list;
	}

	@Override
	public Hospital getHospitalById(int id) {
	    try {
	        String sql = "SELECT * FROM Hospitals WHERE ID = ?";
	        PreparedStatement p = c.prepareStatement(sql);
	        p.setInt(1, id);
	        ResultSet rs = p.executeQuery();

	        if (rs.next()) {
	            return new Hospital(
	                rs.getInt("ID"),
	                rs.getString("name"),
	                rs.getString("city"),
	                rs.getString("address"),
	                rs.getString("person_responsible"),
	                rs.getString("contact_number")
	            );
	        }
	        rs.close();
	        p.close();
	    } catch (SQLException e) {
	        System.out.println("Error retrieving Hospital with ID " + id);
	        e.printStackTrace();
	    }
	    return null;
	}
	
	@Override
	public Recipient getRecipientById(int id) {
	    try {
	        String sql = "SELECT * FROM Recipients WHERE ID = ?";
	        PreparedStatement p = c.prepareStatement(sql);
	        p.setInt(1, id);
	        ResultSet rs = p.executeQuery();

	        if (rs.next()) {
	            return new Recipient(
	                rs.getInt("ID"),
	                rs.getString("first_name"),
	                rs.getString("last_name"),
	                rs.getDate("DOB"),
	                rs.getString("blood_type"),
	                rs.getString("country"),
	                rs.getString("contact_number"),
	                rs.getString("emergency_contact_number")
	            );
	        }
	        rs.close();
	        p.close();
	    } catch (SQLException e) {
	        System.out.println("Error retrieving Recipient with ID " + id);
	        e.printStackTrace();
	    }
	    return null;
	}

	
	@Override
	public void deleteRecipient(int id) {
		
		try {
		String sql= "DELETE FROM Recipient WHERE ID = ?";
		PreparedStatement p=c.prepareStatement(sql);
		p.setInt(1, id);
		p.executeUpdate();
		p.close();
		}catch (SQLException e) {
            System.out.println("Database error in deleteRecipient.");
            e.printStackTrace();
        }

	}

}
