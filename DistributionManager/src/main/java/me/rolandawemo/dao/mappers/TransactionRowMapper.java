package me.rolandawemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.Account;
import me.rolandawemo.dao.model.Client;
import me.rolandawemo.dao.model.Product;
import me.rolandawemo.dao.model.Transaction;

import org.springframework.jdbc.core.RowMapper;

public class TransactionRowMapper implements RowMapper<Transaction>{
	public Transaction mapRow(ResultSet rs, int row) {
		Transaction transaction = new Transaction();
		try {
			transaction.setQuantity(rs.getInt("transactionQuantity"));
			transaction.setDate(rs.getString("transactionDate"));
			transaction.setType(rs.getString("transactionType"));
			transaction.setCost(rs.getInt("transactionPayment"));
			
			Account account = new Account();
			account.setId(rs.getInt("accountId"));
			account.setAmount(rs.getInt("budget"));
			account.setClientId(rs.getInt("clientId"));
			
			Client client = new Client();
			client.setId(rs.getInt("clientId"));
			client.setFirstName(rs.getString("clientFirstName"));
			client.setLastName(rs.getString("clientLastName"));
			client.setPrefix(rs.getString("clientPrefix"));
			client.setType(rs.getString("clientType"));
			client.setCompany(rs.getString("clientCompany"));
			account.setClient(client);
			
			Product product = new Product();
			product.setId(rs.getInt("productId"));
			product.setClientId(rs.getInt("supplierId"));
			product.setName(rs.getString("productName"));
			product.setQuantity(rs.getInt("productQuantity"));
			product.setPrice(rs.getInt("productPrice"));
			
			Client supplier = new Client();
			supplier.setId(rs.getInt("supplierId"));
			supplier.setFirstName(rs.getString("supplierFirstName"));
			supplier.setLastName(rs.getString("supplierLastName"));
			supplier.setPrefix(rs.getString("supplierPrefix"));
			supplier.setType(rs.getString("supplierType"));
			supplier.setCompany(rs.getString("supplierCompany"));
			product.setClient(supplier);
			
			transaction.setAccount(account);
			transaction.setProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transaction;
	}
}
