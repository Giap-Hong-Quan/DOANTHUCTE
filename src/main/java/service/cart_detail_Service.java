package service;

import java.sql.SQLException;
import java.util.List;

import connectDatabase.connect;
import dao.cartDetail_Dao;
import entities.cart_detail;

public class cart_detail_Service  {
	private cartDetail_Dao cartDetailService=new cartDetail_Dao(connect.getConnections());
	// hàm lấy ra danh sách cart_detail
	 public List<cart_detail> getCartDetailsByCartId(int cartId) throws SQLException {
	        return cartDetailService.findByCartId(cartId);
	    }
	 public boolean updateProductQuantity(int cartId, int productId, String action) throws SQLException {
		    return cartDetailService.updateProductQuantity(cartId, productId, action);
		}
	 public cart_detail getCartDetail(int cartId, int productId) throws SQLException {
		    return cartDetailService.findCartDetailByCartIdAndProductId(cartId, productId);
		}
	  public boolean deleteCartDetail(int cartId, int productId) {
	        try {
	            return cartDetailService.deleteCartItem(cartId, productId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	  public boolean deleteCartItems(int cartId) {
	        try {
	            return cartDetailService.deleteCartItems(cartId);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
}
