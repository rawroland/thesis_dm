package me.rolandawemo.dao;

import static org.apache.commons.lang.StringUtils.leftPad;

import java.util.ArrayList;

import me.rolandawemo.dao.mappers.EmployeeRowMapper;
import me.rolandawemo.dao.model.Employee;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Concrete JDBC Employee DAO implementation
 * 
 * @author Roland Awemo
 * 
 */
public class EmployeeDAO implements IEmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public EmployeeDAO() {
	}

	@Override
	public Employee findByUsernameAndPassword(String username, String password) {
		String query = "SELECT Employee.id, Employee.givenname, Employee.surname, Employee.role, Employee.username FROM employees as Employee WHERE Employee.username = ? and Employee.password = ? LIMIT 1";
		Employee emp = new Employee();
		try {
			emp = this.jdbcTemplate.queryForObject(query, new String[] {
					username, password }, new EmployeeRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return emp;
	}

	@Override
	public int create(String givenname, String surname, String username,
			String role) {
		String query = "INSERT INTO employees (givenname, surname, username, role, password) VALUES(?, ?, ?, ?, ?)";
		int employeeAdded = 0;
		try {
			employeeAdded = this.jdbcTemplate.update(
					query,
					new Object[] { givenname, surname, username, role,
							this.generatePassword(6) });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return employeeAdded;
	}

	@Override
	public int update(int id, String givenname, String surname,
			String username, String role) {
		String query = "UPDATE employees SET givenname=?, surname=?, username=?, role=? WHERE id=?";
		int employeeEdited = 0;
		try {
			employeeEdited = this.jdbcTemplate.update(query, new Object[] {
					givenname, surname, username, role, id });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return employeeEdited;
	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM employees WHERE id=?";
		int employeeDeleted = 0;
		try {
			employeeDeleted = this.jdbcTemplate.update(query,
					new Object[] { id });
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return employeeDeleted;
	}

	@Override
	public ArrayList<Employee> getAll() {
		String query = "SELECT Employee.id, Employee.givenname, Employee.surname, Employee.role, Employee.username FROM employees as Employee WHERE 1";
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			employees = (ArrayList<Employee>) this.jdbcTemplate.query(query, new EmployeeRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee getById(int id) {
		String query = "SELECT Employee.id, Employee.givenname, Employee.surname, Employee.role, Employee.username FROM employees as Employee WHERE Employee.id = ?";
		Employee emp = new Employee();
		try {
			emp = this.jdbcTemplate.queryForObject(query, new Integer[] {id}, new EmployeeRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}

		return emp;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	public EmployeeDAO(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	public String generatePassword(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = length; i > 0; i -= 12) {
			int n = Math.min(12, Math.abs(i));
			sb.append(leftPad(Long.toString(
					Math.round(Math.random() * Math.pow(36, n)), 36), n, '0'));
		}
		return sb.toString();
	}

}
