package bloodbank.db.pojos;
import java.io.Serializable;

import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder= {"name","city","address","person_responsible","contact_number"})

public class Hospital implements Serializable{
	
	private static final long serialVersionUID =1L;
	@XmlTransient
	private Integer id;
	@XmlElement
	private String name;
	@XmlElement
	private String city;
	@XmlElement
	private String address;
	@XmlElement
	private String person_responsible;
	@XmlElement
	private String contact_number;
	
	
	
	public Hospital() {
		super();
	}

	public Hospital(String name, String city, String address, String person_responsible,
			String contact_number) {
		super();
		this.name = name;
		this.city = city;
		this.address = address;
		this.person_responsible = person_responsible;
		this.contact_number = contact_number;
	}
	
	public Hospital(Integer id, String name, String city, String address, String person_responsible,
			String contact_number) {
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

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
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
