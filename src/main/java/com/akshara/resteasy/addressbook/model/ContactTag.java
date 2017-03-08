package com.akshara.resteasy.addressbook.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Table(name = "CNT_TAG")
@Entity
//@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ContactTag implements Serializable//,FieldHandled
{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="tag_id")
	private long id;

	private int tag;
	//private FieldHandler fieldHandler;

	@OneToOne(mappedBy="contactTag",fetch = FetchType.LAZY)
	//@JsonBackReference
	private ContactDetails contactDetails;

	public ContactTag(long id, int tag) {
		super();
		this.id = id;
		this.tag = tag;
	}

	public ContactTag() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}
/*
	//@JsonProperty
	@LazyToOne(LazyToOneOption.NO_PROXY)
	public ContactDetails getContactDetail() {
		if (fieldHandler != null) {
			   return (ContactDetails) fieldHandler.readObject(this,"contactDetails", contactDetails);
		}
		return contactDetails;
	}

	//@JsonIgnore
	public void setContactDetail(ContactDetails contactDetails) {
		if (fieldHandler != null) {
			   this.contactDetails = (ContactDetails)fieldHandler.writeObject(this,"contactDetails",(Object)this.contactDetails,contactDetails);
			   return;
			  }
		this.contactDetails = contactDetails;
	}
*/
	
	@Override
	public String toString() {
		return "ContactTag [id=" + id + ", tag=" + tag + ", contactDetails=" + contactDetails + "]";
	}

	public ContactDetails getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
/*
	@Override
	public FieldHandler getFieldHandler() {
		return fieldHandler;
	}

	@Override
	public void setFieldHandler(FieldHandler fieldHandler) {
		this.fieldHandler=fieldHandler;
		
	}
*/
}
