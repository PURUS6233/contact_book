package com.mental.contactbook.dao.impl;

import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mental.contactbook.dao.ContactDao;
import com.mental.contactbook.dao.DaoException;
import com.mental.contactbook.entity.Contact;

@Repository
@PropertySource(value = "classpath:sql.properties")
public class ContactDaoImpl extends AbstractDao implements ContactDao {

	private LoadingCache<String, Collection<Contact>> contactDaoCache;

	ContactDaoImpl() {
		initCaches();
	}

	private static final String cacheKey = "contacts";

	public void initCaches() {
		log.debug("initing ResponseCache");
		initResponseCache();
	}

	private void initResponseCache() {
		log.debug("loading contacts from DB and save it to cache");
		contactDaoCache = CacheBuilder.newBuilder()
				.refreshAfterWrite(1, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Collection<Contact>>() {
					public Collection<Contact> load(String id) throws Exception {
						return getContactsFromDB();
					}
				});
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	private @Value("${app.getContacts}") String getContacts;

	public Collection<Contact> getContactsFromDB() throws DaoException {
		log.trace("Start to fetch contacts from DB.");
		try {
			log.trace("Contacts successfully loaded from DB.");
			return getJdbcTemplate().query(getContacts,
					new BeanPropertyRowMapper<Contact>(Contact.class));
		} catch (DataAccessException e) {
			log.error("Problem occured while loading Contacts! ", e);
			throw new DaoException("Problem occured while loading Contacts! ",
					e);
		}
	}

	public LoadingCache<String, Collection<Contact>> getContactsFromCache() {
		return contactDaoCache;
	}

	public Collection<Contact> getContacts() {
		log.info("Start to fetch contacts from Cache.");
		try {
			log.trace("Cache loaded.");
			return getContactsFromCache().get(cacheKey);
		} catch (ExecutionException e) {
			log.error("Problem occured while loading Cache! ", e);
			throw new DaoException("Problem occured while get cash form "
					+ ContactDaoImpl.class.getName(), e);
		}
	}
}
