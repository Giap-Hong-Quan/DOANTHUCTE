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
<!--

TemplateMo 571 Hexashop

https://templatemo.com/tm-571-hexashop

-->
    </head>
    
    <body>
    <!-- ***** Header Area End ***** -->
<%@ include file="../../header_client.jsp" %>



    <!-- ***** Main Banner Area Start ***** -->
    <div class="main-banner" id="top">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">
                    <div class="left-content">
                        <div class="thumb">
                            <div class="inner-content">
                                <h4>Chúng tôi là Dogoshop</h4>
                                <span>Mang lại cho khách hàng những sản phẩm tốt nhất</span>
                                <div class="main-border-button">
                                    <a href="<%= request.getContextPath() %>/product">Mua ngay!</a>
                                </div>
                            </div>
                            <img src="assets/images/left-banner-image.jpg" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="right-content">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>Nữ</h4>
                                            <span>Quần áo đẹp nhất cho nữ</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>Nữ</h4>
                                                <p>Khám phá thế giới thời trang nữ đầy cuốn hút – nơi vẻ đẹp và phong cách lên ngôi..</p>
                                                <div class="main-border-button">
                                                    <a href="<%= request.getContextPath() %>/product/1">Khám phá thêm</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/baner-right-image-01.jpg">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>Nam</h4>
                                            <span>Quần áo đẹp nhất cho nam</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>Men</h4>
                                                <p>Khám phá phong cách thời trang nam đầy lịch lãm và cá tính – định hình đẳng cấp riêng của bạn..</p>
                                                <div class="main-border-button">
                                                    <a href="<%= request.getContextPath() %>/product/4">Khám phá thêm</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/baner-right-image-02.jpg">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>Trẻ em</h4>
                                            <span>Quần áo tốt nhất cho trẻ em</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>Trẻ em</h4>
                                                <p>Khám phá thời trang trẻ em đáng yêu và năng động – nơi bé thoải mái vui chơi và tỏa sáng mỗi ngày.</p>
                                                <div class="main-border-button">
                                                    <a href="<%= request.getContextPath() %>/product/6">Khám phá thêm</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/baner-right-image-03.jpg">
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="right-first-image">
                                    <div class="thumb">
                                        <div class="inner-content">
                                            <h4>Phụ kiện</h4>
                                            <span>Phụ kiện xu hướng tốt nhất</span>
                                        </div>
                                        <div class="hover-content">
                                            <div class="inner">
                                                <h4>Phụ kiện</h4>
                                                <p>Khám phá phụ kiện thời trang tinh tế – điểm nhấn hoàn hảo để tôn lên cá tính và phong cách của bạn.</p>
                                                <div class="main-border-button">
                                                    <a href="<%= request.getContextPath() %>/product/7">Khám phá thêm</a>
                                                </div>
                                            </div>
                                        </div>
                                        <img src="assets/images/baner-right-image-04.jpg">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ***** Main Banner Area End ***** -->

    <!-- ***** Men Area Starts ***** -->
    <section class="section" id="men">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="section-heading">
                        <h2>Nam mới nhất</h2>
                        <span>Chi tiết đến từng chi tiết là điều khiến Dogoshop khác biệt so với các chủ đề khác..</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="men-item-carousel">
                        <div class="owl-men-item owl-carousel">
    <c:forEach var="p" items="${menProducts}">
    <div class="item">
        <div class="thumb">
            <div class="hover-content">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/product_detail/${p.id}"><i class="fa fa-eye"></i></a></li>
                 
                    <li><a href="<%= request.getContextPath() %>/cart/${p.id}"><i class="fa fa-shopping-cart"></i></a></li>
                </ul>
            </div>
            <!-- Hiển thị ảnh đầu tiên của sản phẩm -->
            <img src="${p.images[0].image}" alt="${p.name}" style="height: 350px";object-fit: cover;>
        </div>
        <div class="down-content">
            <h4 style=" display: -webkit-box; -webkit-line-clamp: 2; /* Giới hạn số dòng */ -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;height: 3em; /* Chiều cao tối đa (2 dòng) */ line-height: 1.5em; /* Chiều cao mỗi dòng */ text-align: left;">${p.name}</h4>
            <span  class="price">${p.price}</span>
        </div>
    </div>
</c:forEach>

</div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Men Area Ends ***** -->

    <!-- ***** Women Area Starts ***** -->
    <section class="section" id="women">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="section-heading">
                        <h2>Nữ mới nhất</h2>
                        <span>Chi tiết đến từng chi tiết là điều khiến Hexashop khác biệt so với các chủ đề khác..</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="women-item-carousel">
                        <div class="owl-women-item owl-carousel">
                            <c:forEach var="p" items="${womenProducts}" >
    <div class="item">
        <div class="thumb">
            <div class="hover-content">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/product_detail/${p.id}"><i class="fa fa-eye"></i></a></li>
                    
                    <li><a href="<%= request.getContextPath() %>/cart/${p.id}""><i class="fa fa-shopping-cart"></i></a></li>
                </ul>
            </div>
            <!-- Hiển thị ảnh đầu tiên của sản phẩm -->
            <img src="${p.images[0].image}" alt="${p.name}" style="height: 350px";object-fit: cover;>
        </div>
        <div class="down-content">
             <h4 style=" display: -webkit-box; -webkit-line-clamp: 2; /* Giới hạn số dòng */ -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;height: 3em; /* Chiều cao tối đa (2 dòng) */ line-height: 1.5em; /* Chiều cao mỗi dòng */ text-align: left;">${p.name}</h4>
            <span  class="price">${p.price}</span>
        </div>
    </div>
