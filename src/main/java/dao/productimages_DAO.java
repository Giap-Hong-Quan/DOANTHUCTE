package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entities.productimages;

public class productimages_DAO {
	private Connection conn;
	public productimages_DAO(Connection conn) {
		this.conn=conn;
	}
	// hàm thêm ảnh 
	public boolean addimages(productimages productimage) {
		String query="INSERT INTO productimages(product_id,image) VALUES(?,?)";
		try(PreparedStatement stmt=conn.prepareStatement(query)){
			stmt.setInt(1, productimage.getProduct_id());
			stmt.setString(2, productimage.getImage());
			int rs=stmt.executeUpdate();
			return rs>0;
		}catch (Exception e) {
			  e.printStackTrace();
			  System.out.println("Thêm hình ảnh thất bại");
		}
		return false;
	}
}
