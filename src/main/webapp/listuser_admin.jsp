<%@page import="entities.user"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách tài khoản khách hàng</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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
                        <h1 class="mt-4">Quản lý tài khoản</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Trang thống kê</a>/Tài khoản</li>
                        </ol>     
                        <div class="container mt-5">
                          <div class="d-flex justify-content-between align-items-center mb-3">
                            <h2>Danh sách tài khoản</h2>
                            <a href="<%= request.getContextPath() %>/admin/addUser" class="btn btn-primary">Thêm</a>
                          </div>
                    
                          <table class="table table-bordered table-hover">
                            <thead class="table-light">
                              <tr>
                                <th>Sô thứ tự</th>
                                <th>Tên</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Vai trò</th>
                                <th>Thao tác</th>
                              </tr>
                            </thead>
                            <tbody>
                          	<c:forEach var="user" items="${users}"  varStatus="status">
                                <tr>
                                 <td>${status.index + 1}</td>
                                  <td>${user.name}</td>
                                  <td>${user.email}</td>
                                  <td>${user.phone}</td>
                                  <td>${user.roleName}</td>
                                  <td>
                                    <a href="<%= request.getContextPath() %>/admin/user/detail/${user.id}" class="btn btn-info btn-sm"
                                      >Chi tiết</a
                                    >
                                    <a href="<%= request.getContextPath() %>/admin/user/update/${user.id}" class="btn btn-warning btn-sm"
                                      >Cập nhât</a
                                    >
                                    <a href="<%= request.getContextPath() %>/admin/user/delete/${user.id}" class="btn btn-danger btn-sm"
                                      >Xóa</a
                                    >
                                  </td>
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                        </div>  
                        
                    </div>
                </main>
           <%@include file="footer_admin.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
  document.querySelectorAll(".btn-danger").forEach(button => {
    button.addEventListener("click", function (e) {
      e.preventDefault(); // Ngăn chuyển trang ngay
      const url = this.getAttribute("href");

      Swal.fire({
        title: 'Bạn có chắc muốn xóa?',
        text: "Hành động này không thể hoàn tác!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Xóa',
        cancelButtonText: 'Hủy'
      }).then((result) => {
        if (result.isConfirmed) {
          window.location.href = url; // Nếu xác nhận => chuyển trang để xóa
        }
      });
    });
  });
</script>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-area-demo.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/datatables-simple-demo.js"></script>
    </body>
</html>