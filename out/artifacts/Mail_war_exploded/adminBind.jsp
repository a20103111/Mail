<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>iStudy后台管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <!-- load css -->
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/adminstyle.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/personal.css" media="all">
    <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>

    <script>

        $(function(){

            $.ajax({
                type:"post",
                async:"true",
                url:"MailServlet?action=page",
                success:function(data){
                    $("#bindpage").html(data);
                },
                beforeSend:function(){

                }
            });


        })




    </script>
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
    <div class="layui-main">
        <blockquote class="layui-elem-quote" style="margin-top: 10px;">
            <div id="search" style="width: 50%;float: left;">
                <input type="text" name="title" placeholder="请输入用户信息"
                       autocomplete="off" class="layui-input"
                       style="float: left;width: 80%"> <a class="layui-btn">搜索</a>
            </div>


            <a href="javascript:;" class="layui-btn " id="add"> <i
                    class="layui-icon">&#xe608;</i> 添加信息 </a> <a href="#"
                                                                 class="layui-btn " id="import"> <i class="layui-icon">&#xe608;</i>
            导入信息 </a> <a href="#" class="layui-btn "> <i
                class="fa fa-shopping-cart" aria-hidden="true"></i> 导出信息 </a>

        </blockquote>

        <fieldset class="layui-elem-field layui-field-title">
            <legend>绑定列表</legend>
        </fieldset>




        <div id="bindpage" class="page"></div>




    </div>
</div>


</body>
</html>
