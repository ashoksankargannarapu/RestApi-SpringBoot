package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class ActorServiceConsumingRunner implements CommandLineRunner {
	
	@Autowired
	RestTemplate template=new RestTemplate();
	public void run(String... args)throws Exception{
		String serviceUrl="http://localhost:3030/BootRestProj11-RestFul-ProviderApp/actor-api/wish/Ashok";
		//Generate HttpRequest with GET mode  to consume the web service
		ResponseEntity<String> response=template.getForEntity(serviceUrl, String.class);
		//display the received from the response
		System.out.println("Response body(output)::"+response.getBody());
		System.out.println("Response status code::"+response.getStatusCode().value());
	}
	

}
