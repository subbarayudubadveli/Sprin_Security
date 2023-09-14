package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	
	@GetMapping("/get")
	public String getStudent() {
		
		return "student - name - Subbu";
	}

	@GetMapping("/save")
	public String saveStudent() {
		
		return "student - daved";
	}
	
	@GetMapping("/update")
	public String updateStudent() {
		
		return " updated student details";
	}
	
	
}
