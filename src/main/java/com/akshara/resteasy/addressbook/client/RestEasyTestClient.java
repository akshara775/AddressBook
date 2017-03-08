package com.akshara.resteasy.addressbook.client;



import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.akshara.resteasy.addressbook.model.ContactDetails;
import com.akshara.resteasy.addressbook.model.GenericResponse;


public class RestEasyTestClient {

	public static void main(String[] args) {

		ResteasyClient client = new ResteasyClientBuilder().build();
		
		//GET example
		ResteasyWebTarget getAll = client.target("http://localhost:8080/AddressBook/addressbook/getContacts");
		Response getAllResponse = getAll.request().get();
		String value = getAllResponse.readEntity(String.class);
		//ContactDetails contactDetails[] = (ContactDetails[])value;
		//for(ContactDetails contactDetail:contactDetails)
        System.out.println(value);
		getAllResponse.close();
		
		//GET example
		ResteasyWebTarget get = client.target("http://localhost:8080/AddressBook/addressbook/getContact/100");
		Response getResponse = get.request().get();
		ContactDetails contactDetailsOne = getResponse.readEntity(ContactDetails.class);
        System.out.println(contactDetailsOne);
        getResponse.close();  
        
        //POST example
		ResteasyWebTarget add = client.target("http://localhost:8080/AddressBook/addressbook/addContact");
		ContactDetails contactDetailIns = new ContactDetails();
		contactDetailIns.setId(50);contactDetailIns.setFirstName("Rick");contactDetailIns.setLastName("Martin");
		Response addResponse = add.request().post(Entity.entity(contactDetailIns, MediaType.APPLICATION_JSON));
		System.out.println(addResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+addResponse.getStatus());
		addResponse.close();
		
		addResponse = add.request().post(Entity.entity(contactDetailIns, MediaType.APPLICATION_JSON));
		System.out.println(addResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+addResponse.getStatus());
		addResponse.close();
		
		//DELETE example
		ResteasyWebTarget delete = client.target("http://localhost:8080/AddressBook/addressbook/100");
		Response deleteResponse = delete.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+deleteResponse.getStatus());
		deleteResponse.close();
		
		deleteResponse = delete.request().delete();
		System.out.println(deleteResponse.readEntity(GenericResponse.class));
		System.out.println("HTTP Response Code:"+deleteResponse.getStatus());
		deleteResponse.close();
	}

}
