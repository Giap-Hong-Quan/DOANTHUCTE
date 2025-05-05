<%@page import="entities.category"%>
<%@page import="java.util.List"%>
<%@page import="entities.user"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Font Awesome CDN -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<%
    user u = (user) session.getAttribute("user");
    if (u == null) {
        u = (user) session.getAttribute("userNormal");
    }
    if (u == null) {
        u = (user) session.getAttribute("userGG");
    }
    if (u == null) {
        u = (user) session.getAttribute("userFB");
    }

    // Thêm dòng này để set u vào request
    request.setAttribute("user", u);
%>



<!-- ***** Preloader Start ***** -->
<div id="preloader">
    <div class="jumper">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>  
<!-- ***** Preloader End ***** -->

<!-- ***** Header Area Start ***** -->
<header class="header-area header-sticky">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="main-nav">
                    <!-- ***** Logo Start ***** -->
                    <a href="<%= request.getContextPath() %>/" class="logo">
                        <img src="<%= request.getContextPath() %>/assets/images/logoDOGO.jpg" style="width: 170px">
                    </a>
                    <!-- ***** Logo End ***** -->
                    
                    <!-- ***** Menu Start ***** -->
                    <ul class="nav">
                        <li class="scroll-to-section"><a href="<%= request.getContextPath() %>/" class="active">Trang chủ</a></li>
                        <li class="submenu">
                            <a href="<%= request.getContextPath() %>/product">Sản phẩm</a>
                            <ul>
                                <% 
                                    List<category> categories = (List<category>) session.getAttribute("categories");
                                    if (categories != null) {
                                        for (category c : categories) {
                                %>
                                    <li><a href="<%= request.getContextPath() %>/product/<%= c.getId() %>"><%= c.getName() %></a></li>
                                <% 
                                        }
                                    }
                                %>
                            </ul>
                        </li>
                        
        
 <li><a href="<%= request.getContextPath() %>/about">Giới thiệu</a></li>
    <li><a href="<%= request.getContextPath() %>/contract">Liên hệ</a></li>
                      

                        <c:choose>
                            <c:when test="${not empty user}">
                                <li><a href="<%= request.getContextPath() %>/cart" style="width: 100%; height: 100%; color: inherit; text-decoration: none;">
                                    <i style="font-size: 26px;" class="fa-solid fa-cart-shopping"></i></a>
                                </li>

                                <!-- Nếu có user, hiển thị thông tin người dùng -->
                                <li class="submenu">
                                    <a href="javascript:;">
                                        <img src="${user.avatar != null ? user.avatar : '/path/to/default-avatar.jpg'}" 
                                             alt="${user.name}" 
                                             style="width: 30px; height: 30px; border-radius: 50%; border: 3px solid #666464;">
                                    </a>
                                    <ul>
                                        <li><a href="#">${user.name}</a></li>
                                        <li><a href="<%= request.getContextPath() %>/profile/${user.id}">Thông tin khách hàng</a></li>
                                        <li><a href="<%= request.getContextPath() %>/purchase-history">Lịch sử mua hàng</a></li>
                                        <li><a href="<%= request.getContextPath() %>/logout">Đăng xuất</a></li>
                                    </ul>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <!-- Nếu không có user, hiển thị nút Đăng ký và Đăng nhập -->
                                <li><a href="<%= request.getContextPath() %>/register">Đăng ký</a></li>
                                <li><a href="<%= request.getContextPath() %>/login">Đăng nhập</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>        
                    <a class='menu-trigger'>
                        <span>Menu</span>
                    </a>
                    <!-- ***** Menu End ***** -->
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- ***** Header Area End ***** -->
