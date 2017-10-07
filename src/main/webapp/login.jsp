<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="en">
<head>



	<meta charset="UTF-8">
	<title>邮件代收发管理系统</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<!-- load css -->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css" media="all">
</head>
<body>
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">


	<h1>
		 <strong>邮件代收发管理系统</strong>
		 <em>Management System</em>
	</h1>
	<form id="loginform" class="layui-form" action="UserServlet?action=login" method="post">
	<div class="layui-user-icon larry-login">
		 <input type="text" placeholder="账号" name="username" class="login_txtbx" lay-verify="required" style="height: 40px;"/>
	</div>
	<div class="layui-pwd-icon larry-login">
		 <input type="password" placeholder="密码" name="password"class="login_txtbx" lay-verify="required" style="height:40px;"/>
	</div>
    <div class="layui-val-icon larry-login">
    	<div class="layui-code-box">
    		<input type="text"  name="randomCode" placeholder="验证码" maxlength="4" lay-verify="required" class="login_txtbx">
            <img src="checkCode.jsp" alt="点击刷新" class="verifyImg" id="checkCodeImg" onclick="changeP()">
    	</div>
    </div>
    <div class="layui-submit larry-login">
    	<input type="submit" lay-submit value="登录" class="submit_btn"/>
    </div>
	<div class="layui-submit larry-login">
		<a style="color: #1E9FFF" id="reg_btn" href="reg.jsp">没有账号？</a>
	</div>
		</form>
    <div class="layui-login-text">
    
    </div>
</div>
<script type="text/javascript" src="<%=basePath%>common/layui/lay/dest/layui.all.js"></script>
<script type="text/javascript" src="<%=basePath%>js/login.js"></script>
<script type="text/javascript" src="<%=basePath%>jsplug/jparticle.jquery.js"></script>
<script type="text/javascript">

    /*changP():刷新验证码*/
    function changeP() {
        var checkCodeImg = document
                .getElementById("checkCodeImg");
        checkCodeImg.src = "checkCode.jsp?rnd="+Math.random();
    }

$(function(){
	$(".layui-canvs").jParticle({
		background: "#141414",
		color: "#E6E6E6"
	});
	//登录链接测试，使用时可删除

	//登录链接测试，使用时可删除
	$("#reg_btn").click(function(){
		location.href="reg.jsp";
	});
});
</script>
</body>
</html>