package ws.services;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import me.rolandawemo.dao.model.Product;

@WebService
public interface ProductManagementService {

	@WebMethod
	public boolean addProduct(@WebParam(name = "name") String name,
			@WebParam(name = "quantity") int quantity,
			@WebParam(name = "clientId") int clientId,
			@WebParam(name = "price") int price);

	@WebMethod
	public ArrayList<Product> searchProducts(@WebParam(name = "name") String name);

	@WebMethod
	public int checkProduct(@WebParam(name = "id") int id);
}
