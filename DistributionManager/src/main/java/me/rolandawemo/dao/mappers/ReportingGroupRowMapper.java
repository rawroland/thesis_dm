package me.rolandawemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.ReportingGroup;

import org.springframework.jdbc.core.RowMapper;

public class ReportingGroupRowMapper implements RowMapper<ReportingGroup> {

	@Override
	public ReportingGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
		ReportingGroup group = new ReportingGroup();
		try{
			group.setId(rs.getInt("id"));
			group.setName(rs.getString("name"));
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return group;
	}

}
