package integration;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.dao.IAccountDAO;
import me.rolandawemo.dao.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ws.services.ProductManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/spring-test.xml" })
public class ProductServiceIT {

	private ClassPathXmlApplicationContext context;
	private ProductManagementService client;

	@Before
	public void setUp() {
		String SERVICE_LOCATION = "http://localhost:9001/dm/products?wsdl";
		String NAMESPACE_URI = "http://ws/";
		String NAMESPACE_PORT = "DMProductManagementPort";

		QName SERVICE_NAME = new QName(NAMESPACE_URI,
				"DMProductManagementService");
		Service service = null;

		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		client = service.getPort(ProductManagementService.class);
		context = new ClassPathXmlApplicationContext("spring-test.xml");
	}

	@After
	public void tearDown() {
		context.close();
	}

	@Test
	public void SearchAllProductsService() {
		ArrayList<Product> actual = this.client.searchProducts(null);
		System.out.println(actual.toString());
		assertEquals(4, actual.size());
	}

}
