package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDao;
import com.example.dto.Student;

@Service
public class StudentUserDetailsServiceImpl implements UserDetailsService {
	

	@Autowired
	private StudentDao studentDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//call the StudentDao findCustomerByCustomerName method to get student object from DB
		//convert student object to UserDetails object and returns the UserDetails object  
		
		Student student = studentDao.findCustomerByCustomerName(username);
		 
		UserDetails userDetails = User.withUsername(student.getUserName())
		.password(student.getPassword())
		.roles(student.getRoles())
		.build();
		
		
		return userDetails;
	}

}
