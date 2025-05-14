package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

import connectDatabase.connect;
import dao.order_DAO;
import entities.orders;
import entities.user;
import service.orderService;

@WebServlet(name = "dashboardAdminServlet", urlPatterns = "/admin/dashboardAdmin")
public class dashboardAdmin extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        user user = null;
        
        // Kiểm tra loại session nào còn tồn tại
        if (session.getAttribute("userNormal") != null) {
            user = (user) session.getAttribute("userNormal");
        } else if (session.getAttribute("userGG") != null) {
            user = (user) session.getAttribute("userGG");
        } else if (session.getAttribute("userFB") != null) {
            user = (user) session.getAttribute("userFB");
        }
        
        if (user != null) {
            // Lấy dữ liệu thống kê
            orderService orderService = new orderService();
            
            // 1. Tổng số đơn hàng
            int totalOrders = orderService.getTotalOrders();
            req.setAttribute("totalOrders", totalOrders);
            
            // 2. Tổng doanh thu - tính tổng các total_price của tất cả đơn hàng
            double totalRevenue = getTotalRevenue();
            req.setAttribute("totalRevenue", totalRevenue);
            
            // 3. Tổng sản phẩm đã bán - tính tổng quantity của tất cả order_items
            int totalSoldItems = getTotalSoldItems();
            req.setAttribute("totalSoldItems", totalSoldItems);
            
            // 4. Dữ liệu biểu đồ doanh thu theo tháng
            Map<String, Double> monthlyRevenue = getMonthlyRevenue();
            req.setAttribute("monthlyRevenue", monthlyRevenue);
            
            // 5. Lấy danh sách 10 đơn hàng mới nhất
            List<orders> recentOrders = getRecentOrders(10);
            req.setAttribute("recentOrders", recentOrders);
            
            req.setAttribute("user", user);
            req.getRequestDispatcher("/dashboardAdmin.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
    
    // Lấy tổng doanh thu - tính tổng tất cả total_price
    private double getTotalRevenue() {
        double totalRevenue = 0;
        String sql = "SELECT SUM(total_price) as total FROM orders";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalRevenue;
    }
    
    // Lấy tổng số sản phẩm đã bán - tính tổng tất cả quantity
    private int getTotalSoldItems() {
        int totalItems = 0;
        String sql = "SELECT SUM(quantity) as total FROM order_items";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalItems = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalItems;
    }
    
    // Lấy doanh thu theo tháng cho năm hiện tại
    private Map<String, Double> getMonthlyRevenue() {
        Map<String, Double> monthlyRevenue = new HashMap<>();
        String sql = "SELECT MONTH(order_date) as month, SUM(total_price) as revenue " +
                     "FROM orders WHERE YEAR(order_date) = YEAR(CURRENT_DATE()) " +
                     "GROUP BY MONTH(order_date) ORDER BY month";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int month = rs.getInt("month");
                double revenue = rs.getDouble("revenue");
                
                // Tên tháng theo tiếng Việt
                String monthName = "Tháng " + month;
                monthlyRevenue.put(monthName, revenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return monthlyRevenue;
    }
    
    // Lấy danh sách đơn hàng mới nhất
    private List<orders> getRecentOrders(int limit) {
        List<orders> ordersList = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY order_date DESC LIMIT ?";
        
        try (Connection conn = connect.getConnections();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            
            orderService orderService = new orderService();
            while (rs.next()) {
                int orderId = rs.getInt("id");
                orders order = orderService.getOrderById(orderId);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ordersList;
    }
}