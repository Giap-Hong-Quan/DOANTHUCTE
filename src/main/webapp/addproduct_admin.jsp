<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
 <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
 <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous" />
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous" ></script>
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
                        <h1 class="mt-4">Thêm sản phẩm</h1>
                        <ol class="breadcrumb mb-4">
                          <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Thống kê</a><a href="<%= request.getContextPath() %>/admin/product">/Tài khoản</a>/Thêm</li>
                        </ol>     
                        <div class="container mt-4">
                          <div class="card">
                            <div class="card-header">Thêm tài khoản</div>
                            <form action="<%= request.getContextPath() %>/admin/product/add" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                           
              <div class="mb-3">
                <label for="name" class="form-label">Tên sản phẩm</label>
                <input type="text" class="form-control" id="name" name="name"  placeholder="Nhập tên sản phẩm" required>
              </div>

              <div class="mb-3">
                <label for="description" class="form-label">Mô tả sản phẩm</label>
                <input type="text" class="form-control" id="description" name="description"  placeholder="Nhập mô tả sản phẩm" required>
              </div>

              <div class="mb-3">
                <label for="price" class="form-label">Số giá tiền</label>
                <input type="text" class="form-control" id="price" name="price"  placeholder="Nhập giá tiền" required>
              </div>
				<div class="mb-3">
                <label for="quantity" class="form-label">Nhập số lượng</label>
                <input type="text" class="form-control" id="quantity" name="quantity"  placeholder="Nhập số lượng" required>
              </div>
			  <div class="mb-3">
			  <label for="role" class="form-label">Chọn danh mục</label>
			 <select class="form-select" id="category_id" name="category_id" required>
				  <option value="">-- Chọn danh mục --</option>
				  <c:forEach var="cat" items="${listCategory}">
				    <option value="${cat.id}">${cat.name}</option>
				  </c:forEach>
				</select>
			</div>
              <div class="mb-3">
                <label for="image" class="form-label">Hình ảnh </label>
           <input class="form-control" type="file" id="image" name="image" accept="image/*" multiple>
              <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg">Thêm</button>
              </div>
            </form>
                        </div>   
                        
                    </div>	
                </main> 
             <%@include file="footer_admin.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scripts.js"></script>
        <script src="<%= request.getContextPath() %>/assets/https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-area-demo.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/datatables-simple-demo.js"></script>
    </body>
</html>