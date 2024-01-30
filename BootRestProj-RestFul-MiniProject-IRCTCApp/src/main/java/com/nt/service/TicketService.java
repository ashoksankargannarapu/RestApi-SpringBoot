package com.nt.service;

import java.util.List;

import com.nt.entity.Ticket;
import com.nt.request.Passenger;

public interface TicketService {

	public Ticket bookTicket(Passenger passenger);
	
	public Ticket getTicket(Integer ticketNum);
	
	public List<Ticket> getAllTickets();

}
