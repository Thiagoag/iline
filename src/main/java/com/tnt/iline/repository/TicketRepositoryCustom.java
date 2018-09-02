package com.tnt.iline.repository;

import java.util.Date;

import org.springframework.data.mongodb.repository.Query;

import com.tnt.iline.domain.Ticket;

public interface TicketRepositoryCustom {
	
	
	
	Ticket maxTicket();
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }] }")
	Ticket nextTicket(boolean priority, Date minDate, Date maxDate);
	//List<LegalProcess> fullSearch(String text, Date minDate, Date maxDate);
	
}
