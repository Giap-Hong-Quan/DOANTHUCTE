package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	// Lấy tất cả các mục đơn hàng của một người dùng
    public List<order_items> getAllOrderItemsByUserId(int userId) {
        List<order_items> orderItems = new ArrayList<>();
        String sql = "SELECT oi.*, o.user_id FROM order_items oi " +
                     "JOIN orders o ON oi.order_id = o.id " +
                     "WHERE o.user_id = ? " +
                     "ORDER BY o.order_date DESC";
        
        try (
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	order_items item = new order_items();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                orderItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderItems;
    }
    // Lấy một mục đơn hàng cụ thể
    public order_items getOrderItemById(int id) {
    	order_items item = null;
        String sql = "SELECT * FROM order_items WHERE id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                item = new order_items();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal")); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

 // Lấy tất cả các items của một đơn hàng
    public List<order_items> getOrderItemsByOrderId(int orderId) {
        List<order_items> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                order_items item = new order_items();
                item.setId(rs.getInt("id"));
                item.setOrder_id(rs.getInt("order_id"));
                item.setProduct_id(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSubtotal(rs.getDouble("subtotal"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return items;
    }
    
    
    public static void main(String[] args) {
		Connection conn=connect.getConnections();
		order_items_DAO a=new order_items_DAO(conn);
		System.out.println(a.getOrderItemById(4).toString());
	}
}
