package ws;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	public void savePurchaseSuccessful() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01")
				.getTime();
		when(this.transactionDAO.create(1, 50, 1, "purchase", 0, new Date(timestamp)))
				.thenReturn(1);
		boolean actual = this.dm.saveTransaction(1, 50, 1, "purchase", 0, "2014-11-01");
		assertTrue("Purchase was successfully saved", actual);
	}

	@Test
	public void savePurchaseFailed() throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01")
				.getTime();
		when(this.transactionDAO.create(1, 500, 1, "purchase", 0, new Date(timestamp)))
				.thenReturn(0);
		boolean actual = this.dm.saveTransaction(1, 500, 1, "purchase", 0, "2014-11-01");
		assertFalse("Purchase was not saved", actual);
	}

	@Test
	public void saveSaleFailed()  throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		when(this.transactionDAO.create(2, 500, 1, "sale", 0, new Date(timestamp)))
				.thenReturn(0);
		boolean actual = this.dm.saveTransaction(2, 500, 1, "sale", 0, "2014-11-01");
		assertFalse("Purchase was successfully saved", actual);
	}

	@Test
	public void saveSaleSuccessful()  throws ParseException {
		long timestamp = new SimpleDateFormat("yyyy-MM-dd").parse("2014-11-01").getTime();
		when(this.transactionDAO.create(2, 50, 1, "sale", 0, new Date(timestamp))).thenReturn(
				1);
		boolean actual = this.dm.saveTransaction(2, 50, 1, "sale", 0, "2014-11-01");
		assertTrue("Purchase was not saved", actual);
	}

}
