package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.user;
import service.user_Service;

@WebServlet(name = "deleteUserAdminServlet", urlPatterns = "/admin/user/delete/*")
public class delete_User_Admin extends HttpServlet {
    private user_Service userService = new user_Service();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        
        if (pathInfo == null || pathInfo.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/user");
            return;
        }

        try {
            String userIdStr = pathInfo.substring(1); // loại bỏ dấu "/"
            int userId = Integer.parseInt(userIdStr);

            boolean success = userService.deleteUserById(userId);

            if (!success) {
                // Gán thông báo thất bại vào session
                req.getSession().setAttribute("error", "Xóa người dùng thất bại.");
            } else {
                // Gán thông báo thành công vào session
                req.getSession().setAttribute("success", "Xóa người dùng thành công.");
            }

            // Redirect về danh sách người dùng
            resp.sendRedirect(req.getContextPath() + "/admin/user");

        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/user");
        }
    }
}
