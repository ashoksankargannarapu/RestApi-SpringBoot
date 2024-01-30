package com.nt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="RestApi_Employee")
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
 
	private String empName;
	
	@Column(length = 20)
	@NonNull
	private Integer empDesignation;
	
	@NonNull
	private Double empSalary;
	
	

}
