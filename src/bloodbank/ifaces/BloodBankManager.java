package bloodbank.ifaces;

import java.util.List;

import bloodbank.db.pojos.*;

public interface BloodBankManager {
	
	
		public void newBloodBank(BloodBank bloodbank);
		public void deleteBloodBank(int id);
		public List<BloodBank> searchBloodBank(String name, String city);
		//public BloodBank getBloodBankById(int id);
			
		}

