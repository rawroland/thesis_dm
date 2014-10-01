package me.rolandawemo.models;

public class Employee {
	private int id;
	private String givenname;
	private String surname;
	private String role;
	private String username;
	private String password;

	public Employee(int id, String givenname, String surname, String role,
			String username) {
		this.id = id;
		this.givenname = givenname;
		this.surname = surname;
		this.username = username;
	}

	public Employee() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
