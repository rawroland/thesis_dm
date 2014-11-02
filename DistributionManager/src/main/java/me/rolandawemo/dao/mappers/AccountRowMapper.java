package me.rolandawemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.Account;
import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<Account>{
	public Account mapRow(ResultSet rs, int row) {
		Account account = new Account();
		try {
			account.setId(rs.getInt("id"));
			account.setClientId(rs.getInt("clientId"));
			account.setAmount(rs.getInt("amount"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}
}
