package ws;

import static org.junit.Assert.*;

import me.rolandawemo.dao.EmployeeDAO;
import me.rolandawemo.dao.model.Employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;
import ws.DMAuthentication;

public class DMAuthenticationTest {

	private DMAuthentication dm;
	@Mock
	private EmployeeDAO employeeDAO;
	@Mock
	private JdbcTemplate jdbc;
	private Employee gm;
	private Employee cashier1;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMAuthentication();
		this.employeeDAO.setJdbcTemplate(this.jdbc);
		this.dm.setEmployeeDAO(this.employeeDAO);
		this.gm = new Employee(1, "Roland", "Awemo", "General Manager",
				"rolandawemo");
		this.gm.setPassword("rawroro");
		this.cashier1 = new Employee(1, "John", "Doe", "Cashier", "johndoe");
		this.cashier1.setPassword("jdoe");
		when(
				this.employeeDAO.findByUsernameAndPassword("rolandawemo",
						"rawroro")).thenReturn(this.gm);
		when(this.employeeDAO.create("John", "Doe", "johndoe", "cashier")).thenReturn(1);
		when(this.employeeDAO.update(2 ,"John", "Doe", "johndoe", "cashier")).thenReturn(1);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}

	@Test
	public void authenticateSuccessfull() {
		Employee employee = this.dm.authenticate("rolandawemo", "rawroro");
		assertEquals("Authenticated successfully", 1, employee.getId());
	}
}
