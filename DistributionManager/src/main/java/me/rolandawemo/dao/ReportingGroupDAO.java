package me.rolandawemo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import me.rolandawemo.dao.mappers.ClientRowMapper;
import me.rolandawemo.dao.mappers.ReportingGroupRowMapper;
import me.rolandawemo.dao.model.Client;
import me.rolandawemo.dao.model.ReportingGroup;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		String query = "INSERT INTO groups(name) VALUES (?)";
		int groupCreated = 0;
		int groupId = 0;
		try {
			groupCreated = this.jdbcTemplate.update(query,
					new Object[] { name });
			groupId = this.jdbcTemplate.queryForInt("select last_insert_id()");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (0 == groupCreated) {
			return 0;
		}

		String associationsQuery = "INSERT INTO accounts_groups(accountId,groupId) VALUES ";
		int numberOfMembers = members.size();
		Object[] values = new Object[numberOfMembers * 2];
		int index;
		for (int accountId : members) {
			index = members.indexOf(accountId);
			values[index] = accountId;
			values[index + 1] = groupId;
			if (index == numberOfMembers - 1) {
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
			query = "SELECT * FROM accounts_groups WHERE groupId = ?";
			ArrayList<Integer> accounts = new ArrayList<Integer>();
			try {
				accounts = (ArrayList<Integer>) this.jdbcTemplate.query(query,
						new Object[] { group.getId() },
						new RowMapper<Integer>() {

							@Override
							public Integer mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return rs.getInt("accountId");
							}
						});
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

			group.setAccounts(accounts);
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

		query = "SELECT * FROM accounts_groups WHERE groupId = ?";
		ArrayList<Integer> accounts = new ArrayList<Integer>();
		try {
			accounts = (ArrayList<Integer>) this.jdbcTemplate.query(query,
					new Object[] { group.getId() }, new RowMapper<Integer>() {

						@Override
						public Integer mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return rs.getInt("accountId");
						}
					});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		group.setAccounts(accounts);
		return group;
	}

}
