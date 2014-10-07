package me.rolandawemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.Client;
import org.springframework.jdbc.core.RowMapper;

public class ClientRowMapper implements RowMapper<Client>{
	public Client mapRow(ResultSet rs, int row) {
		Client client = new Client();
		try {
			client.setId(rs.getInt("id"));
			client.setFirstName(rs.getString("firstName"));
			client.setLastName(rs.getString("lastName"));
			client.setPrefix(rs.getString("prefix"));
			client.setType(rs.getString("type"));
			client.setCompany(rs.getString("company"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
}
