package bloodank.db.pojos;
import java.io.Serializable;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Donor implements Serializable {
	
	//aqui lo d edonations, creo que debe ser asi. 
	//TODO Confirmar que está bien lo de donations aquí.
	
	private static final long serialVersionUID =1L;
	private Integer id;
	private String name;
	private String adress;
	private String city;
	private Integer contact_number;
	private String person_responsible;
	private Integer capacity_stock;
	private List<Donation>donations;
	
	
	public Donor() {
		super();
		this.donations=new ArrayList<Donation>();
	}
	
	
	public Donor(Integer id, String name, String adress, String city, Integer contact_number, String person_responsible, Integer capacity_stock) {
		super();
		this.id=id;
		this.name=name;
		this.adress=adress;
		this.city=city;
		this.contact_number=contact_number;
		this.person_responsible=person_responsible;
		this.capacity_stock=capacity_stock;
		this.donations=new ArrayList<Donation>();
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


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
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
	
	
	public List<Donation> getDonations() {
		return donations;
	}


	public void setDonations(List<Donation> donations) {
		this.donations = donations;
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
		return "Donor [id=" + id + ", name=" + name + ", adress=" + adress + ", city=" + city + ", contact_number="
				+ contact_number + ", person_responsible=" + person_responsible + ", capacity_stock=" + capacity_stock
				+ "]";
	}
	
	
	

}
