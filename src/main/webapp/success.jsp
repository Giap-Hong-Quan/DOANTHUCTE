<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt hàng thành công</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            flex-direction: column;
        }
        
        .success-container {
            text-align: center;
            width: 100%;
            max-width: 600px;
            padding: 20px;
        }
        
        .check-icon {
            background-color: #5cb85c;
            color: white;
            border-radius: 50%;
            width: 80px;
            height: 80px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0 auto 20px;
            font-size: 40px;
        }
        
        h1 {
            font-size: 32px;
            margin-bottom: 10px;
            color: #333;
        }
        
        p {
            font-size: 18px;
            color: #555;
            margin-bottom: 10px;
        }
        
        .back-button {
            display: inline-block;
            padding: 10px 30px;
            background-color: white;
            color: #333;
            text-decoration: none;
            border: 1px solid #333;
            border-radius: 30px;
            font-weight: bold;
            transition: all 0.3s;
            margin-top: 20px;
        }
        
        .back-button:hover {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <div class="check-icon">✓</div>
        
        <h1>Cảm ơn bạn đã mua hàng</h1>
        
        <p>Chúng tôi đã nhận được đơn hàng của bạn và sẽ giao hàng trong 5-7 ngày làm việc.</p>
        <p>Mã đơn hàng của bạn là #<c:out value="${orderId}" /></p>
        
        <a href="<%= request.getContextPath() %>" class="back-button">Trở về trang chủ</a>
    </div>
</body>
</html>