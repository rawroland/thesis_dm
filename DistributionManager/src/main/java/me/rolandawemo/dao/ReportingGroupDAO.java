package me.rolandawemo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import me.rolandawemo.dao.mappers.ClientRowMapper;
import me.rolandawemo.dao.mappers.ReportingGroupRowMapper;
import me.rolandawemo.dao.model.Client;
import me.rolandawemo.dao.model.ReportingGroup;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ReportingGroupDAO implements IReportingGroupDAO {

	private JdbcTemplate jdbcTemplate;

	public ReportingGroupDAO(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	public ReportingGroupDAO() {
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	@Override
	public int create(String name, ArrayList<Integer> members) {
		final String QUERY = "INSERT INTO groups(name) VALUES (?)";
		final String PASS_NAME = name;
		int groupCreated = 0;
		Number groupId = 0;
		KeyHolder keyholder = new GeneratedKeyHolder();
		try {
			// groupCreated = this.jdbcTemplate.update(query,
			// new Object[] { name });
			groupCreated = this.jdbcTemplate.update(
					new PreparedStatementCreator() {

						@Override
						public PreparedStatement createPreparedStatement(
								Connection con) throws SQLException {
							PreparedStatement ps = con.prepareStatement(QUERY);
							ps.setString(1, PASS_NAME);
							return ps;
						}

					}, keyholder);
			groupId = keyholder.getKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (0 == groupCreated) {
			return 0;
		}

		String associationsQuery = "INSERT INTO clients_groups(clientId,groupId) VALUES ";
		int numberOfMembers = members.size();
		Object[] values = new Object[numberOfMembers * 2];
		int index = 0;
		for (int clientId : members) {
			values[index] = clientId;
			index = index + 1;
			values[index] = groupId;
			index = index + 1;
			if (members.indexOf(clientId) == numberOfMembers - 1) {
				associationsQuery = associationsQuery + "(?,?);";
			} else {
				associationsQuery = associationsQuery + "(?,?),";
			}
		}

		int associationsCreated = 0;
		try {
			associationsCreated = this.jdbcTemplate.update(associationsQuery,
					values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return associationsCreated;
	}

	@Override
	public ArrayList<ReportingGroup> getAll() {
		String query = "SELECT * FROM groups WHERE 1";
		ArrayList<ReportingGroup> groups = new ArrayList<ReportingGroup>();
		try {
			groups = (ArrayList<ReportingGroup>) this.jdbcTemplate.query(query,
					new ReportingGroupRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		for (ReportingGroup group : groups) {
			query = "SELECT * FROM clients_groups WHERE groupId = ?";
			ArrayList<Integer> clients = new ArrayList<Integer>();
			try {
				clients = (ArrayList<Integer>) this.jdbcTemplate.query(query,
						new Object[] { group.getId() },
						new RowMapper<Integer>() {

							@Override
							public Integer mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return rs.getInt("clientId");
							}
						});
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

			group.setClients(clients);
		}
		return groups;
	}

	@Override
	public ReportingGroup getById(int id) {
		String query = "SELECT * FROM groups WHERE id = ?";
		ReportingGroup group = new ReportingGroup();
		try {
			group = this.jdbcTemplate.queryForObject(query,
					new Integer[] { id }, new ReportingGroupRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		query = "SELECT * FROM clients_groups WHERE groupId = ?";
		ArrayList<Integer> clients = new ArrayList<Integer>();
		try {
			clients = (ArrayList<Integer>) this.jdbcTemplate.query(query,
					new Object[] { group.getId() }, new RowMapper<Integer>() {

						@Override
						public Integer mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getInt("clientId");
						}
					});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		group.setClients(clients);
		return group;
	}

}
