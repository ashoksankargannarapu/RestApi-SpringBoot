package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Ticket;


public interface TicketRepo extends JpaRepository<Ticket, Integer>{

}
