<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html lang="vi">

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DOGO - Liên hệ">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">

    <title>DOGO - Liên hệ</title>

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
                        <h2>Liên hệ với chúng tôi</h2>
                        <span>DOGO - Thời trang chất lượng cao</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Banner chính End ***** -->

    <!-- ***** Khu vực liên hệ ***** -->
    <div class="contact-us">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div id="map">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3501.3752735653784!2d109.15063067635377!3d14.127532153196759!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x316f36691c92db1d%3A0xc83053c2d447352a!2zVHLGsOG7nW5nIFRIUFQgU-G7kSAyIFBow7kgQ8OhdA!5e1!3m2!1svi!2sus!4v1746094867261!5m2!1svi!2sus" width="100%" height="400" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                      
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="section-heading">
                        <h2>Gửi lời chào đến chúng tôi!</h2>
                        <span>Chúng tôi luôn sẵn sàng lắng nghe ý kiến của bạn</span>
                    </div>
                    <form id="contact" action="" method="post">
                        <div class="row">
                          <div class="col-lg-6">
                            <fieldset>
                              <input name="name" type="text" id="name" placeholder="Họ và tên" required="">
                            </fieldset>
                          </div>
                          <div class="col-lg-6">
                            <fieldset>
                              <input name="email" type="text" id="email" placeholder="Email của bạn" required="">
                            </fieldset>
                          </div>
                          <div class="col-lg-12">
                            <fieldset>
                              <textarea name="message" rows="6" id="message" placeholder="Nội dung liên hệ" required=""></textarea>
                            </fieldset>
                          </div>
                          <div class="col-lg-12">
                            <fieldset>
                              <button type="submit" id="form-submit" class="main-dark-button"><i class="fa fa-paper-plane"></i> Gửi tin nhắn</button>
                          </div>
                        </div>
                      </form>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Khu vực liên hệ Ends ***** -->

   
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