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
		Employee generalManager = new Employee(1, "Roland", "Awemo", "general manager", "rolandawemo");
		employees.add(generalManager);
		Employee cashier = new Employee(2, "Jane", "Doe", "cashier", "janedoe");
		employees.add(cashier);
		when(this.employeeDAO.getAll()).thenReturn(employees);
		when(this.employeeDAO.getById(2)).thenReturn(cashier);
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
	public void searchAllEmployees() {
		ArrayList<Employee> employees = this.dm.searchEmployees();
		int expected = 2;
		assertEquals("Returning correct number of employees", expected, employees.size());
	}
	
	@Test
	public void searchEmployeebyId() {
		ArrayList<Employee> employees = this.dm.searchEmployees(2);
		int expected = 1;
		assertEquals("Found only one employee with this id.", expected, employees.size());
		String expectedUsername = "janedoe";
		assertEquals("Retrieved the correct employee", expectedUsername, employees.get(0).getUsername());
	}

}
