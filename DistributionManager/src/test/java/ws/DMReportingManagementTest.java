package ws;

import static org.junit.Assert.*;

import java.util.ArrayList;

import me.rolandawemo.dao.ReportingGroupDAO;
import me.rolandawemo.dao.TransactionDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class DMReportingManagementTest {

	private DMReportingManagement dm;
	@Mock
	private TransactionDAO transactionDAO;
	@Mock
	private ReportingGroupDAO reportingGroupDAO;
	private JdbcTemplate jdbc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMReportingManagement();
		this.transactionDAO.setJdbcTemplate(this.jdbc);
		this.reportingGroupDAO.setJdbcTemplate(this.jdbc);
		this.dm.setTransactionDAO(transactionDAO);
		this.dm.setReportingGroupDAO(reportingGroupDAO);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}

	@Test
	public void createGroupSuccessful() {
		ArrayList<Integer> consumers = new ArrayList<Integer>();
		consumers.add(2);
		consumers.add(3);
		when(this.reportingGroupDAO.create("Consumers", consumers)).thenReturn(1);
		assertTrue(this.dm.createReportingGroup("Consumers", consumers));
		verify(this.reportingGroupDAO, times(1)).create("Consumers", consumers);
	}
	
	@Test
	public void createDuplicateGroupFails() {
		ArrayList<Integer> consumers = new ArrayList<Integer>();
		consumers.add(2);
		consumers.add(3);
		when(this.reportingGroupDAO.create("Consumers", consumers)).thenReturn(1,0);
		assertTrue(this.dm.createReportingGroup("Consumers", consumers));
		assertFalse(this.dm.createReportingGroup("Consumers", consumers));
		verify(this.reportingGroupDAO, times(2)).create("Consumers", consumers);
	}
}
