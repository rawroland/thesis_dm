package me.rolandawemo.dao;

import me.rolandawemo.dao.mappers.AccountRowMapper;
import me.rolandawemo.dao.model.Account;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDAO implements IAccountDAO {

	private JdbcTemplate jdbcTemplate;

	public AccountDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public AccountDAO() {
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	@Override
	public int create(int clientId, int amount) {
		String query = "INSERT INTO accounts(clientId, amount) VALUES(?, ?)";
		int accountAdded = 0;
		try {
			accountAdded = this.jdbcTemplate.update(query, new Object[] {
					clientId, amount });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountAdded;
	}

	@Override
	public int credit(int clientId, int amount) {
		String query = "UPDATE accounts SET amount=amount + ? WHERE clientId=?";
		int accountUpdated = 0;
		try {
			accountUpdated = this.jdbcTemplate.update(query, new Object[] {
					amount, clientId });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return accountUpdated;
	}

	@Override
	public int debit(int clientId, int amount) {
		String query = "UPDATE accounts SET amount=amount - ? WHERE clientId=?";
		int accountUpdated = 0;
		try {
			accountUpdated = this.jdbcTemplate.update(query, new Object[] {
					amount, clientId });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return accountUpdated;
	}

	@Override
	public Account getAccount(int clientId) {
		String query = "SELECT Account.id, Account.clientId, Account.amount FROM accounts as Account WHERE Account.clientId = ?";
		Account account = new Account();
		try {
			account = this.jdbcTemplate.queryForObject(query,
					new Integer[] { clientId }, new AccountRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return account;
	}

}
