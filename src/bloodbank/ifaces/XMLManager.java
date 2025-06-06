package bloodbank.ifaces;

import java.io.File;

import bloodbank.db.pojos.Donation;

public interface XMLManager {

	public void donation2Xml(Donation o);
	public Donation xml2Donation(File xml);
	
}
