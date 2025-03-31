package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.login_Service;
@WebServlet(name="registerServlet",urlPatterns = "/register")
public class register extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8"); // Đảm bảo trình duyệt hiểu là UTF-8
	    req.setCharacterEncoding("UTF-8"); // Đảm bảo Java Servlet sử dụng UTF-8
	    String name=req.getParameter("name");
	    String phone=req.getParameter("phone");
	    String password=req.getParameter("password");
	    login_Service registerService=new login_Service();
	    boolean register=registerService.registerService(name, phone, password);
	    if(register) {
	    	req.getSession().setAttribute("message", "Đăng ký thành công! Vui lòng đăng nhập.");
	    	resp.sendRedirect(req.getContextPath()+"/login");
	    }else {
	    	req.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
	    	req.getRequestDispatcher("register.jsp").forward(req, resp);
	    }
	}
}
