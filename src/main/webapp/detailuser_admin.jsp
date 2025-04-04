<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết thông tin khách hàng</title>
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
                        <h1 class="mt-4">Chi tiết tài khoản</h1>
                        <ol class="breadcrumb mb-4">
                          <li class="breadcrumb-item active"><a href="<%= request.getContextPath() %>/admin/dashboardAdmin">Thống kê</a><a href="<%= request.getContextPath() %>/admin/user">/Tài khoản</a>/Chi tiết</li>
                        </ol>     
                        <div class="container mt-4">
                          <div class="card">
                            <div class="card-header">Thông tin tài khoản</div>
                            <div class="card-body">
		                              <% if (request.getAttribute("user") != null) { %>
					    <p><strong>Mã id:</strong> ${user.id}</p>
					    <p><strong>Email:</strong> ${user.email}</p>
					    <p><strong>Tên:</strong> ${user.name}</p>
					    <p><strong>Vai trò:</strong> ${user.roleName}</p>
										 <c:choose>
									    <c:when test="${fn:startsWith(user.avatar, 'http')}">
									        <img src="${user.avatar}" alt="Avatar">
									    </c:when>
									    <c:when test="${not empty user.avatar}">
									        <img src="<%= request.getContextPath() %>/assets/images/${user.avatar}" 
									             alt="Avatar"  
									             style="width: 200px; height: 200px; object-fit: cover; border-radius: 10px;">
									    </c:when>
									    <c:otherwise>
									        <img src="<%= request.getContextPath() %>/assets/images/default-avatar.jpg" 
									             alt="Default Avatar"  
									             style="width: 200px; height: 200px; object-fit: cover; border-radius: 10px;">
									    </c:otherwise>
									</c:choose>

					  	
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/scripts.js"></script>
        <script src="<%= request.getContextPath() %>/assets/https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-area-demo.js"></script>
        <script src="<%= request.getContextPath() %>/assets/js/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="<%= request.getContextPath() %>/assets/js/datatables-simple-demo.js"></script>
    </body>
</html>