</c:forEach>
                           
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Women Area Ends ***** -->

    <!-- ***** Kids Area Starts ***** -->
    <section class="section" id="kids">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="section-heading">
                        <h2>Trẻ em mới nhất</h2>
                        <span>Chi tiết đến từng chi tiết là điều khiến Hexashop khác biệt so với các chủ đề khác..</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="kid-item-carousel">
                        <div class="owl-kid-item owl-carousel">
                        
                            <c:forEach var="p" items="${kidsProducts}">
    <div class="item">
        <div class="thumb">
            <div class="hover-content">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/product_detail/${p.id}"><i class="fa fa-eye"></i></a></li>
               
                    <li><a href="<%= request.getContextPath() %>/cart/${p.id}""><i class="fa fa-shopping-cart"></i></a></li>
                </ul>
            </div>
            <!-- Hiển thị ảnh đầu tiên của sản phẩm -->
            <img src="${p.images[0].image}" alt="${p.name}" style="height: 350px";object-fit: cover;>
        </div>
        <div class="down-content">
             <h4 style=" display: -webkit-box; -webkit-line-clamp: 2; /* Giới hạn số dòng */ -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;height: 3em; /* Chiều cao tối đa (2 dòng) */ line-height: 1.5em; /* Chiều cao mỗi dòng */ text-align: left;">${p.name}</h4>
            <span  class="price">${p.price}</span>
        </div>
    </div>
</c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Kids Area Ends ***** -->
 <section class="section" id="kids">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="section-heading">
                        <h2>Phụ kiện mới nhất</h2>
                        <span>Chi tiết đến từng chi tiết là điều khiến Hexashop khác biệt so với các chủ đề khác..</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="kid-item-carousel">
                        <div class="owl-kid-item owl-carousel">
                                                    <c:forEach var="p" items="${accessoriesProducts}">
    <div class="item">
        <div class="thumb">
            <div class="hover-content">
                <ul>
                    <li><a href="<%= request.getContextPath() %>/product_detail/${p.id}"><i class="fa fa-eye"></i></a></li>
          
                    <li><a href="<%= request.getContextPath() %>/cart/${p.id}""><i class="fa fa-shopping-cart"></i></a></li>
                </ul>
            </div>
            <!-- Hiển thị ảnh đầu tiên của sản phẩm -->
            <img src="${p.images[0].image}" alt="${p.name}"style="height: 350px";object-fit: cover; >
        </div>
        <div class="down-content">
             <h4 style=" display: -webkit-box; -webkit-line-clamp: 2; /* Giới hạn số dòng */ -webkit-box-orient: vertical; overflow: hidden; text-overflow: ellipsis;height: 3em; /* Chiều cao tối đa (2 dòng) */ line-height: 1.5em; /* Chiều cao mỗi dòng */ text-align: left;">${p.name}</h4>
            <span class="price">${p.price}</span>
        </div> 
    </div>
</c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Kids Area Ends ***** -->
    <!-- ***** Explore Area Starts ***** -->
    <section class="section" id="explore">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="left-content">
                        <h2>Khám phá sản phẩm của chúng tôi</h2>
                        <span>Chúng tôi mang đến các bộ sưu tập thời trang dành cho nam giới mạnh mẽ và lịch lãm, nữ giới hiện đại và quyến rũ, cùng những bộ đồ dễ thương và năng động dành cho trẻ em. Tất cả sản phẩm đều được tuyển chọn kỹ lưỡng, đảm bảo chất lượng và cập nhật theo xu hướng mới nhất..</span>
                        <div class="quote">
                            <i class="fa fa-quote-left"></i><p>Chúng tôi luôn nỗ lực để mang đến cho bạn trải nghiệm mua sắm dễ dàng, tiện lợi và đầy cảm hứng.</p>
                        </div>
                        <p>Không chỉ có quần áo, chúng tôi còn cung cấp các phụ kiện thời trang như túi xách, mũ nón, kính mắt, đồng hồ… giúp bạn hoàn thiện vẻ ngoài và tạo nên phong cách riêng biệt.</p>
                        <p>Hãy dành chút thời gian để khám phá từng danh mục sản phẩm – biết đâu bạn sẽ tìm được món đồ ưng ý cho bản thân hoặc món quà ý nghĩa dành tặng người thân!Cảm ơn.</p>
                        <div class="main-border-button">
                            <a href="products.html">Khám phá thêm</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="right-content">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="leather">
                                    <h4>Túi da</h4>
                                    <span>Bộ sưu tập mới nhất</span>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="first-image">
                                    <img src="assets/images/explore-image-01.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="second-image">
                                    <img src="assets/images/explore-image-02.jpg" alt="">
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="types">
                                    <h4>Các loại khác nhau</h4>
                                    <span>Hơn 304 sản phẩm</span>
                                </div>
                            </div>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ***** Explore Area Ends ***** -->

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
   <%@include file="../../footer_client.jsp" %>
    
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