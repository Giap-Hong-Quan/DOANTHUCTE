package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import entities.cart_detail;

public class cartDetail_Dao {
	private Connection conn;
	public cartDetail_Dao (Connection conn) {
		this.conn=conn;
	}
 // Tìm sản phẩm trong giỏ hàng
    public cart_detail findCartDetailByCartIdAndProductId(int cartId, int productId) throws SQLException {
    	cart_detail cartDetail = null;
        String sql = "SELECT * FROM cart_detail WHERE cart_id = ? AND product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    double price = rs.getDouble("price"); // Sửa thành getDouble
                    int quantity = rs.getInt("quantity");
                    int cart_id = rs.getInt("cart_id");
                    int product_id = rs.getInt("product_id");
                    
                    cartDetail = new cart_detail(id, price, quantity, cart_id, product_id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding cart detail: " + e.getMessage());
        }
        return cartDetail;
    }
    // Thêm sản phẩm mới vào giỏ
    public void insertCartDetail(cart_detail cartDetail) throws SQLException {
        String sql = "INSERT INTO cart_detail(price, quantity, cart_id, product_id) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, cartDetail.getPrice());
            ps.setInt(2, cartDetail.getQuantity());
            ps.setInt(3, cartDetail.getCart_id());
            ps.setInt(4, cartDetail.getProduct_id());
            ps.executeUpdate();
        }
    }
 // Cập nhật số lượng sản phẩm
    public void updateCartDetailQuantity(int cartDetailId, int quantity) throws SQLException {
        String sql = "UPDATE cart_detail SET quantity = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, cartDetailId);
            ps.executeUpdate();
        }
    }
 // Tính tổng số lượng sản phẩm trong cart
    public int countTotalQuantityByCartId(int cartId) throws SQLException {
        String sql = "SELECT SUM(quantity) AS total FROM cart_detail WHERE cart_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }
    public List<cart_detail> findByCartId(int cartId) throws SQLException {
        List<cart_detail> list = new ArrayList<>();
        String sql = "SELECT * FROM cart_detail WHERE cart_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	 int id = rs.getInt("id");
                 double price = rs.getDouble("price"); // Sửa thành getDouble
                 int quantity = rs.getInt("quantity");
                 int cart_id = rs.getInt("cart_id");
                 int product_id = rs.getInt("product_id");
                list.add(new cart_detail(id, price, quantity, cart_id, product_id));
            }
        }
        return list;
    }
    public boolean updateProductQuantity(int cartId, int productId, String action) throws SQLException {
        String sql = action.equals("increase")
                ? "UPDATE cart_detail SET quantity = quantity + 1 WHERE cart_id = ? AND product_id = ?"
                : "UPDATE cart_detail SET quantity = quantity - 1 WHERE cart_id = ? AND product_id = ? AND quantity > 1";
        try (
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        }
    }

  public static void main(String[] args) {
	Connection con=connect.getConnections();
	cartDetail_Dao a=new cartDetail_Dao(con);
	try {
		System.out.println(a.updateProductQuantity(1, 18, "decrease"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
  public boolean deleteCartItem(int cartId, int productId) throws SQLException {
      String sql = "DELETE FROM cart_detail WHERE cart_id = ? AND product_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
          ps.setInt(1, cartId);
          ps.setInt(2, productId);
          return ps.executeUpdate() > 0;
      }
  }	
  public boolean deleteCartItems(int cartId) throws SQLException {
      String sql = "DELETE FROM cart_detail WHERE cart_id = ?";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
          ps.setInt(1, cartId);
          return ps.executeUpdate() > 0;
      }
  }	
}
