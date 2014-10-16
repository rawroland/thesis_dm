package me.rolandawemo.dao;

import java.util.ArrayList;

import me.rolandawemo.dao.mappers.ClientRowMapper;
import me.rolandawemo.dao.mappers.EmployeeRowMapper;
import me.rolandawemo.dao.model.Client;
import me.rolandawemo.dao.model.Employee;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Concrete JDBC Employee DAO implementation
 * 
 * @author Roland Awemo
 * 
 */
public class ClientDAO implements IClientDAO {

	private JdbcTemplate jdbcTemplate;

	public ClientDAO(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	public ClientDAO() {
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	@Override
	public int create(String prefix, String firstName, String lastName,
			String company, String type) {
		String query = "INSERT INTO clients (prefix, firstName, lastName, company, type) VALUES(?, ?, ?, ?, ?)";
		int clientAdded = 0;
		try {
			clientAdded = this.jdbcTemplate.update(query, new Object[] {
					prefix, firstName, lastName, company, type });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return clientAdded;
	}

	@Override
	public int update(int id, String prefix, String firstName, String lastName,
			String company, String type) {
		String query = "UPDATE clients SET prefix=?, firstName=?, lastName=?, company=?, type=? WHERE id=?";
		int clientEdited = 0;
		try {
			clientEdited = this.jdbcTemplate.update(query, new Object[] {
					prefix, firstName, lastName, company, type, id });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return clientEdited;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM clients WHERE id=?";
		int clientDeleted = 0;
		try {
			clientDeleted = this.jdbcTemplate
					.update(query, new Object[] { id });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return clientDeleted;
	}

	@Override
	public ArrayList<Client> getAll() {
		String query = "SELECT Client.id, Client.prefix, Client.firstName, Client.lastName, Client.company, Client.type FROM clients as Client WHERE 1";
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			clients = (ArrayList<Client>) this.jdbcTemplate.query(query,
					new ClientRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return clients;
	}

	@Override
	public Client getById(int id) {
		String query = "SELECT Client.id, Client.prefix, Client.firstName, Client.lastName, Client.company, Client.type FROM clients as Client WHERE Client.id = ?";
		Client client = new Client();
		try {
			client = this.jdbcTemplate.queryForObject(query,
					new Integer[] { id }, new ClientRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return client;
	}

	@Override
	public ArrayList<Client> getClients(String query) {
		String sqlQuery = "SELECT Client.id, Client.prefix, Client.firstName, Client.lastName, Client.company, Client.type FROM clients as Client WHERE Client.firstName LIKE '%' || ? || '%'";
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			clients = (ArrayList<Client>) this.jdbcTemplate.query(sqlQuery, new String[] {query},
					new ClientRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return clients;
	}

}
