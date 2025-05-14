<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý đơn hàng</title>
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
                        <h1 class="mt-4">Quản lý đơn hàng</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Trang thống kê</a>/Đơn hàng</li>
                        </ol>     
                        <div class="container mt-5">
                          <div class="d-flex justify-content-between align-items-center mb-3">
                            <h2>Danh sách đơn hàng</h2>
                          </div>
                    
                          <table class="table table-bordered table-hover">
                            <thead class="table-light">
                              <tr>
                                <th>STT</th>
                                <th>Mã đơn hàng</th>
                                <th>Khách hàng</th>
                                <th>Ngày đặt</th>
                                <th>Tổng tiền</th>
                                <th>Số sản phẩm</th>
                                <th>Trạng thái</th>
                                <th>Thanh toán</th>
                                <th>Thao tác</th>
                              </tr>
                            </thead>
                            <tbody>
                              <c:forEach var="order" items="${orders}" varStatus="status">
                                <tr>
                                  <td>${(currentPage - 1) * pageSize + status.index + 1}</td>
                                  <td>${order.id}</td>
                                  <td>${order.name}</td>
                                  <td><fmt:formatDate value="${order.order_date}" pattern="dd/MM/yyyy HH:mm" /></td>
                                  <td class="price">${order.total_price}</td>
                                  <td>${order.totalItems}</td>
                                  <td>
                                    <c:choose>
                                      <c:when test="${order.status_id == 0}">
                                        <span class="badge bg-primary">Đang giao</span>
                                      </c:when>
                                      <c:when test="${order.status_id == 1}">
                                        <span class="badge bg-success">Đã giao</span>
                                      </c:when>
                                      <c:otherwise>
                                        <span class="badge bg-secondary">Không xác định</span>
                                      </c:otherwise>
                                    </c:choose>
                                  </td>
                                  <td>
                                    <c:choose>
                                      <c:when test="${order.payment_status == 0}">
                                        <span class="badge bg-warning">Chưa thanh toán</span>
                                      </c:when>
                                      <c:when test="${order.payment_status == 1}">
                                        <span class="badge bg-success">Đã thanh toán</span>
                                      </c:when>
                                      <c:otherwise>
                                        <span class="badge bg-secondary">Không xác định</span>
                                      </c:otherwise>
                                    </c:choose>
                                  </td>
                                  <td>
                                    <a href="<%= request.getContextPath() %>/admin/order/detail/${order.id}" class="btn btn-info btn-sm">Chi tiết</a>
                                    <div class="btn-group">
                                      <button type="button" class="btn btn-warning btn-sm dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                        Cập nhật trạng thái
                                      </button>
                                      <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="<%= request.getContextPath() %>/admin/order/updateStatus?id=${order.id}&status=0">Đang giao</a></li>
                                        <li><a class="dropdown-item" href="<%= request.getContextPath() %>/admin/order/updateStatus?id=${order.id}&status=1">Đã giao</a></li>
                                      </ul>
                                    </div>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        
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