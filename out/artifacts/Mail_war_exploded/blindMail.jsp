<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>


<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人信息</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/bootstrap/css/bootstrap.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/personal.css" media="all">
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>

    <script type="text/javascript">

        function bind(){


                $.ajax({

                    type: "POST",
                    url: "MailServlet?action=bind",
                    data: $('#bindform').serialize(),// 你的formid
                    async: true,
                    error: function(request) {
                        alert("Connection error");
                    },
                    success: function (data) {
                        layer.msg(data);
                    }
                });


            }


    </script>
</head>
<body>
<div>
<section class="layui-larry-box">
    <div class="larry-personal" >
        <header class="larry-personal-tit">
            <span>绑定邮箱</span>
        </header><!-- /header -->
        <div class="larry-personal-body clearfix changepwd" style="margin-left: 30%;margin-top: 10%;">
            <form class="layui-form col-lg-4"  id ="bindform" >
                <div class="layui-form-item">
                    <label class="layui-form-label">选择邮箱</label>
                    <div class="layui-input-block">
                        <select name="mail_id" lay-verify="required">
                            <option value=""></option>
                            <option value="1">163.com</option>
                            <option value="2">126.com</option>


                        </select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱账号</label>
                    <div class="layui-input-block">
                        <input type="text"   autocomplete="off"  class="layui-input" name="mail_acc" lay-verify="required" value="" placeholder="请输入要绑定的邮箱">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">授权码</label>
                    <div class="layui-input-block">
                        <input type="password"   autocomplete="off"  class="layui-input" name="mail_auth" lay-verify="required" value="" placeholder="请输入服务授权码">
                    </div>
                </div>

                <div class="layui-form-item change-submit">
                    <div class="layui-input-block">
                        <a class="layui-btn "  onclick="bind()"  >绑定</a>

                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
    </div>

<script type="text/javascript">
    layui.use(['form','upload'],function(){
        var form = layui.form();
    });
</script>
</body>
</html>