package com.mental.contactbook.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;

import com.mental.contactbook.AbstractTest;
import com.mental.contactbook.entity.Contact;

public class ContactResourceAssemblerTest extends AbstractTest {

	@Autowired
	ContactResourceAssembler assembler;

	private Contact getEntityStubData() {
		return new Contact(1, "Test");
	}

	@Test
	public void test_type() throws Exception {
		assertNotNull(ContactResourceAssembler.class);
	}

	@Test
	public void test_instantiation() throws Exception {
		assertNotNull(assembler);
	}

	@Test
	public void test_toResource() {
		Resource<Contact> actual = assembler.toResource(getEntityStubData());
		String expected = "<http://localhost/hello/contacts/1>;rel=\"contact\"";
		assertEquals(expected, actual.getLink("contact").toString());
	}
}
