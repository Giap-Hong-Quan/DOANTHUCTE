package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import controller.ImageUtil;
import dao.order_DAO;
import dao.order_items_DAO;
import dao.product_DAO;
import dao.productimages_DAO;
import entities.order_items;
import entities.product;
import entities.productimages;

public class orderItemsService {
	 private product_DAO productDao = new product_DAO(connect.getConnections());
	private order_items_DAO orderItemsService=new order_items_DAO(connect.getConnections());
	 private productimages_DAO productimagesDao = new productimages_DAO(connect.getConnections());
	//
	public boolean insertOrderItems(int orderId, List<order_items> orderItems) {
	    try {
	        return orderItemsService.insertOrderItems(orderId, orderItems);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Trả về false khi có lỗi
	    }
	}
	
	//
	 public List<order_items> getAllOrderItemsByUserId(int userId) {
		 List<order_items> listorderitems = orderItemsService.getAllOrderItemsByUserId(userId);
		 // Lặp qua từng sản phẩm để gán ảnh đầu tiên
	        for (order_items orderitem : listorderitems) {
	            String firstImage = productimagesDao.getFirstImageByProductId(orderitem.getProduct_id());

	            // Kiểm tra nếu có ảnh đầu tiên thì gán vào sản phẩm
	            if (firstImage != null) {
	                // Tạo một danh sách ảnh và thêm ảnh đầu tiên
	                List<productimages> imageList = new ArrayList<>();
	                imageList.add(new productimages(orderitem.getId(), firstImage)); 
	                orderitem.setProductImage(firstImage);  // Gán danh sách ảnh vào sản phẩm
	            }
	            // Lấy tên sản phẩm
	            String productName = productDao.getProductNameById(orderitem.getProduct_id());
	            orderitem.setProductName(productName);
	        }
	        return listorderitems;
	 }
	 // hàm lấy đơn order item cụ thể
	 public order_items getOrderItemById(int id) {
		 order_items orderitem=orderItemsService.getOrderItemById(id);
	    	if(orderitem!=null) {
	    		String firstImage = productimagesDao.getFirstImageByProductId(orderitem.getProduct_id());
	    		 orderitem.setProductImage(firstImage);  
	    		  // Lấy tên sản phẩm
		            String productName = productDao.getProductNameById(orderitem.getProduct_id());
		            orderitem.setProductName(productName);
	    	}
	    	return orderitem;
	 }
	// Lấy tất cả các items của một đơn hàng và thêm thông tin sản phẩm
	 public List<order_items> getOrderItemsByOrderId(int orderId) {
	     List<order_items> items = orderItemsService.getOrderItemsByOrderId(orderId);
	     
	     // Lặp qua từng item để lấy thông tin sản phẩm
	     for (order_items item : items) {
	         // Lấy thông tin ảnh sản phẩm
	         String firstImage = productimagesDao.getFirstImageByProductId(item.getProduct_id());
	         if (firstImage != null) {
	             item.setProductImage(firstImage);
	         }
	         
	         // Lấy tên sản phẩm
	         String productName = productDao.getProductNameById(item.getProduct_id());
	         item.setProductName(productName);
	     }
	     
	     return items;
	 }
	 public static void main(String[] args) {
		Connection conn=connect.getConnections()	;
		orderItemsService a=new orderItemsService();
		System.out.println(a.getOrderItemById(4));
	}
}
