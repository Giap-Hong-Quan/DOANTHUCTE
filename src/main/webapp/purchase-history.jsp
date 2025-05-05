<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DOGO - Thời trang chất lượng cao">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <title>DOGO - Lịch sử mua hàng</title>

    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/templatemo-hexashop.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl-carousel.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lightbox.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
        }
        
        .purchase-history-container {
            padding: 40px 20px;
            max-width: 1200px;
            margin: 0 auto;
        }
        
        .page-title {
            font-weight: 600;
            color: #333;
            margin-bottom: 30px;
            position: relative;
            padding-bottom: 15px;
            text-align: center;
        }
        
        .page-title:after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 80px;
            height: 3px;
            background: linear-gradient(to right, #ff7d7d, #fb5c42);
        }
        
        .order-item {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.08);
            margin-bottom: 25px;
            transition: transform 0.3s ease;
            overflow: hidden;
        }
        
        .order-item:hover {
            transform: translateY(-5px);
        }
        
        .order-header {
            padding: 10px 20px;
            background-color: #f5f5f5;
            border-bottom: 1px solid #eee;
            font-weight: 500;
            color: #555;
            font-size: 14px;
        }
        
        .order-body {
            padding: 20px;
        }
        
        .product-image {
            width: 100px;
            height: 100px;
            object-fit: cover;
            border-radius: 8px;
            border: 1px solid #eee;
        }
        
        .product-info h5 {
            font-weight: 600;
            font-size: 16px;
            margin-bottom: 10px;
            color: #333;
        }
        
        .product-detail {
            font-size: 14px;
            color: #666;
            margin-bottom: 6px;
        }
        
        .product-price {
            font-weight: 600;
            color: #fb5c42;
        }
        
        .btn-view-detail {
            background: linear-gradient(to right, #ff7d7d, #fb5c42);
            border: none;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            font-size: 13px;
            font-weight: 500;
            transition: all 0.3s;
        }
        
        .btn-view-detail:hover {
            background: linear-gradient(to right, #fb5c42, #ff7d7d);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(251, 92, 66, 0.3);
        }
        
        .empty-orders {
            text-align: center;
            padding: 50px 0;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.08);
        }
        
        .empty-orders i {
            font-size: 60px;
            color: #ddd;
            margin-bottom: 20px;
            display: block;
        }
        
        .empty-orders p {
            font-size: 18px;
            color: #888;
        }
        
        .shop-now-btn {
            background: linear-gradient(to right, #ff7d7d, #fb5c42);
            color: white;
            border: none;
            padding: 10px 25px;
            border-radius: 30px;
            font-weight: 500;
            margin-top: 20px;
            transition: all 0.3s;
        }
        
        .shop-now-btn:hover {
            background: linear-gradient(to right, #fb5c42, #ff7d7d);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(251, 92, 66, 0.3);
            color: white;
            text-decoration: none;
        }
        
        @media (max-width: 768px) {
            .product-image {
                width: 80px;
                height: 80px;
                margin-bottom: 15px;
            }
            
            .order-actions {
                margin-top: 15px;
                text-align: left;
            }
        }
    </style>
</head>
<body>
    <!-- Header Area -->
    <%@ include file="header_client.jsp" %>

    <!-- Page Content -->
    <div class="purchase-history-container">
        <h2 class="page-title">Lịch Sử Mua Hàng</h2>
        
        <c:if test="${empty orderItems}">
            <div class="empty-orders">
                <i class="fas fa-shopping-bag"></i>
                <p>Bạn chưa có đơn hàng nào</p>
                <a href="<%= request.getContextPath() %>/shop" class="btn shop-now-btn">
                    <i class="fas fa-store mr-2"></i> Mua sắm ngay
                </a>
            </div>
        </c:if>
        
        <c:forEach var="item" items="${orderItems}">
            <div class="order-item">
                <div class="order-header">
                    <div class="row">
                        <div class="col-md-6">
                            <i class="fas fa-receipt mr-2"></i> Mã đơn hàng: #${item.id}
                        </div>
                    </div>
                </div>
                <div class="order-body">
                    <div class="row align-items-center">
                        <div class="col-md-2 col-sm-4 mb-3 mb-md-0">
                            <img src="${item.productImage}" alt="${item.productName}" class="product-image">
                        </div>
                        <div class="col-md-7 col-sm-8 product-info">
                            <h5>${item.productName}</h5>
                            <div class="product-detail">
                                <i class="fas fa-cubes mr-2"></i> <strong>Số lượng:</strong> ${item.quantity}
                            </div>
                            <div class="product-detail product-price">
                                <i class="fas fa-tag mr-2"></i> <strong>Giá:</strong><span class="price">${item.price}</span>
                            </div>
                        </div>
                        <div class="col-md-3 text-md-right order-actions">
                            <a href="purchase-history?action=detail&id=${item.id}" class="btn btn-view-detail">
                                <i class="fas fa-eye mr-2"></i> Xem chi tiết
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Footer Area -->
    <%@include file="footer_client.jsp" %>
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
    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/popper.js"></script>
    <script src="<%= request.getContextPath() %>/assets/js/bootstrap.min.js"></script>
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
    <script src="<%= request.getContextPath() %>/assets/js/custom.js"></script>
</body>
</html>