package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.entity.Ticket;
import com.nt.repo.TicketRepo;
import com.nt.request.Passenger;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired
	private TicketRepo repo;

	@Override
	public Ticket bookTicket(Passenger passenger) {
		
		Ticket ticket = new Ticket();
		BeanUtils.copyProperties(passenger, ticket);
		
		ticket.setPrice(1500.00);
		ticket.setTicketStatus("CONFIRMED");
		
		ticket = repo.save(ticket);
		
		return ticket;
	}

	@Override
	public Ticket getTicket(Integer ticketNum) {
		
		Optional<Ticket> findById = repo.findById(ticketNum);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		
		return null;
	}

	@Override
	public List<Ticket> getAllTickets() {
		return repo.findAll();
	}
	
	

}
