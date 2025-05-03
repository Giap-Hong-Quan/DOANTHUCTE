package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
	 public static void main(String[] args) {
		    Connection conn = null;
		    try {
		        conn = connect.getConnections();
		        order_DAO a = new order_DAO(conn);
		        
		        java.util.Date utilDate = new java.util.Date();
		         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		        
		        // Test case 2: Transaction ID null (COD)
		        orders order2 = new orders(
		            47,sqlDate, 100000, 0, 
		            "quan", "hcm", "033890607", 0, 0, "ưbjhwegie912213"
		        );
		        System.out.println("Order 2 ID: " + a.insertOrder(order2));
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        if (conn != null) {
		            try {
		                conn.close();
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
}
