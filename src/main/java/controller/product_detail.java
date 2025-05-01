package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.product;
import service.product_Service;

@WebServlet(name = "productDetailServlet",urlPatterns = "/product_detail/*")
public class product_detail extends HttpServlet{
	private product_Service productService=new product_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		  String pathInfo = req.getPathInfo();
	        
	        if (pathInfo == null || pathInfo.isEmpty()) {
	            resp.sendRedirect(req.getContextPath() + "/product");
	            return;
	        }
			try {
				  String productIdStr = pathInfo.substring(1); // loại bỏ dấu "/"
			      int productId = Integer.parseInt(productIdStr);
			      
			      product product=productService.getProductById(productId);
			      System.out.println("🔍 product tìm thấy: " + product);
			      if(product==null) {
			    	  resp.sendRedirect("/admin/product"); // Nếu không tìm thấy user, quay lại danh sách user
			          return;
			      }
			      req.setAttribute("product", product);
			      req.getRequestDispatcher("/productDetail.jsp").forward(req, resp);
			
			} catch (Exception e) {
				resp.sendRedirect(req.getContextPath() + "/admin/product");
			}
	}
}
