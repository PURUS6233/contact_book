package com.mental.contactbook.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mental.contactbook.dao.ContactDao;
import com.mental.contactbook.dao.DaoException;
import com.mental.contactbook.entity.Contact;
import com.mental.contactbook.service.ContactService;
import com.mental.contactbook.service.ServiceException;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;

	private Collection<Contact> contacts;

	private static final Logger log = LoggerFactory
			.getLogger(ContactServiceImpl.class);

	public ContactDao getContactDao() {
		return contactDao;
	}

	@Cacheable(value = "contact", key = "#id")
	public Contact getContact(Long id) throws ServiceException {
		log.info("Start to fetch contact with id: " + id + ".");
		Contact contact = null;
		try {
			contacts = getContactDao().getContacts();
		} catch (DaoException e) {
			log.trace("Error occured while loading contacts from DB.");
			throw new ServiceException();
		}
		for (Contact entity : contacts) {
			if (entity.getId() == id) {
				contact = entity;
				log.trace("Contact with id: " + id + ", found in collection.");
			}
		}
		if (contact == null) {
			log.trace("There is no contact with id: " + id
					+ ". Please try to find it later!");
			throw new ServiceException("There is no contact with id: " + id
					+ ". Please try to find it later!");
		}
		log.info("Contact found and retrieved!");
		return contact;
	}

	public Collection<Contact> getContacts() throws ServiceException {
		log.info("Start to fetch contacts from DB.");
		try {
			contacts = getContactDao().getContacts();
		} catch (DaoException e) {
			log.trace("Error occured while loading contacts from DB.");
			throw new ServiceException();
		}
		if (contacts.isEmpty()) {
			log.trace("Error occured while loading contacts");
			throw new ServiceException("Error occured while loading contacts");
		}
		log.info("Contacts retrieved!");
		return contacts;
	}

	@Cacheable(value = "contacts", key = "#regex")
	public Collection<Contact> getAllContactsExceptRegex(String regex) throws ServiceException{

		log.info("Start to fetch contacts from DB.");
		Collection<Contact> result = new ArrayList<Contact>();

		try {
			contacts = getContactDao().getContacts();
		} catch (DaoException e) {
			log.trace("Error occured while loading contacts from DB.");
			throw new ServiceException();
		}
		log.trace("Start loading collection with regex.");
		for (Contact entity : contacts) {
			if (!entity.getName().matches(regex)) {
				result.add(entity);
			}
		}
		if (result.isEmpty()) {
			log.trace("There is no matches by regex: " + regex
					+ " in contacts collection");
			throw new ServiceException("There is no matches by regex: " + regex
					+ " in contacts collection");
		}
		log.info("Contacts for regex retrieved!");
		return result;
	}
}
