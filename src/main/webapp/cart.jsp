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
 <style>
        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #eee;
        }
        .empty-message {
            text-align: center;
            color: red;
            margin-top: 50px;
        }
    </style>
<h2 style="text-align:center;margin:150px 0 30px 0;">GIỎ HÀNG</h2>

<c:if test="${not empty cartItems}">
<div class="cart-container">
    <div class="cart-items">
        <table border="1" cellpadding="10">
            <thead>
                <tr>
                    <th></th>
                    <th>Hình ảnh</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartItems}" varStatus="loop">
                <tr data-product-id="${item.productId}">
                    <td>${loop.index + 1}</td>
                    <td><img src="${item.imageUrl}" class="cart-item-image"/></td>
                    <td>${item.productName}</td>
                    <td class="price">${item.price} đ</td>
                    <td>
                        <div class="quantity-control">
                            <button class="btn-quantity btn-decrease" data-product-id="${item.productId}">−</button>
                            <input type="text" id="quantity-${item.productId}" value="${item.quantity}" class="quantity-input" readonly />
                            <button class="btn-quantity btn-increase" data-product-id="${item.productId}">+</button>
                        </div>
                    </td>
                    <td class="price" id="total-${item.productId}">${item.totalPrice} đ</td>
                    <td>
                        <button class="btn-delete" data-product-id="${item.productId}" title="Xóa sản phẩm">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                                <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
                                <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
                            </svg>
                        </button>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <div class="cart-summary">
        <div class="cart-total-box">
            <h3>Tổng giỏ hàng</h3>
            <div class="cart-summary-row">
                <span>Tổng tiền:</span>
                <span class="cart-total-price">
                    <c:set var="totalAmount" value="0" />
                    <c:forEach var="item" items="${cartItems}">
                        <c:set var="totalAmount" value="${totalAmount + item.totalPrice}" />
                    </c:forEach>
                    <span class="price cart-grand-total">${totalAmount} đ</span>
                </span>
            </div>
            <div class="cart-summary-row">
                <span>Số lượng sản phẩm:</span>
                <span>
                    <c:set var="totalQuantity" value="0" />
                    <c:forEach var="item" items="${cartItems}">
                        <c:set var="totalQuantity" value="${totalQuantity + item.quantity}" />
                    </c:forEach>
                    ${totalQuantity}
                </span>
            </div>
            <div class="cart-action-buttons">
                <a href="<%= request.getContextPath() %>/" class="btn-continue-shopping">Tiếp tục mua sắm</a>
                <a href="<%= request.getContextPath() %>/checkout" class="btn-checkout">Thanh toán</a>
            </div>
        </div>
    </div>
</div>

<style>
    .cart-container {
        display: flex;
        gap: 20px;
        margin: 0 auto;
        max-width: 90%;
        padding: 0 15px;
    }
    
    .cart-items {
        flex: 3;
    }
    
    .cart-summary {
        flex: 1;
        min-width: 300px;
    }
    
    .cart-total-box {
        background-color: #f8f9fa;
        border-radius: 8px;
        padding: 20px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.05);
    }
    
    .cart-total-box h3 {
        margin-top: 0;
        padding-bottom: 10px;
        border-bottom: 1px solid #dee2e6;
        font-size: 18px;
        color: #333;
    }
    
    .cart-summary-row {
        display: flex;
        justify-content: space-between;
        padding: 10px 0;
        border-bottom: 1px solid #eee;
    }
    
    .cart-grand-total {
        font-weight: bold;
        font-size: 18px;
        color: #e73b3b;
    }
    
    .cart-action-buttons {
        margin-top: 20px;
        display: flex;
        flex-direction: column;
        gap: 10px;
    }
    
    .btn-continue-shopping, .btn-checkout {
        display: block;
        text-align: center;
        padding: 12px;
        border-radius: 5px;
        text-decoration: none;
        font-weight: 500;
        transition: all 0.3s;
    }
    
    .btn-continue-shopping {
        background-color: #f8f9fa;
        color: #333;
        border: 1px solid #ddd;
    }
    
    .btn-continue-shopping:hover {
        background-color: #e9ecef;
    }
    
    .btn-checkout {
        background-color: #e73b3b;
        color: white;
        border: none;
    }
    
    .btn-checkout:hover {
        background-color: #d32f2f;
    }
    
    table {
        width: 100%;
        margin: 0;
        border-collapse: collapse;
    }
    
    .cart-item-image {
        width: 100px;
        height: 100px;
        object-fit: cover;
        border-radius: 4px;
    }
    
    .quantity-control {
        display: flex;
        align-items: center;
        gap: 5px;
    }
    
    .btn-quantity {
        width: 30px;
        height: 30px;
        border: 1px solid #ddd;
        background: #f8f9fa;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
        display: flex;
        align-items: center;
        justify-content: center;
        transition: all 0.3s;
    }
    
    .btn-quantity:hover {
        background: #e9ecef;
    }
    
    .quantity-input {
        width: 40px;
        text-align: center;
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 5px;
    }
    
    .btn-delete {
        background: none;
        border: none;
        color: #dc3545;
        cursor: pointer;
        padding: 5px;
        border-radius: 50%;
        width: 32px;
        height: 32px;
        display: flex;
        align-items: center;
        justify-content: center;
        transition: all 0.3s;
    }
    
    .btn-delete:hover {
        background-color: rgba(220, 53, 69, 0.1);
        color: #c82333;
    }
    
    tr {
        transition: background-color 0.2s;
    }
    
    tr:hover {
        background-color: rgba(0,0,0,0.02);
    }
    
    /* Responsive design */
    @media (max-width: 992px) {
        .cart-container {
            flex-direction: column;
        }
        
        .cart-summary {
            min-width: 100%;
        }
    }
