<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <style>
        body{
            background-image: url(/static/img/bg.jpg);
        }
    </style>
</head>
<body>
<div class="container hold-transition login-pag">
    <div class="col-md-5" style="background-color: #98a6ff">
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
        <form method="post">
            <legend>系统登录</legend>
            <div class="form-group">
                <label>账号</label>
                <input type="text" name="username" class="form-control">
            </div>
            <div class="form-group">
                <label>密码</label>
                <input type="password" name="password" class="form-control">
            </div>
            <div class="form-group">
                <button class="btn">登录</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>