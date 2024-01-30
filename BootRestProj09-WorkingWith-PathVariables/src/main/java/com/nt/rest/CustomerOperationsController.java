package com.nt.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customerapi")
public class CustomerOperationsController {
	
	/*@GetMapping("/report/{cno}/{name}")
	public String fetchData(@PathVariable("name") String cname,
			                @PathVariable Integer cno) {
		return cno+"-------"+cname;	
	}*/
	
	/*@GetMapping("/report/{cno}/{name}")
	public String fetchData(@PathVariable(name="name",required=false) String cname,
			                @PathVariable(required=false) Integer cno) {
		return cno+"-------"+cname;	
	}*/
	
	@GetMapping("report/no/name")
	public String fetchData1(@PathVariable(name="name",required = false)String cname,
			                 @PathVariable(required = false)Integer no) {
		return "From FetchData1";
	}
	@GetMapping("report/no/{name}")
	public String fetchData2(@PathVariable(name="name",required = false)String cname,
			                 @PathVariable(required = false)Integer no) {
		return "From FetchData2";
	}
	@GetMapping("report/{no}/{name}")
	public String fetchData3(@PathVariable(name="name",required = false)String cname,
			                 @PathVariable(required = false)Integer no) {
		return "From FetchData3";
	}
	@GetMapping("report/{no}/{name}")
	public String fetchData4(@PathVariable(name="name",required = false)String cname,
			                 @PathVariable(required = false)Integer no) {
		return "From FetchData4";
	}
	@GetMapping("report/101/raja")
	public String fetchData5(@PathVariable(name="name",required = false)String cname,
			                 @PathVariable(required = false)Integer no) {
		return "From FetchData5";
	}
	

}
