package me.rolandawemo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.mappers.AccountRowMapper;
import me.rolandawemo.dao.mappers.ClientRowMapper;
import me.rolandawemo.dao.mappers.ProductRowMapper;
import me.rolandawemo.dao.mappers.TransactionRowMapper;
import me.rolandawemo.dao.model.Account;
import me.rolandawemo.dao.model.Client;
import me.rolandawemo.dao.model.Product;
import me.rolandawemo.dao.model.Transaction;
import me.rolandawemo.dao.queries.TransactionQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
			int payment, Date date) {

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
		String creditedQuery = "UPDATE accounts SET amount = amount + ? - ? WHERE id = ?";
		int transactionAmount = product.calculateTotalPrice(quantity);
		this.jdbcTemplate.update(creditedQuery, new Object[] { payment,
				transactionAmount, accountId });

		int newQuantity = product.getQuantity();
		if ("purchase".equals(type)) {
			newQuantity = product.getQuantity() + quantity;
		} else {
			newQuantity = product.getQuantity() - quantity;
			//Sale
			creditedQuery = "UPDATE accounts SET amount = amount + ? WHERE id = ?";
			transactionAmount = product.calculateTotalPrice(quantity);
			this.jdbcTemplate.update(creditedQuery, new Object[] {
					transactionAmount, 1 });
		}
		String quantityReducedQuery = "UPDATE products SET quantity = ? WHERE id = ?";
		this.jdbcTemplate.update(quantityReducedQuery, new Object[] {
				newQuantity, productId });
		String insertTransaction = "INSERT INTO transactions(accountId,quantity,productId,cost,date,type) VALUES(?,?,?,?,?,?)";

		String dateStr = new SimpleDateFormat("YYYY-MM-dd").format(date);
		return this.jdbcTemplate.update(insertTransaction, new Object[] {
				accountId, quantity, productId, cost, dateStr, type });
	}

	@Override
	public ArrayList<Transaction> getTransactions(TransactionQuery query) {
		String select = "SELECT SUM(Transaction.quantity) as transactionQuantity, "
				+ "SUM(Transaction.cost) as transactionPayment, Transaction.date as transactionDate, "
				+ "Transaction.type as transactionType, Account.id as accountId, Account.amount as budget, "
				+ "Client.id as clientId, Client.prefix as clientPrefix, Client.firstName as clientFirstName, "
				+ "Client.lastName as clientLastName, Client.company as clientCompany, Client.type as clientType, "
				+ "Product.id as productId, Product.name as productName, Product.quantity as productQuantity, "
				+ "product.price as productPrice , Supplier.id as supplierId, Supplier.prefix as supplierPrefix, "
				+ "Supplier.firstName as supplierFirstName, Supplier.lastName as supplierLastName, "
				+ "Supplier.company as supplierCompany, Supplier.type as supplierType FROM transactions as Transaction ";
		String joins = "LEFT JOIN accounts as Account ON Transaction.accountId = Account.id "
				+ "LEFT JOIN clients as Client ON Account.clientId = Client.id "
				+ "LEFT JOIN products as Product ON Transaction.productId = Product.id "
				+ "LEFT JOIN clients as Supplier ON Product.clientId = Supplier.id ";
		String where = "WHERE 1 ";
		String group = "GROUP BY accountId, productId, transactionDate ";

		String fromDate = query.getFromDate();
		if (null != fromDate && !fromDate.isEmpty()) {
			where = where + " AND Transaction.date >= '" + fromDate + "' ";
		}

		String toDate = query.getToDate();
		if (null != toDate && !toDate.isEmpty()) {
			where = where + " AND Transaction.date <= '" + toDate + "' ";
		}

		String type = query.getType();
		if (null != type && !type.isEmpty()) {
			where = where + " AND Transaction.type = '" + type + "' ";
		}
		
		ArrayList<Integer> accounts = query.getAccounts();
		if (null != accounts && !accounts.isEmpty()) {
			String accountList = "(";
			for (Iterator<Integer> iterator = accounts.iterator(); iterator.hasNext();) {
				Integer account = iterator.next();
				accountList = accountList + account;
				if (iterator.hasNext()) {
					accountList = accountList + ",";
				} else {
					accountList = accountList + ")";
				}
			}			
			where = where + " AND Transaction.accountId IN " + accountList + " ";
		}
		
		int groupId = query.getReportingGroupId();
		if(0 < groupId) {
			String membersQuery = "SELECT * FROM clients_groups WHERE groupId = ?";
			ArrayList<Integer> clients = new ArrayList<Integer>();
			try {
				clients = (ArrayList<Integer>) this.jdbcTemplate.query(membersQuery,
						new Object[] { groupId }, new RowMapper<Integer>() {

							@Override
							public Integer mapRow(ResultSet rs, int rowNum)
									throws SQLException {
								return rs.getInt("clientId");
							}
						});
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			if (!clients.isEmpty()) {
				String clientList = "(";
				for (Iterator<Integer> iterator = clients.iterator(); iterator.hasNext();) {
					Integer client = iterator.next();
					clientList = clientList + client;
					if (iterator.hasNext()) {
						clientList = clientList + ",";
					} else {
						clientList = clientList + ")";
					}
				}			
				where = where + " AND (Client.id IN " + clientList + " OR Supplier.id IN " + clientList + ") ";
			}
		}
		String sqlQuery = new StringBuilder().append(select).append(joins)
				.append(where).append(group).toString();

		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			TransactionRowMapper mapper = new TransactionRowMapper();
			transactions = (ArrayList<Transaction>) this.jdbcTemplate.query(
					sqlQuery, mapper);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return transactions;
	}
}
