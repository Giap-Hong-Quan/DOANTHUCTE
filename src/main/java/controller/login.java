package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.user;
import service.login_Service;

@WebServlet(name = "loginServlet",urlPatterns ="/login")
public class login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8"); // Đảm bảo trình duyệt hiểu là UTF-8
	    req.setCharacterEncoding("UTF-8"); // Đảm bảo Java Servlet sử dụng UTF-8
	    String phone=req.getParameter("phone");
	    String password=req.getParameter("password");
	   
	    
	    login_Service loginService=new login_Service();
	    user user=loginService.login(phone, password);
	    if(user!=null) {
			 HttpSession session =	req.getSession();
			 session.invalidate();  // xóa hết session
			 HttpSession newsession=req.getSession(true);
				// lưu vào session đê sử dụng 
				 System.out.println("Lưu vào session normal: " + user.toString()); 
				 newsession.setAttribute("userNormal", user);
				 if(user.getRole_id()==1) {
					 resp.sendRedirect(req.getContextPath()+"/admin/dashboardAdmin");
				 }else {
					 resp.sendRedirect(req.getContextPath()+"/");
				 }
			}else {
				req.setAttribute("error", "đăng nhập thất bại");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
	}
}
