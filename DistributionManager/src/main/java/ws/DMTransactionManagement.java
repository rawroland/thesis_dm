package ws;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import me.rolandawemo.dao.TransactionDAO;

import org.apache.commons.lang.BooleanUtils;

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
			String type, int payment, String date) throws ParseException {
		Date dateObj = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(date).getTime());
		int saved = this.transactionDAO.create(accountId, quantity, productId, type, payment, dateObj);
		return BooleanUtils.toBoolean(saved);
	}
}
