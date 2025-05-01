package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import connectDatabase.connect;
import controller.ImageUtil;
import dao.product_DAO;
import dao.productimages_DAO;
import entities.product;
import entities.productimages;

public class product_Service {
	 private product_DAO productDao = new product_DAO(connect.getConnections());
	 private productimages_DAO productimagesDao = new productimages_DAO(connect.getConnections());
	 private ImageUtil imageUtil=new ImageUtil();
	 public boolean insertProductAndImages(product product, List<Part> imageParts, ServletContext context) {
		    int productId = productDao.addproduct(product); // trả về id sản phẩm sau khi thêm
		    if (productId <= 0) {
		        return false; // Thêm sản phẩm thất bại
		    }

		    for (Part part : imageParts) {
		        if (part.getSize() > 0) {
		            try {
		                String fileName = imageUtil.saveImage(part, context); // lưu ảnh và lấy tên file
		                productimages productImage = new productimages();
		                productImage.setProduct_id(productId); // gán id sản phẩm
		                productImage.setImage(fileName); // gán tên ảnh
		              boolean addimage=  productimagesDao.addimages(productImage); // thêm ảnh vào DB
		            } catch (IOException e) {
		                e.printStackTrace();
		                return false; // Nếu lỗi khi xử lý ảnh thì báo thất bại
		            }
		        }
		    }

		    return true; // Thành công
		}
	// ProductService.java
	 public List<product> getProducts(int page, int pageSize) {  // Bỏ sort
	     int offset = (page - 1) * pageSize;
	     return productDao.getAllProducts(offset, pageSize);  // Không truyền sort
	 }
	// Tính tổng số trang (getTotalPages)
	    public int getTotalPages(int pageSize) {
	        int totalProducts = productDao.countAllProducts();
	        return (int) Math.ceil((double) totalProducts / pageSize);
	    }
	// hàm sử lý lấy ra thông tin sản phẩm và hình ảnh  theo 
	    public product getProductById(int id) {
	    	product product=productDao.getProductById(id);
	    	if(product!=null) {
	    		product.setImages(productimagesDao.getImageByProductId(id));
	    	}
	    	return product;
	    }
	    // hàm xóa sản phẩm 
	    public boolean deleteProduct(int id) {
	        try {
	            // 1. Xóa ảnh trước
	            productimagesDao.deleteImagesByProductId(id);
	            
	            // 2. Xóa sản phẩm
	            return productDao.deleteProduct(id);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    // update
	    public boolean updateProduct(int id,product product, List<Part> imageParts, ServletContext context) {
	        try {
	            // 1. Cập nhật thông tin sản phẩm
	            boolean productUpdated = productDao.updateProduct(id, product);
	            
	            if (productUpdated) {
	                // 2. Xóa ảnh cũ
	                productimagesDao.deleteImagesByProductId(id);
	              
	    		    for (Part part : imageParts) {
	    		        if (part.getSize() > 0) {
	    		            try {
	    		                String fileName = imageUtil.saveImage(part, context); // lưu ảnh và lấy tên file
	    		                productimages productImage = new productimages();
	    		                productImage.setProduct_id(id); // gán id sản phẩm
	    		                productImage.setImage(fileName); // gán tên ảnh
	    		              boolean addimage=  productimagesDao.addimages(productImage); // thêm ảnh vào DB
	    		            } catch (IOException e) {
	    		                e.printStackTrace();
	    		                return false; // Nếu lỗi khi xử lý ảnh thì báo thất bại
	    		            }
	    		        }
	    		    }
	               
	            }
	            return productUpdated;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	   // hàm lấy ra 4 sản phẩm mới nhất theo id loại sản phẩm 
	    public List<product> getLatestProductsByCategory(int categoryId, int limit) {
	        List<product> products = productDao.getLatestProductsByCategory(categoryId, limit);

	        // Lặp qua từng sản phẩm để gán ảnh đầu tiên
	        for (product p : products) {
	            String firstImage = productimagesDao.getFirstImageByProductId(p.getId());

	            // Kiểm tra nếu có ảnh đầu tiên thì gán vào sản phẩm
	            if (firstImage != null) {
	                // Tạo một danh sách ảnh và thêm ảnh đầu tiên
	                List<productimages> imageList = new ArrayList<>();
	                imageList.add(new productimages(p.getId(), firstImage)); 
	                p.setImages(imageList);  // Gán danh sách ảnh vào sản phẩm
	            }
	        }

	        return products;
	    }
	    
	    
	    
	    // hàm lấy ra tất cả sản phẩm kèm 1 ảnh
	    public List<product> getAllProducts(int page) throws SQLException {
	        int productsPerPage = 9;
	        int offset = (page - 1) * productsPerPage;
	        List<product> products = productDao.getAllProduct(offset, productsPerPage);
	        
	        // Gán ảnh đầu tiên cho sản phẩm
	        for (product p : products) {
	            String firstImage = productimagesDao.getFirstImageByProductId(p.getId());
	            if (firstImage != null) {
	                List<productimages> imageList = new ArrayList<>();
	                imageList.add(new productimages(p.getId(), firstImage)); 
	                p.setImages(imageList);
	            }
	        }
	        return products;
	    }

	    
	    // lấy sản phẩm theo category 
	    public List<product> getProductsByCategoryId(int categoryId, int page) throws SQLException {
	        int productsPerPage = 9;
	        int offset = (page - 1) * productsPerPage;
	        List<product> products = productDao.getProductsByCategoryId(categoryId, offset, productsPerPage);
	        
	        // Gán ảnh đầu tiên cho sản phẩm
	        for (product p : products) {
	            String firstImage = productimagesDao.getFirstImageByProductId(p.getId());
	            if (firstImage != null) {
	                List<productimages> imageList = new ArrayList<>();
	                imageList.add(new productimages(p.getId(), firstImage));
	                p.setImages(imageList);
	            }
	        }
	        return products;
	    }

	    // hàm lấy ra tất cả sản phẩm kèm 1 ảnh
	    public product  getProductByIdd(int productId) throws SQLException {
	    	 product p = productDao.getProductById(productId);
	         // Lấy ảnh đầu tiên cho sản phẩm
	         String firstImage = productimagesDao.getFirstImageByProductId(productId);
	         if (firstImage != null) {
	             p.setImages(List.of(new productimages(productId, firstImage))); // set ảnh đầu tiên
	         }
	         return p;
	    }
}
