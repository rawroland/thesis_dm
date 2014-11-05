package me.rolandawemo.dao;

import java.sql.Date;
import java.util.ArrayList;

import me.rolandawemo.dao.model.Transaction;
import me.rolandawemo.dao.queries.TransactionQuery;


/**
 * Transaction DAO Interface to be implemented by a concrete implementation
 * @author Roland Awemo
 *
 */
public interface ITransactionDAO {

	/**
	 * Create and save a new transaction.
	 * @param accountId
	 * @param quantity
	 * @param productId
	 * @param type
	 * @param payment TODO
	 * @param date TODO
	 * @param cost
	 * @return
	 */
	int create(int accountId, int quantity, int productId, String type, int payment, Date date);
	
	ArrayList<Transaction> getTransactions(TransactionQuery query);
}
