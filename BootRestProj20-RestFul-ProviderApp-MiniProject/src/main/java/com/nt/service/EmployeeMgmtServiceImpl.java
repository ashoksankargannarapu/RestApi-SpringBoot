package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Employee;
import com.nt.exception.EmployeeNotFoundException;
import com.nt.repository.IEmployeeRepo;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {

	@Autowired
	private IEmployeeRepo empRepo;

	@Override
	public String registerEmployee(Employee employee) {
		int idVal = empRepo.save(employee).getEmpNo();
		return "Employee is registered having id value::" + idVal;
	}

	@Override
	public List<Employee> fetchAllEmployees() {
		List<Employee> list = empRepo.findAll();
		list.sort((t1, t2) -> t1.getEmpNo().compareTo(t2.getEmpNo()));
		return list;
	}

	@Override
	public Employee fetchEmployeeById(Integer eid) throws EmployeeNotFoundException {
		return empRepo.findById(eid)
				.orElseThrow(() -> new EmployeeNotFoundException(eid + ": Employee is not found"));
	}

	

	@Override
	public String updateEmployeeDetails(Employee employee) throws EmployeeNotFoundException {
		Optional<Employee> optional = empRepo.findById(employee.getEmpNo());
		if (optional.isPresent()) {
			empRepo.save(employee);// save(-)performs either save obj or update obj creation
			return employee.getEmpNo() + "Employee is updated";
		} else {
			throw new EmployeeNotFoundException(employee.getEmpNo() + " Employee is not found");
		}
	}


	@Override
	public String removeEmployeeById(int id) throws EmployeeNotFoundException {
		Optional<Employee> optional = empRepo.findById(id);
		if (optional.isPresent()) {
			// use Repo
			empRepo.deleteById(id);
			return id + ": id employee found and deleted";
		} else {
			throw new EmployeeNotFoundException(id + ":id  Employee is not found for deletion");
		}
	}

	

}
