<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thống kê</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    <%@include file="header_admin.jsp" %>

    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <%@include file="sidebar_admin.jsp" %>
        </div>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Thống kê</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active">Thống kê</li>
                    </ol>
                    <div class="row">
                        <div class="col-xl-4 col-md-6">
                            <div class="card bg-primary text-white mb-4">
                                <div class="card-body">
                                    <h5>Tổng đơn hàng</h5>
                                    <h2 class="text-center">${totalOrders}</h2>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="<%= request.getContextPath() %>/admin/orders">Xem chi tiết</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6">
                            <div class="card bg-warning text-white mb-4">
                                <div class="card-body">
                                    <h5>Tổng sản phẩm đã bán</h5>
                                    <h2 class="text-center">${totalSoldItems}</h2>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">Xem chi tiết</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6">
                            <div class="card bg-success text-white mb-4">
                                <div class="card-body">
                                    <h5>Tổng doanh thu</h5>
                                    <h2 class="text-center"><fmt:formatNumber value="${totalRevenue}" pattern="#,###" /> VNĐ</h2>
                                </div>
                                <div class="card-footer d-flex align-items-center justify-content-between">
                                    <a class="small text-white stretched-link" href="#">Xem chi tiết</a>
                                    <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-12">
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-chart-area me-1"></i>
                                    Doanh thu theo tháng
                                </div>
                                <div class="card-body">
                                    <canvas id="myAreaChart" width="100%" height="40"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Danh sách đơn hàng mới
                        </div>
                        <div class="card-body">
                            <table id="datatablesSimple">
                                <thead>
                                    <tr>
                                        <th>Mã đơn</th>
                                        <th>Khách hàng</th>
                                        <th>Ngày đặt</th>
                                        <th>Tổng tiền</th>
                                        <th>Sản phẩm</th>
                                        <th>Trạng thái</th>
                                        <th>Thanh toán</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>Mã đơn</th>
                                        <th>Khách hàng</th>
                                        <th>Ngày đặt</th>
                                        <th>Tổng tiền</th>
                                        <th>Sản phẩm</th>
                                        <th>Trạng thái</th>
                                        <th>Thanh toán</th>
                                        <th>Thao tác</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="order" items="${recentOrders}">
                                        <tr>recentOrders
                                            <td><c:out value="${order.id}" /></td>
                                            <td><c:out value="${order.name}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy" value="${order.order_date}" /></td>
                                            <td><fmt:formatNumber value="${order.total_price}" pattern="#,###" /> VNĐ</td>
                                            <td><c:out value="${order.totalItems}" /> sản phẩm</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status_id == 0}">
                                                        <span class="badge bg-primary">Đang giao</span>
                                                    </c:when>
                                                    <c:when test="${order.status_id == 1}">
                                                        <span class="badge bg-success">Đã giao</span>
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.payment_status == 1}">
                                                        <span class="badge bg-success">Đã thanh toán</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-secondary">Chưa thanh toán</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <a href="<%= request.getContextPath() %>/admin/order/detail/${order.id}" class="btn btn-primary btn-sm">
                                                    <i class="fas fa-eye"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </main>
            <%@include file="footer_admin.jsp" %>
        </div>
    </div>

    <script src="<%= ((HttpServletRequest) request).getContextPath() %>/assets/js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script>
    // Set new default font family and font color to mimic Bootstrap's default styling
    Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
    Chart.defaults.global.defaultFontColor = '#292b2c';

    // Area Chart Example
    var ctx = document.getElementById("myAreaChart");
    var myLineChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: [
          <c:forEach var="revenue" items="${monthlyRevenue}" varStatus="status">
            "${revenue.key}"<c:if test="${!status.last}">,</c:if>
          </c:forEach>
        ],
        datasets: [{
          label: "Doanh thu",
          lineTension: 0.3,
          backgroundColor: "rgba(2,117,216,0.2)",
          borderColor: "rgba(2,117,216,1)",
          pointRadius: 5,
          pointBackgroundColor: "rgba(2,117,216,1)",
          pointBorderColor: "rgba(255,255,255,0.8)",
          pointHoverRadius: 5,
          pointHoverBackgroundColor: "rgba(2,117,216,1)",
          pointHitRadius: 50,
          pointBorderWidth: 2,
          data: [
            <c:forEach var="revenue" items="${monthlyRevenue}" varStatus="status">
              ${revenue.value}<c:if test="${!status.last}">,</c:if>
            </c:forEach>
          ],
        }],
      },
      options: {
        scales: {
          xAxes: [{
            time: {
              unit: 'date'
            },
            gridLines: {
              display: false
            },
            ticks: {
              maxTicksLimit: 7
            }
          }],
          yAxes: [{
            ticks: {
              min: 0,
              maxTicksLimit: 5,
              callback: function(value, index, values) {
                return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' VNĐ';
              }
            },
            gridLines: {
              color: "rgba(0, 0, 0, .125)",
            }
          }],
        },
        legend: {
          display: false
        },
        tooltips: {
          callbacks: {
            label: function(tooltipItem, chart) {
              var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
              return datasetLabel + ': ' + tooltipItem.yLabel.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' VNĐ';
            }
          }
        }
      }
    });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="<%= request.getContextPath() %>/assets/js/datatables-simple-demo.js"></script>
</body>
</html>