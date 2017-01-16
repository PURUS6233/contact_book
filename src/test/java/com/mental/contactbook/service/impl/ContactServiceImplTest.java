package com.mental.contactbook.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mental.contactbook.dao.ContactDao;
import com.mental.contactbook.dao.DaoException;
import com.mental.contactbook.entity.Contact;
import com.mental.contactbook.service.ContactService;

public class ContactServiceImplTest {

	@Test
	public void test_type() throws Exception {
		assertNotNull(ContactServiceImpl.class);
	}

	private static final Contact contact = new Contact(1, "Test");

	private static final List<Contact> contacts = Arrays.asList(contact);
	
	@Mock
	private ContactDao contactDaoMock;
	
	@InjectMocks
	ContactService service = new ContactServiceImpl();
    
	@Before
	public void setUp() throws DaoException {
		MockitoAnnotations.initMocks(this);
		when(contactDaoMock.getContacts()).thenReturn(contacts);
	}
	
    @Test
    public void test_getContact() throws Exception {
        assertEquals(contact.toString(), service.getContact(1L).toString());
        verify(contactDaoMock).getContacts();
    }
    
    @Test
    public void test_getContacts() throws Exception {
        assertEquals(contacts.toString(), service.getContacts().toString());
        verify(contactDaoMock).getContacts();
    }
    
    @Test
    public void test_getAllContactsExceptRegex() throws Exception {
        assertEquals(contacts.toString(), service.getAllContactsExceptRegex("all").toString());
        verify(contactDaoMock).getContacts();
    }

}
