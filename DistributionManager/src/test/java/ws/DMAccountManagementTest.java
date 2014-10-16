package ws;

import static org.junit.Assert.*;

import me.rolandawemo.dao.AccountDAO;
import me.rolandawemo.dao.model.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class DMAccountManagementTest {

	private DMAccountManagement dm;
	@Mock
	private AccountDAO accountDAO;
	@Mock
	private JdbcTemplate jdbc;
	private Account account;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMAccountManagement();
		this.accountDAO.setJdbcTemplate(this.jdbc);
		this.dm.setAccountDAO(this.accountDAO);
		when(this.accountDAO.create(4,0))
				.thenReturn(1);
		when(this.accountDAO.credit(3, 1000000)).thenReturn(1);
		when(this.accountDAO.debit(3, 1000000)).thenReturn(1);
		this.account = new Account(1,3,2000000);
		when(this.accountDAO.getAccount(3)).thenReturn(this.account);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}

	@Test
	public void addAccount() {
		boolean accountAdded = this.dm.addAccount(4);
		assertTrue("Account was successfully added", accountAdded);
		verify(this.accountDAO, times(1)).create(4,0);
	}
	
	@Test 
	public void creditAccount() {
		boolean accountCredited = this.dm.creditAccount(3, 1000000);
		assertTrue("Account was successfully credited", accountCredited);
		verify(this.accountDAO, times(1)).credit(3, 1000000);
	}
	
	@Test 
	public void debitAccount() {
		boolean accountDebited = this.dm.debitAccount(3, 1000000);
		assertTrue("Account was successfully debited", accountDebited);
		verify(this.accountDAO, times(1)).debit(3, 1000000);
	}
	
	@Test
	public void checkAccount() {
		int expected = 2000000;
		int actual = this.dm.checkAccount(3);
		assertEquals("Returned the right balance", expected, actual);
		verify(this.accountDAO, times(1)).getAccount(3);
	}

}
