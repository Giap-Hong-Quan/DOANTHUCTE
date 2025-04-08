package service;

import java.io.IOException;
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
}
