package com.tnt.iline.repository;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.tnt.iline.domain.Ticket;

public class TicketRepositoryImpl implements TicketRepositoryCustom{

	@Autowired
    private MongoTemplate mongoTemplate;
	
	@Override
	public Ticket maxTicket() {
		final Query query = new Query()
                .limit(1)
                .with(new Sort(Sort.Direction.DESC, "number"));
        return mongoTemplate.findOne(query, Ticket.class);
	}

	@Override
	public Ticket nextTicket(boolean priority, Date minDate, Date maxDate) {
		// TODO Auto-generated method stub
		return null;
	}


}
