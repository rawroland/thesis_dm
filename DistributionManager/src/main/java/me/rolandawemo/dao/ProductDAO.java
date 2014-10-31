package me.rolandawemo.dao;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import me.rolandawemo.dao.mappers.ProductRowMapper;
import me.rolandawemo.dao.model.Product;

public class ProductDAO implements IProductDAO {

	private JdbcTemplate jdbcTemplate;

	public ProductDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	public ProductDAO() {
		super();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int create(String name, int clientId, int price) {
		String query = "INSERT INTO products(name, clientId, price) VALUES(?,?,?)";
		int productAdded = 0;
		try {
			productAdded = this.jdbcTemplate.update(query, new Object[] {
					name, clientId, price});
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return productAdded;
	}

	@Override
	public ArrayList<Product> getProducts(String name) {
		String sqlQuery = "SELECT id, name, quantity, clientId, price FROM products WHERE name LIKE '%' || ? || '%'";
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			products = (ArrayList<Product>) this.jdbcTemplate.query(sqlQuery,
					new String[] { name }, new ProductRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Product getById(int id) {
		String query = "SELECT id, name, quantity, clientId, price FROM products WHERE id = ?";
		Product product = this.jdbcTemplate.queryForObject(query, new Object[] {id},new ProductRowMapper());
		try {
			product = this.jdbcTemplate.queryForObject(query, new Object[] {id},new ProductRowMapper());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return product;
	}

}
