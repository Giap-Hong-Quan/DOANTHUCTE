<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- Font Awesome CDN -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <%@ page import="entities.user" %>
<%
    user u = (user) request.getAttribute("user");
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
                                <a href="products.html">Sản phẩm</a>
                                <ul>
                                   <li ><a href="">Nam</a></li>
		                            <li ><a href="#">Nữ</a></li>
		                            <li ><a href="">Trẻ em</a></li>
		                            <li><a href="">Phụ kiện</a></li>
		                            <li ><a href="">Khác</a></li>
                                </ul>
                            </li>
		                            <li class=""><a href="">Sản phẩm duy nhất</a></li>
                            
                            <li class="submenu">
                                <a href="">Trang</a>
                                <ul>
                                    <li><a href="">Giới thiệu</a></li>
                                    <li class=""><a href="">Khám phá</a></li>
                                    <li><a href="contact.html">Liên hệ</a></li>
                                </ul>
                            </li>
                          
                        <c:choose>
    <c:when test="${not empty user}">
                           	<li><a href="" style=" width: 100%;height: 100%;color: inherit; text-decoration: none; "><i style=" font-size: 26px; " class="fa-solid fa-cart-shopping"></i></a></li>

        <!-- Nếu có user, hiển thị thông tin người dùng -->
        <li class="submenu avatar">
            <a href="javascript:;">
                <img src="${user.avatar}" alt="" style="width: 30px; height: 30px; border-radius: 50%; border: 3px solid #666464;">
            </a>
            <ul>
                <li><a href="#">${user.name}</a></li>
                <li><a href="<%= request.getContextPath() %>/profile/${user.id}">Thông tin khách hàng</a></li>
                <li><a href="#">Lịch sử mua hàng</a></li>
                <li><a href="<%= request.getContextPath()%>/logout">Đăng xuất</a></li>
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