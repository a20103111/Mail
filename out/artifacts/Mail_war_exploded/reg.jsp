<%--
  Created by IntelliJ IDEA.
  User: Lhy
  Date: 2017/5/8 0008
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
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

</head>
<body style="background: white">
<div style="text-align: center;padding-top: 10%;">
<section class="layui-larry-box">
    <div class="larry-personal">

            <span>注册</span>

        <div class="larry-personal-body clearfix changepwd" style="text-align: center">
            <form class="layui-form col-lg-4" id="regform"  action ="UserServlet?action=regist" method ="post" style="margin-left: 30%;">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="username"  autocomplete="off"  lay-verify="required"  class="layui-input " value="" >
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="password"  autocomplete="off"   lay-verify="required"  class="layui-input" value="" placeholder="请输入旧密码">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">重复密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="repassword"  autocomplete="off"  lay-verify="required"   class="layui-input" value="" placeholder="请输入新密码">
                    </div>
                </div>

                <div class="layui-form-item change-submit">
                    <div class="layui-input-block">
                        <button  id="reg" type="submit" class="layui-btn" lay-submit >立即注册</button>

                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
    </div>


</body>
</html>
