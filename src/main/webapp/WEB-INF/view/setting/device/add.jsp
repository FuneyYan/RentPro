<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%@include file="../../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp"%>
    <%@include file="../../include/sider.jsp"%>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="sys_device"/>
    </jsp:include>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="box box-solid box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">添加设备</h3>
                </div>
                <div class="box-body">
                    <form method="post">
                        <div class="form-group">
                            <label>设备名称</label>
                            <input type="text" name="name" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>单位</label>
                            <input type="text" name="unit" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>总数量</label>
                            <input type="text" name="totalnum" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>租赁单价</label>
                            <input type="text" name="price" class="form-control">
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
