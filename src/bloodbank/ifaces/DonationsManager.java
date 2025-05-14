package bloodbank.ifaces;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import bloodbank.db.pojos.*;



public interface DonationsManager {
	
	public void newDonation(Donation Donation);
	public void newDonor(Donor donor);
	public void linkDonorToDonation(int donorId, int donationId);
	public List<Donor> searchDonor(String firstName,String lastName, Date DOB);
	public List<Donation> searchDonation(String status);
	public void updateDonation(Donation donation);
	

	
	
}

