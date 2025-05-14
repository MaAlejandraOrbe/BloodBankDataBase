package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import bloodbank.db.pojos.BloodRequest;
import bloodbank.db.pojos.Hospital;
import bloodbank.db.pojos.Recipient;
import bloodbank.ifaces.RequestsManager;

public class RequestsManagerImpl implements RequestsManager {
	
	  private Connection c;

	    public RequestsManagerImpl(Connection c) {
	        this.c = c;
	    }

	@Override
	public void fullfillBloodRequest(BloodRequest bloodRequest) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newBloodRequest(BloodRequest bloodRequest) {
		

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
		p.setDate(3,Date.valueOf(recipient.getDOB()));
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

	@Override
	public void linkRecipientToBloodRequest(int recipientId, int bloodRequestId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteRecipient(int id) {
		// TODO Auto-generated method stub

	}

}
