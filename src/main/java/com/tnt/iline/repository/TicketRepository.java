package com.tnt.iline.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tnt.iline.domain.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String>, TicketRepositoryCustom{

	
	
}