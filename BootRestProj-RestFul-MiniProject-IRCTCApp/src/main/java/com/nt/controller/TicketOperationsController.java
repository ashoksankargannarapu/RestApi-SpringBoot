package com.nt.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Ticket;
import com.nt.request.Passenger;
import com.nt.service.TicketService;



@RestController
public class TicketOperationsController {

	@Autowired
	private TicketService service;
	
	@PostMapping(value= "/ticket", 
				consumes="application/json", 
				produces="application/json"
	)
	public ResponseEntity<Ticket> bookTicket(@RequestBody Passenger passenger){
		System.out.println("TicketOperationsController.bookTicket()");
		Ticket ticket = service.bookTicket(passenger);
		
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}
	
	@GetMapping(value="/ticket/{ticketNum}", produces="application/json")
	public ResponseEntity<Ticket> getTicket(@PathVariable("ticketNum") Integer ticketNum){
		
		Ticket ticket = service.getTicket(ticketNum);
		
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}
	
	@GetMapping(value="/tickets", produces="application/json")
	public ResponseEntity<List<Ticket>> getAllTicket(){
		
		List<Ticket> allTickets = service.getAllTickets();
		
		return new ResponseEntity<>(allTickets, HttpStatus.OK);
	}
}
