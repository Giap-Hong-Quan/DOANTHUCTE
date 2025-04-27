package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.category_Service;
@WebServlet(name = "deleteCategoryAdminServlet", urlPatterns = "/admin/category/delete/*")
public class delete_Category_Admin extends HttpServlet {
	private category_Service categoryService=new category_Service();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/category");
            return;
        }
        try {
            String categoryIdStr = pathInfo.substring(1); // loại bỏ dấu "/"
            int categoryId = Integer.parseInt(categoryIdStr);
            boolean success = categoryService.deleteCategoryById(categoryId);
            if (!success) {
                // Gán thông báo thất bại vào session
                req.getSession().setAttribute("error", "Xóa danh mục sản phẩm thất bại.");
            } else {
                // Gán thông báo thành công vào session
                req.getSession().setAttribute("success", "Xóa danh mục sản phẩm thành công.");
            }
            // Redirect về danh sách người dùng
            resp.sendRedirect(req.getContextPath() + "/admin/category");

        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/admin/category");
        }
    }
}
