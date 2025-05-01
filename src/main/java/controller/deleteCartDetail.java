package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;

import entities.cart;
import entities.user;
import service.cart_detail_Service;
import service.cartService;

@WebServlet("/cart/delete")
public class deleteCartDetail extends HttpServlet {
    private static final long serialVersionUID = 1L; // Thêm serialVersionUID
    
    private cartService cartService = new cartService();
    private cart_detail_Service cartDetailService = new cart_detail_Service();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        JsonObject jsonResponse = new JsonObject();

        try {
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
            if (u == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Bạn chưa đăng nhập");
                out.print(jsonResponse.toString());
                return;
            }

            // 2. Lấy và validate productId
            String productIdParam = req.getParameter("productId");
            if (productIdParam == null || productIdParam.isEmpty()) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Thiếu thông tin sản phẩm");
                out.print(jsonResponse.toString());
                return;
            }

            int productId;
            try {
                productId = Integer.parseInt(productIdParam);
            } catch (NumberFormatException e) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "ID sản phẩm không hợp lệ");
                out.print(jsonResponse.toString());
                return;
            }

            // 3. Lấy giỏ hàng
            cart cart = cartService.getCartByUserId(u.getId());
            if (cart == null) {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Không tìm thấy giỏ hàng");
                out.print(jsonResponse.toString());
                return;
            }

            // 4. Thực hiện xóa
            boolean deleted = cartDetailService.deleteCartDetail(cart.getId(), productId);
            if (deleted) {
                // Tính lại tổng giỏ hàng
                double cartTotal = cartService.calculateCartTotal(cart.getId());

                jsonResponse.addProperty("success", true);
                jsonResponse.addProperty("productId", productId);
                jsonResponse.addProperty("cartTotal", cartTotal);
                
            } else {
                jsonResponse.addProperty("success", false);
                jsonResponse.addProperty("message", "Xóa sản phẩm thất bại");
            }

        } catch (Exception e) {
            jsonResponse.addProperty("success", false);
            jsonResponse.addProperty("message", "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        } finally {
            out.print(jsonResponse.toString());
            out.flush();
        }
    }
}