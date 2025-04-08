package service;

import java.util.List;

import connectDatabase.connect;
import dao.user_DAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.user;

public class user_Service {
	user_DAO userDao=new user_DAO(connect.getConnections());
	// hàm hash password( argon2 mạnh nhất hiện tại )hơi chậm 
		public static String hashPassword(String password) {
			Argon2 argon2=Argon2Factory.create();
			return argon2.hash(10,65536,1 , password);
		}
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
	// hàm update 
	public boolean updateUserById(int id,user user) {
		return userDao.updateUserById(id, user);
	}
	// hàm thêm user
	public boolean insertUser(String name,String email,String phone,String password,int role_id,String avatar) {
		String hashPassword=hashPassword(password);
		return userDao.addUser(name, email, phone, hashPassword, role_id, avatar);
	}
}
