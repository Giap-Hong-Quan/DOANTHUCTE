package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.product;
import entities.user;
import service.cartService;
import service.product_Service;

@WebServlet(name = "cartServlet",urlPatterns ="/cart/*")

public class add_cart  extends HttpServlet{
	private product_Service productService=new product_Service();
	private cartService cartService=new cartService();
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

        try  {
            // 2. Lấy product_id từ URL
            String pathInfo = req.getPathInfo(); // Ví dụ: "/15"
            if (pathInfo == null || pathInfo.equals("/")) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Thiếu product id.");
                return;
            }
            int productId = Integer.parseInt(pathInfo.substring(1)); // bỏ dấu /

            // 3. Lấy giá sản phẩm từ product_id
            
            product product = productService.getProductById(productId);
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Sản phẩm không tồn tại.");
                return;
            }
            double price = product.getPrice();

            // 4. Thêm vào giỏ hàng
       
            cartService.addToCart(u.getId(), productId, price);

            // 5. Xong thì redirect
            resp.sendRedirect(req.getContextPath() + "/cart");
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.");
        }
	}
}
