package com.akshara.resteasy.addressbook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.akshara.resteasy.addressbook.model.ContactDetails;
import com.akshara.resteasy.addressbook.model.ContactTag;
import com.akshara.resteasy.addressbook.model.GenericResponse;

@Path("/addressbook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressBook implements AddressBookIntf {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("addressBookUnit");
	EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
	@GET
	@Path("/getContacts")
	public List<ContactDetails> getContacts() {
		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		List<ContactDetails> Contactlist = manager.createNamedQuery("ContactDetails.findAll", ContactDetails.class)
				.getResultList();
		//manager.close();
		return Contactlist;
	}

	@GET
	@Path("/getTags")
	public List<ContactTag> getTags() {
		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		List<ContactTag> tagList = manager.createNamedQuery("ContactTag.findAll", ContactTag.class).getResultList();
		//manager.close();
		return tagList;
	}

	@GET
	@Path("/getContact/{contactId}")
	public ContactDetails getContact(@PathParam("contactId") String contactId) {

		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		Query query = manager.createNamedQuery("ContactDetails.findByContactId", ContactDetails.class);
		query.setParameter("id", Long.parseLong(contactId));
		ContactDetails contactDetails = (ContactDetails) query.getSingleResult();
		//contactDetails.getContactTag();
		// contactDetails.setContactTag(contactTag);
		//manager.close();
		return contactDetails;
		
	}

	@GET
	@Path("/getTag/{tagId}")
	public ContactTag getTag(@PathParam("tagId") String tagId) {

		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		Query tagQuery = manager.createNamedQuery("ContactTag.findById", ContactTag.class);
		tagQuery.setParameter("id", Long.parseLong(tagId));
		ContactTag contactTag = (ContactTag) tagQuery.getSingleResult();
		//manager.close();
		return contactTag;
		
	}

	@POST
	@Path("/addContact")
	public Response addContact( ContactDetails contactDetails) {
		GenericResponse response = new GenericResponse();
		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();
			// Save the contactDetails object
			ContactTag contactTag = contactDetails.getContactTag();
			manager.persist(contactTag);
			contactDetails.setContactTag(contactTag);
			manager.persist(contactDetails);
			System.out.println("contactDetails:" + contactDetails);
			System.out.println("contactTag:" + contactTag);
			response.setStatus(true);
			response.setMessage("ContactDetails created successfully");
			// Commit the transaction
			transaction.commit();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
			response.setMessage("ContactDetails not created");
			response.setErrorCode("EC-01");
		} finally {
			// Close the EntityManager
			//manager.close();

		}
		return Response.ok(response).build();
	}

	@PUT
	@Path("/updateContact")
	public Response updateContact(ContactDetails contactDetails) {
		GenericResponse response = new GenericResponse();
		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();
			ContactTag contactTag = getTag(new Long(contactDetails.getId()).toString());
			ContactTag currentContactTag = contactDetails.getContactTag();
			contactTag.setTag(currentContactTag.getTag());
			contactDetails.setContactTag(contactTag);
			manager.merge(contactDetails);
			response.setStatus(true);
			response.setMessage("ContactDetails updated successfully");
			transaction.commit();
			return Response.ok(response).build();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
			response.setMessage("ContactDetails not updated successfully ");
			response.setErrorCode("EC-01");
		} finally {
			// Close the EntityManager
			//manager.close();
		}
		return Response.ok(response).build();
	}

	@DELETE
	@Path("/deleteContact/{contactId}")
	public Response deleteContact(@PathParam("contactId") String contactId) {

		GenericResponse response = new GenericResponse();
		//EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;
		try {
			// Get a transaction
			transaction = manager.getTransaction();
			// Begin the transaction
			transaction.begin();

			// Get the ContactDetails object
			ContactDetails contactDetails = manager.find(ContactDetails.class, new Long(contactId));

			// Delete the ContactDetails
			manager.remove(contactDetails);
			response.setStatus(true);
			response.setMessage("ContactDetails deleted successfully");
			// Commit the transaction
			transaction.commit();
			return Response.ok(response).build();
		} catch (Exception ex) {
			// If there are any exceptions, roll back the changes
			if (transaction != null) {
				transaction.rollback();
			}
			// Print the Exception
			ex.printStackTrace();
			response.setMessage("ContactDetails not deleted successfully ");
			response.setErrorCode("EC-01");
		} finally {
			// Close the EntityManager
			//manager.close();
		}

		return Response.status(422).entity(response).build();

	}
	
}
