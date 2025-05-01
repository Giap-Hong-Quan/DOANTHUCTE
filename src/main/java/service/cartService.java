package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connectDatabase.connect;
import dao.cartDetail_Dao;
import dao.cart_Dao;
import entities.cart;
import entities.cart_detail;

public class cartService {
	private cart_Dao cartDAO= new cart_Dao(connect.getConnections());
    private cartDetail_Dao cartDetailDAO=new cartDetail_Dao(connect.getConnections());
    
  
    
    public void addToCart(int userId, int productId, double price) throws SQLException {
        // 1. Kiểm tra có cart chưa
        cart cart = cartDAO.findCartByUserId(userId);
        if (cart == null) {
            int cartId = cartDAO.createCart(userId);
            cart = new cart();
            cart.setId(cartId);
            cart.setSum(0);
            cart.setUser_id(userId);
        }

        // 2. Kiểm tra sản phẩm đã có trong giỏ chưa
        cart_detail detail = cartDetailDAO.findCartDetailByCartIdAndProductId(cart.getId(), productId);
        if (detail != null) {
            // Đã có => tăng số lượng
            int newQuantity = detail.getQuantity() + 1;
            cartDetailDAO.updateCartDetailQuantity(detail.getId(), newQuantity);
        } else {
            // Chưa có => thêm mới
        	cart_detail newDetail = new cart_detail();
            newDetail.setPrice(price);
            newDetail.setQuantity(1);
            newDetail.setCart_id(cart.getId());
            newDetail.setProduct_id(productId);
            cartDetailDAO.insertCartDetail(newDetail);
         // Tăng số lượng loại sản phẩm trong cart (sum++)
            cartDAO.updateSum(cart.getId(), cart.getSum() + 1);
        }        
    }
    // Lấy cart của người dùng
    public cart getCartByUserId(int userId) throws SQLException {
        return cartDAO.findCartByUserId(userId);
    }
    
    public double calculateCartTotal(int cartId) throws SQLException {
        List<cart_detail> details = cartDetailDAO.findByCartId(cartId);
        double total = 0;
        for (cart_detail detail : details) {
            double price = detail.getPrice(); // hoặc lấy lại từ DB nếu cần cập nhật theo giá mới
            total += price * detail.getQuantity();
        }
        return total;
    }



}
