<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <%@include file="../include/sider.jsp"%>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">新建账户</h3>
                    <div class="box-tools pull-right">
                        <a href="/user/add" class="btn"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <form method="post">
                        <div class="form-group">
                            <label>账号</label>
                            <input type="text" name="username" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>密码(默认aaa)</label>
                            <input type="password" name="password" value="aaa" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>手机号(用于和微信企业号捆绑)</label>
                            <input type="text" name="mobile" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>角色</label>
                            <div>
                                <c:forEach items="${roleList}" var="role">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="roleIds" value="${role.id}">${role.viewname}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success">提交</button>
                        </div>

                    </form>
                </div>
            </div>
        </section>
        <!-- /.content -->
    </div>

</div>


</body>
</html>
