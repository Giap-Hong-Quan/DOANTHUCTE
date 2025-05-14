package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.orders;

@WebServlet(name = "listOrderAdminServlet",urlPatterns = "/admin/order")
public class list_Order_admin extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private service.orderService orderService = new service.orderService();
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	        // Xử lý phân trang
	        int page = 1;
	        int pageSize = 10;
	        
	        try {
	            if(req.getParameter("page") != null) {
	                page = Integer.parseInt(req.getParameter("page"));
	            }
	        } catch (NumberFormatException e) {
	            page = 1;
	        }
	        
	        // Lấy tổng số đơn hàng
	        int totalOrders = orderService.getTotalOrders();
	        
	        // Tính tổng số trang
	        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);
	        
	        // Lấy danh sách đơn hàng theo trang
	        List<orders> orders = orderService.getAllOrders(page, pageSize);
	        
	        // Set các thuộc tính vào request
	        req.setAttribute("orders", orders);
	        req.setAttribute("currentPage", page);
	        req.setAttribute("totalPages", totalPages);
	        req.setAttribute("pageSize", pageSize);
	        
	        // Forward request đến trang JSP
	        RequestDispatcher dispatcher = req.getRequestDispatcher("/listorder_admin.jsp");
	        dispatcher.forward(req, resp);
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
}
