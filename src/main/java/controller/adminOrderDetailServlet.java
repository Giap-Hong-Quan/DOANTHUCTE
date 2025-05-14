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

@WebServlet(name = "AdminOrderDetailServlet", urlPatterns = "/admin/order/detail/*")
public class adminOrderDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private orderItemsService orderItemService = new orderItemsService();
    private orderService orderService = new orderService();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        // 1. Lấy user từ session
        HttpSession session = req.getSession();
        user currentUser = (user) session.getAttribute("user");
        
        // Kiểm tra xem có user đăng nhập không
        if (currentUser == null) {
            currentUser = (user) session.getAttribute("userNormal");
        }
        if (currentUser == null) {
            currentUser = (user) session.getAttribute("userGG");
        }
        if (currentUser == null) {
            currentUser = (user) session.getAttribute("userFB");
        }
        
        if (currentUser == null) {
            // Nếu không có user, chuyển hướng về trang đăng nhập
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        // Kiểm tra xem người dùng có phải là admin không (role_id = 1)
        if (currentUser.getRole_id() != 1) {
            session.setAttribute("error", "Bạn không có quyền truy cập tính năng này");
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        
        // 2. Lấy ID đơn hàng từ URL
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/admin/orders");
            return;
        }
        
        try {
            int orderId = Integer.parseInt(pathInfo.substring(1));
            
            // 3. Lấy thông tin đơn hàng
            orders order = orderService.getOrderById(orderId);
            
            if (order == null) {
                // Nếu không tìm thấy đơn hàng
                session.setAttribute("error", "Không tìm thấy đơn hàng với mã #" + orderId);
                resp.sendRedirect(req.getContextPath() + "/admin/orders");
                return;
            }
            
            // 4. Lấy danh sách các sản phẩm trong đơn hàng
            List<order_items> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
            
            // 5. Đặt thông tin vào request
            req.setAttribute("order", order);
            req.setAttribute("orderItems", orderItems);
            
            // 6. Forward đến trang JSP hiển thị chi tiết đơn hàng
            req.getRequestDispatcher("/order-detail-admin.jsp").forward(req, resp);
            
        } catch (NumberFormatException e) {
            // Nếu ID không hợp lệ
            session.setAttribute("error", "Mã đơn hàng không hợp lệ");
            resp.sendRedirect(req.getContextPath() + "/admin/orders");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            resp.sendRedirect(req.getContextPath() + "/admin/orders");
        }
    }
}