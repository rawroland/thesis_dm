package ws.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface AccountManagementService {

	@WebMethod
	public boolean addAccount(@WebParam(name="clientId")int clientId);
	
	@WebMethod
	public boolean creditAccount(@WebParam(name="clientId")int clientId, @WebParam(name="amount")int amount);
	
	@WebMethod
	public boolean debitAccount(@WebParam(name="clientId")int clientId, @WebParam(name="amount")int amount);

	@WebMethod
	public int checkAccount(@WebParam(name="clientId")int clientId);
}
