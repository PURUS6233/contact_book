package com.mental.contactbook.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.mental.contactbook.entity.Contact;

@Component
public class ContactResourceAssembler implements
		ResourceAssembler<Contact, Resource<Contact>> {

	private static final Logger log = LoggerFactory.getLogger(ContactController.class);
	
	/**
	 * Converts the Contact entity into an {@link ResourceSupport}.
	 */
	public Resource<Contact> toResource(Contact entity) {
		log.info("Adding resource to contact");
		Resource<Contact> resource = new Resource<Contact>(entity);
		resource.add(linkTo(ContactController.class).slash(entity.getId())
				.withRel("contact"));
		log.info("Resource created with contact: " +  entity.toString());
		return resource;
	}
}