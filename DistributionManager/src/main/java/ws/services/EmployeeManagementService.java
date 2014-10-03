package ws.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import me.rolandawemo.dao.model.Employee;

@WebService
public interface EmployeeManagementService {

	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "addEmployee", targetNamespace = "http://ws/", className = "ws.services.AddEmployee")
	@ResponseWrapper(localName = "addEmployeeResponse", targetNamespace = "http://ws/", className = "ws.services.AddEmployeeResponse")
	public boolean addEmployee(
			@WebParam(name = "givenname", targetNamespace = "") String givenname,
			@WebParam(name = "surname", targetNamespace = "") String surname,
			@WebParam(name = "username", targetNamespace = "") String username,
			@WebParam(name = "role", targetNamespace = "") String role);

	@WebMethod
	public boolean editEmployee(@WebParam(name = "id") int id,
			@WebParam(name = "givenname") String givenname,
			@WebParam(name = "surname") String surname,
			@WebParam(name = "username") String username,
			@WebParam(name = "role") String role);

	@WebMethod
	public boolean deleteEmployee(@WebParam(name = "id") int id);
	
	@WebMethod 
	public ArrayList<Employee> searchClients();
}
