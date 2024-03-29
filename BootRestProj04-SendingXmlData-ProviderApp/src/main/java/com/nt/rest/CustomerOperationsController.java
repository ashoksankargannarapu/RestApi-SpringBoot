package com.nt.rest;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Company;
import com.nt.model.Customer;
import com.nt.model.Employee;

@RestController
@RequestMapping("/customerapi")
public class CustomerOperationsController {
	
	/*
	 * @GetMapping("/cust_report") public ResponseEntity<Customer>
	 * showDataCustomer() { Customer cust=new Customer(101,"raja", "hyd",
	 * 54543.98f); return new ResponseEntity<Customer>(cust,HttpStatus.OK); }
	 */
	
	@GetMapping("/cust_report")
	public Customer showDataCustomer() {
		System.out.println("CustomerOperationsController.showDataCustomer()");
		Customer cust=new Customer(101,"raja", "hyd", 54543.98f);
		return cust;
	}
	
	
	/*@GetMapping("/emp_report")
	public ResponseEntity<Employee> showEmployeeData(){	  
	Employee emp=new Employee(1001, "Ashok", 67677.0f, new String[]
	{"rajesh","suresh","harsha"}, List.of("ram","syam","sonu"),
	Set.of(9347423171L,8096012327L,9743847473L),
	Map.of("aadhar",209577451329L,"panNo","DVKPg3749v"), new
	Company("HCL","Hyd",435));
	
	return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	
	}*/
	 
	
	@GetMapping("/emp_report")
	public Employee showEmployeeData(){
		
		Employee emp=new Employee(1001, "Ashok", 67677.0f,
				new String[] {"rajesh","suresh","harsha"},
				List.of("ram","syam","sonu"),
				Set.of(9347423171L,8096012327L,9743847473L),
				Map.of("aadhar",209577451329L,"panNo","DVKPg3749v"),
				new Company("HCL","Hyd",435));
		
		return emp;
		
	}

}
