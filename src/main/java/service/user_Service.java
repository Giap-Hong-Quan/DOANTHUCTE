package service;

import java.util.List;

import connectDatabase.connect;
import dao.user_DAO;
import entities.user;

public class user_Service {
	user_DAO userDao=new user_DAO(connect.getConnections());
	// hàm service lấy ra dánh sách user
	public List<user> getAllUser(){
		return userDao.getAllUser();
	}
	// hàm service lấy ra user theo id
	public user getUserById(int id) {
		return userDao.getUserById(id);
	}
	// hàm xóa user
	public boolean deleteUserById(int id) {
		return userDao.deleteUserById(id);
	}
}
