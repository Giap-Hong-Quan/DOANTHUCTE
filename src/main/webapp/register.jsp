<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
<!-- Login 11 - Bootstrap Brain Component -->
<section class="py-3 py-md-5 py-xl-8">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="mb-5">
          <h2 class="display-5 fw-bold text-center">Đăng Ký</h2>
          <p class="text-center m-0">Bạn đã có tài khoản? <a href="./login.jsp">Đăng nhập</a></p>
        </div>
      </div>
    </div>
    <div class="row justify-content-center">
      <div class="col-12 col-lg-10 col-xl-8">
        <div class="row gy-5 justify-content-center">
          <div class="col-12 col-lg-5">
            <form action="<%= request.getContextPath() %>/register" method="post">
              <div class="row gy-3 overflow-hidden">
              	<div class="col-12">
                  <div class="form-floating mb-3">
                    <input type="text" class="form-control border-0 border-bottom rounded-0" name="name" id="name" placeholder="Giaphongquan" required>
                    <label for="name" class="form-label">Họ và tên</label>
                  </div>
                </div>
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
                        <a href="#!" class="link-secondary text-decoration-none">Quên mật khẩu?</a>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="col-12">
                  <div class="d-grid">
                    <button class="btn btn-primary btn-lg" type="submit">Đăng ký</button>
                  </div>
                </div>
              </div>
            </form>
          </div>
          
          
        </div>
      </div>
    </div>
  </div>
</section><!-- Login 11 - Bootstrap Brain Component -->


</body>
</html>