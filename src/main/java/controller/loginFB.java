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

@WebServlet(name="loginFBServlet",urlPatterns = "/loginFB")
public class loginFB extends HttpServlet {
	private static final String FACEBOOK_CLIENT_ID="2141588736276166";
	private static final String FACEBOOK_CLIENT_SECRET="08d6daec6fe2b586b05c7ba5576ef249";
	private static final String FACEBOOK_REDIRECT_URL="http://localhost:8080/DOANTHUCTE/loginFB";
	// hàm đổi mã accssetoken ra ra chuỗi json chứ thông itn 
			private String getUserInfo(String accessToken) throws IOException{
				URL url=new URL("https://graph.facebook.com/me?fields=id,name,email&access_token="+accessToken);
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				Scanner scanner=new Scanner(conn.getInputStream());
				String response=scanner.useDelimiter("\\A").next();
				scanner.close();
				return response;
			}
			//hàm đổi mã code từ get lên fb lấy mã accssetoken;
			private String getAccessToken(String code) throws IOException{
				URL url=new URL("https://graph.facebook.com/v17.0/oauth/access_token"); // định dạng yêu cầu 
				HttpURLConnection conn=(HttpURLConnection) url.openConnection();// mở kết nối
				conn.setRequestMethod("POST");  // định nghĩa method gửi
				conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); // định dạng dữ liệu
				conn.setDoOutput(true); // cho phep body gửi
				// thiếu lập thông tin gửi gôm id secret ..url
				String params="client_id="+FACEBOOK_CLIENT_ID+
					    "&client_secret="+FACEBOOK_CLIENT_SECRET+
					    "&redirect_uri="+FACEBOOK_REDIRECT_URL+
					    "&code="+code+
					    "&grant_type=authorization_code";

				try(OutputStream os=conn.getOutputStream()){  // cho phép dữ liệu gửi server
					os.write(params.getBytes()); // chuyển dữ liệu thành dạng buyte
				}
				// nhận phản hồi từ fb
				Scanner scanner=new Scanner(conn.getInputStream()); // tạo scaaner để dọc 
				String response=scanner.useDelimiter("\\A").next(); // dọc dưới dạng json
				scanner.close();
				return response; // trả lại chuỗi json
			}
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				resp.setContentType("text/html; charset=UTF-8");
				resp.setCharacterEncoding("UTF-8");
				
				//b1 lấy giá trị code
				String code=req.getParameter("code");
				System.out.println(code);
				//b2:kiểm tra giá trị code 
				if(code==null) {
					resp.getWriter().write("lỗi,không nhận được mã code từ facebook");
					return;
				}
				//gọi hàm getAccsseToken lấy ra lấy ra chuỗi json chứa thông chứa accssetoken
				
				String tokenResponse=getAccessToken(code);  // gọi hàm để gửi
				JsonObject tokenJson=JsonParser.parseString(tokenResponse).getAsJsonObject();  // chuyển chuỗi json thành đối tượng
				String accessToken=tokenJson.get("access_token").getAsString();  // từ đối tượng lấy ra mã acc rồi chuyền lại thành chuỗi
				// có accesstoken rồi gửi để lấy thông tin user
				
				String userInfo=getUserInfo(accessToken);
				JsonObject userJson=JsonParser.parseString(userInfo).getAsJsonObject();
				
				String facebookId=userJson.get("id").getAsString();
				String name=userJson.get("name").getAsString();
				String email = userJson.has("email") ? userJson.get("email").getAsString() : "";
				String picture="https://graph.facebook.com/" + facebookId + "/picture?type=large";
				
				login_DAO loginFB=new login_DAO(connect.getConnections());
				user user=loginFB.loginFaceBook(name, email, facebookId, picture);
				if(user!=null) {
					HttpSession session =	req.getSession();
					 session.invalidate();  // xóa hết session
					 HttpSession newsession=req.getSession(true);
					// lưu vào session đê sử dụng 
					System.out.println("Lưu vào session fb: " + user.toString()); 
					newsession.setAttribute("userFB",user);
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
