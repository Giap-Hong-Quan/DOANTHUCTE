package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.product;
import service.product_Service;

@WebServlet(name = "listProductAdminServlet",urlPatterns = "/admin/product")
public class list_Product_Amdin extends HttpServlet {
	private product_Service product_Service=new product_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int page = 1;
		    int pageSize = 10;

		    try {
		        page = Integer.parseInt(req.getParameter("page"));
		        pageSize = Integer.parseInt(req.getParameter("pageSize"));
		    } catch (NumberFormatException e) {
		        // Giữ giá trị mặc định nếu có lỗi
		    }

		    List<product> products = product_Service.getProducts(page, pageSize);
		    int totalPages = product_Service.getTotalPages(pageSize);

		    req.setAttribute("products", products);
		    req.setAttribute("currentPage", page);
		    req.setAttribute("totalPages", totalPages);
		    // Chuyển sang trang JSP hiển thị
	        req.getRequestDispatcher("/listproduct_admin.jsp").forward(req, resp);
	}
}
