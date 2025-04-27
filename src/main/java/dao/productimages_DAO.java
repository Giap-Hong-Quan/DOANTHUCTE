package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
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
	// hàm lấy ra tất cả ảnh theo id sản phẩm
	public List<productimages> getImageByProductId(int id){
		List<productimages> listProductimage=new ArrayList<productimages>();
		String query="SELECT * FROM productimages WHERE product_id = ?";
		try(PreparedStatement stmt=conn.prepareStatement(query)){
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				int id1=rs.getInt("id");
				int product_id =rs.getInt("product_id");
				String image=rs.getString("image");
				listProductimage.add(new productimages(id1, product_id, image));
			}
		}catch (Exception e) {
			  e.printStackTrace();
		}
		return listProductimage;
	}
	
	// Hàm xóa tất cả ảnh của sản phẩm
	public boolean deleteImagesByProductId(int productId) {
	    String query = "DELETE FROM productimages WHERE product_id = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, productId);
	        int rs = stmt.executeUpdate();
	        return rs >= 0; // Trả về true ngay cả khi không có ảnh nào để xóa
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	// hàm lấy ra 1 ảnh đầu tiên theo id sản phẩm 
	public String getFirstImageByProductId(int productId) {
	    String query = "SELECT image FROM productimages WHERE product_id = ? LIMIT 1";
	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, productId);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return rs.getString("image");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null; // nếu không có ảnh nào
	}

	public static void main(String[] args) {
		Connection conn=connect.getConnections();
		productimages_DAO a=new productimages_DAO(conn);
		System.out.println(a.getFirstImageByProductId(20));
	}
}
