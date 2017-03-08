package com.akshara.resteasy.addressbook.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@NamedQueries({

		@NamedQuery(name = "ContactTag.findAll", query = "SELECT c FROM ContactTag c"),
		@NamedQuery(name = "ContactDetails.findAll", query = "SELECT c FROM ContactDetails c"),
		@NamedQuery(name = "ContactTag.findById", query = "SELECT c FROM ContactTag c WHERE c.id = :id"),
		@NamedQuery(name = "ContactDetails.findByContactId", query = "SELECT c FROM ContactDetails c WHERE c.id = :id"),
		@NamedQuery(name = "ContactDetails.deleteContact", query = "DELETE FROM ContactDetails c WHERE c.id = :id"),
		@NamedQuery(name = "ContactTag.deleteContact", query = "DELETE FROM ContactTag c WHERE c.id = :id")

})
@Table(name = "CONTACT_DTLS")
@Entity
//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ContactDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="contact_id")
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;
	private String homeAddress;
	private String workAddress;
	private String homePhone;
	private String workPhone;
	private String cellPhone;
	private String fax;
	private String[] emailIds;

	
	@OneToOne(orphanRemoval = true, 
			optional = false, cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
	//@JsonManagedReference
	private ContactTag contactTag;

	public ContactDetails() {
		super();
	}



	public ContactDetails(long id, String firstName, String middleName, String lastName, String dateOfBirth,
			String homeAddress, String workAddress, String homePhone, String workPhone, String cellPhone, String fax,
			String[] emailIds) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
		this.cellPhone = cellPhone;
		this.fax = fax;
		this.emailIds = emailIds;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String[] getEmailIds() {
		return emailIds;
	}

	public void setEmailIds(String[] emailIds) {
		this.emailIds = emailIds;
	}

	public ContactTag getContactTag() {
		return contactTag;
	}

	public void setContactTag(ContactTag contactTag) {
		this.contactTag = contactTag;
	}

	@Override
	public String toString() {
		return "ContactDetails [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", homeAddress=" + homeAddress + ", workAddress="
				+ workAddress + ", homePhone=" + homePhone + ", workPhone=" + workPhone + ", cellPhone=" + cellPhone
				+ ", fax=" + fax + ", emailIds=" + Arrays.toString(emailIds) + ", contactTag=" + contactTag + "]";
	}

}
