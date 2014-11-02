package ws;

import static org.junit.Assert.*;

import java.util.ArrayList;

import me.rolandawemo.dao.ProductDAO;
import me.rolandawemo.dao.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class DMProductManagementTest {

	private DMProductManagement dm;
	@Mock
	private ProductDAO productDAO;
	@Mock
	private JdbcTemplate jdbc;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.dm = new DMProductManagement();
		this.productDAO.setJdbcTemplate(this.jdbc);
		this.dm.setProductDAO(this.productDAO);
	}

	@After
	public void tearDown() throws Exception {
		this.dm = null;
	}

	@Test
	public void addProduct() {
		when(this.productDAO.create("MTN Credit Card 10000",1,8500))
		.thenReturn(1);
		boolean productAdded = this.dm.addProduct("MTN Credit Card 10000",1,8500);
		assertTrue("Product was successfully added", productAdded);
		verify(this.productDAO, times(1)).create("MTN Credit Card 10000",1,8500);
	}
	
	@Test
	public void searchProducts() {
		Product productA = new Product(1, 1, 5000, 200, "MTN Sim cards");
		Product productB = new Product(2, 2, 5000, 200, "Orange Sim cards");
		ArrayList<Product> expected = new ArrayList<Product>();
		expected.add(productA);
		expected.add(productB);
		when(this.productDAO.getProducts("Sim")).thenReturn(expected);
		ArrayList<Product> actual = this.dm.searchProducts("Sim");
		assertEquals("Correct products retrieved", expected, actual);
		verify(this.productDAO, times(1)).getProducts("Sim");
	}
	
	@Test
	public void searchAllProducts() {
		Product productA = new Product(1, 1, 5000, 200, "MTN Sim cards");
		Product productB = new Product(2, 2, 5000, 200, "Orange Sim cards");
		Product productC = new Product(3, 2, 2000, 100, "MTN 2500 Credit");
		Product productD = new Product(4, 3, 2000, 100, "Orange 2500 Credit");
		ArrayList<Product> expected = new ArrayList<Product>();
		expected.add(productA);
		expected.add(productB);
		expected.add(productC);
		expected.add(productD);
		when(this.productDAO.getAllProducts()).thenReturn(expected);
		ArrayList<Product> actual = this.dm.searchProducts(null);
		assertEquals("Correct products retrieved", expected, actual);
		verify(this.productDAO, times(1)).getAllProducts();
	}
	
	@Test 
	public void checkProduct() {
		Product product = new Product(1, 1, 5000, 200, "MTN Sim cards");
		when(this.productDAO.getById(1)).thenReturn(product);
		int expected = product.getQuantity();
		int actual = this.dm.checkProduct(1);
		assertEquals("Correct quantity retrieved", expected, actual);
		verify(this.productDAO, times(1)).getById(1);
	}
}
