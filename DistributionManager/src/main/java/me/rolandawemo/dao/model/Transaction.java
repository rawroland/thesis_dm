package me.rolandawemo.dao.model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Transaction")
public class Transaction {

	private int id;
	private int accountId;
	private int quantity;
	private int productId;
	private int cost;
	private String date;
	private String type;
	private Account account;
	private Product product;
	
	public Transaction(int id, int accountId, int quantity, int productId,
			int cost, String date, String type, Account account, Product product) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.quantity = quantity;
		this.productId = productId;
		this.cost = cost;
		this.date = date;
		this.type = type;
		this.account = account;
		this.product = product;
	}

	public Transaction() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@XmlElement(name="Account")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@XmlElement(name="Product")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Check if a transaction is feasible.
	 * @param int quantity Product Quantity
	 * @param payment
	 * @param type
	 * @return
	 */
	public boolean feasible(int quantity, int payment, String type) {
		boolean productIsAvailable = true;
		if ("sale" == type) {
			productIsAvailable = this.product.available(quantity);
		}
		int totalPrice = this.product.calculateTotalPrice(quantity);
		boolean accountCanAfford = this.account.canAfford(totalPrice, payment);
		return productIsAvailable && accountCanAfford;
	}
}
