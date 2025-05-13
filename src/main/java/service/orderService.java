package service;

import java.sql.SQLException;
import java.util.List;

import connectDatabase.connect;
import dao.order_DAO;
import entities.orders;

public class orderService {
    order_DAO orderDao = new order_DAO(connect.getConnections());
    
    // Lấy thông tin chi tiết của một đơn hàng
    public orders getOrderById(int orderId, int userId) {
        return orderDao.getOrderById(orderId, userId);
    }
    
    // Thêm phương thức lấy danh sách đơn hàng của user
    public List<orders> getOrdersByUserId(int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    // hàm insert order mới
    public int insertOrder(orders order) {
        try {
            return orderDao.insertOrder(order);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    // Cập nhật trạng thái thanh toán
    public void updatePaymentStatus(String transactionId) {
        try {
            orderDao.updatePaymentStatus(transactionId);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}