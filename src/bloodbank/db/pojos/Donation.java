package bloodbank.db.pojos;

import java.io.Serializable;
import java.time.Date;
import java.util.Objects;

public class Donation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String status;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date donation_date; //Date fecha = Date.of(1995, 4, 10); (ano, mes, dia)
	private Integer quantity;
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date expiration_date;
	private BloodBank bloodbank;
	private Donor donor;
	
	
	public Donation() {
		super();
	}
	
	
	public Donation(Integer id, String status, Date donation_date, Integer quantity, Date expiration_date,
			BloodBank bloodbank, Donor donor) {
		super();
		this.id = id;
		this.status = status;
		this.donation_date = donation_date;
		this.quantity = quantity;
		this.expiration_date = expiration_date;
		this.bloodbank = bloodbank;
		this.donor = donor;
	}


	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getDonation_date() {
		return donation_date;
	}


	public void setDonation_date(Date donation_date) {
		this.donation_date = donation_date;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Date getExpiration_date() {
		return expiration_date;
	}


	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}


	public BloodBank getBloodbank() {
		return bloodbank;
	}


	public void setBloodbank(BloodBank bloodbank) {
		this.bloodbank = bloodbank;
	}


	public Donor getDonor() {
		return donor;
	}


	public void setDonor(Donor donor) {
		this.donor = donor;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Donation other = (Donation) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Donation [id=" + id + ", status=" + status + ", donation_date=" + donation_date + ", quantity="
				+ quantity + ", expiration_date=" + expiration_date + ", bloodbank=" + bloodbank + ", donor=" + donor
				+ "]";
	}

	
	
	
	
}
