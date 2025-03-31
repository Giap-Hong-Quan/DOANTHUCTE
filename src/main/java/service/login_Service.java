package service;

import connectDatabase.connect;
import dao.login_DAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import entities.user;

public class login_Service {
	login_DAO loginDao=new login_DAO(connect.getConnections());
	
	// hàm hash password( argon2 mạnh nhất hiện tại )hơi chậm 
	public static String hashPassword(String password) {
		Argon2 argon2=Argon2Factory.create();
		return argon2.hash(10,65536,1 , password);
	}
	// hàm sử lý regiter service
	public boolean registerService(String name,String phone,String password) {
		String hashPassword=hashPassword(password);
		return loginDao.register(name, phone, hashPassword);
	}
	// hàm sử lý login 
	public user login(String phone,String password) {
		user user=loginDao.loginWithPhone(phone);
		if(user==null)return null;
		Argon2 argon2=Argon2Factory.create();
		if(argon2.verify(user.getPassword(), password)) {
			return user;
		}else {
			return null;
		}
		
	}
}
