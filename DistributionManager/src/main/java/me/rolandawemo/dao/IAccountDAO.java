package me.rolandawemo.dao;

import me.rolandawemo.dao.model.Account;

public interface IAccountDAO {

	/**
	 * Create a new account for a client
	 * @param int clientId Client id
	 * @param int ammount Ammount to create the ammount with.
	 * @return int 1 If the client was updated successfully
	 */
	int create(int clientId, int ammount);
	
	/**
	 * Update a new account for a client
	 * @param int id Account id
	 * @param int clientId Client id
	 * @param int ammount Ammount to credit the account with.
	 * @return int 1 If the client was credited successfully
	 */
	int credit(int clientId, int ammount); 
	
	/**
	 * Update a new account for a client
	 * @param int id Account id
	 * @param int clientId Client id
	 * @param int ammount Ammount to debit the account with.
	 * @return int 1 If the client was debited successfully
	 */
	int debit(int clientId, int ammount); 
	
	/**
	 * Get the account details
	 * @param clientId Client Id
	 * @return Account
	 */
	Account getAccount(int clientId);
}
