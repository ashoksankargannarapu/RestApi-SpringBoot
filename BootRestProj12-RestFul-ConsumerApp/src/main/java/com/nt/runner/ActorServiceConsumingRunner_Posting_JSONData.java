package com.nt.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//@Component
public class ActorServiceConsumingRunner_Posting_JSONData implements CommandLineRunner {
	
	@Autowired
	RestTemplate template=new RestTemplate();
	public void run(String... args)throws Exception{
		String serviceUrl="http://localhost:3030/BootRestProj11-RestFul-ProviderApp/actor-api/register";
		//prepare json data request body
		String json_body= "{\"id\":1001,\"name\":\"Ashok\",\"addrs\":\"Hyd\",\"remuneration\":54666}";
		//prepare headers
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		//Prepare HttpRequest as HttpEntity obj having head,body
		HttpEntity<String> request=new HttpEntity<String>(json_body,headers);
		//make http request call in post mode
		ResponseEntity<String> response=template.postForEntity(serviceUrl, request, String.class);
		
		//display the received details from the response
		System.out.println("Response body(output)::"+response.getBody());
		System.out.println("Response status code::"+response.getStatusCode().value());
		System.out.println("Response headers::"+response.getHeaders().toString());
		
	}
	

}
