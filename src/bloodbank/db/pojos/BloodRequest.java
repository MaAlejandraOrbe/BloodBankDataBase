package bloodbank.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class BloodRequest implements Serializable{
	
	//TODO: COMPROBAR LOS OBJETOS DE LAS OTRAS CLASES
	
	
	private static final long serialVersionUID=1L;
	private Integer id;
	private Integer quantity_order;
	private String status;
	private BloodBank bloodBank;
	private Recipient recipient;
	private Donation donation;
	
	public BloodRequest() {
		super();
	}
	
	//TODO: EN CONSTRUCTOR DEBEN IR ESOS OBJETOS DE OTRA CLASE?
	public BloodRequest(Integer id,Integer quantity_order,String status) {
		super();
		this.id=id;
		this.quantity_order=quantity_order;
		this.status=status;
	}

	public BloodRequest(Integer quantity_order, String status, BloodBank bloodBank, Recipient recipient,
			Donation donation) {
		super();
		this.quantity_order = quantity_order;
		this.status = status;
		this.bloodBank = bloodBank;
		this.recipient = recipient;
		
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity_order() {
		return quantity_order;
	}

	public void setQuantity_order(Integer quantity_order) {
		this.quantity_order = quantity_order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BloodBank getBloodBank() {
		return bloodBank;
	}

	public void setBloodBank(BloodBank bloodBank) {
		this.bloodBank = bloodBank;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
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
		BloodRequest other = (BloodRequest) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BloodRequest [id=" + id + ", quantity_order=" + quantity_order + ", status=" + status + ", bloodBank="
				+ bloodBank + ", recipient=" + recipient + ", donation=" + donation + "]";
	}
	
	
	
	
	
	
	

}
