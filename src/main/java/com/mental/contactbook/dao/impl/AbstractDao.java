package com.mental.contactbook.dao.impl;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class AbstractDao {
	
	private DataSource dataSource;
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
}
