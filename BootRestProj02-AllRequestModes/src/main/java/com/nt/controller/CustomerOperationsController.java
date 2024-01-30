package com.nt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerOperationsController {
	
	@GetMapping("/report")
	public ResponseEntity<String> showCustomersReport(){
		return new ResponseEntity<String>("Selecting All Records(GET)",HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> registerCustomer(){
		return new ResponseEntity<String>("Inserting  the Records(POST)",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateCustomer(){
		return new ResponseEntity<String>("Update the Records(PUT)",HttpStatus.OK);
	}
	
	@PatchMapping("/modify")
	public ResponseEntity<String> updateCustomerByEmail(){
		return new ResponseEntity<String>("Update the Customer Email Record(PATCH)",HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(){
		return new ResponseEntity<String>("Delete the Records(DELETE)",HttpStatus.OK);
	}

}
