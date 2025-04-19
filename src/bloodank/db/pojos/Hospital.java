package bloodank.db.pojos;
import java.io.Serializable;
import java.util.Objects;

public class Hospital implements Serializable{
	
	private static final long serialVersionUID =1L;
	private Integer id;
	private String name;
	private String city;
	private String address;
	private String person_responsible;
	private Integer contact_number;
	
	//TODO: connection of recipient with hospitals
	
	public Hospital() {
		super();
	}

	public Hospital(Integer id, String name, String city, String address, String person_responsible,
			Integer contact_number) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.person_responsible = person_responsible;
		this.contact_number = contact_number;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerson_responsible() {
		return person_responsible;
	}

	public void setPerson_responsible(String person_responsible) {
		this.person_responsible = person_responsible;
	}

	public Integer getContact_number() {
		return contact_number;
	}

	public void setContact_number(Integer contact_number) {
		this.contact_number = contact_number;
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
		Hospital other = (Hospital) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Hospital [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address
				+ ", person_responsible=" + person_responsible + ", contact_number=" + contact_number + "]";
	}
	
	
	

}
