package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "logoutServlet",urlPatterns = "/logout")
public class logout extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false); // lấy session hiện tại, không tạo mới

    if (session != null) {
        session.invalidate(); // huỷ toàn bộ session => đăng xuất
    }

    resp.sendRedirect(req.getContextPath() + "/"); // quay về trang chủ sau khi logout
}
}
