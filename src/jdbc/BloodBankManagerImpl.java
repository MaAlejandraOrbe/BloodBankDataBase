package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bloodank.db.pojos.BloodBank;
import bloodbank.ifaces.BloodBankManager;

public class BloodBankManagerImpl implements BloodBankManager {

	//TODO initialize this somehow?
	Connection c;
	
	
	@Override
	public int insertBloodBank(BloodBank bloodbank) {
		try {
		// create an INSERT statement
		String sql = "INSERT INTO bloodbanks(address, city, contact number) VALUES(?,?,?) ";
		PreparedStatement p = c.prepareStatement(sql);
		p.setString(1, bloodbank.getAddress());
		p.setString(2, bloodbank.getCity());
		// execute the statement
		p.executeUpdate();
		//return the new bloodbank's ID
				String query = "SELECT last_insert_rowid() AS lastId";
				PreparedStatement p2 = c.prepareStatement(query);
				ResultSet rs = p2.executeQuery();
				Integer lastId = rs.getInt("lastId");
				return lastId;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public List<BloodBank> searchBloodBank(String address, String city, Integer contact_number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BloodBank getBloodBank(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeBloodBank(BloodBank bloodbank) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDonation(int BloodBankId, int DonationId) {
		// TODO Auto-generated method stub

	}

}
