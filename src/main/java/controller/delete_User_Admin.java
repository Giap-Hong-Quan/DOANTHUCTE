package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.user;
import service.user_Service;

@WebServlet(name = "deleteUserAdminServlet",urlPatterns = "/admin/user/delete/*")
public class delete_User_Admin extends HttpServlet{
	private user_Service userService=new user_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();	
		  if (pathInfo == null || pathInfo.isEmpty()) {
	            resp.sendRedirect("/admin/user"); // Nếu không có ID, quay lại danh sách user
	            return;
	        }
		  // Cắt phần pathInfo để lấy ID người dùng
		  
		  String userId=pathInfo.substring(1); // Loại bỏ dấu '/' ở đầu
		  int userIds = Integer.parseInt(userId);// ép kiểu thành int
		  boolean user=userService.deleteUserById(userIds);	
		  if (user = false) {
	            resp.sendRedirect("/admin/user"); // Nếu không tìm thấy user, quay lại danh sách user
	            return;
	      }
		  req.setAttribute("user", user);
		  req.getRequestDispatcher("/detailuser_admin.jsp").forward(req, resp);
		  
	}
}
