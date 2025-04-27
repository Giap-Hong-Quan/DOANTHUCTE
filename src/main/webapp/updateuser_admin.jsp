<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật thông tin khách hàng</title>
 <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
  <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
 <body class="sb-nav-fixed">
      <%@include file="header_admin.jsp" %>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
              <%@include file="sidebar_admin.jsp" %>
            </div>
            <div id="layoutSidenav_content">
             <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Cập nhật tài khoản</h1>
                        <ol class="breadcrumb mb-4">
                          <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Thống kê</a><a href="<%= request.getContextPath() %>/admin/user">/Tài khoản</a>/Cập nhật</li>
                        </ol>     
                        <div class="container mt-4">
                          <div class="card">
                            <div class="card-header">Cập nhật tài khoản</div>
                            <form action="<%= request.getContextPath() %>/admin/user/update/${user.id}" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                           
              <div class="mb-3">
                <label for="name" class="form-label">Họ và tên</label>
                <input type="text" class="form-control" id="name" name="name" value="${user.name}" placeholder="Nhập họ và tên" required>
              </div>

              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="Nhập email" required>
              </div>

              <div class="mb-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input type="tel" class="form-control" id="phone" name="phone" value="${user.phone}" placeholder="Nhập số điện thoại" required>
              </div>

			            <div class="mb-3">
			  <label for="role" class="form-label">Vai trò</label>
			  <select class="form-select" id="role" name="role_id" required>
			    <option value="" disabled ${empty user.role_id ? 'selected' : ''}>-- Chọn vai trò --</option>
			    <option value="1" ${user.role_id == 1 ? 'selected' : ''}>ADMIN</option>
			    <option value="2" ${user.role_id == 2 ? 'selected' : ''}>USER</option>
			  </select>
			</div>


              <div class="mb-3">
                <label for="avatar" class="form-label">Ảnh đại diện</label>
                <input class="form-control" type="file" id="avatar" name="avatar" accept="image/*">
                 									<c:choose>
    <c:when test="${not empty user.avatar}">
        <img src="${user.avatar}" 
             alt="Avatar"  
             style="width: 200px; height: 200px; object-fit: cover; border-radius: 10px;">
    </c:when>
    <c:otherwise>
        <img src="<%= request.getContextPath() %>/assets/images/default-avatar.jpg" 
             alt="Default Avatar"  
             style="width: 200px; height: 200px; object-fit: cover; border-radius: 10px;">
    </c:otherwise>
</c:choose>

              <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg">Cập nhật</button>
              </div>
            </form>
                        </div>   
                        
                    </div>
                </main> 
             <%@include file="footer_admin.jsp" %>
            </div>
        </div>
     
        <script src="<%= request.getContextPath() %>/assets/js/scripts.js"></script>
        <script src="<%= request.getContextPath() %>/assets/https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-area-demo.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/datatables-simple-demo.js"></script>
    </body>
</html>