package ws;

import java.util.ArrayList;

import javax.jws.WebService;

import ws.services.EmployeeManagementService;
import me.rolandawemo.dao.EmployeeDAO;
import me.rolandawemo.dao.model.Employee;

@WebService(endpointInterface = "ws.services.EmployeeManagementService", serviceName = "DMEmployeeManagementService", portName = "DMEmployeeManagementPort")
public class DMEmployeeManagement implements EmployeeManagementService {

	private EmployeeDAO employeeDAO;

	@Override
	public boolean addEmployee(String givenname, String surname,
			String username, String role) {
		int employeesAdded = this.employeeDAO.create(givenname, surname,
				username, role);
		if (1 == employeesAdded) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editEmployee(int id, String givenname, String surname,
			String username, String role) {
		int employeesEdited = this.employeeDAO.update(id, givenname, surname,
				username, role);
		if (1 == employeesEdited) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(int id) {
		int employeeDeleted = this.employeeDAO.delete(id);
		if (1 == employeeDeleted) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		return this.employeeDAO.getAll();
	}

	@Override
	public Employee searchEmployeesById(int id) {
		Employee employee = this.employeeDAO.getById(id);
		return employee;
	}


	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
}
