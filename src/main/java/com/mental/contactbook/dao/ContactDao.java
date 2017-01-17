package com.mental.contactbook.dao;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;
import com.mental.contactbook.entity.Contact;

public interface ContactDao {
	
	/**
	 * Load contacts to cache
	 * @return
	 */
	void initCaches();
	
	/**
	 * Get contact cache
	 * @return
	 * @throws ExecutionException 
	 */
	LoadingCache<String, Collection<Contact>> getContactsFromCache() throws DaoException;
	
	/**
	 * Get all contacts from DataBase
	 * @return
	 */
	Collection<Contact> getContactsFromDB();
	
	/**
	 * Get all contacts
	 * @return
	 */
	Collection<Contact> getContacts();
}