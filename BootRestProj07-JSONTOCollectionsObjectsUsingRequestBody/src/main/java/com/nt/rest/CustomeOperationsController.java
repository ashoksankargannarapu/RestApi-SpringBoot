package com.nt.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Customer;

@RestController
@RequestMapping("/customerapi")
public class CustomeOperationsController {
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody Customer cust){
		return new ResponseEntity<String>(cust.toString(),HttpStatus.CREATED);
	}

}
