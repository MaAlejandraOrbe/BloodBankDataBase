package bloodank.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bloodbank.ifaces.*;

//POJO (plain old java object)
//POJOs must use object wrappers (Integer, Float, etc.) instead of primitive types (int, floar, etc.)
//POJOs need to import the class serializable (means it can be saved to a file/database...)

public class BloodBank implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//POJOs used to contain attributes
	private Integer id;
	private String address;
	private String city;
	private Integer contact_number;
	private BloodBankManager bbManager;
	private List<Donation> donations;
	
	//POJOs must have their lists initialized in the constructor
	//POJOs need an empty constructor (without parameters), can have others but this one is mandatory
	public BloodBank() {
		super();
		this.donations = new ArrayList<Donation>();
	}
	
	
	//Not needed for POJOs but useful to me
	public BloodBank(Integer id, String address, String city, Integer contact_number) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.contact_number = contact_number;
		this.donations = new ArrayList<Donation>();
	}

	public BloodBank(String address, String city, Integer contact_number) {
		super();
		this.address = address;
		this.city = city;
		this.contact_number = contact_number;
		this.donations = new ArrayList<Donation>();
	}


	//POJOs need getters and setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getContact_number() {
		return contact_number;
	}
	public void setContact_number(Integer contact_number) {
		this.contact_number = contact_number;
	}
	public BloodBankManager getBbManager() {
		return bbManager;
	}
	public void setBbManager(BloodBankManager bbManager) {
		this.bbManager = bbManager;
	}
	
	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	//These 2 methods are not needed by the POJO
	public void addDonation(Donation donation) {
		if (!donations.contains(donation)) {
			donations.add(donation);
		}
		
	}
	
	public void removeDonation(Donation donation) {
		if (donations.contains(donation)) {
			donations.remove(donation);
		}
	}

	//POJOs need an equals() method which use just the primary key
		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			BloodBank other = (BloodBank) obj;
			return id == other.id;
		}
	
	
	//could have other methods such as to string
	@Override
	public String toString() {
		return "BloodBank [id=" + id + ", contact_number=" + contact_number + "]";
	}
	
	

}
