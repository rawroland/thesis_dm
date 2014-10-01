package me.rolandawemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.Employee;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee>{
	public Employee mapRow(ResultSet rs, int row) {
		Employee employee = new Employee();
		try {
			employee.setId(rs.getInt("id"));
			employee.setGivenname(rs.getString("givenname"));
			employee.setSurname(rs.getString("surname"));
			employee.setUsername(rs.getString("username"));
			employee.setRole(rs.getString("role"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
