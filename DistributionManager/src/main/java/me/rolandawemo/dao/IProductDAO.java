package me.rolandawemo.dao;

import java.util.ArrayList;

import me.rolandawemo.dao.model.Product;

public interface IProductDAO {

	/**
	 * Create a Product for an existing client
	 * @param String name The name of the product
	 * @param int quantity The quantity of the product
	 * @param int clientId The corresponding client
	 * @param int price The unit price of the product
	 * @return 1 if the product was successfully added, 0 otherwise
	 */
	int create(String name, int quantity, int clientId, int price);
	
	/**
	 * Get a list of products filtered by the name.
	 * @param name The name of the product to be filtered by
	 * @return A list of products
	 */
	ArrayList<Product> getProducts(String name);
	
	/**
	 * Get a product by its id
	 * @param int id Product id
	 * @return Product a Product
	 */
	Product getById(int id);
}
