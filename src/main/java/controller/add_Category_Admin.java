package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import service.category_Service;
import service.user_Service;
@MultipartConfig
@WebServlet(name = "addCategoryAdminServlet",urlPatterns = "/admin/category/add")
public class add_Category_Admin extends HttpServlet {
	private category_Service categoryService=new category_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/addcategory_admin.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("UTF-8");
		   // Lấy dữ liệu từ form
	    String name = req.getParameter("name");
	   
	    // Avatar xử lý
	    Part imagePart = req.getPart("image");
	    String image = ImageUtil.saveImage(imagePart, getServletContext());
	    boolean insert=categoryService.insertUser(name, image);
	    // Gán thông báo kết quả vào session
	    if (!insert) {
	        req.getSession().setAttribute("error", "Thêm doanh mục thất bại.");
	    } else {
	        req.getSession().setAttribute("success", "Thêm doanh mục thành công.");
	    }

	    // Redirect về danh sách người dùng
	    resp.sendRedirect(req.getContextPath() + "/admin/category");
	}
}
