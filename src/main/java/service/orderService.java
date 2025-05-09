package service;

import java.sql.SQLException;

import connectDatabase.connect;
import dao.order_DAO;
import dao.user_DAO;
import entities.orders;

public class orderService {
	order_DAO orderDao=new order_DAO(connect.getConnections());
	 
    // Lấy thông tin chi tiết của một đơn hàng
    public orders getOrderById(int orderId, int userId) {
        return orderDao.getOrderById(orderId, userId);
    }
	// hàm 
	public int insertOrder(orders order) {
		try {
			return orderDao.insertOrder(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	// 
	public void updatePaymentStatus(String transactionId) {
		try {
			orderDao.updatePaymentStatus(transactionId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
