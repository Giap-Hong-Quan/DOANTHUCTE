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

import entities.category;
import service.category_Service;
@MultipartConfig
@WebServlet(name = "updateCategoryAdminServlet",urlPatterns ="/admin/category/update/*")
public class upcate_Category_Admin extends HttpServlet {
	private category_Service categoryService=new category_Service();
	  private MultipartUtils multipartUtils =new MultipartUtils();
	  private ImageUtil imageUtil=new ImageUtil();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  String pathInfo = req.getPathInfo();
		  if (pathInfo == null || pathInfo.isEmpty()) {
	            resp.sendRedirect("/admin/category"); // Nếu không có ID, quay lại danh sách user
	            return;
	        }
		  // Cắt phần pathInfo để lấy ID người dùng
		  
		  String categoryId=pathInfo.substring(1); // Loại bỏ dấu '/' ở đầu
		  int categoryIds = Integer.parseInt(categoryId);// ép kiểu thành int
		  category category=categoryService.getCategoryById(categoryIds);	
		  if(category ==null) {
			  resp.sendRedirect("/admin/category"); // Nếu không tìm thấy user, quay lại danh sách user
	            return;
		  }
		  req.setAttribute("category", category);
		  req.getRequestDispatcher("/updatecategory_admin.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    String pathInfo = req.getPathInfo();
	    System.out.println("id: " + pathInfo);
	    // Nếu không có ID, quay lại danh sách user
	    if (pathInfo == null || pathInfo.isEmpty()) {
	        resp.sendRedirect(req.getContextPath() + "/admin/category");
	        return;
	    }
	    String categoryId = pathInfo.substring(1); // Loại bỏ dấu '/' ở đầu
	    int categoryIds = Integer.parseInt(categoryId); // ép kiểu thành int
	    // Lấy dữ liệu từ form
	    String name = req.getParameter("name");
	    // Avatar xử lý
	    Part imagePart = req.getPart("image");
	    String image = ImageUtil.saveImage(imagePart, getServletContext());
	    if (image == null) {
	    	image = categoryService.getCategoryById(categoryIds).getImage();
	    }
	    // Cập nhật thông tin người dùng, bao gồm avatar
	    boolean update = categoryService.updateCategoryById(categoryIds, new category(name, image));

	    // Gán thông báo kết quả vào session
	    if (!update) {
	        req.getSession().setAttribute("error", "Cập nhật người dùng thất bại.");
	    } else {
	        req.getSession().setAttribute("success", "Cập nhật người dùng thành công.");
	    }

	    // Redirect về danh sách người dùng
	    resp.sendRedirect(req.getContextPath() + "/admin/category");
	}
	

}
