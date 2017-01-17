package com.mental.contactbook.controller;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import com.mental.contactbook.entity.Contact;

@Component
public class ContactResourceAssembler implements
		ResourceAssembler<Contact, Resource<Contact>> {

	public Resource<Contact> toResource(Contact entity) {
		Resource<Contact> resource = new Resource<Contact>(entity);
		resource.add(linkTo(ContactController.class).slash(entity.getId())
				.withRel("contact"));
		return resource;
	}
}