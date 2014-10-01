package ws.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import me.rolandawemo.exceptions.AddEmployeeFailedException;
import me.rolandawemo.exceptions.DeleteEmployeeFailedException;

@WebService
public interface EmployeeManagementService {

	@WebMethod
	public boolean addEmployee(@WebParam(name = "givenname") String givenname,
			@WebParam(name = "surname") String surname,
			@WebParam(name = "username") String username,
			@WebParam(name = "role") String role)
			throws AddEmployeeFailedException;

	@WebMethod
	public boolean editEmployee(@WebParam(name = "id") int id,
			@WebParam(name = "givenname") String givenname,
			@WebParam(name = "surname") String surname,
			@WebParam(name = "username") String username,
			@WebParam(name = "role") String role)
			throws AddEmployeeFailedException;
	
	@WebMethod
	public boolean deleteEmployee(@WebParam(name="id") int id) throws DeleteEmployeeFailedException;
}
