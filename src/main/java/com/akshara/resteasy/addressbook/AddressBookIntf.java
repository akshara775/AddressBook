package com.akshara.resteasy.addressbook;

import java.util.List;

import javax.ws.rs.core.Response;

import com.akshara.resteasy.addressbook.model.ContactDetails;


public interface AddressBookIntf {
	List<ContactDetails> getContacts();
	ContactDetails getContact(String contactId);
	Response addContact(ContactDetails contactDetails);
	Response updateContact(ContactDetails contactDetails);
	Response deleteContact(String contactId);
}
