 <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ</title>
</head>
<body>
    <h1>Xin chào, ${user.name}!</h1>
    <p>ID: ${user.id}</p>
    <p>Email: ${user.email}</p>
    <img src="${user.avatar}" alt="Avatar" width="100">
</body>
</html>
 