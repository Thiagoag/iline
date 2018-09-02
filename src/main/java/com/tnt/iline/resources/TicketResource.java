package com.tnt.iline.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tnt.iline.DTO.TicketDTO;
import com.tnt.iline.domain.Ticket;
import com.tnt.iline.resources.util.URL;
import com.tnt.iline.services.TicketService;

@RestController
@RequestMapping(value="/tickets")
public class TicketResource {

	@Autowired
	TicketService ticketService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<TicketDTO>> findAll(){
		List<Ticket> list = ticketService.findAll();
		List<TicketDTO> listDTO = list.stream().map(x -> new TicketDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
		
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<TicketDTO> findById(@PathVariable String id){
		Ticket obj = ticketService.findById(id);
		return ResponseEntity.ok().body(new TicketDTO(obj));		
	}
	
	//@PostMapping also works
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( method=RequestMethod.POST)
	public ResponseEntity<TicketDTO> insert(@RequestBody TicketDTO objDTO){
		Ticket obj = ticketService.fromDTO(objDTO);
		obj = ticketService.insert(obj);
		//To return a header with the created resource URL is good practice
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id){
		ticketService.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody TicketDTO objDTO, @PathVariable String id){
		Ticket obj = ticketService.fromDTO(objDTO);
		obj.setId(id);
		obj = ticketService.update(obj);
		return ResponseEntity.noContent().build();	
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/max", method=RequestMethod.GET)
	public ResponseEntity<TicketDTO> findMax(){
		Ticket obj = ticketService.maxTicket();
		return ResponseEntity.ok().body(new TicketDTO(obj));		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/getnextticket", method=RequestMethod.GET)
	public ResponseEntity<Ticket> fullSearch(
			@RequestParam(value="priority", defaultValue="") String priorityText,
			@RequestParam(value="minDate", defaultValue="") String minDate,
			@RequestParam(value="maxDate", defaultValue="") String maxDate){
		boolean priority = URL.convertBoolean(priorityText, false);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		
		Ticket ticket = ticketService.getNextPendingTicket(priority, min, max);
		return ResponseEntity.ok().body(ticket);		
	}
}
