package ws;

import static org.junit.Assert.*;

import me.rolandawemo.dao.TransactionDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class DMTransactionManagementTest {

	private DMTransactionManagement dm;
	@Mock
	private TransactionDAO transactionDAO;
	private JdbcTemplate jdbc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMTransactionManagement();
		this.transactionDAO.setJdbcTemplate(this.jdbc);
		this.dm.setTransactionDAO(transactionDAO);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}
	
	@Test
	public void savePurchaseSuccessful() {
		when(this.transactionDAO.create(1, 50, 1, "purchase", 0)).thenReturn(1);
		boolean actual = this.dm.saveTransaction(1, 50, 1, "purchase", 0);
		assertTrue("Purchase was successfully saved", actual);
	}
	
	@Test
	public void savePurchaseFailed() {
		when(this.transactionDAO.create(1, 500, 1, "purchase", 0)).thenReturn(0);
		boolean actual = this.dm.saveTransaction(1, 500, 1, "purchase", 0);
		assertFalse("Purchase was not saved", actual);
	}
	
	@Test
	public void saveSaleFailed() {
		when(this.transactionDAO.create(2, 500, 1, "sale", 0)).thenReturn(0);
		boolean actual = this.dm.saveTransaction(2, 500, 1, "sale", 0);
		assertFalse("Purchase was successfully saved", actual);
	}
	
	@Test
	public void saveSaleSuccessful() {
		when(this.transactionDAO.create(2, 50, 1, "sale", 0)).thenReturn(1);
		boolean actual = this.dm.saveTransaction(2, 50, 1, "sale", 0);
		assertTrue("Purchase was not saved", actual);
	}

}
