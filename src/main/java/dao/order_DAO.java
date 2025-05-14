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
import service.orderItemsService;

public class order_DAO {
    private Connection conn;
    private orderItemsService orderItemsService;
    
    public order_DAO(Connection conn) {
        this.conn = conn;
        this.orderItemsService = new service.orderItemsService();
    }
    
    // Hàm tạo order
    public int insertOrder(orders order) throws SQLException {
        String sql = "INSERT INTO orders (user_id, order_date, total_price, status_id, name, address, phone, payment_method, payment_status, transaction_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUser_id());
            stmt.setTimestamp(2, new Timestamp(order.getOrder_date().getTime()));
            stmt.setDouble(3, order.getTotal_price());
            stmt.setInt(4, order.getStatus_id());
            stmt.setString(5, order.getName());
            stmt.setString(6, order.getAddress());
            stmt.setString(7, order.getPhone());
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

    // Cập nhật trạng thái thanh toán
    public void updatePaymentStatus(String transactionId) throws SQLException {
        String sql = "UPDATE orders SET payment_status = 1 WHERE transaction_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transactionId);
            stmt.executeUpdate();
        }
    }
    
    // Cập nhật trạng thái đơn hàng
    public boolean updateOrderStatus(int orderId, int statusId) throws SQLException {
        String sql = "UPDATE orders SET status_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, statusId);
            stmt.setInt(2, orderId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
    
    // Lấy danh sách đơn hàng của user
    public List<orders> getOrdersByUserId(int userId) {
        List<orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return ordersList;
    }
    
    // Lấy thông tin chi tiết của một đơn hàng theo id và user id
    public orders getOrderById(int orderId, int userId) {
        orders order = null;
        String sql = "SELECT * FROM orders WHERE id = ? AND user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
    
    // Lấy thông tin chi tiết của một đơn hàng theo id (cho admin)
    public orders getOrderById(int orderId) {
        orders order = null;
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
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
                
                // Lấy danh sách các mục trong đơn hàng
                order.setOrder_items(orderItemsService.getOrderItemsByOrderId(orderId));
                
                // Tính tổng số sản phẩm trong đơn hàng
                int totalItems = 0;
                if(order.getOrder_items() != null) {
                    for(entities.order_items item : order.getOrder_items()) {
                        totalItems += item.getQuantity();
                    }
                }
                order.setTotalItems(totalItems);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return order;
    }
    
    // Lấy tất cả đơn hàng có phân trang
    public List<orders> getAllOrders(int page, int pageSize) {
        List<orders> ordersList = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        
        String sql = "SELECT * FROM orders ORDER BY order_date DESC LIMIT ?, ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
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
                
                // Lấy danh sách các mục trong đơn hàng
                order.setOrder_items(orderItemsService.getOrderItemsByOrderId(order.getId()));
                
                // Tính tổng số sản phẩm trong đơn hàng
                int totalItems = 0;
                if(order.getOrder_items() != null) {
                    for(entities.order_items item : order.getOrder_items()) {
                        totalItems += item.getQuantity();
                    }
                }
                order.setTotalItems(totalItems);
                
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ordersList;
    }
    
    // Lấy tổng số đơn hàng
    public int getTotalOrders() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM orders";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return count;
    }
}