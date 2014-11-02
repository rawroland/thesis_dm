package me.rolandawemo.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/transaction-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionDAOTest {

	private ITransactionDAO transactionDAO;
	private JdbcTemplate jdbcTemplate;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("transaction-test.xml");
		transactionDAO = context.getBean("transactionDAO", ITransactionDAO.class);
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
	public void createPurchaseTransactionFails() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		int actual = this.transactionDAO.create(1, 500, 1, "purchase", 0, new Date(timestamp));
		int expected = 0;
		assertEquals("Company does not have enough funds", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void createPurchaseTransactionPasses() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		int actual = this.transactionDAO.create(1, 50, 1, "purchase", 0, new Date(timestamp));
		int expected = 1;
		assertEquals("Purchase successful", expected, actual);
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int quantity = jdbc.queryForInt("SELECT quantity from products where id = " + 1);
		assertEquals("Quantity was increased", 250, quantity);
	}
	
	@DirtiesContext
	@Test
	public void createSaleTransactionFailsProductNotAvailable() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		int actual = this.transactionDAO.create(2, 500, 1, "sale", 0, new Date(timestamp));
		int expected = 0;
		assertEquals("Company does not have enough of the product", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void createSaleTransactionFailsCannotAfford() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		int actual = this.transactionDAO.create(3, 1, 1, "sale", 0, new Date(timestamp));
		int expected = 0;
		assertEquals("Consumer cannot afford", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void createSaleTransactionPassesWithPayment() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		int actual = this.transactionDAO.create(3, 1, 1, "sale", 6000, new Date(timestamp));
		int expected = 1;
		assertEquals("Consumer can afford after payment", expected, actual);
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int funds = jdbc.queryForInt("SELECT amount from accounts where id = " + 3);
		assertEquals("Account was credited then debited", 1400, funds);
		int quantity = jdbc.queryForInt("SELECT quantity from products where id = " + 1);
		assertEquals("Quantity was reduced", 199, quantity);
	}
	
	@DirtiesContext
	@Test
	public void createSaleTransactionPasses() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		int actual = this.transactionDAO.create(2, 50, 1, "sale", 0, new Date(timestamp));
		int expected = 1;
		assertEquals("Sale was successful", expected, actual);
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int funds = jdbc.queryForInt("SELECT amount from accounts where id = " + 2);
		assertEquals("Account was debited", 250000, funds);
		int quantity = jdbc.queryForInt("SELECT quantity from products where id = " + 1);
		assertEquals("Quantity was reduced", 150, quantity);
	}
}
