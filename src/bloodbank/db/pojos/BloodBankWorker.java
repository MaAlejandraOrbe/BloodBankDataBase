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
@XmlRootElement(name = "BloodBankWorker")
@XmlType(propOrder = { "phone", "email"})


public class BloodBankWorker implements Serializable{

		
		private static final long serialVersionUID= -123242454242523L;
		
		@XmlTransient
		private Integer id;
		@XmlAttribute
		private String name;
		@XmlElement
		private Integer phone;
		@XmlElement
		private String email;
		
		
		public BloodBankWorker() {
			super();
		}


		public BloodBankWorker(String name, Integer phone, String email) {
			super();
			this.name = name;
			this.phone = phone;
			this.email = email;
		}


		public BloodBankWorker(Integer id, String name, Integer phone, String email) {
			super();
			this.id = id;
			this.name = name;
			this.phone = phone;
			this.email = email;
		}


		@Override
		public String toString() {
			return "BloodBankWorker [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
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
			BloodBankWorker other = (BloodBankWorker) obj;
			return Objects.equals(id, other.id);
		}
		
		
		
		


}
