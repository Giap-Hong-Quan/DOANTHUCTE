<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DOGO - Chi tiết đơn hàng">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet">

    <title>DOGO - Chi tiết đơn hàng #${order.id}</title>

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
        
        .order-detail-container {
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
        
        .order-summary {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.08);
            padding: 20px;
            margin-bottom: 30px;
        }
        
        .order-summary h3 {
            color: #333;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }
        
        .summary-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 12px;
            font-size: 15px;
        }
        
        .summary-label {
            color: #666;
            font-weight: 500;
        }
        
        .summary-value {
            color: #333;
            font-weight: 600;
        }
        
        .order-status {
            display: inline-block;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 13px;
            font-weight: 500;
            margin-top: 5px;
        }
        
        .status-pending {
            background-color: #fef9e6;
            color: #d4a500;
        }
        
        .status-processing {
            background-color: #e8f4fd;
            color: #0a84ff;
        }
        
        .status-shipped {
            background-color: #e6f7ff;
            color: #00a0e9;
        }
        
        .status-delivered {
            background-color: #e6f7ec;
            color: #00c853;
        }
        
        .status-canceled {
            background-color: #fde8e8;
            color: #e53935;
        }
        
        .payment-status {
            display: inline-block;
            padding: 5px 12px;
            border-radius: 20px;
            font-size: 13px;
            font-weight: 500;
        }
        
        .payment-status-paid {
            background-color: #e6f7ec;
            color: #00c853;
        }
        
        .payment-status-unpaid {
            background-color: #fde8e8;
            color: #e53935;
        }
        
        .order-items {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.08);
            padding: 20px;
            margin-bottom: 30px;
        }
        
        .order-items h3 {
            color: #333;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }
        
        .item-row {
            display: flex;
            align-items: center;
            padding: 15px 0;
            border-bottom: 1px solid #f5f5f5;
        }
        
        .item-row:last-child {
            border-bottom: none;
        }
        
        .item-image {
            width: 80px;
            height: 80px;
            object-fit: cover;
            border-radius: 8px;
            border: 1px solid #eee;
            margin-right: 15px;
        }
        
        .item-details {
            flex: 1;
        }
        
        .item-name {
            font-weight: 600;
            color: #333;
            margin-bottom: 5px;
            font-size: 16px;
        }
        
        .item-price {
            color: #fb5c42;
            font-weight: 600;
            font-size: 14px;
        }
        
        .item-quantity {
            color: #666;
            font-size: 14px;
        }
        
        .item-subtotal {
            text-align: right;
            color: #333;
            font-weight: 600;
            font-size: 16px;
        }
        
        .back-button {
            background: linear-gradient(to right, #ff7d7d, #fb5c42);
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: 500;
            transition: all 0.3s;
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
        }
        
        .back-button:hover {
            background: linear-gradient(to right, #fb5c42, #ff7d7d);
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(251, 92, 66, 0.3);
            color: white;
            text-decoration: none;
        }
        
        .order-total {
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #eee;
            text-align: right;
            font-size: 18px;
            font-weight: 600;
            color: #fb5c42;
        }
        
        .customer-info {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0, 0, 0, 0.08);
            padding: 20px;
            margin-bottom: 30px;
        }
        
        .customer-info h3 {
            color: #333;
            font-size: 18px;
            font-weight: 600;
            margin-bottom: 20px;
            padding-bottom: 10px;
            border-bottom: 1px solid #eee;
        }
        
        .customer-info-item {
            margin-bottom: 12px;
            font-size: 15px;
        }
        
        .customer-info-label {
            color: #666;
            font-weight: 500;
        }
        
        .customer-info-value {
            color: #333;
            font-weight: 500;
        }
        
        @media (max-width: 768px) {
            .item-row {
                flex-direction: column;
                align-items: flex-start;
            }
            
            .item-image {
                margin-bottom: 10px;
            }
            
            .item-subtotal {
                text-align: left;
                margin-top: 10px;
            }
        }
    </style>
</head>
<body>
    <!-- Header Area -->
    <%@ include file="header_client.jsp" %>

    <!-- Page Content -->
    <div class="order-detail-container">
        <h2 class="page-title">Chi Tiết Đơn Hàng #${order.id}</h2>
        
        <div class="row">
            <div class="col-md-8">
                <!-- Order Items -->
                <div class="order-items">
                    <h3><i class="fas fa-shopping-bag mr-2"></i> Sản phẩm đã mua</h3>
                    
                    <c:forEach var="item" items="${orderItems}">
                        <div class="item-row">
                            <img src="${item.productImage}" alt="${item.productName}" class="item-image">
                            
                            <div class="item-details">
                                <div class="item-name">${item.productName}</div>
                                <div class="item-price">Đơn giá: <span class="price">${item.price}</span></div>
                                <div class="item-quantity">Số lượng: ${item.quantity}</div>
                            </div>
                            
                            <div class="item-subtotal">
                                <div>Thành tiền:</div>
                                <div class="price">${item.subtotal}</div>
                            </div>
                        </div>
                    </c:forEach>
                    
                    <div class="order-total">
                        Tổng thanh toán: <span class="price">${order.total_price}</span>
                    </div>
                </div>
                
                <!-- Back Button -->
                <a href="<%= request.getContextPath() %>/purchase-history" class="back-button">
                    <i class="fas fa-arrow-left mr-2"></i> Quay lại lịch sử mua hàng
                </a>
            </div>
            
            <div class="col-md-4">
                <!-- Order Summary -->
                <div class="order-summary">
                    <h3><i class="fas fa-receipt mr-2"></i> Thông tin đơn hàng</h3>
                    
                    <div class="summary-item">
                        <div class="summary-label">Mã đơn hàng:</div>
                        <div class="summary-value">#${order.id}</div>
                    </div>
                    
                    <div class="summary-item">
                        <div class="summary-label">Ngày đặt hàng:</div>
                        <div class="summary-value">
                            <fmt:formatDate value="${order.order_date}" pattern="dd/MM/yyyy HH:mm" />
                        </div>
                    </div>
                    
                    <div class="summary-item">
                        <div class="summary-label">Trạng thái đơn hàng:</div>
                        <div class="summary-value">
                            <c:choose>
                                <c:when test="${order.status_id == 0}">
                                    <span class="order-status status-pending">Đang giao </span>
                                </c:when>
                                <c:when test="${order.status_id == 1}">
                                    <span class="order-status status-processing">Đã giao</span>
                                </c:when>
                               
                               
                            </c:choose>
                        </div>
                    </div>
                    
                    <div class="summary-item">
                        <div class="summary-label">Phương thức thanh toán:</div>
                        <div class="summary-value">
                            <c:choose>
                                <c:when test="${order.payment_method == 0}">
                                    <span>Thanh toán khi nhận hàng (COD)</span>
                                </c:when>
                                <c:when test="${order.payment_method == 1}">
                                    <span>Thanh toán online</span>
                                </c:when>
                                <c:otherwise>
                                    <span>Không xác định</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    
                    <div class="summary-item">
                        <div class="summary-label">Trạng thái thanh toán:</div>
                        <div class="summary-value">
                            <c:choose>
                                <c:when test="${order.payment_status == 1}">
                                    <span class="payment-status payment-status-paid">Đã thanh toán</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="payment-status payment-status-unpaid">Chưa thanh toán</span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                
                <!-- Customer Information -->
                <div class="customer-info">
                    <h3><i class="fas fa-user mr-2"></i> Thông tin người nhận</h3>
                    
                    <div class="customer-info-item">
                        <div class="customer-info-label">Họ tên:</div>
                        <div class="customer-info-value">${order.name}</div>
                    </div>
                    
                    <div class="customer-info-item">
                        <div class="customer-info-label">Số điện thoại:</div>
                        <div class="customer-info-value">${order.phone}</div>
                    </div>
                    
                    <div class="customer-info-item">
                        <div class="customer-info-label">Địa chỉ giao hàng:</div>
                        <div class="customer-info-value">${order.address}</div>
                    </div>
                </div>
            </div>
        </div>
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