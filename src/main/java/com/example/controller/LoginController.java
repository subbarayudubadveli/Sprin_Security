package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.SignupDto;

@RestController
public class LoginController {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
//	@Autowired
//	private DataSource dataSource ;
	
//	@Autowired
//	private SignupDao signupDao;
	
	@PostMapping("/signup")
	public String signUp(@RequestBody SignupDto json) {
		
		String encodedPassword = passwordEncoder.encode(json.getPassword());
		json.setPassword(encodedPassword);
		
//      //Using custom DAO persistence layer		
//		signupDao.saveUser(json);
		
		/*
		 * //Using JdbcUserDetailsManager to save the user details.
		 * JdbcUserDetailsManager jdbcUserDetailsManager = new
		 * JdbcUserDetailsManager(dataSource);
		 * 
		 * UserDetails user =
		 * User.withUsername(json.getUsername()).password(json.getPassword()).
		 * authorities("USER").build(); jdbcUserDetailsManager.createUser(user);
		 */
		
		//Using JdbcUserDetailsManager bean instead of creating object.
		//Here no need to write a code for persistence layer 
		  UserDetails user = User.withUsername(json.getUsername()).password(json.getPassword()).
		  authorities("USER").build(); 
		  userDetailsManager.createUser(user);
		 
		

		
		return "successfully signed up , please login";
	}
	
}
