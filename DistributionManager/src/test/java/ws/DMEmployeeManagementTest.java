package ws;

import static org.junit.Assert.*;

import java.util.ArrayList;

import me.rolandawemo.dao.EmployeeDAO;
import me.rolandawemo.dao.model.Employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class DMEmployeeManagementTest {

	private DMEmployeeManagement dm;
	@Mock
	private EmployeeDAO employeeDAO;
	@Mock
	private JdbcTemplate jdbc;
	private Employee gm;
	private Employee cashier1;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMEmployeeManagement();
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
		when(this.employeeDAO.delete(2)).thenReturn(1);
		ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "Roland", "Awemo", "general manager", "rolandawemo"));
		employees.add(new Employee(1, "Jane", "Doe", "cashier", "janedoe"));
		when(this.employeeDAO.getAll()).thenReturn(employees);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}
	
	@Test
	public void addedEmployee() {
		boolean actual = this.dm.addEmployee("John", "Doe", "johndoe", "cashier");
		boolean expected = true;
		assertEquals("Employee successfully added", expected, actual);
	}
	
	@Test
	public void editedEmployee() {
		boolean actual = this.dm.editEmployee(2 ,"John", "Doe", "johndoe", "cashier");
		boolean expected = true;
		assertEquals("Employee successfully edited", expected, actual);
	}
	
	@Test
	public void deletedEmployee() {
		boolean actual = this.dm.deleteEmployee(2);
		boolean expected = true;
		assertEquals("Employee successfully deleted", expected, actual);
	}
	
	@Test
	public void getAllEmployees() {
		ArrayList<Employee> employees = this.dm.searchClients();
		int expected = 2;
		assertEquals("Returning correct number of employees", expected, employees.size());
	}

}
