package me.rolandawemo.dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.JdbcTemplate;

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
	public int create(int name, ArrayList<Integer> members) {
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

}
