<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html lang="vi">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DOGO - Thời trang chất lượng cao">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>DOGO - Thời trang Việt</title>

    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/templatemo-hexashop.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl-carousel.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lightbox.css">
    </head>
    
    <body>
    <!-- ***** Header Area End ***** -->
    <%@ include file="header_client.jsp" %>
    
    <!-- ***** Banner chính ***** -->
    <div class="page-heading about-page-heading" id="top">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="inner-content">
                        <h2>Về cửa hàng chúng tôi</h2>
                        <span>Phong cách, sáng tạo &amp; đẳng cấp</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Banner chính End ***** -->

    <!-- ***** Về chúng tôi ***** -->
    <div class="about-us">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="left-image">
                        <img src="assets/images/about-left-image.jpg" alt="Về DOGO">
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="right-content">
                        <h4>Về chúng tôi &amp; Khả năng</h4>
                        <span>Chúng tôi mang đến những sản phẩm thời trang chất lượng cao với giá cả hợp lý</span>
                        <div class="quote">
                            <i class="fa fa-quote-left"></i><p>Thời trang không chỉ là quần áo, đó là phong cách sống</p>
                        </div>
                        <p>DOGO được thành lập với sứ mệnh mang đến cho khách hàng những trải nghiệm mua sắm tuyệt vời nhất. Chúng tôi luôn nỗ lực cải tiến chất lượng sản phẩm và dịch vụ mỗi ngày.</p>
                        <ul>
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-behance"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Về chúng tôi Ends ***** -->

    <!-- ***** Đội ngũ ***** -->
    <section class="our-team">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-heading">
                        <h2>Đội ngũ của chúng tôi</h2>
                        <span>Sự chuyên nghiệp và tận tâm tạo nên sự khác biệt</span>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="team-item">
                        <div class="thumb">
                            <div class="hover-effect">
                                <div class="inner-content">
                                    <ul>
                                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#"><i class="fa fa-behance"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <img src="assets/images/team-member-01.jpg" alt="Thành viên 1">
                        </div>
                        <div class="down-content">
                            <h4>Nguyễn Văn A</h4>
                            <span>Giám đốc sản phẩm</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="team-item">
                        <div class="thumb">
                            <div class="hover-effect">
                                <div class="inner-content">
                                    <ul>
                                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#"><i class="fa fa-behance"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <img src="assets/images/team-member-02.jpg" alt="Thành viên 2">
                        </div>
                        <div class="down-content">
                            <h4>Trần Thị B</h4>
                            <span>Thiết kế trưởng</span>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="team-item">
                        <div class="thumb">
                            <div class="hover-effect">
                                <div class="inner-content">
                                    <ul>
                                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                                        <li><a href="#"><i class="fa fa-behance"></i></a></li>
                                    </ul>
                                </div>
                            </div>
                            <img src="assets/images/team-member-03.jpg" alt="Thành viên 3">
                        </div>
                        <div class="down-content">
                            <h4>Lê Văn C</h4>
                            <span>Giám đốc kinh doanh</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Đội ngũ Ends ***** -->

    <!-- ***** Dịch vụ ***** -->
    <section class="our-services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-heading">
                        <h2>Dịch vụ của chúng tôi</h2>
                        <span>Chất lượng dịch vụ làm nên thương hiệu</span>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="service-item">
                        <h4>Giao hàng nhanh</h4>
                        <p>Giao hàng toàn quốc trong vòng 2-3 ngày với khu vực thành phố</p>
                        <img src="assets/images/service-01.jpg" alt="Giao hàng">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="service-item">
                        <h4>Đổi trả dễ dàng</h4>
                        <p>Chính sách đổi trả trong vòng 7 ngày với sản phẩm chưa sử dụng</p>
                        <img src="assets/images/service-02.jpg" alt="Đổi trả">
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="service-item">
                        <h4>Hỗ trợ 24/7</h4>
                        <p>Đội ngũ CSKH luôn sẵn sàng hỗ trợ mọi yêu cầu của bạn</p>
                        <img src="assets/images/service-03.jpg" alt="Hỗ trợ">
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Dịch vụ Ends ***** -->

   <!-- ***** Subscribe Area Starts ***** -->
    <div class="subscribe">
        <div class="container">
            <div class="row">
                <div class="col-lg-8">
                    <div class="section-heading">
                        <h2>Băng cách đăng ký bạn có thể nhận những ưu đãi ngầu nhiên từ cửa hàng</h2>
                        <span>Chi tiết đến từng chi tiết là điều khiến Hexashop khác biệt so với các chủ đề khác..</span>
                    </div>
                    <form id="subscribe" action="" method="get">
                        <div class="row">
                          <div class="col-lg-5">
                            <fieldset>
                              <input name="name" type="text" id="name" placeholder="Tên của bạn" required="">
                            </fieldset>
                          </div>
                          <div class="col-lg-5">
                            <fieldset>
                              <input name="email" type="text" id="email" pattern="[^ @]*@[^ @]*" placeholder="Địa chỉ email của bạn" required="">
                            </fieldset>
                          </div>
                          <div class="col-lg-2">
                            <fieldset>
                              <button type="submit" id="form-submit" class="main-dark-button"><i class="fa fa-paper-plane"></i></button>
                            </fieldset>
                          </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-4">
                    <div class="row">
                        <div class="col-6">
                            <ul>
                                <li>Vị trí cửa hàng:<br><span>29-Thôn đức phổ 2-Thị trấn Cát khách,Tỉnh Bình Định</span></li>
                                <li>Điện thoại:<br><span>0335906807</span></li>
                                <li>Địa điểm văn phòng:<br><span>Tại nhà </span></li>
                            </ul>
                        </div>
                        <div class="col-6">
                            <ul>
                                <li>Giờ làm việc:<br><span>07:30 sáng - 9:30 tối hàng ngày</span></li>
                                <li>Email:<br><span>giaphongquan2407@gmail.com</span></li>
                                <li>Phương tiện truyền thông xã hội:<br><span><a href="#">Facebook</a>, <a href="#">Instagram</a>, <a href="#">Behance</a>, <a href="#">Linkedin</a></span></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Subscribe Area Ends ***** -->

    <!-- ***** Footer Start ***** -->
    <%@include file="footer_client.jsp" %>

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
    
    <!-- Global Init -->
    <script src="<%= request.getContextPath() %>/assets/js/custom.js"></script>
  </body>
</html>