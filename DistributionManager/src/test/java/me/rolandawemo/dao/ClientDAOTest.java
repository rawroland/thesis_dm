package me.rolandawemo.dao;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import me.rolandawemo.dao.model.Client;

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
@ContextConfiguration(locations = { "classpath:*/client-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientDAOTest {

	private IClientDAO clientDAO;
	private JdbcTemplate jdbcTemplate;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("client-test.xml");
		clientDAO = context.getBean("clientDAO", IClientDAO.class);
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


	@DirtiesContext
	@Test
	public void addClient() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int actual = jdbc.queryForInt("SELECT count(id) from clients");
		int expected = 4;
		assertEquals(
				"Count of the entries in the db before insert is consistent.",
				expected, actual);
		int cllientAdded = clientDAO.create("Mr", "John", "Doe", "Sosidef",
				"consumer");
		assertEquals("Client was successfully added.", 1, cllientAdded);
		actual = jdbc.queryForInt("SELECT count(id) from clients");
		expected = 5;
		assertEquals(
				"Count of the entries in the db after insert is consistent.",
				expected, actual);
	}

	@DirtiesContext
	@Test
	public void editClient() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		jdbc.queryForObject("select firstName from clients where id = 2",
				new RowMapper<Object>() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						assertEquals("Jane", rs.getString("firstName"));
						return null;
					}

				});
		int employeeUpdated = clientDAO.update(2, "Mr", "Johnny", "Doe", "New Company",
				"consumer");
		assertEquals("Client updated", 1, employeeUpdated);
		jdbc.queryForObject("select firstName from clients where id = 2",
				new RowMapper<Object>() {

					@Override
					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						assertEquals("Johnny", rs.getString("firstName"));
						return null;
					}

				});
	}

	@DirtiesContext
	@Test
	public void deleteClient() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int actual = jdbc.queryForInt("SELECT count(id) from clients");
		int expected = 4;
		assertEquals(
				"Count of the entries in the db before delete is consistent.",
				expected, actual);

		int employeeDeleted = this.clientDAO.delete(2);
		assertEquals("Client successfully deleted.", 1, employeeDeleted);

		actual = jdbc.queryForInt("SELECT count(id) from clients");
		expected = 3;
		assertEquals(
				"Count of the entries in the db after delete is consistent.",
				expected, actual);
	}
	
	@DirtiesContext
	@Test 
	/**
	 * @todo Equals or contains
	 */
	public void getAllClients() {
		ArrayList<Client> clients = this.clientDAO.getAll();
		assertEquals("Correct number of clients", 4, clients.size());
	}
	
	@DirtiesContext
	@Test 
	public void getClientById() {
		String expected = "Jane";
		Client client = this.clientDAO.getById(2);
		assertEquals("Correct firstname found", expected, client.getFirstName());
	}
	
	@DirtiesContext
	@Test
	public void searchClients() {
		int expected = 3;
		ArrayList<Client> clients = this.clientDAO.getClients("J");
		assertEquals("Correct number of clients returned", expected, clients.size());
	}

}
