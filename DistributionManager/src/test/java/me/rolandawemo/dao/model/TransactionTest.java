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
public class TransactionTest {

	private Transaction transaction;
	private Product product;
	private Account accountPurchase;
	private Account accountSale;

	@Before
	public void setUp() throws Exception {
		this.product = new Product(1, 1, 5000, 200, "MTN Sim cards");
		this.accountPurchase = new Account(1, 1, 10000000);
		this.accountSale = new Account(2, 2, 1000000);
	}

	@After
	public void tearDown() throws Exception {
		this.accountPurchase = null;
		this.accountSale = null;
		this.product = null;
		this.transaction = null;
	}
	
	@Test
	public void purchaseIsFeasible() {
		this.transaction = new Transaction();
		this.transaction.setAccount(this.accountPurchase);
		this.transaction.setProduct(this.product);
		this.transaction.setType("purchase");
		boolean feasible = this.transaction.feasible(50, 0, "purchase");
		assertTrue("Purchase is feasible", feasible);
	}
	
	@Test
	public void purchaseIsNotFeasible() {
		this.transaction = new Transaction();
		this.transaction.setAccount(this.accountPurchase);
		this.transaction.setProduct(this.product);
		this.transaction.setType("purchase");
		boolean feasible = this.transaction.feasible(100000, 0, "purchase");
		assertFalse("Purchase is not feasible", feasible);
	}
	
	@Test
	public void saleIsNotFeasible() {
		this.transaction = new Transaction();
		this.transaction.setAccount(this.accountSale);
		this.transaction.setProduct(this.product);
		this.transaction.setType("sale");
		boolean feasible = this.transaction.feasible(1000, 0, "sale");
		assertFalse("Sale is not feasible, account cannot cover cost", feasible);
	}
	
	@Test
	public void saleIsFeasible() {
		this.transaction = new Transaction();
		this.transaction.setAccount(this.accountSale);
		this.transaction.setProduct(this.product);
		this.transaction.setType("sale");
		boolean feasible = this.transaction.feasible(100, 0, "sale");
		assertTrue("Sale is not feasible account covers cost", feasible);
		this.product.setQuantity(1000);
		feasible = this.transaction.feasible(1000, 4000000, "sale");
		assertTrue("Sale is not feasible account covers cost with payment made", feasible);
	}
}
