package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.order_items;
import entities.user;
import service.orderItemsService;

@WebServlet(name ="PurchaseHistoryServlet",urlPatterns = "/purchase-history")
public class purchaseHistory extends HttpServlet {
	private orderItemsService orderItemService=new orderItemsService()	;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		   // 1. Lấy user từ session
        HttpSession session = req.getSession();
        user u = (user) session.getAttribute("user");
        if (u == null) {
            u = (user) session.getAttribute("userNormal");
        }
        if (u == null) {
            u = (user) session.getAttribute("userGG");
        }
        if (u == null) {
            u = (user) session.getAttribute("userFB");
        }

        // Đưa user vào request (nếu cần dùng bên jsp)
        req.setAttribute("user", u);
        try {
        	 
            List<order_items> orderItems = orderItemService.getAllOrderItemsByUserId(u.getId());
            req.setAttribute("orderItems", orderItems);
            req.getRequestDispatcher("/purchase-history.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
