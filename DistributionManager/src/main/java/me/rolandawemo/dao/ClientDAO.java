package me.rolandawemo.dao;

import java.util.ArrayList;

import me.rolandawemo.dao.mappers.AccountRowMapper;
import me.rolandawemo.dao.mappers.ClientRowMapper;
import me.rolandawemo.dao.model.Account;
import me.rolandawemo.dao.model.Client;

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
	private AccountDAO accountDAO;

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
		
		for(Client client : clients) {
			if ("consumer".equals(client.getType()) || "company".equals(client.getType())) {
				client.setAccount(this.accountDAO.getAccount(client.getId()));
			}
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
		
		if ("consumer".equals(client.getType()) || "company".equals(client.getType())) {
			client.setAccount(this.accountDAO.getAccount(client.getId()));
		}

		return client;
	}

	@Override
	public ArrayList<Client> getClients(String query) {
		String sqlQuery = "SELECT id, prefix, firstName, lastName, company, type FROM clients WHERE firstName LIKE ? OR lastName LIKE ? OR company LIKE ?";
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			clients = (ArrayList<Client>) this.jdbcTemplate.query(sqlQuery,
					new String[] { "%" + query + "%", "%" + query + "%", "%" + query + "%"}, new ClientRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		for(Client client : clients) {
			if ("consumer".equals(client.getType()) || "company".equals(client.getType())) {
				client.setAccount(this.accountDAO.getAccount(client.getId()));
			}
		}
		return clients;
	}

	@Override
	public ArrayList<Client> getByType(String type) {
		String query = "SELECT Client.id, Client.prefix, Client.firstName, Client.lastName, Client.company, Client.type FROM clients as Client WHERE Client.type = ?";
		ArrayList<Client> clients = new ArrayList<Client>();
		try {
			clients = (ArrayList<Client>) this.jdbcTemplate.query(query,
					new String[] { type}, new ClientRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		for(Client client : clients) {
			if ("consumer".equals(client.getType()) || "company".equals(client.getType())) {
				client.setAccount(this.accountDAO.getAccount(client.getId()));
			}
		}
		return clients;
	}

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
}
