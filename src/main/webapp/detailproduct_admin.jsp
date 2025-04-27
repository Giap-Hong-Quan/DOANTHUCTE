<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
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
                        <h1 class="mt-4">Chi tiết sản phẩm</h1>
                        <ol class="breadcrumb mb-4">
                          <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Thống kê</a><a href="<%= request.getContextPath() %>/admin/product">/Sản phẩm</a>/Chi tiết</li>
                        </ol>     
                        <div class="container mt-4">
                          <div class="card">
                            <div class="card-header">Thông tin sản phẩm</div>
                            <div class="card-body">
		                              <% if (request.getAttribute("product") != null) { %>
					    <p><strong>Mã id:</strong> ${product.id}</p>
					    <p><strong>Danh mục:</strong> ${product.categoryName}</p>
					    <p><strong>Tên:</strong> ${product.name}</p>
					     <p><strong>Mô tả:</strong> ${product.description}</p>
					  <p><strong>Giá:</strong> <span class="price">${product.price}</span></p>
					     <p><strong>Số lượng</strong> ${product.quantity}</p>

<div class="row">
    <!-- Ảnh chính (lấy ảnh đầu tiên) -->
    <div class="col-md-4">
        <c:if test="${not empty product.images}">
            <img src="${product.images[0].image}" 
                 class="img-fluid main-image">
        </c:if>
    </div>
    
    <!-- Danh sách ảnh phụ -->
    <div class="col-md-4">
        <div class="row">
            <c:forEach items="${product.images}" var="image" varStatus="loop">
                <div class="col-6 mb-3">
                    <img style="width: 100px" src="${image.image}" 
                         class="img-thumbnail ${loop.first ? 'border-primary' : ''}"
                         onclick="changeMainImage(this)">
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script>
function changeMainImage(thumb) {
    document.querySelector('.main-image').src = thumb.src;
}
</script>
</div>
					  	
					<% } else { %>
					    <p class="text-danger">User not found!</p>
					<% } %>

                          </div>
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
        <script>
  // Format giá tiền kiểu Việt Nam cho một phần tử duy nhất
  document.addEventListener("DOMContentLoaded", function () {
    const priceEl = document.querySelector('.price');
    if (priceEl) {
      const raw = parseFloat(priceEl.textContent); // Lấy giá trị gốc (số)
      if (!isNaN(raw)) {
        const formatted = raw.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        priceEl.textContent = formatted; // Cập nhật lại nội dung đã format
      }
    }
  });
</script>
        
    </body>
</html>