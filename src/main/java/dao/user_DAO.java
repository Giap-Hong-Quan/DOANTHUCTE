package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import entities.user;

public class user_DAO {
	private Connection conn;
	public user_DAO(Connection conn) {
		this.conn=conn;
	}
	
	// hàm get list user
	public List<user> getAllUser() {
	    List<user> listUser = new ArrayList<>();
	    String query = "SELECT u.*, r.name AS role_name FROM users u JOIN roles r ON u.role_id = r.id";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String google_id = rs.getString("google_id");
	            String facebook_id = rs.getString("facebook_id");
	            String phone = rs.getString("phone");
	            String password = rs.getString("password");
	            String avatar = rs.getString("avatar");
	            int role_id = rs.getInt("role_id");
	            String role_name = rs.getString("role_name"); // Lấy tên của role
	            Timestamp create_at = rs.getTimestamp("create_at");
	            Timestamp update_at = rs.getTimestamp("update_at");

	            // Thêm role_name vào User (Cần sửa class User để chứa role_name)
	            listUser.add(new user(id, name, email, google_id, facebook_id, phone, password, avatar, role_id, role_name, create_at, update_at));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listUser;
	}

	public user getUserById(int id) {
	    user user = null;
	    String query = "SELECT u.*, r.name AS role_name FROM users u JOIN roles r ON u.role_id = r.id WHERE u.id = ?";
	    
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int id1 = rs.getInt("id");
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            String google_id = rs.getString("google_id");
	            String facebook_id = rs.getString("facebook_id");
	            String phone = rs.getString("phone");
	            String password = rs.getString("password");
	            String avatar = rs.getString("avatar");
	            int role_id = rs.getInt("role_id");
	            String role_name = rs.getString("role_name"); // Lấy tên role từ alias
	            Timestamp create_at = rs.getTimestamp("create_at");
	            Timestamp update_at = rs.getTimestamp("update_at");

	            // Cần sửa constructor của class user để có thêm roleName
	            user = new user(id1, name, email, google_id, facebook_id, phone, password, avatar, role_id, role_name, create_at, update_at);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return user;
	}

	
	// hàm xóa user
	public boolean deleteUserById(int id) {
		String query="DELETE FROM users WHERE id = ?";
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
	public static void main(String[] args) {
		Connection conn=connect.getConnections();
		user_DAO dao=new user_DAO(conn);
		System.out.println( dao.deleteUserById(34));
	}
}

