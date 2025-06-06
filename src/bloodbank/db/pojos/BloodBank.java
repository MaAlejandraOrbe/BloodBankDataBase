package bloodbank.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import bloodbank.ifaces.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "name", "city", "contact_numer", "person_responsible", "capacity_stock","bbManager", "donations", "bloodbankWorker"} )
public class BloodBank implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String address;
	@XmlElement
	private String city;
	@XmlElement
	private String contact_number;
	@XmlElement
	private String person_responsible;
	@XmlTransient
	private Integer capacity_stock;
	@XmlElement
	private BloodBankManager bbManager;
	@XmlTransient
	private List<Donation> donations;
	@XmlElement
	private BloodBankWorker bloodbankWorker;

	public BloodBank() {
		super();
		this.donations = new ArrayList<Donation>();
	}
	
	

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
	

	public BloodBank(String name, String address, String city, String contact_number,String person_responsible,Integer capacity_stock,BloodBankWorker bloodbankWorker) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.contact_number = contact_number;
		this.person_responsible = person_responsible;
		this.capacity_stock = capacity_stock;
		this.donations = new ArrayList<Donation>();
		this.bloodbankWorker=bloodbankWorker;
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

		
		
		

}
