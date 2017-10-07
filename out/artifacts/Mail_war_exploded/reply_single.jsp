<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
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





</head>
<body>
<section class="layui-larry-box" id="">
    <div class="larry-personal">

        <div class="larry-personal-body clearfix" >
            <form class="layui-form" id="mailForm"  method="post" action="SendMailServlet?action=send" enctype="multipart/form-data">
                <div class="layui-form-item">
                    <label class="layui-form-label">收件人</label>
                    <div class="layui-input-block">
                        <input type="text" name="email_recipientTo"  autocomplete="off"  class="layui-input " value="${requestScope.from}"  >
                    </div>
                </div>
                <div class="layui-form-item" >
                    <label class="layui-form-label">使用邮箱</label>
                    <div class="layui-input-block">
                    <input type="text"   autocomplete="off"  class="layui-input " value="${requestScope.send}"  >
                        </div>
                    </div>

                <input type="text" name="email_from" value="${sessionScope.bindid}"style="display:none"/>
                <div class="layui-form-item">
                    <label class="layui-form-label">上传附件</label>
                    <div class="layui-input-block">
                        <input type="file" name="file" multiple>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">添加图片</label>
                    <div class="layui-input-block">
                        <a  onclick="addimg()">添加图片</a>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮件主题</label>
                    <div class="layui-input-block">
                        <input type="text" name="email_subject"  autocomplete="off" class="layui-input" value="${requestScope.subject}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮件内容</label>
                    <div class="layui-input-block">
                        <textarea id="email_content" name="email_content" value="${requestScope.subject}+${requestScope.from}+${requestScope.date}" style="display: none;"></textarea>
                        <script>
                            layui.use('layedit', function(){
                                var layedit = layui.layedit;
                                layedit.build('email_content'); //建立编辑器
                            });
                        </script>
                    </div>
                </div>

                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1" >发送</button>

                    </div>
                </div>
            </form>
        </div>
    </div>
</section>


</body>
</html>