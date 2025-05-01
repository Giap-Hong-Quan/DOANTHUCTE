package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.cart;
import entities.cart_detail;
import entities.cart_item_DTO;
import entities.product;
import entities.user;
import service.cartService;
import service.cart_detail_Service;
import service.product_Service;

@WebServlet(name = "cartsServlet",urlPatterns = "/cart")
public class carts extends HttpServlet {
	private cartService cartService=new cartService();
	private cart_detail_Service cartDetailServices= new cart_detail_Service();
	  private product_Service productService = new product_Service();
	
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
            // Lấy giỏ hàng
            cart cart = cartService.getCartByUserId(u.getId());
            if (cart != null) {
                // Lấy các chi tiết giỏ hàng
                List<cart_detail> cartDetails = cartDetailServices.getCartDetailsByCartId(cart.getId());
                List<cart_item_DTO> items = new ArrayList<>();

                // Lấy thông tin cho từng sản phẩm trong giỏ
                for (cart_detail cd : cartDetails) {
                    product p = productService.getProductById(cd.getProduct_id());

                    // Tạo DTO để gửi qua jsp
                    cart_item_DTO dto = new cart_item_DTO();
                    dto.setProductId(p.getId()); // ✅ Quan trọng
                    dto.setProductName(p.getName());
                    dto.setImageUrl(p.getImages().get(0).getImage()); // ảnh đầu tiên
                    dto.setPrice(p.getPrice());
                    dto.setQuantity(cd.getQuantity());

                    items.add(dto);
                }

                req.setAttribute("cartItems", items);
            } else {
                req.setAttribute("message", "Giỏ hàng trống.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
	}
}
