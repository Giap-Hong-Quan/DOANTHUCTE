<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách sản phẩm</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
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
                        <h1 class="mt-4">Quản lý sản phẩm</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Trang thống kê</a>/Sản phẩm</li>
                        </ol>     
                        <div class="container mt-5">
                          <div class="d-flex justify-content-between align-items-center mb-3">
                            <h2>Danh sách sản phẩm</h2>
                            <a href="<%= request.getContextPath() %>/admin/product/add" class="btn btn-primary">Thêm</a>
                          </div>
                    
                          <table class="table table-bordered table-hover">
                            <thead class="table-light">
                              <tr>
                                <th>STT</th>
                                <th>Tên</th>
                                <th>Giá tiền</th>
                                <th>Số lượng</th>
                                <th>Danh mục</th>
                                <th>Thao tác</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach var="product" items="${products}" varStatus="status">
                                <tr>
                                  <td>${(currentPage - 1) * pageSize + status.index + 1}</td>
                                  <td>${product.name}</td>
                                 <td class="price">${product.price}</td>
                                  <td>${product.quantity}</td>
                                  <td>${product.categoryName}</td>
                                  <td>
                                    <a href="<%= request.getContextPath() %>/admin/product/detail/${product.id}" class="btn btn-info btn-sm">Chi tiết</a>
                                    <a href="<%= request.getContextPath() %>/admin/product/update/${product.id}" class="btn btn-warning btn-sm">Cập nhật</a>
                                    <a href="<%= request.getContextPath() %>/admin/product/delete/${product.id}" class="btn btn-danger btn-sm">Xóa</a>
                                  </td>
                                </tr>
                              </c:forEach>
                            </tbody>
                          </table>
                          
                          <!-- Phân trang -->
                          <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-center">
                              <c:if test="${currentPage > 1}">
                                <li class="page-item">
                                  <a class="page-link" href="?page=${currentPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                  </a>
                                </li>
                              </c:if>
                              
                              <c:forEach begin="1" end="${totalPages}" var="i">
                                <li class="page-item ${i == currentPage ? 'active' : ''}">
                                  <a class="page-link" href="?page=${i}">${i}</a>
                                </li>
                              </c:forEach>
                              
                              <c:if test="${currentPage < totalPages}">
                                <li class="page-item">
                                  <a class="page-link" href="?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                  </a>
                                </li>
                              </c:if>
                            </ul>
                          </nav>
                        </div>  
                    </div>
                </main>
                <%@include file="footer_admin.jsp" %>
            </div>
        </div>
        <script>
  // Format giá tiền kiểu Việt Nam
  document.querySelectorAll('.price').forEach(el => {
    const raw = parseFloat(el.textContent); // Lấy giá trị gốc (số)
    if (!isNaN(raw)) {
      const formatted = raw.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
      el.textContent = formatted; // Cập nhật lại nội dung đã format
    }
  });
</script>
        <!-- Các script -->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
          document.querySelectorAll(".btn-danger").forEach(button => {
            button.addEventListener("click", function (e) {
              e.preventDefault();
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
                  window.location.href = url;
                }
              });
            });
          });
        </script>
        
      
        <script src="<%= request.getContextPath() %>/assets/js/scripts.js"></script>
        
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
        %>

        <%
            String errorMessage = (String) session.getAttribute("error");
            if (errorMessage != null) {
        %>
            <script>
              Swal.fire({
                icon: 'error',
                title: 'Thất bại!',
                text: '<%= errorMessage %>',
                showConfirmButton: false,
                timer: 3000
              });
            </script>
        <%
                session.removeAttribute("error");
            }
        %>
        <script src="<%= ((HttpServletRequest) request).getContextPath() %>/assets/js/toast.js"></script>
        <script src="<%= ((HttpServletRequest) request).getContextPath() %>/assets/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-area-demo.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/datatables-simple-demo.js"></script>
    </body>
</html>