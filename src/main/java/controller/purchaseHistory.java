package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.orders;
import entities.order_items;
import entities.user;
import service.orderItemsService;
import service.orderService;

@WebServlet(name = "PurchaseHistoryServlet", urlPatterns = "/purchase-history")
public class purchaseHistory extends HttpServlet {
    private orderItemsService orderItemService = new orderItemsService();
    private orderService orderService = new orderService();
    
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
        
        if (u == null) {
            // Nếu không có user, chuyển hướng về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        // Đưa user vào request (nếu cần dùng bên jsp)
        req.setAttribute("user", u);
        
        try {
            // Lấy danh sách đơn hàng của người dùng
            List<orders> ordersList = orderService.getOrdersByUserId(u.getId());
            
            // Đếm số lượng sản phẩm trong mỗi đơn hàng
            for (orders order : ordersList) {
                List<order_items> items = orderItemService.getOrderItemsByOrderId(order.getId());
                int totalItems = 0;
                for (order_items item : items) {
                    totalItems += item.getQuantity();
                }
                // Thêm thuộc tính để đếm tổng số sản phẩm
                order.setTotalItems(totalItems);
            }
            
            req.setAttribute("orders", ordersList);
            req.getRequestDispatcher("/purchase-history.jsp").forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath());
        }
    }
}