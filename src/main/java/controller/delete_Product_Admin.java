package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.product_Service;

@WebServlet(name = "deleteProductAdminServlet",urlPatterns = "/admin/product/delete/*")
public class delete_Product_Admin extends HttpServlet {
	private product_Service productService=new product_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
        
        if (pathInfo == null || pathInfo.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/product");
            return;
        }
		try {
			  String productIdStr = pathInfo.substring(1); // loại bỏ dấu "/"
		      int productId = Integer.parseInt(productIdStr);
		      boolean success = productService.deleteProduct(productId);
	            if (!success) {
	                // Gán thông báo thất bại vào session
	                req.getSession().setAttribute("error", "Xóa sản phẩm thất bại.");
	            } else {
	                // Gán thông báo thành công vào session
	                req.getSession().setAttribute("success", "Xóa sản phẩm thành công.");
	            }
	            // Redirect về danh sách người dùng
	            resp.sendRedirect(req.getContextPath() + "/admin/product");
		}catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/admin/product");
		}
	}
}
