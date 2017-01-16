package com.mental.contactbook.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class AbstractDao {
	
	private DataSource dataSource;

	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
}
