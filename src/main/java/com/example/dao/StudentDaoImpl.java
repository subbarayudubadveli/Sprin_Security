package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dto.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	//Autowired the JdbcTemplate bean
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Student findCustomerByCustomerName(String username) {

        //write a db query to fetch the customer object from the db
		String sql = "select * from students where username=?";
		Object[] args = {username};
		
		 Student student = jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<Student>(Student.class));
		
		return student;
	}

}
