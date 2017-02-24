<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>新增劳务外包流水</title>
    <%@include file="../../include/css.jsp" %>
    <link rel="stylesheet" href="/static/plugins/select2/select2.min.css">
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="/static/plugins/uploader/webuploader.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="app">

    <%@include file="../../include/header.jsp" %>
    <jsp:include page="../../include/sider.jsp">
        <jsp:param name="menu" value="business_device_rent"/>
    </jsp:include>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增劳务外包流水</h3>

                    <div class="box-tools pull-right">
                        <a href="/device/rent" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></a>
                    </div>
                </div>
                <div class="box-body">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>租赁公司</label>
                                <input type="text" class="form-control" id="companyname">
                            </div>
                            <div class="form-group">
                                <label>法人代表</label>
                                <input type="text" class="form-control" id="linkman">
                            </div>
                            <div class="form-group">
                                <label>佣金金额</label>
                                <input type="text" class="form-control" id="brokerage" readonly>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>地址</label>
                                <input type="text" class="form-control" id="address">
                            </div>
                            <div class="form-group">
                                <label>电话</label>
                                <input type="text" class="form-control" id="tel">
                            </div>
                            <div class="form-group">
                                <label>预付款</label>
                                <input type="text" class="form-control" id="precost" readonly>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>公司电话</label>
                                <input type="text" class="form-control" id="companytel">
                            </div>
                            <div class="form-group">
                                <label>身份证号</label>
                                <input type="text" class="form-control" id="cardnum">
                            </div>
                            <div class="form-group">
                                <label>尾款</label>
                                <input type="text" class="form-control" id="lastcost" readonly>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">设备列表</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal"><i
                                class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>工种</th>
                            <th>单位佣金</th>
                            <th>工种数量</th>
                            <th>小计</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-if="workArray.length==0">
                            <td colspan="6">暂无数据</td>
                        </tr>
                        <tr v-for="work in workArray">
                            <td>{{work.name}}</td>
                            <td>{{work.price}}</td>
                            <td>{{work.num}}</td>
                            <td>{{work.total}}</td>
                            <td><a href="javascript:;" @click="remove(work)"><i class="fa fa-trash text-danger"></i></a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title">合同扫描件</h3>
                </div>
                <div class="box-body">
                    <div id="picker">选择文件</div>
                    注意：上传合同扫描件要求清晰可见 合同必须公司法人签字盖章
                    <ul id="fileList">
                    </ul>
                    <button class="btn btn-primary pull-right" @click="saveRent">保存合同</button>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加工种</h4>
                </div>
                <div class="modal-body">
                    <form action="">
                        <div class="form-group">
                            <input type="hidden" id="worktypename">
                            <label>工种</label>
                            <select id="worktype" style="width: 300px;" class="form-control">
                                <option value="">选择工种</option>
                                <c:forEach items="${workTypeList}" var="work">
                                    <option value="${work.id}">${work.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>单位佣金</label>
                            <input type="text"  class="form-control" id="workprice" readonly>
                        </div>
                        <div class="form-group">
                            <label>工种数量</label>
                            <input type="text" class="form-control" id="worknum" value="1">
                        </div>
                        <div class="form-group">
                            <label>小计</label>
                            <input type="text" class="form-control" id="totalprice" readonly>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" v-on:click="addWork">加入列表</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>

<%@include file="../../include/js.jsp" %>

<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/select2/select2.full.min.js"></script>
<script src="/static/plugins/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="/static/plugins/vue.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<script src="/static/plugins/uploader/webuploader.min.js"></script>
<script>
    var fileArray = [];
    $(function () {
        $("#worktype").select2();

        $("#worktype").change(function () {
            var id=$(this).val();
            $.get("/labour/dispatch/render.json",{"id":id}).done(function (resp) {

                if(resp.status=='success'){
                    $("#worktypename").val(resp.data.name);
                    $("#workprice").val(resp.data.price);
                    var worknum=$("#worknum").val();
                    $("#totalprice").val(resp.data.price*worknum);
                }

            }).error(function () {
                layer.msg("服务器异常");
            });
        });

        var uploader = WebUploader.create({
            swf: "js/uploader/Uploader.swf",
            server: "/file/upload",
            pick: '#picker',
            auto: true,
            fileVal: 'file'
        });

        uploader.on("uploadSuccess", function (file, resp) {
            layer.msg("上传成功");
            var html = "<h5>" + resp.data.sourceName + "</h5>";
            $("#fileList").append(html);

            var json = {
                newFileName: resp.data.newFileName,
                sourceName: resp.data.sourceName
            };
            fileArray.push(json);
        });

        uploader.on("uploadError", function () {
            layer.msg("服务器忙,请稍后");
        });
    });


    var totalprice=0;
    var percostprice=0;
    var lastcostprice=0;

    var app = new Vue({
        el:"#app",
        data: {
            workArray:[]
        },
        methods: {
            addWork:function(){
                var id = $("#worktype").val();
                //判断数组中是否存在当前的设备，如果有则数量累加，更新总价
                var flag = false;
                for(var i = 0;i < this.$data.workArray.length;i++) {
                    var item = this.$data.workArray[i];
                    if(item.id == id) {
                        this.$data.workArray[i].num = parseInt(this.$data.workArray[i].num) + parseInt($("#worknum").val());
                        this.$data.workArray[i].total = parseInt(this.$data.workArray[i].num) * parseInt($("#workprice").val());
                        flag = true;
                        totalprice+=parseInt(parseInt($("#workprice").val()));
                        percostprice=totalprice*0.3;
                        lastcostprice=totalprice*0.7;
                        $("#brokerage").val(totalprice);
                        $("#precost").val(percostprice);
                        $("#lastcost").val(lastcostprice);
                        break;
                    }
                }
                //如果没有则添加新JSON对象
                if(!flag) {
                    var json = {};
                    json.id = id;
                    json.name = $("#worktypename").val();
                    json.price = parseFloat($("#workprice").val());
                    json.num = parseInt($("#worknum").val());
                    json.totalprice = parseFloat(json.price) * parseFloat(json.num);

                    this.$data.workArray.push(json);

                    totalprice+=json.price*json.num;
                    percostprice=totalprice*0.3;
                    lastcostprice=totalprice*0.7;
                    $("#brokerage").val(totalprice);
                    $("#precost").val(percostprice);
                    $("#lastcost").val(lastcostprice);
                    this.$data.workArray[i].total = parseInt(this.$data.workArray[i].num) * parseInt($("#workprice").val());
                }



            },
            remove:(function (work) {
                layer.confirm("确定要删除吗?",function (index) {
                    totalprice-=work.num*work.price;
                    percostprice=totalprice*0.3;
                    lastcostprice=totalprice*0.7;
                    $("#brokerage").val(totalprice);
                    $("#precost").val(percostprice);
                    $("#lastcost").val(lastcostprice);
                    app.$data.workArray.splice(app.$data.workArray.indexOf(work),1);
                    layer.close(index);
                });
            }),
            saveRent:function(){
                var json = {
                    workArray : app.$data.workArray,
                    fileArray : fileArray,
                    companyName : $("#companyname").val(),
                    tel : $("#tel").val(),
                    companyTel:$("#companytel").val(),
                    linkMan : $("#linkman").val(),
                    cardNum : $("#cardnum").val(),
                    address : $("#address").val(),
                    brokerage:$("#brokerage").val(),
                    preCost:$("#precost").val(),
                    lastCost:$("#lastcost").val(),
                };

                //JSON.parse()
                $.ajax({
                    url:"/labour/dispatch/new",
                    type:"post",
                    data: JSON.stringify(json),
                    contentType: "application/json;charset=UTF-8",
                    success:function(data){
                        if(data.status == 'success') {
                            layer.confirm("保存成功",{btn:['继续添加','打印合同']},function(){
                                window.history.go(0);
                            },function(){
                                window.location.href = "/labour/dispatch/rent/"+data.data;
                            });
                        } else {
                            layer.msg(data.message);
                        }
                    },
                    error:function(){
                        layer.msg("服务器忙，请稍后");
                    }
                });

            }
        }

    });


</script>
</body>
</html>
