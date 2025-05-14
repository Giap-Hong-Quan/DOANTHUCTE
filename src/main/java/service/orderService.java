package service;

import java.sql.SQLException;
import java.util.List;

import connectDatabase.connect;
import dao.order_DAO;
import entities.orders;

public class orderService {
    private order_DAO orderDao;
    
    public orderService() {
        this.orderDao = new order_DAO(connect.getConnections());
    }
    
    // Lấy thông tin chi tiết của một đơn hàng theo id và user id
    public orders getOrderById(int orderId, int userId) {
        return orderDao.getOrderById(orderId, userId);
    }
    
    // Lấy thông tin chi tiết của một đơn hàng cho admin (không cần user_id)
    public orders getOrderById(int orderId) {
        return orderDao.getOrderById(orderId);
    }
    
    // Thêm phương thức lấy danh sách đơn hàng của user
    public List<orders> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }
    
    // Lấy tất cả đơn hàng cho admin
    public List<orders> getAllOrders(int page, int pageSize) {
        return orderDao.getAllOrders(page, pageSize);
    }
    
    // Lấy tổng số đơn hàng
    public int getTotalOrders() {
        return orderDao.getTotalOrders();
    }
    
    // Hàm insert order mới
    public int insertOrder(orders order) {
        try {
            return orderDao.insertOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // Cập nhật trạng thái thanh toán
    public void updatePaymentStatus(String transactionId) {
        try {
            orderDao.updatePaymentStatus(transactionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Cập nhật trạng thái đơn hàng
    public boolean updateOrderStatus(int orderId, int statusId) {
        try {
            return orderDao.updateOrderStatus(orderId, statusId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}