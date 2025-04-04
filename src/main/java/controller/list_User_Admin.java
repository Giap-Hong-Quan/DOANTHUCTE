package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.user;
import service.user_Service;

@WebServlet(name = "listUserAdminServlet",urlPatterns = "/admin/user")
public class list_User_Admin extends HttpServlet {
	private user_Service userService=new user_Service();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<user> listUser=userService.getAllUser();
		req.setAttribute("users", listUser);
		req.getRequestDispatcher("/listuser_admin.jsp").forward(req, resp);
	}
}
