package com.mental.contactbook.entity;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Contact implements Serializable {

	private static final long serialVersionUID = -3201943418570781691L;
	private long id;
	private String name;
	
	private static final Logger log = LoggerFactory.getLogger(Contact.class);

	public Contact() {
		log.info("Created Contact instance!");
	}

	public Contact(long id, String name) {
		this.id = id;
		this.name = name;
		log.info("Created Contact instance with id:" + id
				+ " and name: " + name + "!");
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String lastName) {
		this.name = lastName;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + "]";
	}
}
