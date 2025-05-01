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

    <title>DOGO</title>

    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/templatemo-hexashop.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl-carousel.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lightbox.css">

  </head>
  
  <body>
    <!-- ***** Header Area Start ***** -->
    <%@ include file="header_client.jsp" %>

    <!-- ***** Main Banner Area Start ***** -->
    <div class="page-heading" id="top">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="inner-content">
                      
                      
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Main Banner Area End ***** -->

    <!-- ***** Products Area Starts ***** -->
    <section class="section" id="products">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-heading">
                        <h2>Sản phẩm</h2>
                        <span>Hãy xem sản phẩm của chúng tôi.</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <!-- Hiển thị sản phẩm -->
                <c:forEach var="product" items="${products}">
                    <div class="col-lg-4">
                        <div class="item">
                            <div class="thumb">
                                <div class="hover-content">
                                    <ul>
                                        <li><a href="<%= request.getContextPath() %>/product_detail/${product.id}"><i class="fa fa-eye"></i></a></li>
                                     
                                        <li><a href="<%= request.getContextPath() %>/cart/${product.id}"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <img src="${product.images[0].image}" alt="${product.name}" style="height: 350px; object-fit: cover;">
                            </div>
                            <div class="down-content">
                                <h4 style=" display: -webkit-box; -webkit-line-clamp: 2; /* Giới hạn số dòng */ -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;height: 3em; /* Chiều cao tối đa (2 dòng) */ line-height: 1.5em; /* Chiều cao mỗi dòng */ text-align: left;">${product.name}</h4>
                                <span class="price">${product.price}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                
                <!-- Phân trang -->
                <div class="col-lg-12">
                    <div class="pagination">
                        <ul>
                            <!-- Hiển thị nút Previous -->
                            <c:if test="${currentPage > 1}">
                                <li><a href="?page=${currentPage - 1}"><i class="fa fa-chevron-left"></i> </a></li>
                            </c:if>

                            <!-- Hiển thị các trang -->
                            <c:forEach var="i" begin="1" end="${totalPages}">
                                <li class="${i == currentPage ? 'active' : ''}">
                                    <a href="?page=${i}">${i}</a>
                                </li>
                            </c:forEach>

                            <!-- Hiển thị nút Next -->
                            <c:if test="${currentPage < totalPages}">
                                <li><a href="?page=${currentPage + 1}"> <i class="fa fa-chevron-right"></i></a></li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Products Area Ends ***** -->

    <!-- ***** Footer Start ***** -->
    <%@ include file="footer_client.jsp" %>

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    
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
    <!-- Global Init -->
    <script src="<%= request.getContextPath() %>/assets/js/custom.js"></script>
  </body>
</html>
