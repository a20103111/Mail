<%--
  Created by IntelliJ IDEA.
  User: Lhy
  Date: 2017/5/7 0007
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<head>
    <title>Title</title>
    <!-- <script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script> -->
<link rel="stylesheet" type="text/css"
          href="<%=basePath%>common/layui/css/layui.css" media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/adminstyle.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/personal.css" media="all">
    <!--<script type="text/javascript" src="<%=basePath%>common/layui/layer.js"> </script> -->
    <!--<script type="text/javascript" src="<%=basePath%>common/layui/lay/modules/layer.js"></script> -->

    <script>
        layui.use(['layer'], function() {
            var layer = layui.layer;
            $(":button").on('click', function () {
                var id= $(this).attr("sid");

                layer.confirm('确定要删除绑定', {
                    btn: ['确定', '取消'] //按钮
                }, function () {


                    $.ajax({
                        async: false,
                        type: "post",
                        async: "true",
                        url: "MailServlet?action=deleteBind&bind_id="+id,

                        success: function (data) {
                            $("#mail").html(data);
                            layer.msg('删除成功！', {icon: 1});

                        }
                    });

                }, function () {
                    layer.msg("取消删除");
                });
            });
        })


    </script>
</head>
<body>





<div id="mail">
    <table class="layui-table">
        <thead>
        <tr>

            <th>绑定邮箱</th>
            <th>邮箱账号</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.bindlist}" var="vo">
            <tr>

                <td><c:out value="${vo.mail_name}"></c:out></td>

                <td><c:out value="${vo.mail_acc}"></c:out></td>
                <td><c:out value="已绑定"></c:out></td>

                <td>

                    <button  class="layui-btn layui-btn-danger layui-btn-mini"  sid="${vo.bind_id}">删除</button>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>



</div>

</body>

</html>
