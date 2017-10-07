<%--
  Created by IntelliJ IDEA.
  User: Lhy
  Date: 2017/5/10 0010
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>

<head>
    <title>绑定情况</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
    <script>

        $("document").ready(function() {
            layui.use(['layer'], function() {
                var layer = layui.layer;
                layer.open({
                    type: 1
                    ,
                    title: false //不显示标题栏
                    ,
                    closeBtn: false
                    ,
                    area: '300px;'
                    ,
                    shade: 0.8
                    ,
                    id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,
                    resize: false
                    ,
                    btn: ['再发一封', '回到首页']
                    ,
                    btnAlign: 'c'
                    ,
                    moveType: 1 //拖拽模式，0或者1
                    ,
                    content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">邮件发送成功！</div>'
                    ,
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            href: 'sendMail.jsp'

                        });
                        btn.find('.layui-layer-btn1').attr({
                            href: 'main.jsp'

                        });
                    }
                });
            })
})
    </script>
</head>
<body>
  发送成功 ！
  <a href="">再发一封</a> <a>关闭</a>


</body>
</html>
