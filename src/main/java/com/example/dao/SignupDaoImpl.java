package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dto.SignupDto;


//if we are using JdbcUserDetailsManager then not required this DAO layer 
@Repository
public class SignupDaoImpl implements SignupDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveUser(SignupDto signupDto) {
		String sql = "insert into spring_security.users values(?,?,?)";
		String sql2 = "insert into spring_security.authorities values(?,?)";
		
		jdbcTemplate.update(sql, signupDto.getUsername(),signupDto.getPassword(),1);
		jdbcTemplate.update(sql2, signupDto.getUsername(),"Security");
		
	}

}
