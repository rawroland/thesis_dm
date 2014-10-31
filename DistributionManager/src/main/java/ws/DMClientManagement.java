package ws;

import java.util.ArrayList;
import java.util.HashMap;

import me.rolandawemo.dao.ClientDAO;
import me.rolandawemo.dao.model.Client;
import ws.services.ClientManagementService;

public class DMClientManagement implements ClientManagementService {

	private ClientDAO clientDAO;
	
	@Override
	public boolean addClient(String prefix, String firstName, String lastName,
			String company, String type) {
		int clientAdded = this.clientDAO.create(prefix, firstName, lastName, company, type);
		if (1 == clientAdded) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editClient(int id, String prefix, String firstName,
			String lastName, String company, String type) {
		int clientEdited = this.clientDAO.update(id, prefix, firstName, lastName, company, type);
		if (1 == clientEdited) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteClient(int id) {
		int clientDeleted = this.clientDAO.delete(id);
		if (1 == clientDeleted) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Client> searchClients(String query, int id, String type) {
		if (!"".equals(query)) {
			return this.clientDAO.getClients(query);
		}
		if (0 != id) {
			Client client = this.clientDAO.getById(id);
			ArrayList<Client> clients = new ArrayList<Client>();
			clients.add(client);
			return clients;
		}
		
		if (!"".equals(type)) {
			return this.clientDAO.getByType(type);
		}
		return this.clientDAO.getAll();
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

}
