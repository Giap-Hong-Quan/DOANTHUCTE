<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>Thông tin khách hàng</title>


    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/font-awesome.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/templatemo-hexashop.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl-carousel.css">

    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lightbox.css">
    </head>
    
    <body>
    <!-- ***** Header Area End ***** -->
  <%@include file="header_client.jsp" %>

    <div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
            <h4 class="mb-0">Thông Tin Khách Hàng</h4>
            <a href="<%= request.getContextPath() %>/edit-profile" class="btn btn-light btn-sm">
                <i class="bi bi-pencil-square"></i> Chỉnh sửa
            </a>
        </div>
        <div class="card-body">
            <div class="row align-items-center">
                <div class="col-md-4 text-center mb-3">
                    <img src="${not empty user.avatar ? user.avatar : pageContext.request.contextPath.concat('/assets/images/default-avatar.jpg')}"
                         class="rounded-circle shadow" width="150" height="150" alt="Avatar"
                         style="object-fit: cover; border: 4px solid #0d6efd;">
                </div>
                <div class="col-md-8">
                    <div class="mb-3">
				    <label class="form-label fw-bold">Tên:</label>
				    <p class="form-control-plaintext" style="display: inline;">${user.name}</p>
				</div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Email:</label>
                        <p class="form-control-plaintext" style="display: inline;">${user.email}</p>
                   </div>
                     <div class="mb-3">
                        <label class="form-label fw-bold">Số điện thoại:</label>
                        <p class="form-control-plaintext" style="display: inline;">${user.phone}</p>
                   </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- ***** Footer Start ***** -->
   <%@include file="footer_client.jsp" %>
    

    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/assets/js/jquery-2.1.0.min.js"></script>

    <!-- Bootstrap -->
    <script src="<%= request.getContextPath() %>/assets/js/popper.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>

    <!-- Plugins -->
    <script src="<%= request.getContextPath() %>/assets/js/owl-carousel.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/accordions.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/datepicker.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/scrollreveal.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/waypoints.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/jquery.counterup.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/imgfix.min.js"></script> 
    <script src="<%= request.getContextPath() %>/assets/js/slick.js"></script> 
    <script src="<%= request.getContextPath() %>/assets/js/lightbox.js"></script> 
    <script src="<%= request.getContextPath() %>/assets/js/isotope.js"></script> 
    
    <!-- Global Init -->
    <script src="<%= request.getContextPath() %>/assets/js/custom.js"></script>

    <script>

        $(function() {
            var selectedClass = "";
            $("p").click(function(){
            selectedClass = $(this).attr("data-rel");
            $("#portfolio").fadeTo(50, 0.1);
                $("#portfolio div").not("."+selectedClass).fadeOut();
            setTimeout(function() {
              $("."+selectedClass).fadeIn();
              $("#portfolio").fadeTo(50, 1);
            }, 500);
                
            });
        });

    </script>

  </body>
</html>