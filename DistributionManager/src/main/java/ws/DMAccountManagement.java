package ws;

import me.rolandawemo.dao.AccountDAO;
import ws.services.AccountManagementService;

public class DMAccountManagement implements AccountManagementService {

	private AccountDAO accountDAO;

	public AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public boolean addAccount(int clientId) {
		int accountAdded = this.accountDAO.create(clientId, 0);
		if (1 == accountAdded) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean creditAccount(int clientId, int ammount) {
		int accountCredited = this.accountDAO.credit(clientId, ammount);
		if (1 == accountCredited) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean debitAccount(int clientId, int ammount) {
		int accountDebited = this.accountDAO.debit(clientId, ammount);
		if (1 == accountDebited) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int checkAccount(int clientId) {
		return this.accountDAO.getAccount(clientId).getAmmount();
	}

}
