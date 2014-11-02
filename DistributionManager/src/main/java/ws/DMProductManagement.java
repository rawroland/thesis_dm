package ws;

import java.util.ArrayList;

import me.rolandawemo.dao.ProductDAO;
import me.rolandawemo.dao.model.Product;
import ws.services.ProductManagementService;

public class DMProductManagement implements ProductManagementService {

	private ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
	@Override
	public boolean addProduct(String name, int clientId, int price) {
		int productAdded = this.productDAO.create(name, clientId, price); 
		if (1 == productAdded) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ArrayList<Product> searchProducts(String name) {
		if (null == name) {
			return this.productDAO.getAllProducts();
		}
		return this.productDAO.getProducts(name);
	}

	@Override
	public int checkProduct(int id) {
		return this.productDAO.getById(1).getQuantity();
	}

}
