package ws;

import java.util.ArrayList;

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
	public ArrayList<Client> getAllClients() {
		return this.clientDAO.getAll();
	}

	@Override
	public Client searchClientsById(int id) {
		Client client = this.clientDAO.getById(id);
		return client;
	}

	@Override
	public ArrayList<Client> searchClients(String query) {
		return this.clientDAO.getClients(query);
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

}
