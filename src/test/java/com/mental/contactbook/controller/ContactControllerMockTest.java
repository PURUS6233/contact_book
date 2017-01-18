package com.mental.contactbook.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.context.junit4.SpringRunner;

import com.mental.contactbook.entity.Contact;
import com.mental.contactbook.service.ContactService;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerMockTest{

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ContactService contactServiceMock;

	@MockBean
	private ContactResourceAssembler contactResourceAssemblerMock;

	private final Collection<Contact> contacts = Arrays.asList(contact);

	private static final Contact contact = new Contact(1, "Test");

	@Test
	public void test_getContacts() throws Exception {

		String uri = "/hello/contacts";

		given(this.contactServiceMock.getContacts()).willReturn(contacts);

		MvcResult result = this.mvc.perform(
				get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

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
		String uri = "/hello/contacts?nameFilter=" + regex;

		given(this.contactServiceMock.getAllContactsExceptRegex(regex))
				.willReturn(contacts);

		MvcResult result = this.mvc.perform(
				get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

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
		String uri = "/hello/contacts/{id}";

		given(this.contactServiceMock.getContact(id)).willReturn(contact);

		MvcResult result = this.mvc.perform(
				get(uri, id).accept(MediaType.APPLICATION_JSON)).andReturn();

		int status = result.getResponse().getStatus();

		verify(contactServiceMock, times(1)).getContact(id);
		verify(contactResourceAssemblerMock, times(1)).toResource(contact);

		assertEquals("failure - expected HTTP status 200", 200, status);
	}

	@Test
	public void test_getContactNotFound() throws Exception {

		Long id = Long.MAX_VALUE;

		given(this.contactServiceMock.getContact(id)).willReturn(null);

		String uri = "/hello/contacts/{id}";

		MvcResult result = this.mvc.perform(
				get(uri, id).accept(MediaType.APPLICATION_JSON)).andReturn();

		String content = result.getResponse().getContentAsString();

		assertTrue(
				"failure - expected HTTP responce body to return empty response",
				content.trim().length() == 0);
	}
}
