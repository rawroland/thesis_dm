package ws;

import javax.jws.WebService;

import ws.services.AuthenticationService;
import me.rolandawemo.dao.EmployeeDAO;
import me.rolandawemo.dao.model.Employee;

@WebService(endpointInterface="ws.services.AuthenticationService", serviceName="authentication")
public class DMAuthentication implements AuthenticationService{

	private EmployeeDAO employeeDAO;

	@Override
	public Employee authenticate(String username, String password) {
		Employee employee = this.employeeDAO.findByUsernameAndPassword(
				username, password);
		return employee;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

}
