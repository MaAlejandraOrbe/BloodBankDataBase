package bloodbank.db.pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Donation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer quantity;
	private String status;
	private LocalDate donation_date; //LocalDate fecha = LocalDate.of(1995, 4, 10); (ano, mes, dia)
	private LocalDate expiration_date;
	private BloodBank bloodbank;
	
	
	public Donation() {
		super();
	}
	
	public Donation(Integer id, Integer quantity, String status, LocalDate donation_date, LocalDate expiration_date) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.status = status;
		this.donation_date = donation_date;
		this.expiration_date = expiration_date;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDonation_date() {
		return donation_date;
	}
	public void setDonation_date(LocalDate donation_date) {
		this.donation_date = donation_date;
	}
	public LocalDate getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(LocalDate expiration_date) {
		this.expiration_date = expiration_date;
	}
	public BloodBank getBloodbank() {
		return bloodbank;
	}
	public void setBloodbank(BloodBank bloodbank) {
		this.bloodbank = bloodbank;
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
		return "Donation [id=" + id + ", quantity=" + quantity + ", status=" + status + ", donation_date="
				+ donation_date + ", expiration_date=" + expiration_date + ", bloodbank=" + bloodbank + "]";
	}
	
	
	
}
