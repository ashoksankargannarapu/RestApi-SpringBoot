package com.nt.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.nt.model.Product;

@RestController
@RequestMapping("/productapi")
public class ProductOperationsController {
	
	@PostMapping("/register")
	public ResponseEntity<String> registerProduct(@RequestBody Product prod) {
		return new ResponseEntity<String>(prod.toString(),HttpStatus.OK);
	}
	
	

}
