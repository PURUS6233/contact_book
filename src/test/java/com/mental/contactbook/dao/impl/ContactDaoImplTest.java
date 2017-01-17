package com.mental.contactbook.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mental.contactbook.AbstractTest;

public class ContactDaoImplTest extends AbstractTest{

	@Autowired
	ContactDaoImpl assembler;
	
	@Test
	public void test_type() throws Exception {
		assertNotNull(ContactDaoImpl.class);
	}

	@Test
	public void test_instantiation() throws Exception {
		assertNotNull(assembler);
	}
	
	@Test
	public void test_cache() throws Exception {
		assembler.initCaches();
		assertNotNull(assembler.getContacts());
	}
}
