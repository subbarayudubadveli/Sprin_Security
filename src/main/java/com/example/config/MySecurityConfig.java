package com.example.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import com.example.service.StudentUserDetailsServiceImpl;

@EnableWebSecurity(debug = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JdbcUserDetailsManager userDetailsManager;
	
	@Autowired
	private StudentUserDetailsServiceImpl studentUserDetailsServiceImpl;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	 
		//------- using InMemory Authentication -------------//
		/*
		 * //Approach-1 : To configure the userdeatils 
		 * auth.inMemoryAuthentication()
		 * .withUser("subbu")
		 * .password("$2y$10$WQ4u77wfZSgFXwM3ssjnBuwYaFu17BwtnZhHDhSE.xDCK.bsTL/.i")
		 * .roles("user");
		 */
   
		
		/*
		 * //Approach-2 : To configure the userdeatils
		 * 
		 * InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
		 *  //creating user roles
		 * ArrayList<GrantedAuthority> roles= new ArrayList<GrantedAuthority>();
		 * SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("USER");
		 * SimpleGrantedAuthority role2 = new SimpleGrantedAuthority("ADMIN");
		 * roles.add(role1); roles.add(role2);
		 * 
		 * //creating user details User subbu = new User("subbu","subbu", roles);
		 * userDetailsManager.createUser(subbu);
		 * auth.userDetailsService(userDetailsManager);
		 */
		 
    	 
		/*
		 * //Approach-3: Using the UserBuilder inner class to initialize the user details and creates UserDetails Object
		 * 
		 * UserDetails subbu =User.withUsername("subbu").password("subbu").roles("ADMIN","USER").build();
		 * InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(); 
		 * userDetailsManager.createUser(subbu);
		 * auth.userDetailsService(userDetailsManager);
		 */
    	 
		//------using JdbcAuthentication---------//
		
		
		/*
		 * //connecting with default spring user table using jdbc authentication
		 * auth.jdbcAuthentication() .dataSource(dataSource)
		 * .passwordEncoder(passwordEncoder);
		 */
		
		
		/*
		 * //using the spring JdbcUserDetailsMaager service with custom table queries
		 * to load the user details from data base auth.jdbcAuthentication()
		 * .dataSource(dataSource)//pass the data source bean //write a custom table
		 * queries;
		 * .usersByUsernameQuery("select username,password,enabled from students where username=?"
		 * ) //write a custom authorities table queries
		 * .authoritiesByUsernameQuery("select username,roles from students where username=?"
		 * ) .passwordEncoder(passwordEncoder);
		 */
		 
		
		/*
		 * //using JdbcUserDetailsManager with custom queries -> as a UserDetailService
		 * auth.userDetailsService(userDetailsManager) // pass the JdbcUserDetails manager bean 
		 * .passwordEncoder(passwordEncoder);
		 */
		 
		//using Custom UserDetails service by implementing our logic to load user from DB
		auth.userDetailsService(studentUserDetailsServiceImpl)
		.passwordEncoder(passwordEncoder);
		
		
		//using  custom AuthenticationProvider instead of DoaAuthenticationProvider  
		
		
    	
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		.antMatchers("/save","/update").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.and().httpBasic()
		.and().csrf().disable();

	}

}
