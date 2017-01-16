package com.mental.contactbook.dao.impl;

import java.util.Collection;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.mental.contactbook.dao.ContactDao;
import com.mental.contactbook.dao.DaoException;
import com.mental.contactbook.entity.Contact;

@Repository
@PropertySource(value = "classpath:sql.properties")
public class ContactDaoImpl extends AbstractDao implements ContactDao {

	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	private @Value("${app.getContacts}") String getContacts;

	public Collection<Contact> getContacts() {
		try {
			return getJdbcTemplate().query(getContacts,
					new BeanPropertyRowMapper<Contact>(Contact.class));
		} catch (DataAccessException e) {
			throw new DaoException("Problem occured while loading Contacts! ",
					e);
		}
	}
}
