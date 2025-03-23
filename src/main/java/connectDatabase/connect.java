package connectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class connect {
	public static Connection getConnections() {
		// Thông tin kết nối
		String url = "jdbc:mysql://localhost:3306/angito";
		String username = "root"; 
		String password = "";
		// Tạo đối tượng kết nối
		Connection conn = null;
		try {
			//1 đăng ký drive 
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2 kết nối
		conn=DriverManager.getConnection(url,username,password);
		System.out.println("Kết nối cơ sở dữ liệu thành công!");
		} catch (Exception e) {
			 System.out.println("Kết nối database thất bại!");
	            e.printStackTrace();
		}
		return conn;
	}
	public static void main(String[] args) {
		getConnections();
	}
}
