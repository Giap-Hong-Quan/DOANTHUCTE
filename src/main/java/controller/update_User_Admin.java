package controller;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.user;
import service.user_Service;

@MultipartConfig
@WebServlet(name = "updateUserAdminServlet",urlPatterns ="/admin/user/update/*")
public class update_User_Admin extends HttpServlet {
	  private user_Service userService = new user_Service();
	  private MultipartUtils multipartUtils =new MultipartUtils();
	  private ImageUtil imageUtil=new ImageUtil();
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
		  user user=userService.getUserById(userIds);	
		  if(user ==null) {
			  resp.sendRedirect("/admin/user"); // Nếu không tìm thấy user, quay lại danh sách user
	            return;
		  }
		  req.setAttribute("user", user);
		  req.getRequestDispatcher("/updateuser_admin.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    String pathInfo = req.getPathInfo();
	    System.out.println("id: " + pathInfo);

	    // Nếu không có ID, quay lại danh sách user
	    if (pathInfo == null || pathInfo.isEmpty()) {
	        resp.sendRedirect(req.getContextPath() + "/admin/user");
	        return;
	    }

	    String userId = pathInfo.substring(1); // Loại bỏ dấu '/' ở đầu
	    int userIds = Integer.parseInt(userId); // ép kiểu thành int

	    // Lấy dữ liệu từ form
	    String name = req.getParameter("name");
	    String email = req.getParameter("email");
	    String phone = req.getParameter("phone");

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
	    if (avatar == null) {
	        avatar = userService.getUserById(userIds).getAvatar();
	    }

	    // Cập nhật thông tin người dùng, bao gồm avatar
	    boolean update = userService.updateUserById(userIds, new user(name, email, phone, avatar, role));

	    // Gán thông báo kết quả vào session
	    if (!update) {
	        req.getSession().setAttribute("error", "Cập nhật người dùng thất bại.");
	    } else {
	        req.getSession().setAttribute("success", "Cập nhật người dùng thành công.");
	    }

	    // Redirect về danh sách người dùng
	    resp.sendRedirect(req.getContextPath() + "/admin/user");
	}
	

}
