package service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import dao.order_items_DAO;
import dao.product_DAO;
import dao.productimages_DAO;
import entities.order_items;
import entities.productimages;

public class orderItemsService {
    private Connection conn;
    private order_items_DAO orderItemsDao;
    private product_DAO productDao;
    private productimages_DAO productimagesDao;
    
    public orderItemsService() {
        this.conn = connect.getConnections();
        this.orderItemsDao = new order_items_DAO(conn);
        this.productDao = new product_DAO(conn);
        this.productimagesDao = new productimages_DAO(conn);
    }
    
    // Thêm các mục vào đơn hàng
    public boolean insertOrderItems(int orderId, List<order_items> orderItems) {
        try {
            return orderItemsDao.insertOrderItems(orderId, orderItems);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Lấy tất cả các mục đơn hàng của một người dùng
    public List<order_items> getAllOrderItemsByUserId(int userId) {
        List<order_items> listOrderItems = orderItemsDao.getAllOrderItemsByUserId(userId);
        
        // Bổ sung thông tin sản phẩm cho mỗi mục đơn hàng
        for (order_items item : listOrderItems) {
            enrichOrderItem(item);
        }
        
        return listOrderItems;
    }
    
    // Lấy thông tin chi tiết của một mục đơn hàng
    public order_items getOrderItemById(int id) {
        order_items item = orderItemsDao.getOrderItemById(id);
        
        if (item != null) {
            enrichOrderItem(item);
        }
        
        return item;
    }
    
    // Lấy tất cả các mục của một đơn hàng
    public List<order_items> getOrderItemsByOrderId(int orderId) {
        List<order_items> items = orderItemsDao.getOrderItemsByOrderId(orderId);
        
        // Bổ sung thông tin sản phẩm cho mỗi mục đơn hàng
        for (order_items item : items) {
            enrichOrderItem(item);
        }
        
        return items;
    }
    
    // Phương thức để bổ sung thông tin sản phẩm cho mục đơn hàng
    private void enrichOrderItem(order_items item) {
        // Lấy ảnh đầu tiên của sản phẩm
        String firstImage = productimagesDao.getFirstImageByProductId(item.getProduct_id());
        if (firstImage != null) {
            item.setProductImage(firstImage);
        }
        
        // Lấy tên sản phẩm
        String productName = productDao.getProductNameById(item.getProduct_id());
        item.setProductName(productName);
    }
}