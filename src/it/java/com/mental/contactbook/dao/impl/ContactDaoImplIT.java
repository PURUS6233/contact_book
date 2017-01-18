package com.mental.contactbook.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mental.contactbook.AbstractTest;

public class ContactDaoImplIT extends AbstractTest{

	@Autowired
	ContactDaoImpl assembler;
	
	@Test
	public void test_cache() throws Exception {
		assembler.initCaches();
		assertNotNull(assembler.getContacts());
	}
}
