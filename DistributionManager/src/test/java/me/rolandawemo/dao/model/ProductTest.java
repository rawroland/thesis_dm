package me.rolandawemo.dao.model;

import static org.junit.Assert.*;
import me.rolandawemo.dao.model.Product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:*/product-test.xml" })
public class ProductTest {


	private Product product;

	@Before
	public void setUp() throws Exception {
		this.product = new Product(1, 1, 5000, 200, "MTN Sim cards");
	}

	@After
	public void tearDown() throws Exception {
		this.product = null;
	}

	@Test
	public void productIsAvalable() {
		boolean available = this.product.available(100);
		assertTrue("Product is available", available);
	}
	
	@Test
	public void productIsNotAvailable() {
		boolean available = this.product.available(300);
		assertFalse("Product is not available", available);
	}
	
	@Test
	public void calculateProductPrice() {
		int actual = this.product.calculateTotalPrice(0);
		int expected = 0;
		assertEquals("Calculation for 0 quantity is consisten", expected, actual);
		actual = this.product.calculateTotalPrice(1);
		expected = 5000;
		assertEquals("Calculation for 1 quantity is consisten", expected, actual);
		actual = this.product.calculateTotalPrice(5);
		expected = 25000;
		assertEquals("Calculation for 5 quantity is consisten", expected, actual);
		actual = this.product.calculateTotalPrice(100);
		expected = 500000;
		assertEquals("Calculation for 100 quantity is consisten", expected, actual);
	}
}
