package com.mental.contactbook.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mental.contactbook.entity.Contact;
import com.mental.contactbook.service.ContactService;
import com.mental.contactbook.service.ServiceException;

@RestController
@RequestMapping("/hello/contacts")
public class ContactController {
	
	private ContactService contactService;
	private ContactResourceAssembler contactResourceAssembler;

	private static final Logger log = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	public ContactController(ContactService contactService,
			ContactResourceAssembler contactResourceAssembler) {
		this.contactService = contactService;
		this.contactResourceAssembler = contactResourceAssembler;
	}
	
	public ContactService getContactService() {
		return contactService;
	}
	
	public ContactResourceAssembler getContactResourceAssembler() {
		return contactResourceAssembler;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Collection<Contact> getAllContacts() throws ServiceException {
		log.info("Request for fetching all Contacts.");
		return getContactService().getContacts();
	}

	@RequestMapping(value = "", params = { "nameFilter" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Collection<Contact> getAllContactsExceptRegex(
			@RequestParam(value = "nameFilter") String nameFilter)
			throws ServiceException {
		log.info("Request for fetching contacts that not match regex: " + nameFilter + "!");
		return getContactService().getAllContactsExceptRegex(nameFilter);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Resource<Contact> getContact(@PathVariable("id") Long id)
			throws ServiceException {
		log.info("Request for fetching Contact with id: " + id + "!");
		return getContactResourceAssembler().toResource(
				getContactService().getContact(id));
	}

}
