package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//@Component
public class DeleteByBudgetBetweenRangeRequest implements CommandLineRunner {
	@Autowired
	RestTemplate template=new RestTemplate();
	public void run(String... args)throws Exception{
		String serviceUrl="http://localhost:3030/BootRestProj10-RestFul-ProviderApp-MiniProject/tourist/delete/{start}/{end}";
		//Generate HttpRequest with Delete mode  to consume the web service
		ResponseEntity<String> response=
                template.exchange(serviceUrl,HttpMethod.DELETE,null,String.class,400000,500000);
		//display the received from the response
		System.out.println("Response body(output)::"+response.getBody());
		System.out.println("Response status code::"+response.getStatusCode().value());
		
		System.exit(0);//Automatically server stops..
	}

}
