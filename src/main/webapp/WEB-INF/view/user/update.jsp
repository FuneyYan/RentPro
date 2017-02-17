<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改用户</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">


    <div class="wrapper">

        <%@include file="../include/header.jsp"%>
        <%@include file="../include/sider.jsp"%>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Main content -->
            <section class="content">
                <div class="box box-solid box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">账户管理</h3>
                        <div class="box-tools pull-right">
                            <a href="/user/new" class="btn"><i class="fa fa-plus"></i></a>
                        </div>
                    </div>
                    <div class="box-body">
                        <form method="post">
                            <div class="form-group">
                                <input type="hidden" value="${user.id}" name="id">
                                <label>账号</label>
                                <input type="text" name="username" class="form-control" value="${user.username}">
                            </div>
                            <div class="form-group">
                                <label>密码</label>
                                <input type="password" name="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>角色</label>
                                <div>
                                    <c:forEach items="${roleList}" var="role">
                                        <c:set var="flag" value="false" scope="page"/>
                                        <c:forEach items="${user.roleList}" var="userRole">
                                            <c:if test="${userRole.id==role.id}">
                                                <label class="checkbox-inline">
                                                    <input type="checkbox" checked name="roleIds" value="${role.id}">${role.viewname}
                                                </label>
                                                <c:set var="flag" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not flag}">
                                            <label class="checkbox-inline">
                                                <input type="checkbox" name="roleIds" value="${role.id}">${role.viewname}
                                            </label>
                                        </c:if>
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
        <!-- /.content-wrapper -->

    </div>

    <%@include file="../include/js.jsp"%>


</body>
</html>
