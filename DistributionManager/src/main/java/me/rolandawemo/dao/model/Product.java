package me.rolandawemo.dao.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Product")
public class Product {

	private int id; 
	private int clientId; 
	private int price;
	private int quantity;
	private String name;
	
	public Product(int id, int clientId, int price, int quantity, String name) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}

	public Product() {
		
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clientId;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		result = prime * result + quantity;
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
		Product other = (Product) obj;
		if (clientId != other.clientId)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	public boolean available(int quantity) {
		return quantity <= this.quantity;
	}

	public int calculateTotalPrice(int quantity) {
		return this.price * quantity;
	}
	
	
}
