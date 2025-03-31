package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connectDatabase.connect;
import entities.user;

public class login_DAO {
	private Connection conn;
	public login_DAO(Connection conn) {
		this.conn=conn;
	}
	
//Hàm register 
public boolean register(String name,String phone,String password) {
	String query="INSERT INTO users(name,phone,password) VALUES (?,?,?)";
	try(PreparedStatement stmt= conn.prepareStatement(query)){
		stmt.setString(1,name);
		stmt.setString(2,phone);
		stmt.setString(3,password);
	int rs=stmt.executeUpdate();
	return rs>0;
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println("Đăng ký không thành công !");
	}
	return false;
}
// lấy user theo fbid
private user getUsersByFBId(String facebook_id) {
	return getUserByColumn("facebook_id", facebook_id);
}
// lấy user theo ggid
private user getUsersByGGId(String google_id) {
	return getUserByColumn("google_id", google_id);
}
// hàm duyet qua user
public user mapUsers(ResultSet rs) throws SQLException{
	return new user(
			rs.getInt("id"), 
			rs.getString("name"), 
			rs.getString("email"), 
			rs.getString("google_id"), 
			rs.getString("facebook_id"), 
			rs.getString("phone"),
			rs.getString("password"), 
			rs.getString("avatar"),
			rs.getInt("role_id"),
			rs.getTimestamp("create_at"),
			rs.getTimestamp("update_at")
			) ;
}
// hàm thêm khi chưa từng đăng nhập
	public boolean insertUser(user user) {
		String query="INSERT INTO users(name,email,google_id,facebook_id,password,phone,avatar,role_id) VALUES (?,?,?,?,?,?,?,?)";
		try(PreparedStatement stmt=conn.prepareStatement(query)){
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getGoogle_id());
			stmt.setString(4, user.getFacebook_id());
			stmt.setString(5, user.getPassword());
			stmt.setString(6, user.getPhone());
			stmt.setString(7, user.getAvatar());
			stmt.setInt(8, user.getRole_id());
			int rsResultSet=stmt.executeUpdate();
			if(rsResultSet>0) {
				System.out.println("thêm thành công");
				return true;
			}else {
				System.out.println("thêm thất bại ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
//login bycomlum
public user getUserByColumn(String colum,String value) {
	String query="SELECT *FROM users WHERE "+ colum +"=?";
	try(PreparedStatement stmt=conn.prepareStatement(query)){
		stmt.setString(1, value);
		ResultSet rs=stmt.executeQuery();
		if(rs.next()) {
			return mapUsers(rs);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
}
// login vơi gg 
	public user loginGoogle(String name,String email,String google_id,String avatar) {
		user user=getUsersByGGId(google_id);
		try {
			if(user==null) {
				user=new user(name, email, google_id, null, null, null, avatar, 2);
				insertUser(user);
				user=getUsersByGGId(google_id);
			}
			
		} catch (Exception e) {
			 System.out.println("Lỗi khi insert user: " + e.getMessage());
	            e.printStackTrace();
		}
		if(user!=null) {
			System.out.println("user là"+ user.toString());
		}else {
			System.out.println("ko có ");
		}
		return user;
	}
// login vơi  fb
public user loginFaceBook(String name,String email,String facebook_id,String avatar) {
	user user=getUsersByFBId(facebook_id);
	try {
		if(user==null) {
			user=new user(name, email, null, facebook_id, null, null, avatar, 2);
			insertUser(user);
			user=getUsersByFBId(facebook_id);
		}
		
	} catch (Exception e) {
		 System.out.println("Lỗi khi insert user: " + e.getMessage());
            e.printStackTrace();
	}
	if(user!=null) {
		System.out.println("user là"+ user.toString());
	}else {
		System.out.println("ko có ");
	}
	return user;
}

// hàm login thương 
public user loginWithPhone(String phone) {
	String query="SELECT * FROM users WHERE phone=?";
	try(PreparedStatement stmt=conn.prepareStatement(query)){
		stmt.setString(1, phone);
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) {
			return mapUsers(rs);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	}


public static void main(String[] args) {
	 Connection conn = connect.getConnections(); 
	 // ✅ 2. Tạo đối tượng DAO để gọi hàm login
     login_DAO dao = new login_DAO(conn);

     // ✅ 3. Nhập số điện thoại test
     String phoneTest = "0335906807"; 
     // ✅ 4. Gọi hàm login và in kết quả
     user user = dao.loginWithPhone(phoneTest);
     if (user != null) {
         System.out.println("🎯 Đăng nhập thành công: " + user.toString());
     } else {
         System.out.println("❌ Không tìm thấy user với số điện thoại: " + phoneTest);
     }
}
}
