package me.rolandawemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import me.rolandawemo.dao.model.Product;
import org.springframework.jdbc.core.RowMapper;

public class ProductRowMapper implements RowMapper<Product>{
	public Product mapRow(ResultSet rs, int row) {
		Product product = new Product();
		try {
			product.setId(rs.getInt("id"));
			product.setClientId(rs.getInt("clientId"));
			product.setName(rs.getString("name"));
			product.setQuantity(rs.getInt("quantity"));
			product.setPrice(rs.getInt("price"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
}
