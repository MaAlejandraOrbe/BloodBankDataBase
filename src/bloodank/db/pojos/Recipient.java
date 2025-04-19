package bloodank.db.pojos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Recipient implements Serializable{
	
	//TODO: CONFIRMAR QUE HOSPITAL ID SEA ASI
	
	private static final long serialVersionUID =1L;
	private Integer id;
	private String first_nanme;
	private String last_name;
	private LocalDate DOB;
	private String blood_type;
	private String country;
	private Integer contact_number;
	private Integer emergeny_contact_number;
	private Hospital hospital;
	
	public Recipient() {
		super();
		
	}

	//TODO: CONFIRMAR QUE LO DE HSOPTIALS ES ASI O NO SE PONE EN CONSTRUCTOR
	public Recipient(Integer id, String first_nanme, String last_name, LocalDate dOB, String blood_type, String country,
			Integer contact_number, Integer emergeny_contact_number, Hospital hospital) {
		super();
		this.id = id;
		this.first_nanme = first_nanme;
		this.last_name = last_name;
		this.DOB = dOB;
		this.blood_type = blood_type;
		this.country = country;
		this.contact_number = contact_number;
		this.emergeny_contact_number = emergeny_contact_number;
		this.hospital = hospital;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_nanme() {
		return first_nanme;
	}

	public void setFirst_nanme(String first_nanme) {
		this.first_nanme = first_nanme;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
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

	public Integer getContact_number() {
		return contact_number;
	}

	public void setContact_number(Integer contact_number) {
		this.contact_number = contact_number;
	}

	public Integer getEmergeny_contact_number() {
		return emergeny_contact_number;
	}

	public void setEmergeny_contact_number(Integer emergeny_contact_number) {
		this.emergeny_contact_number = emergeny_contact_number;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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
		return "Recipient [id=" + id + ", first_nanme=" + first_nanme + ", last_name=" + last_name + ", DOB=" + DOB
				+ ", blood_type=" + blood_type + ", country=" + country + ", contact_number=" + contact_number
				+ ", emergeny_contact_number=" + emergeny_contact_number + ", hospital=" + hospital + "]";
	}
	
	
	
	

}
