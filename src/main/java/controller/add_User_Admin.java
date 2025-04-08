package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import service.user_Service;
@MultipartConfig
@WebServlet(name = "addUserAdminServlet",urlPatterns = "/admin/user/add")
public class add_User_Admin extends HttpServlet {
	private user_Service userService=new user_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/adduser_admin.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		   // Lấy dữ liệu từ form
	    String name = req.getParameter("name");
	    String email = req.getParameter("email");
	    String phone = req.getParameter("phone");
	    String password = req.getParameter("password");
	    // Xử lý role_id với kiểm tra hợp lệ
	    String role_id = req.getParameter("role_id");
	    int role = 2; // Mặc định cho role nếu có lỗi
	    try {
	        role = Integer.parseInt(role_id);
	    } catch (NumberFormatException e) {
	        // Nếu role_id không hợp lệ, có thể set giá trị mặc định hoặc thông báo lỗi
	        req.getSession().setAttribute("error", "Vai trò không hợp lệ.");
	        resp.sendRedirect(req.getContextPath() + "/admin/user");
	        return;
	    }

	    // Avatar xử lý
	    Part avatarPart = req.getPart("avatar");
	    String avatar = ImageUtil.saveImage(avatarPart, getServletContext());
	    boolean insert=userService.insertUser(name, email, phone, password, role, avatar);
	    // Gán thông báo kết quả vào session
	    if (!insert) {
	        req.getSession().setAttribute("error", "Cập nhật người dùng thất bại.");
	    } else {
	        req.getSession().setAttribute("success", "Cập nhật người dùng thành công.");
	    }

	    // Redirect về danh sách người dùng
	    resp.sendRedirect(req.getContextPath() + "/admin/user");
	}
}
