package ws.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import me.rolandawemo.dao.model.ReportingGroup;
import me.rolandawemo.dao.model.Transaction;
import me.rolandawemo.dao.queries.TransactionQuery;

@WebService
public interface ReportingManagementService {

	@WebMethod
	public boolean createReportingGroup(@WebParam(name = "name") String name,
			@WebParam(name = "accounts") ArrayList<Integer> accounts);

	@WebMethod
	public ArrayList<ReportingGroup> searchReportingGroups(@WebParam(name = "id") int id);
	
	@WebMethod
	public ArrayList<Transaction> generateReport(@WebParam(name="query") TransactionQuery query);
}
