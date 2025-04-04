<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
    <div class="sb-sidenav-menu">
        <div class="nav">
            <div class="sb-sidenav-menu-heading">Core</div>
            <a class="nav-link" href="<%= request.getContextPath() %>/admin/dashboardAdmin">
                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                Thống kê
            </a>
            <a class="nav-link" href="<%= request.getContextPath() %>/admin/user   ">
                <div class="sb-nav-link-icon"><i class="fa-solid fa-users"></i></div>
                Tài khoản
            </a>
            <a class="nav-link" href="<%= request.getContextPath() %>/admin/product">
                <div class="sb-nav-link-icon"><i class="fa-solid fa-box"></i></div>
                Sản phẩm
            </a>
            <a class="nav-link" href="<%= request.getContextPath() %>/admin/order">
                <div class="sb-nav-link-icon"><i class="fa-solid fa-cart-shopping"></i></div>
                Đơn hàng
            </a>
        </div>
    </div>
    <div class="sb-sidenav-footer">
        <div class="small">Logged in as:</div>
        WelCome HongQuan
    </div>
</nav>