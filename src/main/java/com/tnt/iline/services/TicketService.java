package com.tnt.iline.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tnt.iline.DTO.TicketDTO;
import com.tnt.iline.domain.Ticket;
import com.tnt.iline.repository.TicketRepository;
import com.tnt.iline.services.exception.ObjectNotFoundException;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}
	
	public List<Ticket> findAllSorted(){
		
		return ticketRepository.findAll();
	}

	public Ticket findById(String id) {
		Optional<Ticket> user = ticketRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
	}
	
	public Ticket insert(Ticket obj) {
		return ticketRepository.insert(obj);
	}
	
	public Ticket update(Ticket obj) {
		Ticket newObj = findById(obj.getId());
		updateCallDate(newObj, obj);
		return ticketRepository.save(newObj);
	}

	private void updateCallDate(Ticket newObj, Ticket obj) {
		newObj.setCallDate(obj.getCallDate());
	}
	
	public Ticket fromDTO(TicketDTO objDTO) {
		return new Ticket(objDTO.getId(), objDTO.getNumber(), objDTO.getCreationDate(), objDTO.getCallDate(), objDTO.isPriority());
	}

	public void delete(String id) {
		findById(id);
		ticketRepository.deleteById(id);
	}
	

	public Ticket maxTicket() {
		return ticketRepository.maxTicket();

	}

	public Ticket getNextPendingTicket(boolean priority, Date minDate, Date maxDate) {
		maxDate  = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		Ticket ticket =  ticketRepository.getNextPendingTicket(priority, minDate, maxDate, PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "number"))).getContent().get(0);
		return  ticket;
	}
}
