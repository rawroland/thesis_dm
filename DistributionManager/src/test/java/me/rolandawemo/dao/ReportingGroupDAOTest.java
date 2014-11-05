package me.rolandawemo.dao;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import me.rolandawemo.dao.model.Client;
import me.rolandawemo.dao.model.ReportingGroup;

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
@ContextConfiguration(locations = { "classpath:*/groups-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ReportingGroupDAOTest {

	private IReportingGroupDAO reportingGroupDAO;
	private JdbcTemplate jdbcTemplate;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("groups-test.xml");
		reportingGroupDAO = context.getBean("reportingGroupDAO",
				IReportingGroupDAO.class);
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
	public void createReportingGroup() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int actual = jdbc.queryForInt("SELECT count(id) from groups where 1");
		int expected = 1;
		assertEquals(
				"Count of the entries in the db before insert is consistent.",
				expected, actual);
		actual = jdbc
				.queryForInt("SELECT count(id) from clients_groups where 1");
		expected = 2;
		assertEquals(
				"Count of the entries in the db before insert is consistent.",
				expected, actual);
		ArrayList<Integer> companyAccount = new ArrayList<Integer>();
		companyAccount.add(1);
		companyAccount.add(4);
		actual = this.reportingGroupDAO.create("Company", companyAccount);
		expected = 1;
		assertTrue(
				"Count of the entries in the db after insert is consistent.",
				actual>=expected);
		actual = jdbc.queryForInt("SELECT count(id) from groups where 1");
		expected = 2;
		assertEquals(
				"Count of the entries in the db after insert is consistent.",
				expected, actual);
		actual = jdbc
				.queryForInt("SELECT count(id) from clients_groups where 1");
		expected = 4;
		assertEquals(
				"Count of the entries in the db after insert is consistent.",
				expected, actual);
	}

	@DirtiesContext
	@Test
	public void createDuplicateReportingGroupFails() {
		ArrayList<Integer> companyAccount = new ArrayList<Integer>();
		companyAccount.add(1);
		int actual = this.reportingGroupDAO.create("Consumers", companyAccount);
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void getAllReportingGroups() {
		ArrayList<Integer> consumers = new ArrayList<Integer>();
		consumers.add(4);
		consumers.add(5);
		ReportingGroup group = new ReportingGroup(1,"Consumers");
		group.setClients(consumers);
		ArrayList<ReportingGroup> expected = new ArrayList<ReportingGroup>();
		expected.add(group);
		ArrayList<ReportingGroup> actual = this.reportingGroupDAO.getAll();
		assertEquals(expected, actual);
	}
	
	@Test
	public void getReportingGroupById() {
		ArrayList<Integer> consumers = new ArrayList<Integer>();
		consumers.add(4);
		consumers.add(5);
		ReportingGroup expected = new ReportingGroup(1,"Consumers");
		expected.setClients(consumers);
		ReportingGroup actual = this.reportingGroupDAO.getById(1);
		assertEquals(expected, actual);
	}
}
