package com.mental.contactbook.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.mental.contactbook.service.ContactService;

@Transactional
@WebAppConfiguration
public class ContactControllerIT extends AbstractControllerTest {
	
	@Autowired
	private ContactService contactService;
	
	
	@Before
	public void setUp(){
		
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void test_getContacts() throws Exception {

		String uri = "/hello/contacts";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		assertEquals("failure - expected HTTP status 200", 200, status);
		assertTrue("failure - expected HTTP responce body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void test_getAllContactsExceptRegex() throws Exception {

		String uri = "/hello/contacts?nameFilter=^$";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		assertEquals("failure - expected HTTP status 200", 200, status);
		assertTrue("failure - expected HTTP responce body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void test_getContact() throws Exception {

		String uri = "/hello/contacts/{id}";
		
		Long id = new Long(1);

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri, id).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		assertEquals("failure - expected HTTP status 200", 200, status);
		assertTrue("failure - expected HTTP responce body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void test_getContactNotFound() throws Exception {

		String uri = "/hello/contacts/{id}";
		
		Long id = Long.MAX_VALUE;

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri, id).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		int status = result.getResponse().getStatus();

		assertEquals("failure - expected HTTP status 404", 404, status);
	}
}
