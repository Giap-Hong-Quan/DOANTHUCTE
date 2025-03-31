package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.user;

@WebServlet(name = "dashboardAdminServlet",urlPatterns = "/admin/dashboardAdmin")
public class dashboardAdmin  extends HttpServlet{
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
            req.setAttribute("user", user);
            req.getRequestDispatcher("/dashboardAdmin.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }
}
