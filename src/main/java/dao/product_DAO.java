package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDatabase.connect;
import entities.product;

public class product_DAO {
	private Connection conn;
	public product_DAO(Connection conn) {
		this.conn=conn;
	}
	// ham thêm sản phâm 
	public int addproduct(product product) {
	    String query = "INSERT INTO products (category_id, name, description, price, quantity) VALUES (?, ?, ?, ?, ?)";
	    // Thêm tham số Statement.RETURN_GENERATED_KEYS
	    try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setInt(1, product.getCategory_id());
	        stmt.setString(2, product.getName());
	        stmt.setString(3, product.getDescription());
	        stmt.setDouble(4, product.getPrice());
	        stmt.setInt(5, product.getQuantity());
	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            return rs.getInt(1); // Trả về product_id
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Thêm sản phẩm thất bại");
	    }
	    return 0;
	}
	// ProductDAO.java
	public List<product> getAllProducts(int offset, int limit) {
	    List<product> products = new ArrayList<>();
	    String query = "SELECT p.*, c.name AS category_name " +
	                   "FROM products p " +
	                   "JOIN category c ON p.category_id = c.id " +
	                   "LIMIT ?, ?";

	    try (PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, offset);
	        stmt.setInt(2, limit);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            int category_id = rs.getInt("category_id");
	            String name = rs.getString("name");
	            double price = rs.getDouble("price");
	            int quantity = rs.getInt("quantity");
	            String categoryName = rs.getString("category_name");

	            // Nếu class product có constructor nhận categoryName thì truyền luôn
	            product p = new product(id, category_id, name, price, quantity);
	            p.setCategoryName(categoryName); // hoặc truyền trong constructor nếu có
	            products.add(p);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return products;
	}

	
	 // Đếm tổng số sản phẩm (dùng cho tính tổng số trang)
    public int countAllProducts() {
        String query = "SELECT COUNT(*) FROM products";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //
    public product getProductById(int id) {
        product product = null;
        String query = "SELECT p.*, c.name as category_name " +
                       "FROM products p " +
                       "JOIN category c ON p.category_id = c.id " +
                       "WHERE p.id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id1 = rs.getInt("id");
                int category_id = rs.getInt("category_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String categoryName = rs.getString("category_name"); // lấy thêm tên category

                // Bạn cần sửa lớp product để nhận thêm category name nếu muốn
                product = new product(id1, category_id, name, description, price, quantity);
                product.setCategoryName(categoryName); // giả sử bạn có setter hoặc constructor mở rộng
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

 // Hàm xóa sản phẩm theo ID
    public boolean deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rs = stmt.executeUpdate();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // update sản phẩm
    public boolean updateProduct(int id,product product) {
    	String query="UPDATE products SET category_id=?,name=?,description=?,price=?,quantity=? WHERE id=?";
    	try(PreparedStatement stmt=conn.prepareStatement(query)){
    		stmt.setInt(1, product.getCategory_id());
			stmt.setString(2, product.getName());
			stmt.setString(3, product.getDescription());
			stmt.setDouble(4, product.getPrice());
			stmt.setInt(5, product.getQuantity());
			stmt.setInt(6, id);
			int rs=stmt.executeUpdate();
			return rs>0;
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	return false;
    }
    
    
    public List<product> getLatestProductsByCategory(int category_id, int limit) {
        List<product> products = new ArrayList<>();
        String sql = "SELECT p.*, c.name AS category_name " +
                "FROM products p JOIN category c ON p.category_id = c.id " +
                "WHERE p.category_id = ? " +
                "ORDER BY p.create_at DESC LIMIT ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, category_id);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int category_Id = rs.getInt("category_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String categoryName = rs.getString("category_name");

                product p = new product(id, category_Id, name, description, price, quantity, categoryName);
                System.out.println("Retrieved product: ID=" + id + ", Name=" + name);
                p.setCategoryName(categoryName);
                products.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

 // Lấy tất cả sản phẩm
    public List<product> getAllProduct(int offset, int limit) throws SQLException {
        List<product> list = new ArrayList<>();
        String sql = "SELECT * FROM products LIMIT ? OFFSET ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limit);  // số sản phẩm mỗi trang
            stmt.setInt(2, offset); // trang nào, tương ứng với OFFSET
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int category_Id = rs.getInt("category_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                list.add(new product(id, category_Id, name, description, price, quantity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<product> getProductsByCategoryId(int categoryId, int offset, int limit) throws SQLException {
        List<product> list = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category_id = ? LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, categoryId);
        ps.setInt(2, limit);  // số sản phẩm mỗi trang
        ps.setInt(3, offset); // trang nào, tương ứng với OFFSET
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            int category_Id = rs.getInt("category_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            list.add(new product(id, category_Id, name, description, price, quantity));
        }
        return list;
    }

    public String getProductNameById(int productId) {
        String name = "";
        try {
            String sql = "SELECT name FROM products WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    
    public static void main(String[] args) {
		Connection conn=connect.getConnections();
		product_DAO a=new product_DAO(conn);
		System.out.println(a.getProductNameById(29));
	}
	
}
