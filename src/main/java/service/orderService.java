package service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    // Lấy tổng doanh thu đã thanh toán
    public double getTotalRevenue() {
        double totalRevenue = 0;
        String sql = "SELECT SUM(total_price) as total FROM orders WHERE payment_status = 1";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalRevenue;
    }
    
    // Lấy tổng số sản phẩm đã bán
    public int getTotalSoldItems() {
        int totalItems = 0;
        String sql = "SELECT SUM(quantity) as total FROM order_items JOIN orders ON order_items.order_id = orders.id WHERE orders.payment_status = 1";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalItems = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalItems;
    }
    
    // Lấy doanh thu theo tháng cho năm hiện tại
    public Map<String, Double> getMonthlyRevenue() {
        Map<String, Double> monthlyRevenue = new HashMap<>();
        String sql = "SELECT MONTH(order_date) as month, SUM(total_price) as revenue " +
                     "FROM orders WHERE YEAR(order_date) = YEAR(CURRENT_DATE()) AND payment_status = 1 " +
                     "GROUP BY MONTH(order_date) ORDER BY month";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                double revenue = rs.getDouble("revenue");
                
                // Tên tháng theo tiếng Việt
                String monthName = "Tháng " + month;
                monthlyRevenue.put(monthName, revenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return monthlyRevenue;
    }
    
    // Lấy danh sách đơn hàng mới nhất
    public List<orders> getRecentOrders(int limit) {
        List<orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_date DESC LIMIT ?";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                int orderId = rs.getInt("id");
                orders order = getOrderById(orderId);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ordersList;
    }
}