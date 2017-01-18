package com.mental.contactbook.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.mental.contactbook.AbstractTest;

@SqlGroup ({ 
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
	@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
public class ContactDaoImplIT extends AbstractTest{

	@Autowired
	ContactDaoImpl assembler;
	
	@Test
	public void test_cache() throws Exception {
		assembler.initCaches();
		assertNotNull(assembler.getContacts());
	}
}
