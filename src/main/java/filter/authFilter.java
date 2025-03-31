package filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.user;

@WebFilter("/*")
public class authFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // Lấy đường dẫn yêu cầu
        String path = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println("🔍 Request Path: " + path); // DEBUG

        // Các đường dẫn được phép truy cập không cần đăng nhập
        String[] allowedPaths = {"/login", "/login.jsp","/register.jsp", "/loginGG", "/loginFB", "/register", "/403.jsp", "/"};
        for (String allowed : allowedPaths) {
            if (path.equals(allowed)) { 
                System.out.println("✅ Bỏ qua kiểm tra đăng nhập: " + path);
                chain.doFilter(request, response);
                return;
            }
        }

        // Bỏ qua kiểm tra cho các file tĩnh (assets, css, js, images, fonts, ...)
        if (path.matches("^/(assets|css|js|images|fonts)/.*")) {
            System.out.println("✅ Bỏ qua filter cho file tĩnh: " + path);
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra session (user có đăng nhập không?)
        HttpSession session = req.getSession(false);
        user currentUser = null;
        
        if (session != null) {
            currentUser = (user) session.getAttribute("userNormal");
            if (currentUser == null) currentUser = (user) session.getAttribute("userGG");
            if (currentUser == null) currentUser = (user) session.getAttribute("userFB");
        }

        // Nếu chưa đăng nhập, chuyển hướng về trang đăng nhập
        if (currentUser == null) {
            System.out.println("⚠️ Người dùng chưa đăng nhập!");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // Kiểm tra quyền admin
        boolean isAdmin = currentUser.getRole_id() == 1;
        boolean isAccessingAdmin = path.startsWith("/admin");

        if (isAccessingAdmin && !isAdmin) {
            System.out.println("⛔ Người dùng không có quyền truy cập admin!");
            resp.sendRedirect(req.getContextPath() + "/403.jsp");
            return;
        }

        // Nếu tất cả hợp lệ, tiếp tục request
        chain.doFilter(request, response);
    }
}
