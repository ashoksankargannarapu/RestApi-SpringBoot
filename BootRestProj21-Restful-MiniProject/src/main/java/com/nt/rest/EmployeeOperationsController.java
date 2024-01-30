package com.nt.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Employee;

import com.nt.repository.IEmployeeRepo;

import com.nt.service.IEmployeeMgmtService;


@RestController
@RequestMapping("/employee")

public class EmployeeOperationsController {

	@Autowired
	private IEmployeeMgmtService service;
	
	

	@PostMapping("/register")
	public ResponseEntity<String> enrollEmployee( @RequestBody @Valid Employee employee) {
		try {
			// use service
			String resultMsg = service.registerEmployee(employee);
			return new ResponseEntity<String>(resultMsg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Problem in Employee enrollment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> getAllEmployees() {
		try {
			List<Employee> list = service.fetchAllEmployees();
			return new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Problem in getting Employee", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
		try {
			Employee employee = service.fetchEmployeeById(id);
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	@PutMapping("/modify/{id}")
	public ResponseEntity<String> modifyEmployee(@PathVariable("id") int id,@RequestBody Employee employee) {
		try {
			// use service
			String resultMsg = service.updateEmployeeDetails(id,employee);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id) {
		try {
            //use service
			String resultMsg = service.removeEmployeeById(id);
			return new ResponseEntity<String>(resultMsg, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
