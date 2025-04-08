package controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.category;
import service.category_Service;
@WebServlet(name = "listCategoryAdminServlet",urlPatterns = "/admin/category")
public class list_Category_Admin extends HttpServlet {
	private category_Service categoryService=new category_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<category> listCategory=categoryService.getAllCategory();
		req.setAttribute("categorys", listCategory);
		req.getRequestDispatcher("/listcategory_admin.jsp").forward(req, resp);
	}
}
