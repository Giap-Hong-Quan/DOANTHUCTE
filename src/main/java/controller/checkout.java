package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connectDatabase.connect;
import dao.order_DAO;
import dao.order_items_DAO;
import entities.cart;
import entities.cart_detail;
import entities.order_items;
import entities.orders;
import entities.user;
import service.cartService;
import service.cart_detail_Service;
import service.orderItemsService;
import service.orderService;
import service.product_Service;

@WebServlet(name = "checkoutServlet",urlPatterns = "/checkout")
public class checkout extends HttpServlet {
	private cartService cartService=new cartService();
	private cart_detail_Service cartDetailServices= new cart_detail_Service();
	  private product_Service productService = new product_Service();
	  private orderService orderService=new orderService()	;
	   private orderItemsService orderItems = new orderItemsService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
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
        System.out.println(u.getId());
		try {
			 cart cart = cartService.getCartByUserId(u.getId());
			 
			 String name = req.getParameter("name");
		        String address = req.getParameter("address");
		        String phone = req.getParameter("phone");
		        String paymentMethod = req.getParameter("payment_method");
		        System.out.println(name);
		        System.out.println(phone);
		        
		        System.out.println(address);
		        System.out.println(paymentMethod);
		        
		        
		        if (cart != null) {
		            // Lấy các chi tiết giỏ hàng
		            List<cart_detail> cartDetails = cartDetailServices.getCartDetailsByCartId(cart.getId());
		            System.out.println(cartDetails.toString());
		            if (cartDetails.isEmpty()) {
		                resp.sendRedirect("cart.jsp");
		                return;
		            }
		            // 2. Tính tổng tiền
		            double total = 0;
		            for (cart_detail item : cartDetails) {
		                total += item.getQuantity() * item.getPrice();
		            }    

		         // Lấy thời gian hiện tại và chuyển sang java.sql.Date
		         java.util.Date utilDate = new java.util.Date();
		         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		         System.out.println(sqlDate);
		            System.out.println(total);
		            orders order = new orders();
		            order.setUser_id(u.getId());
		         // Gán giá trị vào order
		            order.setOrder_date(sqlDate);
		            order.setStatus_id(0);
		            order.setName(name);
		            order.setAddress(address);	
		            order.setPhone(phone);
		            order.setTotal_price(total);
		           
		            order.setPayment_method(paymentMethod.equals("momo") ? 1 : 0);
		            order.setPayment_status(0);  // 0 = UNPAID (chưa thanh toán)
		            order.setTransaction_id(paymentMethod.equals("momo") ? UUID.randomUUID().toString() : null);
		            
		           
		            int orderId = orderService.insertOrder(order);
		            if (orderId <= 0) {
	                    req.setAttribute("error", "Tạo đơn hàng thất bại");
	                    req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
	                    return;
	                }
		            
		            // 4. Chuyển giỏ hàng vào order_items
		           
		            List<order_items> orderItemsList = new ArrayList<>();
		            for (cart_detail cartItem : cartDetails) {
		            	System.out.println(cartItem.getProduct_id());
		            	System.out.println(cartItem.getQuantity());
		            	System.out.println(cartItem.getPrice());
		            	System.out.println(cartItem.getQuantity() * cartItem.getPrice());
		            	
		            	
		                order_items orderItem = new order_items();
		                orderItem.setOrder_id(orderId);
		                orderItem.setProduct_id(cartItem.getProduct_id());
		                orderItem.setQuantity(cartItem.getQuantity());
		                orderItem.setPrice(cartItem.getPrice());
		                orderItem.setSubtotal(cartItem.getQuantity() * cartItem.getPrice());
		                orderItemsList.add(orderItem);
		            }
		            // Lưu order items và xóa giỏ hàng
	                if (orderItems.insertOrderItems(orderId, orderItemsList)) {
	                    cartDetailServices.deleteCartItems(cart.getId());
	                    
	                    // Chuyển hướng đến trang thành công
	                    session.setAttribute("orderId", orderId); // Có thể truyền orderId nếu cần
	                    req.setAttribute("success", "Đặt hàng thành công ");
	                    resp.sendRedirect(req.getContextPath() + "/success");
	                } else {
	                    req.setAttribute("error", "Lỗi khi lưu chi tiết đơn hàng");
	                    req.getRequestDispatcher("checkout.jsp").forward(req, resp);
		            
//		            // 6. Chuyển hướng theo phương thức thanh toán
//		            if (paymentMethod.equals("momo")) {
//		                String momoRedirectUrl = MomoPayment.createPaymentUrl(order); // đã giải thích phần này ở trên
//		                resp.sendRedirect(momoRedirectUrl);
//		            } else {
//		                resp.sendRedirect("order_success.jsp");
//		            }
		            
		            
		        }
		       
		        }
		        }catch (Exception e) {
		}  
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
	}

}
