<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>
<%@ page import="com.Mail.Service.ReceviceMail" %>
<%@ page import="javax.mail.Folder" %>
<%@ page import="javax.mail.Message" %>
<%@ page import="sun.misc.BASE64Decoder" %>
<%@ page import="com.Mail.Util.HandleReceivedMail" %>
<%@ page import="javax.mail.internet.MimeMessage" %>
<%@ page import="java.util.Enumeration" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                type: "post",
                async: "true",
                url: "MailServlet?action=queryBind",
                success: function (data) {
                    $("#list").html(data);
                },
                beforeSend: function () {

                }
            });



        });


</script>



</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout" >
    <div class="layui-main">

        <fieldset class="layui-elem-field layui-field-title">
            <legend>绑定邮箱列表</legend>
        </fieldset>
        <div id="list">


        </div>
    </div>
</div>


</body>
</html>
