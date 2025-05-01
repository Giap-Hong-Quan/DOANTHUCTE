package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.product;
import service.category_Service;
import service.product_Service;

@WebServlet(name = "productSevrvlet",urlPatterns = "/product/*")
public class products extends HttpServlet {
	 private product_Service productService = new product_Service();
		private category_Service categoryService=new category_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 try {
	            String pathInfo = req.getPathInfo(); 
	            List<product> products = new ArrayList<>();
	            int page = 1;  // trang mặc định
	            String pageParam = req.getParameter("page");
	            if (pageParam != null) {
	                page = Integer.parseInt(pageParam);
	            }
	            int pageSize = 9;  // số sản phẩm mỗi trang
	            if (pathInfo == null || pathInfo.equals("/")) {
	                // Nếu không có path info => hiện tất cả sản phẩm
	                products = productService.getAllProducts(page);
	            } else {
	                // Có path info dạng /1, /2,... để lọc theo category
	                String categoryIdStr = pathInfo.substring(1); // bỏ dấu '/'
	                int categoryId = Integer.parseInt(categoryIdStr);
	                products = productService.getProductsByCategoryId(categoryId, page);
	            }
	            
	            req.setAttribute("products", products);
	            req.setAttribute("currentPage", page);
	            
	            // Tính tổng số trang
	            int totalPages = productService.getTotalPages(pageSize);  // Sử dụng phương thức đã có
	            req.setAttribute("totalPages", totalPages);
	            
	            req.getRequestDispatcher("/product.jsp").forward(req, resp);
	        } catch (Exception e) {
	            e.printStackTrace();
	           
	        }
	    }
}
