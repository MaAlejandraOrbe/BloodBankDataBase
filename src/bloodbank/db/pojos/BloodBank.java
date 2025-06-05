package bloodbank.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import bloodbank.ifaces.*;

//POJO (plain old java object)
//POJOs must use object wrappers (Integer, Float, etc.) instead of primitive types (int, floar, etc.)
//POJOs need to import the class serializable (means it can be saved to a file/database...)

public class BloodBank implements Serializable{
 //TODO añadir nombre
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//POJOs used to contain attributes
	private Integer id;
	private String name;
	private String address;
	private String city;
	private String contact_number;
	private String person_responsible;
	private Integer capacity_stock;
	private BloodBankManager bbManager;
	private List<Donation> donations;
	
	private BloodBankWorker bloodbankWorker;
	//POJOs must have their lists initialized in the constructor
	//POJOs need an empty constructor (without parameters), can have others but this one is mandatory
	public BloodBank() {
		super();
		this.donations = new ArrayList<Donation>();
	}
	
	
	//Not nceeded for POJOs but useful to me
	public BloodBank(Integer id,String name, String address, String city, String contact_number,String person_responsible, Integer capacity_stock) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.contact_number = contact_number;
		this.person_responsible = person_responsible;
		this.capacity_stock = capacity_stock;
		this.donations = new ArrayList<Donation>();
	}
	

	public BloodBank(String name, String address, String city, String contact_number) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.contact_number = contact_number;
		this.donations = new ArrayList<Donation>();
	}
	
	public BloodBank(String name, String address, String city, String contact_number,BloodBankWorker bloodbankWorker) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.contact_number = contact_number;
		this.donations = new ArrayList<Donation>();
		this.bloodbankWorker=bloodbankWorker;
	}
	
	


	//POJOs need getters and setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	
	public String getPerson_responsible() {
		return person_responsible;
	}
	public void setPerson_responsible(String person_responsible) {
		this.person_responsible = person_responsible;
	}
	public Integer getCapacity_stock() {
		return capacity_stock;
	}
	public void setCapacity_stock(Integer capacity_stock) {
		this.capacity_stock = capacity_stock;
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


		@Override
		public String toString() {
			return "BloodBank [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city
					+ ", contact_number=" + contact_number + ", person_responsible=" + person_responsible
					+ ", capacity_stock=" + capacity_stock + ", bbManager=" + bbManager + ", donations=" + donations
					+ "]";
		}

		//could have other methods such as to string
		
		
		

}
