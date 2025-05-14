<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đơn hàng #${order.id}</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<style>
    .order-header {
        background-color: #f8f9fa;
        border-radius: 8px;
        padding: 20px;
        margin-bottom: 20px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.05);
    }
    
    .order-header .badge {
        font-size: 0.9rem;
        padding: 8px 12px;
    }
    
    .customer-info {
        background-color: #fff;
        border-radius: 8px;
        padding: 20px;
        margin-bottom: 20px;
        box-shadow: 0 1px 3px rgba(0,0,0,0.1);
    }
    
    .products-table img {
        width: 60px;
        height: 60px;
        object-fit: cover;
        border-radius: 4px;
    }
    
    .status-history {
        background-color: #fff;
        border-radius: 8px;
        padding: 20px;
        margin-bottom: 20px;
        box-shadow: 0 1px 3px rgba(0,0,0,0.1);
    }
    
    .timeline {
        position: relative;
        padding-left: 30px;
    }
    
    .timeline::before {
        content: '';
        position: absolute;
        left: 10px;
        top: 0;
        height: 100%;
        width: 2px;
        background-color: #e9ecef;
    }
    
    .timeline-item {
        position: relative;
        padding-bottom: 20px;
    }
    
    .timeline-item:last-child {
        padding-bottom: 0;
    }
    
    .timeline-item::before {
        content: '';
        position: absolute;
        left: -30px;
        top: 0;
        width: 12px;
        height: 12px;
        border-radius: 50%;
        background-color: #0d6efd;
        border: 2px solid #fff;
        box-shadow: 0 0 0 2px #0d6efd;
    }
    
    .timeline-item.completed::before {
        background-color: #198754;
        box-shadow: 0 0 0 2px #198754;
    }
    
    .timeline-item.canceled::before {
        background-color: #dc3545;
        box-shadow: 0 0 0 2px #dc3545;
    }
    
    .update-status-form {
        background-color: #fff;
        border-radius: 8px;
        padding: 20px;
        box-shadow: 0 1px 3px rgba(0,0,0,0.1);
    }
    
    .btn-status {
        margin-right: 10px;
        margin-bottom: 10px;
    }
</style>
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
                        <h1 class="mt-4">Chi tiết đơn hàng #${order.id}</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Trang thống kê</a></li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath() %>/admin/orders">Quản lý đơn hàng</a></li>
                            <li class="breadcrumb-item active">Chi tiết đơn hàng #${order.id}</li>
                        </ol>     
                        
                        <!-- Order Header -->
                        <div class="row order-header">
                            <div class="col-md-6">
                                <h4>Đơn hàng #${order.id}</h4>
                                <p><strong>Ngày đặt:</strong> <fmt:formatDate value="${order.order_date}" pattern="dd/MM/yyyy HH:mm" /></p>
                                <p><strong>Tổng tiền:</strong> <span class="price">${order.total_price}</span></p>
                            </div>
                            <div class="col-md-6 text-md-end">
                                <div class="mb-3">
                                    <strong>Trạng thái đơn hàng:</strong><br>
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
                                </div>
                                <div>
                                    <strong>Trạng thái thanh toán:</strong><br>
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
                                </div>
                            </div>
                        </div>
                        
                        <div class="row">
                            <!-- Customer Information -->
                            <div class="col-md-4 order-md-2">
                                <div class="customer-info">
                                    <h5 class="mb-3"><i class="fas fa-user me-2"></i>Thông tin khách hàng</h5>
                                    <div class="mb-2">
                                        <strong>Khách hàng:</strong> ${order.name}
                                    </div>
                                    <div class="mb-2">
                                        <strong>Số điện thoại:</strong> ${order.phone}
                                    </div>
                                    <div class="mb-2">
                                        <strong>Địa chỉ giao hàng:</strong> ${order.address}
                                    </div>
                                    
                                    <div class="mb-2">
                                        <strong>Phương thức thanh toán:</strong>
                                        <c:choose>
                                            <c:when test="${order.payment_method == 0}">
                                                Thanh toán khi nhận hàng (COD)
                                            </c:when>
                                            <c:when test="${order.payment_method == 1}">
                                                Thanh toán online
                                            </c:when>
                                            <c:otherwise>
                                                Không xác định
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                              
                            </div>
                            
                            <!-- Order Products -->
                            <div class="col-md-8 order-md-1">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-shopping-cart me-1"></i>
                                        Sản phẩm trong đơn hàng
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-hover products-table">
                                                <thead>
                                                    <tr>
                                                        <th width="80">Ảnh</th>
                                                        <th>Sản phẩm</th>
                                                        <th>Đơn giá</th>
                                                        <th>Số lượng</th>
                                                        <th class="text-end">Thành tiền</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="item" items="${orderItems}">
                                                        <tr>
                                                            <td>
                                                                <img src="${item.productImage}" alt="${item.productName}" class="img-thumbnail">
                                                            </td>
                                                            <td>
                                                                <div><strong>${item.productName}</strong></div>
                                                                <small class="text-muted">${item.product_id}</small>
                                                            </td>
                                                            <td class="price">${item.price}</td>
                                                            <td>${item.quantity}</td>
                                                            <td class="text-end price">${item.subtotal}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                                <tfoot>
                                                    <tr>
                                                        <td colspan="4" class="text-end"><strong>Tổng cộng:</strong></td>
                                                        <td class="text-end price fw-bold">${order.total_price}</td>
                                                    </tr>
                                                </tfoot>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                
                               
                            </div>
                        </div>
                        
                        <!-- Action buttons -->
                        <div class="row mt-4 mb-5">
                            <div class="col-12">
                                <a href="<%= request.getContextPath() %>/admin/order" class="btn btn-secondary">
                                    <i class="fas fa-arrow-left me-2"></i>Quay lại danh sách
                                </a>
                               
                            </div>
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
    </body>
</html>