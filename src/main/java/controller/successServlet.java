package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SuccessServlet", urlPatterns = "/success")
public class successServlet extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer orderId = (Integer) session.getAttribute("orderId");
        
        if (orderId == null) {
            // Nếu không có orderId, có thể user đã truy cập trực tiếp vào trang này
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }
        
        // Đặt orderId vào request
        req.setAttribute("orderId", orderId);
        
        // Xóa orderId khỏi session sau khi đã sử dụng
        session.removeAttribute("orderId");
        
        // Chuyển tiếp đến trang success.jsp
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
    }
}
