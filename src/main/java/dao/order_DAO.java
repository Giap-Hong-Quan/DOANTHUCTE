package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import entities.orders;

public class order_DAO {
	private Connection conn;
	public order_DAO (Connection conn) {
		this.conn=conn;
	}
	
	// hàm tạo order
	public int insertOrder(orders order) throws SQLException {
	    String sql = "INSERT INTO orders (user_id, order_date, total_price, status_id, name, address, phone, payment_method, payment_status, transaction_id) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setInt(1, order.getUser_id());
	        stmt.setTimestamp(2, new Timestamp(order.getOrder_date().getTime()));
	        stmt.setDouble(3, order.getTotal_price()); // sửa lại đúng vị trí
	        stmt.setInt(4, order.getStatus_id());
	        stmt.setString(5, order.getName());
	        stmt.setString(6, order.getAddress()); // address
	        stmt.setString(7, order.getPhone());   // phone
	        stmt.setInt(8, order.getPayment_method());
	        stmt.setInt(9, order.getPayment_status());
	        stmt.setString(10, order.getTransaction_id());

	        int affectedRows = stmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("Creating order failed, no rows affected.");
	        }
	        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getInt(1);
	            } else {
	                throw new SQLException("Creating order failed, no ID obtained.");
	            }
	        }
	    }
	}

	 public void updatePaymentStatus(String transactionId) throws SQLException {
	        String sql = "UPDATE orders SET payment_status = 1 WHERE transaction_id = ?";
	        try (
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, transactionId);
	            stmt.executeUpdate();
	        }
	    }
	// lấy danh sách đơn hàng của từng tài khoản 
	// Lấy danh sách đơn hàng của user
	    public List<orders> getOrdersByUserId(int userId) {
	        List<orders> orders = new ArrayList<>();
	        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
	        try(PreparedStatement stmt=conn.prepareStatement(sql)){
	            stmt.setInt(1, userId);
	            ResultSet rs = stmt.executeQuery();
	            
	            while (rs.next()) {
	            	orders order = new orders();
	                order.setId(rs.getInt("id"));
	                order.setUser_id(rs.getInt("user_id"));
	                order.setOrder_date(new java.sql.Date(rs.getTimestamp("order_date").getTime()));
	                order.setTotal_price(rs.getDouble("total_price"));
	                order.setStatus_id(rs.getInt("status_id"));
	                order.setName(rs.getString("name"));
	                order.setAddress(rs.getString("address"));
	                order.setPhone(rs.getString("phone"));
	                order.setPayment_method(rs.getInt("payment_method"));
	                order.setPayment_status(rs.getInt("payment_status"));
	                order.setTransaction_id(rs.getString("transaction_id"));
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return orders;
	    }
	    
	    // // Lấy thông tin chi tiết của một đơn hàng
	    public orders getOrderById(int orderId, int userId) {
	    	orders order = null;
	    	String sql = "SELECT * FROM orders WHERE id = ? AND user_id = ?";
	        try(PreparedStatement stmt=conn.prepareStatement(sql)) {
	            stmt.setInt(1, orderId);
	            stmt.setInt(2, userId);
	            ResultSet rs = stmt.executeQuery();
	            
	            if (rs.next()) {
	                order = new orders();
	                order.setId(rs.getInt("id"));
	                order.setUser_id(rs.getInt("user_id"));
	                order.setOrder_date(new java.sql.Date(rs.getTimestamp("order_date").getTime()));
	                order.setTotal_price(rs.getDouble("total_price"));
	                order.setStatus_id(rs.getInt("status_id"));
	                order.setName(rs.getString("name"));
	                order.setAddress(rs.getString("address"));
	                order.setPhone(rs.getString("phone"));
	                order.setPayment_method(rs.getInt("payment_method"));
	                order.setPayment_status(rs.getInt("payment_status"));
	                order.setTransaction_id(rs.getString("transaction_id"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return order;
	    }
	    
	    
	    
	    
	    
	    
	    
	    public static void main(String[] args) {
			Connection conn=connect.getConnections();
			order_DAO a=new order_DAO(conn);
			System.out.println(a.getOrderById(7,47).toString());
		}
}
