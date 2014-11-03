package ws.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ReportingManagementService {

	@WebMethod
	public boolean createReportingGroup(@WebParam(name = "name") String name,
			@WebParam(name = "accounts") ArrayList<Integer> accounts);

	@WebMethod
	public boolean searchReportingGroups(@WebParam(name = "id") int id);
}
