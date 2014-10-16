package me.rolandawemo.dao.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Account")
public class Account {

	private int id; 
	private int clientId; 
	private int ammount; 
	
	
	public Account(int id, int clientId, int ammount) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.ammount = ammount;
	}
	
	public Account() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ammount;
		result = prime * result + clientId;
		result = prime * result + id;
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
		Account other = (Account) obj;
		if (ammount != other.ammount)
			return false;
		if (clientId != other.clientId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
}
