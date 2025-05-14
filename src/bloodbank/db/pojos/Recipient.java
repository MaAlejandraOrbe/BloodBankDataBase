package bloodbank.db.pojos;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bloodBankXMLutilis.SQLDateAdapter;

public class Recipient implements Serializable{
	
	//TODO: CONFIRMAR QUE HOSPITAL ID SEA ASI
	
	private static final long serialVersionUID =1L;
	private Integer id;
	private String first_name;
	private String last_name;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date DOB;
	private String blood_type;
	private String country;
	private String  contact_number;
	private String  emergency_contact_number;

	public Recipient() {
		super();
		
	}

	//TODO: CONFIRMAR QUE LO DE HSOPTIALS ES ASI O NO SE PONE EN CONSTRUCTOR
	public Recipient(Integer id, String first_nanme, String last_name, Date DOB, String blood_type, String country,
			String contact_number, String emergency_contact_number) {

		super();
		this.id = id;
		this.first_name = first_nanme;
		this.last_name = last_name;
		this.DOB = DOB;
		this.blood_type = blood_type;
		this.country = country;
		this.contact_number = contact_number;
		this.emergency_contact_number = emergency_contact_number;

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

	public void setDOB(Date DOB) {
		this.DOB = DOB;
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
		Recipient other = (Recipient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Recipient [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", DOB=" + DOB
				+ ", blood_type=" + blood_type + ", country=" + country + ", contact_number=" + contact_number
				+ ", emergency_contact_number=" + emergency_contact_number + "]";
	}
	
	
	
	

}
