package com.nt.runner;



import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Nullable;
@Component
public class PatchModeRequest implements CommandLineRunner {
	@Autowired
	RestTemplate template=new RestTemplate();
	@Nullable
	public void run(String... args)throws Exception{
		String baseUrl="http://localhost:3030/BootRestProj10-RestFul-ProviderApp-MiniProject";
		String path="/tourist/pupdate/{id}/{percentage}";
		
		HttpHeaders headers = new HttpHeaders();
        // Set headers as needed
        // headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        String url = baseUrl + path;
		//make http request call in patch mode
      
		ResponseEntity<String> response=template.exchange(url, HttpMethod.PATCH,requestEntity,
				                                    String.class,2,20);
		
		//display the received details from the response
		System.out.println("Response body(output)::"+response.getBody());
		System.out.println("Response status code::"+response.getStatusCode().value());
		System.out.println("Response headers::"+response.getHeaders().toString());
		System.exit(0);
		
	}
}
