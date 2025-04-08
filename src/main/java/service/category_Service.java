package service;

import java.util.List;

import connectDatabase.connect;
import dao.category_DAO;
import dao.user_DAO;
import entities.category;
import entities.user;

public class category_Service {
	category_DAO categoryDao=new category_DAO(connect.getConnections());
	// hàm service lấy ra dánh sách user
		public List<category> getAllCategory(){
			return categoryDao.getAllCategory();
		}
		// hàm service lấy ra user theo id
		public category getCategoryById(int id) {
			return categoryDao.getCategoryById(id);
		}
		// hàm xóa user
		public boolean deleteCategoryById(int id) {
			return categoryDao.deleteCategoryById(id);
		}
		// hàm update 
		public boolean updateCategoryById(int id,category category) {
			return categoryDao.updateCategoryById(id, category);
		}
		// hàm thêm user
		public boolean insertUser(String name,String image) {
			return categoryDao.addCategory(name, image);
		}
}