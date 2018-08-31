package com.tnt.iline.DTO;

import java.io.Serializable;
import java.util.Date;

import com.tnt.iline.domain.Ticket;

public class TicketDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private int number;
	private Date creationDate;
	private Date callDate;
	private boolean priority;
	
	public TicketDTO(Ticket obj) {
		id = obj.getId();
		number = obj.getNumber();
		creationDate = obj.getCreationDate();
		callDate = obj.getCallDate();
	}

	public TicketDTO(String id, int number) {
		super();
		this.id = id;
		this.number = number;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public boolean isPriority() {
		return priority;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}
	
	
}
