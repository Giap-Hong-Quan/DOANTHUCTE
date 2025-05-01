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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

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
    <!-- ***** Product Area Starts ***** -->
    <section class="section" id="product">
        <div class="container">
            <div class="row" style="margin-top:150px">
              <% if (request.getAttribute("product") != null) { %>
                <div class="col-lg-8">
                
                <div class="left-images">
                  
                    
<div class="row">
  <div class="col-md-12 text-center">
    <!-- Ảnh chính -->
    <img id="mainImage" src="${product.images[0].image}" class="img-fluid" 
         style="width: 400px; height: 400px; object-fit: cover; margin-bottom:20px;">
  </div>

  <div class="col-md-12 d-flex justify-content-center align-items-center">
   <!-- Nút Prev -->
<button id="prevThumbs" onclick="prevThumbs()" >
  <i class="fas fa-chevron-left"></i>
</button>

    <!-- Danh sách thumbnail -->
    <div id="thumbnails" class="d-flex overflow-hidden" style="width: 360px;">
      <!-- Ảnh thumbnails sinh ra từ JavaScript -->
    </div>

   
<!-- Nút Next -->
<button id="nextThumbs" onclick="nextThumbs()">
  <i class="fas fa-chevron-right"></i>
</button>
  </div>
</div>


                </div>
            </div>
            <div class="col-lg-4">
                <div class="right-content">
                    <h4>${product.name}</h4>
                    <span class="price">${product.price}</span>
                
                    <span>${product.description}</span>
                    
                    <div class="quantity-content">
                        <div class="left-content">
                            <h6>Số lượng sản phẩm :${product.quantity}</h6>
                        </div>
                      
                    </div>
                    <div class="total">
                        <div class="main-border-button"><a href="#">Add To Cart</a></div>
                    </div>
                </div>
            </div>
              	
					<% } else { %>
					    <p class="text-danger">User not found!</p>
					<% } %>
            
            </div>
        </div>
    </section>
    <!-- ***** Product Area Ends ***** -->
    <!-- ***** Footer Start ***** -->
    <%@ include file="footer_client.jsp" %>
<script>
//Mảng chứa đường dẫn ảnh
const images = [
  <c:forEach items="${product.images}" var="image" varStatus="status">
    "${image.image}"<c:if test="${!status.last}">,</c:if>
  </c:forEach>
];

let currentIndex = 0; // Ảnh chính hiện tại
let startThumb = 0;   // Vị trí thumbnail bắt đầu

// Load trang xong
document.addEventListener("DOMContentLoaded", function() {
  renderThumbnails();
  updateMainImage();
  updateButtons(); // nếu bạn vẫn cần hiện/ẩn nút, không cần cũng được
});

// Đổi ảnh chính
function updateMainImage() {
  document.getElementById('mainImage').src = images[currentIndex];
}

// Render 4 thumbnails liên tục
function renderThumbnails() {
  const thumbnails = document.getElementById('thumbnails');
  thumbnails.innerHTML = '';

  for (let i = 0; i < 4; i++) {
    const imgIndex = (startThumb + i) % images.length; // dùng % để vòng lại
    const img = document.createElement('img');
    img.src = images[imgIndex];
    img.style.width = '80px';
    img.style.height = '80px';
    img.style.objectFit = 'cover';
    img.classList.add('img-thumbnail', 'mx-1');
    img.style.cursor = 'pointer';
    img.onclick = () => {
      currentIndex = imgIndex;
      updateMainImage();
    };
    thumbnails.appendChild(img);
  }
}

// Next thumbnail (xoay vòng)
function nextThumbs() {
  startThumb = (startThumb + 1) % images.length;
  renderThumbnails();
}

// Prev thumbnail (xoay vòng)
function prevThumbs() {
  startThumb = (startThumb - 1 + images.length) % images.length;
  renderThumbnails();
}

// Nút Prev/Next lúc nào cũng hiện
function updateButtons() {
  document.getElementById('prevThumbs').style.display = 'inline-block';
  document.getElementById('nextThumbs').style.display = 'inline-block';
}


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
  // Format giá tiền kiểu Việt Nam cho một phần tử duy nhất
  document.addEventListener("DOMContentLoaded", function () {
    const priceEl = document.querySelector('.price');
    if (priceEl) {
      const raw = parseFloat(priceEl.textContent); // Lấy giá trị gốc (số)
      if (!isNaN(raw)) {
        const formatted = raw.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        priceEl.textContent = formatted; // Cập nhật lại nội dung đã format
      }
    }
  });
</script>
  </body>
</html>
