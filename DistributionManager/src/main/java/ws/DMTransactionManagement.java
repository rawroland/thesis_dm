package ws;

import org.apache.commons.lang.BooleanUtils;

import me.rolandawemo.dao.ITransactionDAO;
import me.rolandawemo.dao.TransactionDAO;
import ws.services.TransactionManagementService;

public class DMTransactionManagement implements TransactionManagementService {

	private TransactionDAO transactionDAO;

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	@Override
	public boolean saveTransaction(int accountId, int quantity, int productId,
			String type, int payment) {
		int saved = this.transactionDAO.create(accountId, quantity, productId, type, payment);
		return BooleanUtils.toBoolean(saved);
	}
}
