package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import connectDatabase.connect;
import dao.login_DAO;
import entities.user;

@WebServlet(name = "loginGGServlet",urlPatterns = "/loginGG")
public class loginGG extends HttpServlet {
	
	private static final String GOOGLE_CLIENT_ID = "421086665525-9nob9a8csoqp55ohbrluscli4k5ote7b.apps.googleusercontent.com";
	private static final String GOOGLE_CLIENT_SECRET = "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";
	private static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/DOANTHUCTE/loginGG";
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8"); // Đảm bảo trình duyệt hiểu là UTF-8
	    resp.setCharacterEncoding("UTF-8"); // Đảm bảo Java Servlet sử dụng UTF-8
		
		
		// bước 1 :lấy mã code từ đường dẫn gét
		String code=req.getParameter("code"); // lấy ra giá trị code trên đường dẫn get mà gg trả về 
		System.out.println(code);
		if(code==null) {
			resp.getWriter().write("lỗi,không nhận được mã xác thực code từ google");  // kiểm tra có không
			return;
		}
		// bước 2:từ mã code đổi sang acssettoken
		String tokenResponse=getAccsseToken(code);  //Gửi mã code và lấy phản hồi (token response) từ Google
		JsonObject tokenJson=JsonParser.parseString(tokenResponse).getAsJsonObject();//// Chuyển chuỗi phản hồi json thành đối tượng JSON
		String accessToken=tokenJson.get("access_token").getAsString(); // Lấy access_token từ đối tượng JSON và chuyển thành chuỗi
		
		// 3️⃣ Dùng Access Token lấy thông tin người dùng
		String userInfo=getUserInfo(accessToken);  // gửi yêu cầu thông qua acsetoken đê lấy thông tin user
		JsonObject userJson=JsonParser.parseString(userInfo).getAsJsonObject(); // chuyển chuỗi json thành đối tượng json 
		String googleId=userJson.get("id").getAsString();
		String email=userJson.get("email").getAsString();
		String name=userJson.get("name").getAsString();
		String picture=userJson.get("picture").getAsString();
		
		
		
		
		login_DAO loginGG=new login_DAO(connect.getConnections());
		user user= loginGG.loginGoogle(name, email, googleId, picture);
		
		if(user!=null) {
			HttpSession session =	req.getSession();
			 session.invalidate();  // xóa hết session
			 HttpSession newsession=req.getSession(true);
			// lưu vào session đê sử dụng 
			 System.out.println("Lưu vào session gg: " + user.toString()); 
			 newsession.setAttribute("userGG", user);
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
	
	
	
	
	
	
	// xây hàm để gửi mã code lên gg lấy accsseToken(tạo hàm nhận vào mã code)
	private String getAccsseToken(String code) throws IOException{
		URL url=new URL("https://oauth2.googleapis.com/token");//Định nghĩa endpoint API của Google để đổi code lấy Access Token.
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();//Mở kết nối HTTP.
		conn.setRequestMethod("POST");// Google yêu cầu phương thức POST để gửi code.
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // định dạng dữ liệu của post để gửi
		conn.setDoOutput(true);//Cho phép gửi dữ liệu trong body của request.
		//Gửi mã code cùng các thông tin cần thiết
		String params = "code=" + code +
                "&client_id=" + GOOGLE_CLIENT_ID +
                "&client_secret=" + GOOGLE_CLIENT_SECRET +
                "&redirect_uri=" + GOOGLE_REDIRECT_URI +
                "&grant_type=authorization_code";//Loại request theo chuẩn OAuth 2.0.
		// gửi dữ liệu params lên server
		try(OutputStream os=conn.getOutputStream()){// Lấy OutputStream của kết nối, cho phép gửi dữ liệu lên server.
			os.write(params.getBytes());// chuyển dữ liệu thành dạng bety xong gi vao ouputStream ,giup gưi post lên gg
		}
		// bước tiếp theo là nhận phản hồi từ gg 
		Scanner scanner=new Scanner(conn.getInputStream());// tạo scanner để đọc dữ liệu gg gửi dề 
		String response=scanner.useDelimiter("\\A").next(); //Đọc toàn bộ dữ liệu phản hồi dưới dạng chuỗi JSON.
		scanner.close();// đóng lại tránh rò rĩ
		return response;//Trả về chuỗi JSON chứa Access Token.
	}
	
	
		// lấy thông tin người dùng bằng accsseTonken
		private String getUserInfo(String accessToken) throws IOException{
			URL url =new URL("https://www.googleapis.com/oauth2/v2/userinfo?access_token="+accessToken);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			Scanner scanner=new Scanner(conn.getInputStream());
			String response=scanner.useDelimiter("\\A").next();
			scanner.close();
			return response;
		}
	
	
	
}
