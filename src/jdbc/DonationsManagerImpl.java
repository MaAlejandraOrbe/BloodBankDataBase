package jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import bloodbank.db.pojos.Donation;
import bloodbank.db.pojos.Donor;
import bloodbank.ifaces.DonationsManager;

public class DonationsManagerImpl implements DonationsManager {
	
	private Connection c;

	public  DonationsManagerImpl(Connection c) {
		this.c = c;

	}

	@Override
	public void newDonation(Donation Donation) {
	
		
	}
	
	

	@Override
	public void newDonor(Donor donor) {
		

	}

	@Override
	public void linkDonorToDonation(int donorId, int donationId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Donor> searchDonor(String firstName, String lastName, Date DOB) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Donation> searchDonation(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDonation(Donation donation) {
		// TODO Auto-generated method stub

	}

}
