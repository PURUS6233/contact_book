package com.mental.contactbook.dao;

import java.util.Collection;

import com.mental.contactbook.entity.Contact;

public interface ContactDao {
	/**
	 * Get contact by contact id
	 * @param id
	 * @return
	 */
	Contact getContact(Long id);
	/**
	 * Get all contacts
	 * @return
	 */
	Collection<Contact> getContacts();
	/**
	 * Get contacts except equals to regular expression
	 * @param regex
	 * @return
	 */
	Collection<Contact> getAllExceptRegex(String regex);

}