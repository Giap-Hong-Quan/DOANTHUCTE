package controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet(name = "OrderDetailServlet", urlPatterns = "/purchase-history/detail/*")
public class OrderDetailServlet extends HttpServlet {
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
        
        // 2. Lấy ID đơn hàng từ URL
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/purchase-history");
            return;
        }
        System.out.println(pathInfo);
        try {
            int orderId = Integer.parseInt(pathInfo.substring(1));
            System.out.println(orderId);
            // 3. Lấy thông tin đơn hàng
            orders order = orderService.getOrderById(orderId, u.getId());
            
            if (order == null) {
                // Nếu không tìm thấy đơn hàng hoặc đơn hàng không thuộc về user này
                resp.sendRedirect(req.getContextPath() + "/purchase-history");
                return;
            }
            
            // 4. Lấy danh sách các sản phẩm trong đơn hàng
            List<order_items> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            
            // 5. Đặt thông tin vào request
            req.setAttribute("order", order);
            req.setAttribute("orderItems", orderItems);
            req.setAttribute("user", u);
            
            // 6. Forward đến trang JSP hiển thị chi tiết đơn hàng
            req.getRequestDispatcher("/order-detail.jsp").forward(req, resp);
            
        } catch (NumberFormatException e) {
            // Nếu ID không hợp lệ
            resp.sendRedirect(req.getContextPath() + "/purchase-history");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/purchase-history");
        }
    }
}