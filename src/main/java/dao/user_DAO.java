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
	
	//  hàm updateuser by id
	public boolean updateUserById(int id,user user) {
		String query="UPDATE users SET name=?,email=?,phone=?,avatar=?,role_id=? WHERE id=? ";
		try(PreparedStatement stmt =conn.prepareStatement(query)){
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setString(4, user.getAvatar());
			stmt.setInt(5, user.getRole_id());
			stmt.setInt(6, id);
			int rs=stmt.executeUpdate();
			return rs>0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// hàm thêm user 
	public boolean addUser(String name,String email,String phone,String password,int role_id,String avatar) {
		String query="INSERT INTO users(name,email,phone,password,role_id,avatar) VALUES (?,?,?,?,?,?)";
		try(PreparedStatement stmt=conn.prepareStatement(query)){
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, phone);
			stmt.setString(4, password);
			stmt.setInt(5, role_id);
			stmt.setString(6, avatar);
			int rs=stmt.executeUpdate();
			return rs>0;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Đăng ký không thành công !");
		}
		return false;
	}
	public static void main(String[] args) {
		Connection conn=connect.getConnections();
		user_DAO dao=new user_DAO(conn);
		boolean a= dao.addUser("quan", "quan12@gmail.com", "12314324","123456", 2,"ok.jsp");
		if(a) {
			System.out.println("thành cong");
		}else {
			System.out.println("thât bại");
		}
	}
	
	
}

