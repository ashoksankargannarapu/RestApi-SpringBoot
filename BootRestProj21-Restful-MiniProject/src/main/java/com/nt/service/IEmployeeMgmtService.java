package com.nt.service;

import java.util.List;

import com.nt.entity.Employee;
import com.nt.exception.EmployeeNotFoundException;




public interface IEmployeeMgmtService {
	public String registerEmployee(Employee item);
	public List<Employee> fetchAllEmployees();
	public Employee fetchEmployeeById(Integer eid)throws EmployeeNotFoundException;
	public String updateEmployeeDetails(Integer id,Employee emp)throws EmployeeNotFoundException;
	public String removeEmployeeById(int id)throws EmployeeNotFoundException;
	
	

}
