package bloodbank.ifaces;

import java.time.LocalDate;
import java.util.List;

import bloodbank.db.pojos.*;



public interface DonationsManager {

	public int insertDonor(Donor donor);
	public int insertDonation(Donation donation);
	public List<Donor> searchDonor(String name,String adress, String city, Integer contact_number, String person_responsible, Integer capacity_stock);
	public List<Donation> searchDonation(Integer quantity, String status, LocalDate donation_date, LocalDate expiration_date); //TODO hay que poner el atributo bloodbank?
	public Donor getDonor(int id);
	public Donation getDonation(int id);
	public void changeDonor(Donor donor); 
	public void changeDonation(Donation donor); 
	public void addDonor(int DonorId, int DonationId);
	
}
