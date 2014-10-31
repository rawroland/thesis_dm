package me.rolandawemo.dao;

import static org.junit.Assert.assertEquals;

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
	public void createPurchaseTransactionFails() {
		int actual = this.transactionDAO.create(1, 500, 1, "purchase", 0);
		int expected = 0;
		assertEquals("Company does not have enough funds", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void createPurchaseTransactionPasses() {
		int actual = this.transactionDAO.create(1, 50, 1, "purchase", 0);
		int expected = 1;
		assertEquals("Purchase successful", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void createSaleTransactionFails() {
		int actual = this.transactionDAO.create(2, 500, 1, "sale", 0);
		int expected = 0;
		assertEquals("Company does not have enough of the product", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void createSaleTransactionPasses() {
		int actual = this.transactionDAO.create(2, 50, 1, "sale", 0);
		int expected = 1;
		assertEquals("Sale was successful", expected, actual);
	}
}
