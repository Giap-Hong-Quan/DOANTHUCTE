package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.cart;
import entities.cart_detail;
import entities.user;
import service.cart_detail_Service;
import service.product_Service;

@WebServlet("/updateQuantity")
public class UpdateQuantityServlet extends HttpServlet {
    private service.cartService cartService = new service.cartService();
    private cart_detail_Service cartDetailService = new cart_detail_Service();
    private product_Service productService = new product_Service();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set kiểu phản hồi là JSON
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        // 1. Lấy user từ session
        HttpSession session = req.getSession();

        System.out.println("=== [UpdateQuantityServlet] ===");

        user u = null;
        String[] keys = { "user", "userNormal", "userGG", "userFB" };
        for (String key : keys) {
            Object obj = session.getAttribute(key);
            System.out.println("Session key [" + key + "] = " + obj);
            if (obj != null) {
                u = (user) obj;
                break;
            }
        }

        if (u == null) {
            System.out.println("❌ Không có user trong session => chưa đăng nhập.");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"success\": false, \"message\": \"Bạn chưa đăng nhập\"}");
            return;
        }

        try {
        	// 2. Lấy tham số từ request
        	String productIdRaw = req.getParameter("productId");
        	String action = req.getParameter("action");

        	System.out.println("🔧 productId: " + productIdRaw);
        	System.out.println("🔧 action: " + action);

        	if (productIdRaw == null || productIdRaw.isEmpty() || action == null || action.isEmpty()) {
        	    System.out.println("❌ Tham số không hợp lệ");
        	    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	    out.write("{\"success\": false, \"message\": \"Tham số không hợp lệ\"}");
        	    return;
        	}

        	int productId;
        	try {
        	    productId = Integer.parseInt(productIdRaw);
        	} catch (NumberFormatException e) {
        	    System.out.println("❌ productId không phải số");
        	    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        	    out.write("{\"success\": false, \"message\": \"ID sản phẩm không hợp lệ\"}");
        	    return;
        	}

            // 3. Lấy giỏ hàng
            cart cart = cartService.getCartByUserId(u.getId());
            if (cart == null) {
                System.out.println("❌ Không tìm thấy giỏ hàng cho user id: " + u.getId());
                out.write("{\"success\": false, \"message\": \"Không tìm thấy giỏ hàng\"}");
                return;
            }

            System.out.println("📦 Cart ID: " + cart.getId());

            // 4. Cập nhật số lượng sản phẩm
            boolean updated = cartDetailService.updateProductQuantity(cart.getId(), productId, action);
            if (updated) {
                cart_detail updatedDetail = cartDetailService.getCartDetail(cart.getId(), productId);
                double price = productService.getProductById(productId).getPrice();
                double total = updatedDetail.getQuantity() * price;
                double cartTotal = cartService.calculateCartTotal(cart.getId());

                System.out.println("✅ Cập nhật thành công. New quantity = " + updatedDetail.getQuantity());

                out.write("{\"success\": true, " +
                          "\"newQuantity\": " + updatedDetail.getQuantity() + "," +
                          "\"totalPrice\": " + total + "," +
                          "\"cartTotal\": " + cartTotal + "}");
            } else {
                System.out.println("❌ Không thể cập nhật số lượng.");
                out.write("{\"success\": false, \"message\": \"Cập nhật thất bại\"}");
            }
        } catch (Exception e) {
            System.out.println("❌ Lỗi xảy ra:");
            e.printStackTrace();
            out.write("{\"success\": false, \"message\": \"Lỗi hệ thống\"}");
        }
    }
}
