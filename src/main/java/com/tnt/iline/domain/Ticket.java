package com.tnt.iline.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ticket")
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	String id;
	int number;
	Date creationDate;
	Date callDate;
	boolean priority;
	
	public Ticket() {
		super();
	}


	public Ticket(String id, int number, Date creationDate, Date callDate, boolean priority) {
		super();
		this.id = id;
		this.number = number;
		this.creationDate = creationDate;
		this.callDate = callDate;
		this.priority = priority;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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
