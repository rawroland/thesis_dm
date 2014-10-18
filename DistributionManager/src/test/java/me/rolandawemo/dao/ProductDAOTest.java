package me.rolandawemo.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import me.rolandawemo.dao.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/product-test.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductDAOTest {

	private IProductDAO productDAO;
	private JdbcTemplate jdbcTemplate;
	private ClassPathXmlApplicationContext context;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("product-test.xml");
		productDAO = context.getBean("productDAO", IProductDAO.class);
	}

	@After
	public void tearDown() throws Exception {
		context.close();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@DirtiesContext
	@Test
	public void createProduct() {
		JdbcTemplate jdbc = context.getBean("jdbcTemplate", JdbcTemplate.class);
		int actual = jdbc.queryForInt("SELECT count(id) from products");
		int expected = 4;
		assertEquals(
				"Count of the entries in the db before insert is consistent.",
				expected, actual);
		int productAdded = this.productDAO.create("MTN 5000 Credit", 50, 1, 4);
		expected = 1;
		assertEquals("Product successfully added.", expected, productAdded);
		
	}
	
	@DirtiesContext
	@Test
	public void getProductById() {
		Product expected = new Product(1, 1, 5000, 200, "MTN Sim cards");
		Product actual = this.productDAO.getById(1);
		assertEquals("Correct product retrieved", expected, actual);
	}
	
	@DirtiesContext
	@Test
	public void getProducts() {
		Product productA = new Product(1, 1, 5000, 200, "MTN Sim cards");
		Product productB = new Product(2, 2, 5000, 200, "Orange Sim cards");
		ArrayList<Product> expected = new ArrayList<Product>();
		expected.add(productA);
		expected.add(productB);
		ArrayList<Product> actual = this.productDAO.getProducts("Sim");
		assertEquals("Correct number of products", expected.size(), actual.size());
		assertTrue("Contains correct Product A", expected.contains(actual.get(0)));
		assertTrue("Contains correct Product B", expected.contains(actual.get(1)));
	}
}
