package ws;

import java.util.ArrayList;

import org.apache.commons.lang.BooleanUtils;

import me.rolandawemo.dao.ReportingGroupDAO;
import me.rolandawemo.dao.TransactionDAO;
import me.rolandawemo.dao.model.ReportingGroup;
import me.rolandawemo.dao.model.Transaction;
import me.rolandawemo.dao.queries.TransactionQuery;
import ws.services.ReportingManagementService;

public class DMReportingManagement implements ReportingManagementService {

	private TransactionDAO transactionDAO;
	private ReportingGroupDAO reportingGroupDAO;

	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}

	public void setTransactionDAO(TransactionDAO transactionDAO) {
		this.transactionDAO = transactionDAO;
	}

	public ReportingGroupDAO getReportingGroupDAO() {
		return reportingGroupDAO;
	}

	public void setReportingGroupDAO(ReportingGroupDAO reportingGroupDAO) {
		this.reportingGroupDAO = reportingGroupDAO;
	}

	@Override
	public boolean createReportingGroup(String name, ArrayList<Integer> accounts) {
		return BooleanUtils.toBoolean(this.reportingGroupDAO.create(name, accounts));
	}

	@Override
	public ArrayList<ReportingGroup> searchReportingGroups(int id) {
		if (0 < id) {
			ArrayList<ReportingGroup> groups = new ArrayList<ReportingGroup>();
			groups.add(this.reportingGroupDAO.getById(id));
			return groups;
		}
		
		return this.reportingGroupDAO.getAll();
	}

	@Override
	public ArrayList<Transaction> generateReport(TransactionQuery query) {
		return this.transactionDAO.getTransactions(query);
	}
}
