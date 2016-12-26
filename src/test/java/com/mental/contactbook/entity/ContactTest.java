package com.mental.contactbook.entity;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContactTest{
	
	@Test
	public void test_type() throws Exception {
		assertNotNull(Contact.class);
	}
	
	@Test
	public void test_instantiation() throws Exception {
		Contact contact = new Contact();
		assertNotNull(contact);
	}

}
