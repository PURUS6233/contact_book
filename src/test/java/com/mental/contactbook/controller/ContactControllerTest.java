package com.mental.contactbook.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.mental.contactbook.entity.Contact;
import com.mental.contactbook.service.ContactService;

@Transactional
public class ContactControllerTest extends AbstractControllerTest{

	@Mock
	private ContactService contactServiceMock;

	@InjectMocks
	private ContactController contactController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(contactController).build();
	}

	private final Collection<Contact> contacts = Arrays.asList(getEntityStubData());

	private Contact getEntityStubData() {
		return new Contact(1, "Alexander");
	}

	@Test
	public void test_getContacts() throws Exception {

		when(contactServiceMock.getContacts()).thenReturn(contacts);

		String uri = "/hello/contacts";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		verify(contactServiceMock, times(1)).getContacts();

		assertEquals("failure - expected HTTP status 200", 200, status);
		assertTrue("failure - expected HTTP responce body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void test_getAllContactsExceptRegex() throws Exception {

		String regex = "^$";
		
		when(contactServiceMock.getAllContactsExceptRegex(regex)).thenReturn(contacts);

		String uri = "/hello/contacts?nameFilter=" + regex;

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		verify(contactServiceMock, times(1)).getAllContactsExceptRegex(regex);

		assertEquals("failure - expected HTTP status 200", 200, status);
		assertTrue("failure - expected HTTP responce body to have a value",
				content.trim().length() > 0);
	}

	@Test
	public void test_getContact() throws Exception {

		Long id = new Long(1);
		
		when(contactServiceMock.getContact(id)).thenReturn(getEntityStubData());

		String uri = "/hello/contacts/{id}";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri, id).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		verify(contactServiceMock, times(1)).getContact(id);

		assertEquals("failure - expected HTTP status 200", 200, status);
		assertTrue("failure - expected HTTP responce body to have a value",
				content.trim().length() > 0);
	}
	
	@Test
	public void test_getContactNotFound() throws Exception {

		Long id = Long.MAX_VALUE;
		
		when(contactServiceMock.getContact(id)).thenReturn(null);

		String uri = "/hello/contacts/{id}";

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.get(uri, id).accept(
						MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();

		assertTrue("failure - expected HTTP responce body to return empty response",
				content.trim().length() == 0);
	}

}
