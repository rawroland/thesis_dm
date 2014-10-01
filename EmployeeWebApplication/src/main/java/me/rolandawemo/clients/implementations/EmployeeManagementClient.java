package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IEmployeeManagement;

public class EmployeeManagementClient implements IEmployeeManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/employees?wsdl";
	public static final String NAMESPACE_URI = "http://ws";
	public static final String NAMESPACE_PORT = "employeeManagement";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"employees");
	private static final QName PORT_NAME = new QName(NAMESPACE_URI, NAMESPACE_PORT);
	
	@Override
	public boolean add(String givenname, String surname, String username,
			String role) {
		Service service = null;
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
