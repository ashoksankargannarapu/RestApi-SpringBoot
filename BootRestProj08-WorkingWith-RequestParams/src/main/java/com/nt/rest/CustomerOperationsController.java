package com.nt.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerapi")
public class CustomerOperationsController {
	
	/*@GetMapping("/report")
	public String reportData(@RequestParam("cno") Integer no,
			                 @RequestParam("cname") String name) {
		return no+"---"+name;
	}*/
	
	/*@GetMapping("/report")
	public String reportData(@RequestParam Integer no,
			                 @RequestParam(required = false) String name) {
		return no+"---"+name;
	}*/
	
	@GetMapping("/report")
	public String reportData(@RequestParam Integer no,
			                 @RequestParam(required = false,defaultValue = "Ashok") String name) {
		return no+"---"+name;
	}

}
