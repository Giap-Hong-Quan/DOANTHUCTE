package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connectDatabase.connect;
import entities.cart;
import entities.user;

public class cart_Dao {
	private Connection conn;
	public cart_Dao (Connection conn) {
		this.conn=conn;
	}
	  // Tìm cart theo user_id
    public cart findCartByUserId(int userId) throws SQLException {
    	 cart cart = null;
        String sql = "SELECT * FROM cart WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
	            int sum = rs.getInt("sum");
	            int user_id = rs.getInt("sum");
	            cart=new cart(id,sum,user_id);
            }
        }
        return cart; 
    }
  
    // Tạo mới cart
    public int createCart(int userId) throws SQLException {
        String sql = "INSERT INTO cart(sum, user_id) VALUES(0, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }
        }
        return -1;
    }
    public void updateSum(int cartId, int newSum) throws SQLException {
        String sql = "UPDATE cart SET sum = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newSum);
            ps.setInt(2, cartId);
            ps.executeUpdate();
        }
    }
}
