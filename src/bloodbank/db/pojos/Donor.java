package bloodbank.db.pojos;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodBankXMLutilis.SQLDateAdapter;

import java.util.List;
import java.util.ArrayList;

public class Donor implements Serializable {
	
	//aqui lo d edonations, creo que debe ser asi. 
	//TODO Confirmar que está bien lo de donations aquí.
	
	private static final long serialVersionUID =1L;
	private Integer id;
	private String first_name;
	private String last_name;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date DOB;
	private String blood_type;
	private String country;
	private Boolean eligible_donate;
	private String contact_number;
	private String emergency_contact_number;
	private List<Donation> donations;
	
	
	public Donor() {
		super();
		this.donations=new ArrayList<Donation>();
	}
	
	
	public Donor(Integer id, String first_name, String last_name, Date dOB, String blood_type, String country,
			Boolean eligible_donate, String  contact_number, String  emergency_contact_number) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		DOB = dOB;
		this.blood_type = blood_type;
		this.country = country;
		this.eligible_donate = eligible_donate;
		this.contact_number = contact_number;
		this.emergency_contact_number = emergency_contact_number;
		this.donations=new ArrayList<Donation>();
	}

	public Donor( String first_name, String last_name, Date dOB, String blood_type, String country,
			Boolean eligible_donate, String  contact_number, String  emergency_contact_number) {
		super();
		
		this.first_name = first_name;
		this.last_name = last_name;
		DOB = dOB;
		this.blood_type = blood_type;
		this.country = country;
		this.eligible_donate = eligible_donate;
		this.contact_number = contact_number;
		this.emergency_contact_number = emergency_contact_number;
		this.donations=new ArrayList<Donation>();
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getFirst_name() {
		return first_name;
	}


	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}


	public Date getDOB() {
		return DOB;
	}


	public void setDOB(Date dOB) {
		DOB = dOB;
	}


	public String getBlood_type() {
		return blood_type;
	}


	public void setBlood_type(String blood_type) {
		this.blood_type = blood_type;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Boolean getEligible_donate() {
		return eligible_donate;
	}


	public void setEligible_donate(Boolean eligible_donate) {
		this.eligible_donate = eligible_donate;
	}


	

	public String getContact_number() {
		return contact_number;
	}


	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}


	public String getEmergency_contact_number() {
		return emergency_contact_number;
	}


	public void setEmergency_contact_number(String emergency_contact_number) {
		this.emergency_contact_number = emergency_contact_number;
	}


	public List<Donation> getDonations() {
		return donations;
	}


	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		return  Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		return Objects.equals(id,other.id);
	}


	@Override
	public String toString() {
		return "Donor [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", DOB=" + DOB
				+ ", blood_type=" + blood_type + ", country=" + country + ", eligible_donate=" + eligible_donate
				+ ", contact_number=" + contact_number + ", emergency_contact_number=" + emergency_contact_number
				+ ", donations=" + donations + "]";
	}
	
	


}