package com.mental.contactbook.service;

import java.util.Collection;

import com.mental.contactbook.entity.Contact;

public interface ContactService {
	
	/**
	 * Get contact by contact id
	 * @param id
	 * @return
	 */
	Contact getContact(Long id) throws ServiceException;
	
	/**
	 * Get all contacts
	 * @return
	 */
	Collection<Contact> getContacts() throws ServiceException;	
	/**
	 * Get contacts except equals to regular expression 
	 * @param regex
	 * @return
	 */
	Collection<Contact> getAllContactsExceptRegex(String regex) throws ServiceException;

}
