<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập nhật thông tin sản phẩm</title>
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
                        <h1 class="mt-4">Cập nhật sản phẩm</h1>
                        <ol class="breadcrumb mb-4">
                          <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Thống kê</a><a href="<%= request.getContextPath() %>/admin/product">/Sản phẩm</a>/Cập nhật</li>
                        </ol>     
                        <div class="container mt-4">
                          <div class="card">
                            <div class="card-header">Cập nhật tài khoản</div>
                            <form action="<%= request.getContextPath() %>/admin/product/update/${product.id}" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                           
              <div class="mb-3">
                <label for="name" class="form-label">Tên sản phẩm</label>
                <input type="text" class="form-control" id="name" name="name" value="${product.name}" placeholder="Nhập tên sản phẩm" required>
              </div>

              <div class="mb-3">
                <label for="description" class="form-label">Mô tả</label>
                <input type="text" class="form-control" id="description" name="description" value="${product.description}" placeholder="Nhập mô tả" required>
              </div>

              <div class="mb-3">
                <label for="price" class="form-label">Số giá</label>
                <input type="text" class="form-control" id="price" name="price" value="${product.price}" placeholder="Nhập giá" required>
              </div>
  				<div class="mb-3">
                <label for="quantity" class="form-label">Số lượng</label>
                <input type="number" class="form-control" id="quantity" name="quantity" value="${product.quantity}" placeholder="Nhập số lượng" required>
              </div>


    <div class="mb-3">
  <label for="category" class="form-label">Danh mục</label>
  <select class="form-select" id="category" name="category_id" required>
    <!-- Option mặc định -->
    <option value="" disabled>-- Chọn danh mục --</option>
    
    <!-- Option hiện tại của sản phẩm -->
    <option value="${product.category_id}" selected>
      ${product.categoryName} (hiện tại)
    </option>
    
    <!-- Các danh mục khác (nếu cần thay đổi) -->
    <c:forEach items="${allCategories}" var="cat">
      <c:if test="${cat.id != product.category_id}">
        <option value="${cat.id}">${cat.name}</option>
      </c:if>
    </c:forEach>
  </select>
</div>

              <div class="mb-3">
                <label for="image" class="form-label">Ảnh sản phẩm</label>
                 <input class="form-control" type="file" id="image" name="image" accept="image/*" multiple>
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
document.addEventListener("DOMContentLoaded", function () {
  const priceInput = document.getElementById("price");

  // Khi load nếu có sẵn giá thì định dạng
  if (priceInput.value) {
    priceInput.value = Number(priceInput.value).toLocaleString('vi-VN');
  }

  // Khi người dùng rời khỏi ô input (blur)
  priceInput.addEventListener("blur", function () {
    let raw = priceInput.value.replace(/\D/g, "");
    if (raw) {
      priceInput.value = Number(raw).toLocaleString('vi-VN');
    }
  });

  // Trước khi submit, bỏ format để giữ lại giá trị raw (double)
  priceInput.form.addEventListener("submit", function () {
    priceInput.value = priceInput.value.replace(/\D/g, "");
  });
});
</script>



<script>
function changeMainImage(thumb) {
    document.querySelector('.main-image').src = thumb.src;
}
</script>
              </div>

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