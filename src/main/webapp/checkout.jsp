<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="DOGO - Liên hệ">
    <meta name="author" content="">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    <title>DOGO - Liên hệ</title>

    <!-- Additional CSS Files -->
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/assets/css/font-awesome.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/templatemo-hexashop.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl-carousel.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/lightbox.css">
    
    <style>
        .checkout-container {
            margin-top: 150px;
            padding: 30px 0 100px 0;
            background-color: #f8f9fa;
        }
        
        .checkout-form {
            background-color: #fff;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
            padding: 40px;
            max-width: 800px;
            margin: 0 auto;
        }
        
        .checkout-title {
            color: #2a2a2a;
            font-size: 28px;
            font-weight: 700;
            margin-bottom: 30px;
            position: relative;
            padding-bottom: 15px;
        }
        
        .checkout-title:after {
            content: '';
            position: absolute;
            width: 60px;
            height: 3px;
            background-color: #2a2a2a;
            bottom: 0;
            left: 0;
        }
        
        .form-group {
            margin-bottom: 25px;
        }
        
        .form-control {
            height: 50px;
            border: 1px solid #e5e5e5;
            border-radius: 8px;
            padding: 10px 15px;
            font-size: 15px;
            transition: all 0.3s;
        }
        
        .form-control:focus {
            border-color: #2a2a2a;
            box-shadow: 0 0 0 0.2rem rgba(42, 42, 42, 0.1);
        }
        
        .form-label {
            font-weight: 500;
            margin-bottom: 10px;
            color: #2a2a2a;
            display: block;
        }
        
        .payment-methods {
            margin-top: 20px;
        }
        
        .payment-method {
            position: relative;
            display: block;
            margin-bottom: 15px;
            padding: 15px 20px;
            border: 1px solid #e5e5e5;
            border-radius: 8px;
            transition: all 0.3s;
            cursor: pointer;
        }
        
        .payment-method:hover {
            border-color: #2a2a2a;
        }
        
        .payment-method.active {
            border-color: #2a2a2a;
            background-color: rgba(42, 42, 42, 0.03);
        }
        
        .payment-method input {
            position: absolute;
            opacity: 0;
            cursor: pointer;
        }
        
        .payment-method-checkmark {
            position: absolute;
            top: 20px;
            right: 20px;
            height: 20px;
            width: 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            border-radius: 50%;
        }
        
        .payment-method input:checked ~ .payment-method-checkmark {
            background-color: #2a2a2a;
            border: 1px solid #2a2a2a;
        }
        
        .payment-method-checkmark:after {
            content: "";
            position: absolute;
            display: none;
        }
        
        .payment-method input:checked ~ .payment-method-checkmark:after {
            display: block;
        }
        
        .payment-method .payment-method-checkmark:after {
            left: 7px;
            top: 3px;
            width: 6px;
            height: 11px;
            border: solid white;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
        
        .payment-method-title {
            font-weight: 600;
            margin-bottom: 5px;
            font-size: 16px;
        }
        
        .payment-method-description {
            color: #777;
            font-size: 14px;
        }
        
        .payment-icon {
            display: inline-block;
            margin-right: 10px;
            font-size: 18px;
            vertical-align: middle;
        }
        
        .btn-checkout {
            background-color: #2a2a2a;
            color: #fff;
            border: none;
            padding: 15px 40px;
            font-size: 16px;
            font-weight: 600;
            border-radius: 50px;
            cursor: pointer;
            transition: all 0.3s;
            display: inline-block;
            text-align: center;
            margin-top: 20px;
            width: 100%;
        }
        
        .btn-checkout:hover {
            background-color: #1a1a1a;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
        
        .section-title {
            margin-bottom: 30px;
            text-align: center;
        }
        
        .section-title h2 {
            font-size: 32px;
            font-weight: 700;
            color: #2a2a2a;
        }
        
        .section-title p {
            color: #777;
            font-size: 16px;
            margin-top: 10px;
        }
        
        @media (max-width: 767px) {
            .checkout-form {
                padding: 20px;
            }
            
            .checkout-title {
                font-size: 24px;
            }
            
            .btn-checkout {
                padding: 12px 30px;
                font-size: 15px;
            }
        }
    </style>
</head>

<body>
    <!-- ***** Header Area Start ***** -->
    <%@ include file="header_client.jsp" %>
    <!-- ***** Header Area End ***** -->
    
    <!-- ***** Checkout Area Start ***** -->
    <section class="checkout-container">
        <div class="container">
            <div class="section-title">
                <h2>Thông tin đặt hàng</h2>
                <p>Vui lòng điền đầy đủ thông tin để chúng tôi có thể giao hàng đến bạn</p>
            </div>
            
            <div class="checkout-form">
                <form action="<%= request.getContextPath() %>/checkout" method="post">
                    <h2 class="checkout-title">Thông tin giao hàng</h2>
                    
                    <div class="form-group">
                        <label class="form-label" for="name">Họ và tên</label>
                        <input type="text" class="form-control" id="name" name="name" required placeholder="VD: Nguyễn Văn A">
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label" for="address">Địa chỉ</label>
                        <input type="text" class="form-control" id="address" name="address" required placeholder="VD: Số 123, Đường ABC, Quận/Huyện, Tỉnh/Thành phố">
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label" for="phone">Số điện thoại</label>
                        <input type="tel" class="form-control" id="phone" name="phone" required placeholder="VD: 0912345678">
                    </div>
                    
                    <h2 class="checkout-title">Phương thức thanh toán</h2>
                    
                    <div class="payment-methods">
                        <label class="payment-method active">
                            <input type="radio" name="payment_method" value="cod" checked>
                            <div class="payment-method-checkmark"></div>
                            <div class="payment-icon" style="    color: #63775b;">
                                <i class="fas fa-money-bill-wave"></i>
                            </div>
                            <div class="payment-method-title">Thanh toán khi nhận hàng</div>
                            <div class="payment-method-description">Thanh toán bằng tiền mặt khi nhận hàng</div>
                        </label>
                        
                        <label class="payment-method">
                            <input type="radio" name="payment_method" value="momo">
                            <div class="payment-method-checkmark"></div>
                            <div class="payment-icon"  style="    color: #a50064;">
                                <i class="fas fa-wallet"></i>
                            </div>
                            <div class="payment-method-title">Thanh toán qua MoMo</div>
                            <div class="payment-method-description">Thanh toán an toàn qua ví điện tử MoMo</div>
                        </label>
                    </div>
                    
                    <button type="submit" class="btn-checkout">ĐẶT HÀNG</button>
                </form>
            </div>
        </div>
    </section>
    <!-- ***** Checkout Area End ***** -->

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
    
    <script>
        $(document).ready(function() {
            // Highlight selected payment method
            $('.payment-method').click(function() {
                $('.payment-method').removeClass('active');
                $(this).addClass('active');
            });
            
            // Form validation enhancement
            $('form').submit(function(e) {
                let phone = $('#phone').val();
                if (!/^[0-9]{10}$/.test(phone)) {
                    alert('Vui lòng nhập số điện thoại hợp lệ (10 số)');
                    e.preventDefault();
                }
            });
        });
    </script>
</body>
</html>