package com.mental.contactbook.dao;

import java.util.Collection;
import com.mental.contactbook.entity.Contact;

public interface ContactDao {
	
	/**
	 * Get all contacts
	 * @return
	 */
	Collection<Contact> getContacts();
}