package me.rolandawemo.dao.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Account")
public class Account {

	private int id; 
	private int clientId; 
	private int amount; 
	private Client client;
	
	
	public Account(int id, int clientId, int amount) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.amount = amount;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@XmlElement(name="Client")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
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
		if (amount != other.amount)
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (clientId != other.clientId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	public boolean canAfford(int cost, int payment) {
		return (cost <= (this.amount + payment));
	}
	
}
