package com.tnt.iline.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.tnt.iline.domain.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, String>, TicketRepositoryCustom{

	@Query("{ $and: [ { creationDate: {$gte: ?1} }, { creationDate: {$lte: ?2} }, {priority: ?0 }, {callDate: null} ] }")	
	Page<Ticket> getNextPendingTicket(boolean priority, Date minDate, Date maxDate, Pageable pageable);

}