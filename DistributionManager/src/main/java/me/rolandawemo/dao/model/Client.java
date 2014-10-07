package me.rolandawemo.dao.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Client")
public class Client {
	private int id;
	private String firstName;
	private String lastName;
	private String prefix;
	private String company;
	private String type;

	public Client(int id, String firstName, String lastName, String prefix,
			String company, String type) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.prefix = prefix;
		this.company = company;
		this.type = type;
	}

	public Client() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
