package integration;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.dao.model.Client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ws.services.ClientManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/spring-test.xml" })
public class ClientServiceIT {

	private ClassPathXmlApplicationContext context;
	private ClientManagementService client;

	@Before
	public void setUp() {
		String SERVICE_LOCATION = "http://localhost:9001/dm/clients?wsdl";
		String NAMESPACE_URI = "http://ws/";
		String NAMESPACE_PORT = "DMClientManagementPort";

		QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMClientManagementService");
		Service service = null;

		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(ClientManagementService.class);
		context = new ClassPathXmlApplicationContext("spring-test.xml");
	}

	@After
	public void tearDown() {
		context.close();
	}

	@Test
	public void SearchAllClientsService() {
		ArrayList<Client> actual = this.client.searchClients("", 0, "");
		assertEquals(5, actual.size());
	}
	
	@Test
	public void SearchAllClientsByNameService() {
		ArrayList<Client> actual = this.client.searchClients("John", 0, "");
		assertEquals(2, actual.size());
	}
	
	@Test
	public void SearchAllClientsByIdService() {
		ArrayList<Client> actual = this.client.searchClients("", 1, "");
		assertEquals("company", actual.get(0).getType());
	}
	
	@Test
	/*
	 * @todo Improve
	 */
	public void SearchAllClientsByTypeService() {
		ArrayList<Client> actual = this.client.searchClients("", 0, "supplier");
		assertTrue(2<=actual.size());
	}
	
	@DirtiesContext
	@Test
	public void AddClientService() {
		assertTrue(this.client.addClient("Mr", "Roland", "Awemo", "MTN",
				"supplier"));
	}
	
	@DirtiesContext
	@Test
	public void AddClientUniqueService() {
		assertFalse(this.client.addClient("Mr", "Roland", "Awemo", "MTN",
				"supplier"));
	}

}
