<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/toast.css">

</head>
<body>
<%
    String successMessage = (String) session.getAttribute("success");
    if (successMessage != null) {
%>
    <script>
      Swal.fire({
        icon: 'success',
        title: 'Thành công!',
        text: '<%= successMessage %>',
        showConfirmButton: false,
        timer: 3000
      });
    </script>
<%
    session.removeAttribute("success");
    }
%>>
<!-- Login 11 - Bootstrap Brain Component -->
<section class="py-3 py-md-5 py-xl-8">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="mb-5">
          <h2 class="display-5 fw-bold text-center">Đăng nhập</h2>
          <p class="text-center m-0">Bạn chưa có tài khoản? <a href="./register">Đăng ký</a></p>
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-12 col-lg-10 col-xl-8">
        <div class="row gy-5 justify-content-center">
          <div class="col-12 col-lg-5">
            <form action="<%= request.getContextPath() %>/login" method="post">
              <div class="row gy-3 overflow-hidden">
              	
                <div class="col-12">
                  <div class="form-floating mb-3">
                    <input type="phone" class="form-control border-0 border-bottom rounded-0" name="phone" id="phone" placeholder="0335906807" required>
                    <label for="phone" class="form-label">Số điện thoại</label>
                  </div>
                </div>
                <div class="col-12">
                  <div class="form-floating mb-3">
                    <input type="password" class="form-control border-0 border-bottom rounded-0" name="password" id="password" value="" placeholder="Password" required>
                    <label for="password" class="form-label">Mật khẩu</label>
                  </div>
                </div>
                <div class="col-12">
                  <div class="row justify-content-between">
                    <div class="col-6">
                      <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" name="remember_me" id="remember_me">
                        <label class="form-check-label text-secondary" for="remember_me">
                          Lưu tài khoản 
                        </label>
                      </div>
                    </div>
                    <div class="col-6">
                      <div class="text-end">
                        <a href="#!" class="link-secondary text-decoration-none">Quên mật khẩu ?</a>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-12">
                  <div class="d-grid">
                    <button class="btn btn-primary btn-lg" type="submit">Đăng nhập</button>
                  </div>
                </div>
              </div>
            </form>
          </div>
          <div class="col-12 col-lg-2 d-flex align-items-center justify-content-center gap-3 flex-lg-column">
            <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
            <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
            <div>Hoặc</div>
            <div class="bg-dark h-100 d-none d-lg-block" style="width: 1px; --bs-bg-opacity: .1;"></div>
            <div class="bg-dark w-100 d-lg-none" style="height: 1px; --bs-bg-opacity: .1;"></div>
          </div>
          <div class="col-12 col-lg-5 d-flex align-items-center">
            <div class="d-flex gap-3 flex-column w-100 ">
              <a href="https://accounts.google.com/o/oauth2/auth?
client_id=421086665525-9nob9a8csoqp55ohbrluscli4k5ote7b.apps.googleusercontent.com
&redirect_uri=http://localhost:8080/DOANTHUCTE/loginGG
&response_type=code
&scope=email%20profile
&access_type=offline" class="btn btn-lg btn-danger">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                  <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                </svg>
                <span class="ms-2 fs-6">Đăng nhập với Google</span>
              </a>
               <a href="https://www.facebook.com/v22.0/dialog/oauth?client_id=2141588736276166&redirect_uri=http://localhost:8080/DOANTHUCTE/loginFB&scope=email,public_profile" class="btn btn-lg btn-primary">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                  <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z" />
                </svg>
                <span class="ms-2 fs-6">Đăng nhập với Facebook</span>
              </a>
              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section><!-- Login 11 - Bootstrap Brain Component -->
<script src="<%= ((HttpServletRequest) request).getContextPath() %>/assets/javascript/toast.js"></script>

</body>
</html>