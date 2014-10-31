package me.rolandawemo.dao;

import static org.junit.Assert.*;
import me.rolandawemo.dao.model.Account;

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
@ContextConfiguration(locations = { "classpath:*/account-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountDAOTest {

	private IAccountDAO accountDAO;
	private JdbcTemplate jdbcTemplate;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("account-test.xml");
		accountDAO = context.getBean("accountDAO", IAccountDAO.class);
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
	public void createAccount() {
		int expected = 1;
		int actual = this.accountDAO.create(4, 0);
		assertEquals("Account successfully created", expected, actual);
	}
	
	@Test
	public void creditAccount() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int expected = 2000000;
		int actual =  jdbc.queryForInt("SELECT ammount FROM accounts WHERE clientId=1");
		assertEquals("Ammount consistent before crediting", expected, actual);
		actual = this.accountDAO.credit(1, 1000000);
		expected = 1;
		assertEquals("Account successfully updated", expected, actual);
		expected = 3000000;
		actual =  jdbc.queryForInt("SELECT ammount FROM accounts WHERE clientId=1");
		assertEquals("Ammount consistent after crediting", expected, actual);
	}
	
	@Test
	public void debitAccount() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int expected = 2000000;
		int actual =  jdbc.queryForInt("SELECT ammount FROM accounts WHERE clientId=1");
		assertEquals("Ammount consistent before debiting", expected, actual);
		actual = this.accountDAO.debit(1, 1000000);
		expected = 1;
		assertEquals("Account successfully updated", expected, actual);
		expected = 1000000;
		actual =  jdbc.queryForInt("SELECT ammount FROM accounts WHERE clientId=1");
		assertEquals("Ammount consistent after debiting", expected, actual);
	}
	
	@Test
	public void getAccount() {
		Account expected = new Account(1, 1, 2000000);
		Account actual = this.accountDAO.getAccount(1);
		assertTrue("Correct account details retrieved", expected.equals(actual));
	}

}
