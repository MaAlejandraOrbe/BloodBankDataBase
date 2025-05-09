package bloodbank.ifaces;

import java.util.List;

import bloodbank.db.pojos.*;

public interface BloodBankManager {
	
	
		public int insertBloodBank(BloodBank bloodbank);
		public List<BloodBank> searchBloodBank(String address, String city, Integer contact_number);
		public BloodBank getBloodBank(int id);		
		public void changeBloodBank(BloodBank bloodbank); 
		public void addDonation(int BloodBankId, int DonationId);
			
		}

