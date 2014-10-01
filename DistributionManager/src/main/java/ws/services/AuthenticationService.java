package ws.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import me.rolandawemo.dao.model.Employee;
import me.rolandawemo.exceptions.AuthenticationFailedException;

@WebService
public interface AuthenticationService {

	@WebMethod
	public Employee authenticate(@WebParam(name="username") String username, @WebParam(name="password")String password) throws AuthenticationFailedException;
}
