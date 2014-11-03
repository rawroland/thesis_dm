package ws;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import me.rolandawemo.dao.TransactionDAO;

import org.apache.commons.lang.BooleanUtils;

import ws.services.ReportingManagementService;
import ws.services.TransactionManagementService;

public class DMReportingManagement implements ReportingManagementService {

	private TransactionDAO transactionDAO;

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}
}
