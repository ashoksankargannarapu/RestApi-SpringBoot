package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Person;
import com.nt.entity.PhoneNumber;
import com.nt.repository.IPersonRepository;
import com.nt.service.IOToMAssociationMgmtService;


@RestController
@RequestMapping("/AssociationMapping")
public class AssociationMappingOperationsController {

	@Autowired
	private IOToMAssociationMgmtService service;
	
	@Autowired
	private IPersonRepository personRepo;

	@PostMapping("/register/parent")
	public ResponseEntity<?> enrollPerson(@RequestBody Person person) {
		try {
			// use service
			Person resultMsg = service.saveDataUsingParent(person);
			return new ResponseEntity<Person>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Problem in Person enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> getAllPersons() {
		try {
			List<Person> list = service.fetchAllPersons();
			return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem in Person enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/find/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable("id") Integer id) {
		try {
			Person person = service.fetchPersonById(id);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modifyPerson(@RequestBody Person person) {
		try {
			// use service
			String resultMsg = service.updatePersonDetails(person);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePersonById(@PathVariable("id") int id) {
		try {
            //use service
			String resultMsg = service.removePersonById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/delete/parent/{id}")
	public ResponseEntity<String> deletePersonChildsByParentId(@PathVariable("id") int id) {
		try {
	        //use service
			String resultMsg = service.deleteAllChildsOfAParentById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/add/parent/{id}")
	public ResponseEntity<String> addPersonChildsByParentId(@PathVariable("id") int id,@RequestBody PhoneNumber ph) {
		try {
	        //use service
			String resultMsg = service.addNewChildToAParentById(id, ph);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	

}
