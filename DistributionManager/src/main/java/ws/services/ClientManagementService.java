package ws.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import me.rolandawemo.dao.model.Client;

@WebService
public interface ClientManagementService {

	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "addClient", targetNamespace = "http://ws/", className = "ws.services.AddClient")
	@ResponseWrapper(localName = "addClientResponse", targetNamespace = "http://ws/", className = "ws.services.AddClientResponse")
	public boolean addClient(
			@WebParam(name = "prefix", targetNamespace = "") String prefix,
			@WebParam(name = "firstName", targetNamespace = "") String firstName,
			@WebParam(name = "lastName", targetNamespace = "") String lastName,
			@WebParam(name = "company", targetNamespace = "") String company,
			@WebParam(name = "type", targetNamespace = "") String type);

	@WebMethod
	public boolean editClient(@WebParam(name = "id") int id,
			@WebParam(name = "prefix") String prefix,
			@WebParam(name = "firstName") String firstName,
			@WebParam(name = "lastName") String lastName,
			@WebParam(name = "company") String company,
			@WebParam(name = "type") String type);

	@WebMethod
	public boolean deleteClient(@WebParam(name = "id") int id);

	@WebMethod
	public ArrayList<Client> getAllClients();

	@WebMethod
	public Client searchClientsById(int id);
}
