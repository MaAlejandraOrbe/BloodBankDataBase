package bloodBankXMLutilis;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import bloodbank.db.pojos.Donation;
import bloodbank.ifaces.XMLManager;

public class XMLManagerImpl implements XMLManager {

	@Override
	public void donation2Xml(Donation o) {
	    try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(Donation.class);
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        File file = new File("./xmls/Donation.xml");
	        marshaller.marshal(o, file);
	        System.out.println("Donation object saved to XML successfully.");
	    } catch (Exception e) {
	        System.out.println("Error saving Donation to XML.");
	        e.printStackTrace();
	    }
	}

	@Override
	public Donation xml2Donation(File xml) {
	    try {
	        JAXBContext jaxbContext = JAXBContext.newInstance(Donation.class);
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        Donation donation = (Donation) unmarshaller.unmarshal(xml);
	        return donation;
	    } catch (Exception e) {
	        System.out.println("Error reading Donation from XML.");
	        e.printStackTrace();
	        return null;
	    }
	}

}
