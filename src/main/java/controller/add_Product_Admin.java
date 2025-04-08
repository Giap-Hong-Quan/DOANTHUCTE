package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.category;
import entities.product;
import service.category_Service;
import service.product_Service;
@MultipartConfig
@WebServlet(name = "addProductAdminServlet",urlPatterns = "/admin/product/add")
public class add_Product_Admin extends HttpServlet {
	private category_Service categoryService=new category_Service();
	private product_Service productService=new product_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<category> listCategory=categoryService.getAllCategory();
		req.setAttribute("listCategory", listCategory);
		req.getRequestDispatcher("/addproduct_admin.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
	    
	    try {
	        // Lấy tham số từ form
	        String name = req.getParameter("name");
	        String description = req.getParameter("description");
	        String prices = req.getParameter("price");
	        String quantitys = req.getParameter("quantity");
	        String categoryIds = req.getParameter("category_id");

	        double price = Double.parseDouble(prices);
	        int quantity = Integer.parseInt(quantitys);
	        int categoryId = Integer.parseInt(categoryIds);
	        
	        // Tạo đối tượng product
	        product product = new product();
	        product.setName(name);
	        product.setDescription(description);
	        product.setPrice(price);
	        product.setQuantity(quantity);
	        product.setCategory_id(categoryId);
	        
	        // Lấy danh sách các ảnh
	        List<Part> imageParts = new ArrayList<>();
	        for (Part part : req.getParts()) {
	            if (part.getName().equals("image") && part.getSize() > 0) {
	                imageParts.add(part);
	            }
	        }

	        // Gọi service xử lý
	        ServletContext context = req.getServletContext();
	        boolean addproduct = productService.insertProductAndImages(product, imageParts, context);
	        
	        if (!addproduct) {
	            req.getSession().setAttribute("error", "Thêm sản phẩm thất bại.");
	        } else {
	            req.getSession().setAttribute("success", "Thêm sản phẩm thành công.");
	        }
	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        req.getSession().setAttribute("error", "Dữ liệu số không hợp lệ.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        req.getSession().setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
	    }
	    
	    resp.sendRedirect(req.getContextPath() + "/admin/product");
	}
}
