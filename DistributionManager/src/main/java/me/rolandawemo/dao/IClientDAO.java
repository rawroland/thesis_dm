package me.rolandawemo.dao;

import java.util.ArrayList;

import me.rolandawemo.dao.model.Client;

/**
 * Client DAO Interface to be implemented by a concrete implementation
 * @author Roland Awemo
 *
 */
public interface IClientDAO {

	/**
	 * Create a new Client.
	 * @param String givenname The Client's givenname.
	 * @param String surname The Client's surname.
	 * @param String company The Client's company.
	 * @param String role The Client's role.
	 * @param String password The Client's password.
	 * @return boolean true if the Client was successfully created.
	 */
	int create(String prefix, String firstName, String lastName, String company, String type);
	
	/**
	 * Update an Client with the given id.
	 * @param int id The Client's id.
	 * @param String givenname The Client's givenname.
	 * @param String surname The Client's surname.
	 * @param String company The Client's company.
	 * @param String role The Client's role.
	 * @return boolean true if the Client was successfully updated.
	 */
	int update(int id, String prefix, String firstName, String lastName, String company, String type);
	
	/**
	 * Delete the Client with the given id.
	 * @param int id The Client's id.
	 * @return true if the Client was successfully deleted.
	 */
	int delete(int id);
	
	ArrayList<Client> getAll();
	
	Client getById(int id);
	
	ArrayList<Client> getClients(String query);
}
