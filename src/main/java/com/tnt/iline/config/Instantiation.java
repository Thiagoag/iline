package com.tnt.iline.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.tnt.iline.domain.Ticket;
import com.tnt.iline.repository.TicketRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		ticketRepository.deleteAll();
		
		Ticket t1 = new Ticket(null, 1, new Date(), null, false);
		Ticket t2 = new Ticket(null, 2, new Date(), null, true);
		Ticket t3 = new Ticket(null, 3, new Date(), null, false);
		Ticket t4 = new Ticket(null, 3, new Date(), null, false);
		Ticket t5 = new Ticket(null, 5, new Date(), new Date(), true);
		Ticket t6 = new Ticket(null, 5, new Date(), null, true);
		Ticket t7 = new Ticket(null, 6, new Date(), new Date(), false);
		Ticket t8 = new Ticket(null, 6, new Date(), null, false);
		ticketRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5, t6, t7, t8));

	}

}
