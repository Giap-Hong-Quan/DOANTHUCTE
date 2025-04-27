package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.user;
import service.product_Service;

@WebServlet(name = "homeServlet", urlPatterns = "/")
public class home extends HttpServlet {
	private product_Service productService=new product_Service();
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
            // Gửi dữ liệu user đến home.jsp
            req.setAttribute("user", user);
            
            req.setAttribute("menProducts", productService.getLatestProductsByCategory(4, 3));	
            req.setAttribute("womenProducts", productService.getLatestProductsByCategory(1, 3));
            req.setAttribute("kidsProducts", productService.getLatestProductsByCategory(6, 3));
            req.setAttribute("accessoriesProducts", productService.getLatestProductsByCategory(7, 3));
            req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);        
    }
}
