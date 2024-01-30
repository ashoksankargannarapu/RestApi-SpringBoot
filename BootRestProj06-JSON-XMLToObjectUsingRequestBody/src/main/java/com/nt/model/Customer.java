//Customer.java

package com.nt.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data
public class Customer {
	private Integer cno;
	private String cname;
	private Double billAmt;
	private String[] nickNames;
	private List<String> friends;
	private Set<Long> phoneNumbers;
	private Map<String,String> idDetails;
	
	//HAS-A property
	private Address addrs;

}
