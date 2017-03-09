<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>劳务派遣列表</title>
    <%@include file="../../include/css.jsp" %>
    <link rel="stylesheet" href="/static/plugins/datatables1.10.13/media/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables1.10.13/extensions/FixedHeader/css/fixedHeader.bootstrap.min.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../../include/header.jsp" %>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="labour"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box box-primary box-solid">
                <div class="box-header with-border">
                    <h3 class="box-title">租赁合同列表</h3>

                    <div class="box-tools pull-right">
                        <a href="/labour/dispatch/add" class="btn btn-primary"><i class="fa fa-plus"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>流水号</th>
                            <th>需求公司</th>
                            <th>公司地址</th>
                            <th>公司电话</th>
                            <th>法人代表</th>
                            <th>电话号码</th>
                            <th>身份证号</th>
                            <th>创建时间</th>
                            <th>总佣金</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>

<%@include file="../../include/js.jsp" %>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/datatables1.10.13/media/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables1.10.13/media/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/datatables1.10.13/extensions/FixedHeader/js/dataTables.fixedHeader.min.js"></script>
<script>
        $(function () {
            var table = $(".table").DataTable({
                "lengthChange": false,
                "pageLength": 5,
                "serverSide": true,
                "ajax": {
                    "url": "/labour/dispatch/load",
                    "type": "get"
                },
                "searching": false,//不使用自带的搜索
                "order": [[0, 'desc']],//默认排序方式,
                "ordering": false,
                "columns": [
                    {"data": "id", "name": "id"},
                    {"data":function(row){
                        if(row.serialnumber) {
                            return "<a href='/labour/dispatch/rent/" + row.serialnumber + "'>" + row.serialnumber + "</a>";
                        } else {
                            return "";
                        }
                    }},
                    {"data": "companyname"},
                    {"data": "address"},
                    {"data": "companytel"},
                    {"data": "linkman"},
                    {"data": "tel"},
                    {"data": "cardnum"},
                    {"data": "createtime"},
                    {"data": "totalprice"},
                    {"data":"state"},
                    {"data":function(row){
                        if(row.state) {
                            return "";
                        } else {
                            return "<a href='javascript:;' rel='" + row.id + "' class='btn btn-xs btn-default checkBtn'> <i class='fa fa-check'></i> 完成</a>";
                        }
                    }},

                ],
                "columnDefs": [
                    {targets: [0], visible: false}
                ],
                "language": { //定义中文
                    "search": "搜索:",
                    "zeroRecords": "没有匹配的数据",
                    "lengthMenu": "显示 _MENU_ 条数据",
                    "info": "显示从 _START_ 到 _END_ 条数据 共 _TOTAL_ 条数据",
                    "infoFiltered": "(从 _MAX_ 条数据中过滤得来)",
                    "loadingRecords": "加载中...",
                    "processing": "处理中...",
                    "paginate": {
                        "first": "首页",
                        "last": "末页",
                        "next": "下一页",
                        "previous": "上一页"
                    }
                }
            });
            new $.fn.dataTable.FixedHeader(table);
            //将合同变为完成状态
            $(document).delegate(".checkBtn","click",function(){
                var id = $(this).attr("rel");
                layer.confirm("确定要将合同修改为已完成吗",function(index){
                    $.post("/labour/dispatch/state/change",{"id":id}).done(function(resp){
                        if(resp.status == 'success') {
                            table.ajax.reload();
                        }
                    }).error(function () {
                        layer.msg("服务器忙，请稍后再试");
                    });
                    layer.close(index);
                });
            });
        });

</script>
</body>
</html>