</style>
</c:if>

<c:if test="${empty cartItems}">
    <div class="empty-cart-container">
        <p class="empty-message">🛒 Giỏ hàng trống.</p>
        <a href="<%= request.getContextPath() %>/" class="btn-continue-shopping">Tiếp tục mua sắm</a>
    </div>

    <style>
        .empty-cart-container {
            text-align: center;
            padding: 50px 20px;
        }
        
        .empty-message {
            font-size: 18px;
            color: #666;
            margin-bottom: 20px;
        }
        
        .btn-continue-shopping {
            display: inline-block;
            background-color: #f8f9fa;
            color: #333;
            border: 1px solid #ddd;
            padding: 12px 24px;
            border-radius: 5px;
            text-decoration: none;
            font-weight: 500;
            transition: all 0.3s;
        }
        
        .btn-continue-shopping:hover {
            background-color: #e9ecef;
        }
    </style>
</c:if>


    <!-- ***** Footer Start ***** -->
    <%@ include file="footer_client.jsp" %>
<script>
// Format giá tiền kiểu Việt Nam cho tất cả thẻ .price khi mới load
document.querySelectorAll('.price').forEach(el => {
    const raw = parseFloat(el.textContent);
    if (!isNaN(raw)) {
        el.textContent = raw.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    }
});
</script>

<script>
// Xử lý nút tăng/giảm số lượng
document.querySelectorAll(".btn-increase, .btn-decrease").forEach(button => {
    button.addEventListener("click", function () {
        const productId = this.getAttribute("data-product-id");
        const action = this.classList.contains("btn-increase") ? "increase" : "decrease";
        
        const formData = new URLSearchParams();
        formData.append("productId", productId);
        formData.append("action", action);
      
        fetch("updateQuantity", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: formData
        })
        .then(response => {
            if (!response.ok) throw new Error("Network response was not ok");
            return response.json();
        })
        .then(data => {
            if (data.success) {
                document.getElementById("quantity-" + productId).value = data.newQuantity;

                // Cập nhật và format tổng giá sản phẩm
                document.getElementById("total-" + productId).innerText = parseFloat(data.totalPrice)
                    .toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });

                // Cập nhật tổng giỏ hàng và format
                if (data.cartTotal !== undefined) {
                    document.querySelectorAll(".cart-grand-total").forEach(el => {
                        el.innerText = parseFloat(data.cartTotal)
                            .toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    });
                }

                if (data.cartQuantity !== undefined) {
                    document.querySelectorAll(".cart-total-quantity").forEach(el => {
                        el.innerText = data.cartQuantity;
                    });
                }
            } else {
                alert(data.message || "Cập nhật thất bại");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Có lỗi xảy ra khi cập nhật giỏ hàng");
        });
    });
});
</script>

<script>
// Xử lý nút xóa sản phẩm
document.querySelectorAll('.btn-delete').forEach(button => {
    button.addEventListener('click', function () {
        const productId = this.getAttribute('data-product-id');
        if (!productId) return;

        if (confirm('Bạn có chắc muốn xóa sản phẩm này?')) {
            const productRow = this.closest('tr');
            const params = new URLSearchParams();
            params.append('productId', productId);

            fetch('cart/delete', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                body: params
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    if (productRow) productRow.remove();

                    // Cập nhật tổng giỏ hàng và format
                    if (data.cartTotal !== undefined) {
                        document.querySelectorAll('.cart-grand-total').forEach(el => {
                            el.textContent = parseFloat(data.cartTotal)
                                .toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                        });
                    }

                    if (data.cartQuantity !== undefined) {
                        document.querySelectorAll('.cart-total-quantity').forEach(el => {
                            el.textContent = data.cartQuantity;
                        });
                    }

                    // Nếu giỏ hàng rỗng thì hiển thị thông báo
                    const remainingProducts = document.querySelectorAll('table tbody tr');
                    if (remainingProducts.length === 0) {
                        const cartContainer = document.querySelector('.cart-container');
                        if (cartContainer) {
                            const emptyCartHtml = `
                                <div class="empty-cart-container">
                                    <p class="empty-message">🛒 Giỏ hàng trống.</p>
                                    <a href="${window.location.origin}/shop" class="btn-continue-shopping">Tiếp tục mua sắm</a>
                                </div>
                            `;
                            cartContainer.outerHTML = emptyCartHtml;
                        }
                    }
                } else {
                    alert('Lỗi: ' + data.message);
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Có lỗi xảy ra khi xóa sản phẩm');
            });
        }
    });
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
  </body>
</html>
