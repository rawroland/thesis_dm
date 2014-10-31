package me.rolandawemo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import me.rolandawemo.dao.mappers.AccountRowMapper;
import me.rolandawemo.dao.mappers.ProductRowMapper;
import me.rolandawemo.dao.model.Account;
import me.rolandawemo.dao.model.Product;
import me.rolandawemo.dao.model.Transaction;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Concrete JDBC Employee DAO implementation
 * 
 * @author Roland Awemo
 * 
 */
public class TransactionDAO implements ITransactionDAO {

	private JdbcTemplate jdbcTemplate;

	public TransactionDAO(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	public TransactionDAO() {
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbcTemplate = jdbc;
	}

	@Override
	public int create(int accountId, int quantity, int productId, String type,
			int payment) {
		Transaction transaction = new Transaction();
		String prdtQuery = "SELECT * FROM products WHERE id=?";
		Product product = this.jdbcTemplate.queryForObject(prdtQuery,
				new Object[] { productId }, new ProductRowMapper());
		String acctQuery = "SELECT * FROM accounts WHERE id=?";
		Account account = this.jdbcTemplate.queryForObject(acctQuery,
				new Integer[] { accountId }, new AccountRowMapper());
		transaction.setAccount(account);
		transaction.setProduct(product);
		if (!transaction.feasible(quantity, payment, type)) {
			return 0;
		}
		int cost = product.calculateTotalPrice(quantity);
		//Debit account and credit if payment was made
		//Reduce quantity of product
		String insertTransaction = "INSERT INTO transactions(accountId,quantity,productId,cost,date,type) VALUES(?,?,?,?,?,?)";
		String dateStr = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		return this.jdbcTemplate.update(insertTransaction, new Object[] {accountId,quantity,productId,cost,dateStr,type});
	}
}
