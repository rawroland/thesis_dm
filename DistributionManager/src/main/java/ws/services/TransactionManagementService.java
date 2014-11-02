package ws.services;

import java.text.ParseException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface TransactionManagementService {

	@WebMethod
	public boolean saveTransaction(@WebParam(name = "accountId") int accountId,
			@WebParam(name = "quantity") int quantity,
			@WebParam(name = "productId") int productId,
			@WebParam(name = "type") String type,
			@WebParam(name = "payment") int payment,
			@WebParam(name = "date") String date) throws ParseException;
}
