package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entities.category;
import entities.user;

public class category_DAO {
	private Connection conn;
	public category_DAO (Connection conn) {
		this.conn=conn;
	}
	// hàm get list category
		public List<category> getAllCategory() {
		    List<category> listCategory = new ArrayList<>();
		    String query = "SELECT * FROM category ";
		    try (PreparedStatement stmt = conn.prepareStatement(query)) {
		        ResultSet rs = stmt.executeQuery();
		        while (rs.next()) {
		            int id = rs.getInt("id");
		            String name = rs.getString("name");
		            String image = rs.getString("image");
		            // Thêm role_name vào User (Cần sửa class User để chứa role_name)
		           listCategory.add(new category(id, name, image));
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return listCategory;
		}
		// hàm get by id
		public category getCategoryById(int id) {
			category category = null;
		    String query = "SELECT * FROM category WHERE id = ?";
		    
		    try (PreparedStatement stmt = conn.prepareStatement(query)) {
		        stmt.setInt(1, id);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		            int id1 = rs.getInt("id");
		            String name = rs.getString("name");
		            String image = rs.getString("image");
		            // Cần sửa constructor của class user để có thêm roleName
		            category = new category(id1, name, image);
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return category;
		}
		// hàm xóa 
		public boolean deleteCategoryById(int id) {
			String query="DELETE FROM category WHERE id = ?";
			try(PreparedStatement stmt=conn.prepareStatement(query)){
				stmt.setInt(1, id);
				int rs=stmt.executeUpdate();
				if(rs>0) {
					return true;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		//  hàm updateuser by id
		public boolean updateCategoryById(int id,category category) {
			String query="UPDATE category SET name=?,image=? WHERE id=? ";
			try(PreparedStatement stmt =conn.prepareStatement(query)){
				stmt.setString(1, category.getName());
				stmt.setString(2, category.getImage());
				stmt.setInt(3, id);
				int rs=stmt.executeUpdate();
				return rs>0;
			}catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		// hàm thêm 
		public boolean addCategory(String name,String image) {
			String query="INSERT INTO category (name,image) VALUES (?,?)";
			try(PreparedStatement stmt=conn.prepareStatement(query)){
				stmt.setString(1, name);
				stmt.setString(2, image);
				int rs=stmt.executeUpdate();
				return rs>0;
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("Đăng ký không thành công !");
			}
			return false;
		}
}
