package ws;

import static org.junit.Assert.*;

import java.util.ArrayList;

import me.rolandawemo.dao.ClientDAO;
import me.rolandawemo.dao.model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class DMClientManagementTest {

	private DMClientManagement dm;
	@Mock
	private ClientDAO clientDAO;
	@Mock
	private JdbcTemplate jdbc;
	private Client supplier;
	private Client consumer;
	private ArrayList<Client> clients;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMClientManagement();
		this.clientDAO.setJdbcTemplate(this.jdbc);
		this.dm.setClientDAO(this.clientDAO);
		this.supplier = new Client(1, "Roland", "Awemo", "Mr", "MTN",
				"supplier");
		this.consumer = new Client(2, "Jane", "Doe", "Mrs", "Sodisef",
				"consumer");
		this.clients = new ArrayList<Client>();
		this.clients.add(supplier);
		this.clients.add(consumer);
		when(this.clientDAO.create("Mr", "Roland", "Awemo", "MTN", "supplier"))
				.thenReturn(1);
		when(this.clientDAO.update(1, "Mr", "Roland", "Awemo", "MTN",
						"supplier")).thenReturn(1);
		when(this.clientDAO.delete(1)).thenReturn(1);
		when(this.clientDAO.getAll()).thenReturn(clients);
		when(this.clientDAO.getById(1)).thenReturn(supplier);
		ArrayList<Client> supplierList = new ArrayList<Client>();
		supplierList.add(supplier);
		when(this.clientDAO.getClients("Rol")).thenReturn(supplierList);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}

	@Test
	public void addedClient() {
		boolean actual = this.dm.addClient("Mr", "Roland", "Awemo", "MTN",
				"supplier");
		boolean expected = true;
		verify(this.clientDAO, times(1)).create("Mr", "Roland", "Awemo", "MTN", "supplier");
		assertEquals("Client successfully added", expected, actual);
	}

	@Test
	public void editedClient() {
		boolean actual = this.dm.editClient(1, "Mr", "Roland", "Awemo", "MTN", "supplier");
		boolean expected = true;
		verify(this.clientDAO, times(1)).update(1, "Mr", "Roland", "Awemo", "MTN", "supplier");
		assertEquals("Client successfully edited", expected, actual);
	}

	@Test
	public void deletedClient() {
		boolean actual = this.dm.deleteClient(1);
		boolean expected = true;
		verify(this.clientDAO, times(1)).delete(1);
		assertEquals("Client successfully deleted", expected, actual);
	}

	@Test
	public void searchAllClients() {
		ArrayList<Client> clients = this.dm.getAllClients();
		int expected = 2;
		verify(this.clientDAO, times(1)).getAll();
		assertEquals("Returning correct number of employees", expected, clients.size());
	}

	@Test
	public void searchClientsbyId() {
		Client client = this.dm.searchClientsById(1);
		String expectedFirstName = "Roland";
		verify(this.clientDAO, times(1)).getById(1);
		assertEquals("Retrieved the correct employee", expectedFirstName,
				client.getFirstName());
	}
	
	@Test
	public void searchClients() {
		ArrayList<Client> clients = this.dm.searchClients("Rol");
		int expectedCount = 1;
		assertEquals("Return correct number of clients", expectedCount, clients.size());
		Client client = clients.get(0);
		String expectedLastName = "Awemo";
		assertEquals("Correct client returned", expectedLastName,client.getLastName());
	}

}