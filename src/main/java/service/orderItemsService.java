package service;

import java.sql.SQLException;
import java.util.List;

import connectDatabase.connect;
import dao.order_DAO;
import dao.order_items_DAO;
import entities.order_items;

public class orderItemsService {
	order_items_DAO orderItemsService=new order_items_DAO(connect.getConnections());
	//
	public boolean insertOrderItems(int orderId, List<order_items> orderItems) {
	    try {
	        return orderItemsService.insertOrderItems(orderId, orderItems);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Trả về false khi có lỗi
	    }
	}
}
