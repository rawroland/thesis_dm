package me.rolandawemo.dao;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.Employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/employee-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeDAOTest {

	private IEmployeeDAO employeeDAO;
	private JdbcTemplate jdbcTemplate;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("employee-test.xml");
		employeeDAO = context.getBean("employeeDAO", IEmployeeDAO.class);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Test
	public void findByUsernameAndPasswordGeneralManager() {
		Employee actual = employeeDAO.findByUsernameAndPassword("rolandawemo",
				"rawroro");
		assertEquals("Found User with username and password with correct id",
				1, actual.getId());
		assertEquals("The retrieved Employee is a general manager",
				"general manager", actual.getRole());
	}

	@Test
	public void findByUsernameAndPasswordCashier() {
		Employee actual = employeeDAO.findByUsernameAndPassword("janedoe",
				"janedoe");
		assertEquals("Found User with username and password with correct id",
				2, actual.getId());
		assertEquals("The retrieved Employee is a cashier", "cashier",
				actual.getRole());
	}

	@DirtiesContext
	@Test
	public void addEmployee() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int actual = jdbc.queryForInt("SELECT count(id) from employees");
		int expected = 2;
		assertEquals(
				"Count of the entries in the db before insert is consistent.",
				expected, actual);
		int employeeAdded = employeeDAO.create("John", "Doe", "johndoe",
				"cashier");
		assertEquals("Employee was successfully added.", 1, employeeAdded);
		actual = jdbc.queryForInt("SELECT count(id) from employees");
		expected = 3;
		assertEquals(
				"Count of the entries in the db after insert is consistent.",
				expected, actual);
	}

	@DirtiesContext
	@Test
	public void editEmployee() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		jdbc.queryForObject("select givenname from employees where id = 2",
				new RowMapper<Object>() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						assertEquals("Jane", rs.getString("givenname"));
						return null;
					}

				});
		int employeeUpdated = employeeDAO.update(2, "John", "Doe", "janedoe",
				"cashier");
		assertEquals("Employee updated", 1, employeeUpdated);
		jdbc.queryForObject("select givenname from employees where id = 2",
				new RowMapper<Object>() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						assertEquals("John", rs.getString("givenname"));
						return null;
					}

				});
	}

	@DirtiesContext
	@Test
	public void deleteEmployee() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int actual = jdbc.queryForInt("SELECT count(id) from employees");
		int expected = 2;
		assertEquals(
				"Count of the entries in the db before delete is consistent.",
				expected, actual);

		int employeeDeleted = this.employeeDAO.delete(2);
		assertEquals("Employee successfully deleted.", 1, employeeDeleted);

		actual = jdbc.queryForInt("SELECT count(id) from employees");
		expected = 1;
		assertEquals(
				"Count of the entries in the db after delete is consistent.",
				expected, actual);
	}

}
