package com.nt.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.Actor;

@RestController
@RequestMapping("/actor-api")
public class ActorOperationsController {
	
	@GetMapping("/wish")
	public ResponseEntity<String> displayWishMessage(){
		return new ResponseEntity<String>("Good Morning:",HttpStatus.OK);
	}
	@GetMapping("/wish/{id}/{name}/{addrs}")
	public ResponseEntity<String> displayWishMessage(@PathVariable Integer id,
			                                         @PathVariable String name,
			                                         @PathVariable String addrs){
		return new ResponseEntity<String>("Good Morning::"+id+".."+name+".."+addrs,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerActor(@RequestBody Actor actor){
		return new ResponseEntity<String>("Actor Data::"+actor,HttpStatus.OK);
	}
	
	@GetMapping("/report")
	public ResponseEntity<Actor> reportActorData(){
		Actor actor=new Actor(1002, "Ashok","Chennai", 34565);
		return new ResponseEntity<Actor>(actor,HttpStatus.OK);
	}
	
	@GetMapping("/reportAll")
	public ResponseEntity<List<Actor>> reportAllActorsData(){
		List<Actor> list=List.of(new Actor(111, "rajesh", "hyd",23432.0f),
				                new Actor(110, "Suresh", "vizag",23433.0f));
		return new ResponseEntity<List<Actor>>(list,HttpStatus.OK);
	}

}
