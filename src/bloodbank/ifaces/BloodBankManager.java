package bloodbank.ifaces;

import java.util.List;

import bloodbank.db.pojos.*;

public interface BloodBankManager {
	
	
		public void newBloodBank(BloodBank bloodbank);
		public void linkDonation(BloodBank bloodbank, Donation donation);
		public void deleteBloodBank(int id);
		public List<BloodBank> searchBloodBank(String name, String city);
		public void linkBloodRequest(BloodBank bloodbank, BloodRequest bloodrequest);
			
		}

