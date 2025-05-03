package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import entities.cart_detail;
import entities.order_items;

public class order_items_DAO {
	private Connection conn;
	public order_items_DAO (Connection conn) {
		this.conn=conn;
	}
	public boolean insertOrderItems(int orderId, List<order_items> orderItems) throws SQLException {
	    String sql = "INSERT INTO order_items(order_id, product_id, quantity, price, subtotal) VALUES (?, ?, ?, ?, ?)";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        for (order_items item : orderItems) {
	            stmt.setInt(1, orderId);
	            stmt.setInt(2, item.getProduct_id());
	            stmt.setInt(3, item.getQuantity());
	            stmt.setDouble(4, item.getPrice());
	            stmt.setDouble(5, item.getSubtotal());
	            stmt.addBatch();
	        }
	        
	        int[] results = stmt.executeBatch();
	        return true; // Trả về true nếu thực thi thành công
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Trả về false nếu có lỗi
	    }
	}
}
