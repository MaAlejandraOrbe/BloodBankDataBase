package bloodbank.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "RequestsWorker")
@XmlType(propOrder = { "phone", "email", "bloodRequests" })

public class RequestsWorker implements Serializable {
	
private static final long serialVersionUID= -1238696454242523L;
	
	@XmlTransient
	private Integer id;
	@XmlAttribute
	private String name;
	@XmlElement
	private Integer phone;
	@XmlElement
	private String email;
	@XmlElement(name = "bloodRequest")
	@XmlElementWrapper(name="bloodRequests")
	private List<BloodRequest>bloodRequests;
	
	
	public RequestsWorker() {
		
		super();
		bloodRequests=new ArrayList<BloodRequest>();
		
	}


	public RequestsWorker(String name, Integer phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		bloodRequests=new ArrayList<BloodRequest>();
		
	}


	public RequestsWorker(Integer id, String name, Integer phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		bloodRequests=new ArrayList<BloodRequest>();
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


	public Integer getPhone() {
		return phone;
	}


	public void setPhone(Integer phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<BloodRequest> getBloodRequests() {
		return bloodRequests;
	}


	public void setBloodRequests(List<BloodRequest> bloodRequests) {
		this.bloodRequests = bloodRequests;
	}


	@Override
	public String toString() {
		return "RequestsWorker [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", bloodRequests=" + bloodRequests + "]";
	}
	
	
	public void addBloodRequest(BloodRequest bloodRequest) {
		if(!bloodRequests.contains(bloodRequest)) {
			bloodRequests.add(bloodRequest);
		}
	}
	
	public void removeBloodRequest(BloodRequest bloodRequest) {
		if(!bloodRequests.contains(bloodRequest)) {
			bloodRequests.remove(bloodRequest);
		}
	}


}
