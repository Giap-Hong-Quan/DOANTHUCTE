package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import entities.product;

public class product_DAO {
	private Connection conn;
	public product_DAO(Connection conn) {
		this.conn=conn;
	}
	// ham thêm sản phâm 
	public int addproduct(product product) {
	    String query = "INSERT INTO products (category_id, name, description, price, quantity) VALUES (?, ?, ?, ?, ?)";
	    // Thêm tham số Statement.RETURN_GENERATED_KEYS
	    try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setInt(1, product.getCategory_id());
	        stmt.setString(2, product.getName());
	        stmt.setString(3, product.getDescription());
	        stmt.setDouble(4, product.getPrice());
	        stmt.setInt(5, product.getQuantity());
	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            return rs.getInt(1); // Trả về product_id
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Thêm sản phẩm thất bại");
	    }
	    return 0;
	}
	// ProductDAO.java
	public List<product> getAllProducts(int offset, int limit) {  // Bỏ 2 tham số sort
	    List<product> products = new ArrayList<>();
	    String query = "SELECT * FROM products LIMIT ?, ?";  // Bỏ ORDER BY

	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, offset);
	        stmt.setInt(2, limit);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	        	  	int id = rs.getInt("id");
		            int category_id  = rs.getInt("category_id");
		            String name = rs.getString("name");
		            double price = rs.getDouble("price");
		            int quantity = rs.getInt("quantity");  
	            products.add(new product(id, category_id, name, price, quantity));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return products;
	}
	
	 // Đếm tổng số sản phẩm (dùng cho tính tổng số trang)
    public int countAllProducts() {
        String query = "SELECT COUNT(*) FROM products";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
	public static void main(String[] args) {
		Connection conn=connect.getConnections();
		product_DAO a=new product_DAO(conn);
		System.out.println(a.getAllProducts(1,10));
	}
}
