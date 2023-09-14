package com.example.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class UserDetailsMangerConfig {

	
	@Bean 
	public JdbcTemplate getJdbcTemplate() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(datasource());
		return jdbcTemplate;
		
	}
	
	/*
	 * @Bean public JdbcUserDetailsManager jdbcUserDetailsManager() {
	 * JdbcUserDetailsManager jdbcUserDetailsManager = new
	 * JdbcUserDetailsManager(datasource()); return jdbcUserDetailsManager; }
	 */
	
	
	// overrideing the default queries with custom user table queries
	@Bean  
	public JdbcUserDetailsManager jdbcUserDetailsManager(){
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource());
		jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,enabled from students where username=?");
		
		return jdbcUserDetailsManager;
		
	}
	
	// Configure data source bean here
	@Bean
	public DataSource datasource() {

		DriverManagerDataSource driverManagerDataSource= new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/spring_security");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("subbu");
		return driverManagerDataSource;	
	}
	
	/*
	 * // Configuring PasswordEncoder bean
	 * 
	 * @Bean public PasswordEncoder getPasswordEncoder() {
	 * 
	 * return NoOpPasswordEncoder.getInstance();
	 * 
	 * }
	 */
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
	     return new BCryptPasswordEncoder();	
	}

	// Configuring JdbcUserDeatilsManager bean with custom table queries to use default persistance
	//layer from spring
//	@Bean
//	public JdbcUserDetailsManager jdbcUserDetailsManager() {
//
//		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();// pass data source if its ready
//		userDetailsManager.setUsersByUsernameQuery(null);
//		userDetailsManager.setAuthoritiesByUsernameQuery(null);
//		userDetailsManager.setChangePasswordSql(null);
//		userDetailsManager.setDeleteUserSql(null);
//		return userDetailsManager;
//	}

}
