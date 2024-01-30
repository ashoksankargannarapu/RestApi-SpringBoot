package com.nt.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="RestApi_Employee2")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer empNo;
	
	@Column(length = 20,unique = true)
	@NonNull
	@Pattern(regexp = "^[A-Za-z]+$", message = "Employee name must contain only alphabetic characters")

	private String empName;
	
	@Column(length = 20)
	@NonNull
	private String empDesignation;
	
	@NonNull
	private Double empSalary;
	
	

}
