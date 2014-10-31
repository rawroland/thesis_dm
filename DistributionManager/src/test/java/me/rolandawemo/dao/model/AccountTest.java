package me.rolandawemo.dao.model;

import static org.junit.Assert.*;
import me.rolandawemo.dao.model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/account-test.xml" })
public class AccountTest {


	private Account account;

	@Before
	public void setUp() throws Exception {
		this.account = new Account(1, 3, 100000);
	}

	@After
	public void tearDown() throws Exception {
		this.account = null;
	}

	@Test
	public void accountCanAfford() {
		boolean canAfford = this.account.canAfford(50000);
		assertTrue("Account can afford transaction", canAfford);
	}
	
	@Test
	public void accountCannotAfford() {
		boolean cannotAfford = this.account.canAfford(150000);
		assertFalse("Account can afford transaction", cannotAfford);
	}
}
