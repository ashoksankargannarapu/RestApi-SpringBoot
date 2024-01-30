package com.nt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private Integer pid;
	private String pname;
	private Double price;
	private Double qty;
	private LocalDateTime expiry;

}
