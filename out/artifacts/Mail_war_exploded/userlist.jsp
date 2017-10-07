<%--
  Created by IntelliJ IDEA.
  User: Lhy
  Date: 2017/5/14 0014
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css"
          href="<%=basePath%>common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/adminstyle.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/personal.css" media="all">

<script>

    layui.use(['layer'], function() {
        var layer = layui.layer;
        $(":button").on('click', function () {
            var id = $(this).attr("sid");

            layer.confirm('确定要删除绑定', {
                btn: ['确定', '取消'] //按钮
            }, function () {


                $.ajax({
                    async: false,
                    type: "post",
                    async: "true",
                    url: "UserServlet?action=delete&user_id=" + id,
                    success: function (data) {
                        $("#userlist").html(data);
                        layer.msg('删除成功！', {icon: 1});

                    }
                });

            }, function () {
                layer.msg("取消删除");
            });
        });
    })



    $("#pre").click(function () {
    $.ajax({
    async: false,
    type: "post",
    async: "true",
    url: "UserServlet?action=page_pre",

    success: function (data) {
    $("#userlist").html(data);


    }
    });
    });

    $("#next").click(function () {
    $.ajax({
    async: false,
    type: "post",
    async: "true",
    url: "UserServlet?action=page_next",

    success: function (data) {
    $("#userlist").html(data);


    }
    });
    })
</script>
</head>
<body>
<div id="userlist">
<table class="layui-table">
    <thead>
    <tr>
        <th>用户id</th>
        <th>用户名</th>
        <th>介绍</th>
        <th>头像</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${sessionScope.userlist}" var="user">
    <tr>
       <td><c:out value="${user.userId}"></c:out></td>
        <td><c:out value="${user.username}"></c:out></td>
        <td><c:out value="${user.userInfo}"></c:out></td>
        <td><c:out value="${user.userPic}"></c:out></td>
        <td>  <button  class="layui-btn layui-btn-danger layui-btn-mini"  sid="${user.userId}">删除</button></td>
    </tr>
</c:forEach>


    </tbody>

</table>
<div class="larry-table-page clearfix">
    <a href="javascript:;" class="layui-btn layui-btn-small" style="float:left;" ><i class="iconfont icon-shanchu1"></i>删除</a>
    <div id="userpage"  style="padding-left: 8%;">
        <c:choose>
            <c:when test="${sessionScope.pageinfo.hasPreviousPage==true}">
                <a class="layui-btn layui-btn-small" id="pre">
                    <i class="layui-icon">&#xe603;</i>
                </a>
            </c:when>
            <c:otherwise>
                <a class="layui-btn layui-btn-small layui-btn-disabled">
                    <i class="layui-icon">&#xe603;</i>
                </a>


            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${sessionScope.pageinfo.hasNextPage==true}">
                <a class="layui-btn layui-btn-small" id="next">
                    <i class="layui-icon">&#xe602;</i>
                </a>
            </c:when>
            <c:otherwise>
                <a class="layui-btn layui-btn-small layui-btn-disabled">
                    <i class="layui-icon">&#xe602;</i>
                </a>


            </c:otherwise>

        </c:choose>
        <span style="margin-left: 2%;">页数:<c:out value="${sessionScope.pageinfo.pageNum }"></c:out>/<c:out value="${sessionScope.pageinfo.pages }"></c:out> </span>
    </div>
</div>
    </div>
</body>
</html>
