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

	<script type="text/javascript" >

		layui.use(['layer'], function() {
			var layer = layui.layer;
			$(":button").on('click', function () {
				var id= $(this).attr("id");

				layer.confirm('确定要删除绑定', {
					btn: ['确定', '取消'] //按钮
				}, function () {


					$.ajax({
						async: false,
						type: "post",
						async: "true",
						url: "ReceviceMailServlet?action=delete&mess_id="+id,
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






		layui.use(['layer'], function(){
			var layer = layui.layer;

			$('#t a').click( function(){
				var id = $(this).attr("id");
				var url = "mail_single.jsp?mailid="+id;
				layer.open({
					type:2,
					title:"邮件",
					shadeClose: true, //点击遮罩关闭层
					area : ['900px' , '650px'],
					content: url



				});




			});
		});

		$('span').click( function() {
			var id = $(this).attr("id");


			$.ajax({
				async: false,
				type: "post",
				async: "true",
				url: "SendMailServlet?action=reply&mess_id="+id,

				success: function (data) {
					layer.open({
						type: 1,
						title: "回复",
						shadeClose: true, //点击遮罩关闭层
						area: ['900px', '650px'],
						content: data


					});


				}
			});
		})


			$("#pre").click(function () {
				$.ajax({
					async: false,
					type: "post",
					async: "true",
					url: "ReceviceMailServlet?action=page_pre",

					success: function (data) {
						$("#mail").html(data);


					}
				});
			});

			$("#next").click(function () {
				$.ajax({
					async: false,
					type: "post",
					async: "true",
					url: "ReceviceMailServlet?action=page_next",

					success: function (data) {
						$("#mail").html(data);


					}
				});
			})



	</script>
</head>
<body>





<div id="mail">
			<table class="layui-table" id="t">
				<thead>
				    <tr>
				      <th><input type="checkbox" id="selected-all"></th>
				      <th>发件人</th>
				      <th>主题</th>
				      <th>时间</th>
						<th>操作</th>
				    </tr>
			  	</thead>
				<tbody>
        <c:forEach items="${sessionScope.list}" var="mail">
				<tr>
				<td><input type="checkbox" ></td>
				<td><c:out value="${mail.mail_from}"></c:out></td>

				<td><c:out value="${mail.mail_subject}"></c:out></td>
				<td><c:out value="${mail.mail_date}"></c:out></td>

				<td><a  id="${mail.mail_number}" class="layui-btn layui-btn-mini">查看</a>
					<span  class="layui-btn layui-btn-normal layui-btn-mini"  id="${mail.mail_number}" >回复</span>

					<button	class="layui-btn layui-btn-danger layui-btn-mini"  id="${mail.mail_number}">删除</button>

				</td>
				</tr>
			</c:forEach>

				</tbody>

			</table>
<div class="larry-table-page clearfix">
                          <a href="javascript:;" class="layui-btn layui-btn-small" style="float:left;" ><i class="iconfont icon-shanchu1"></i>删除</a>
				          <div id="userpage"  style="padding-left: 8%;">
							<c:choose>
								<c:when test="${sessionScope.pu.isPrePage==true}">
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
								  <c:when test="${sessionScope.pu.isNextPage==true}">
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
							  <span style="margin-left: 2%;">页数:<c:out value="${sessionScope.pu.currentPage }"></c:out>/<c:out value="${sessionScope.pu.totalPages }"></c:out> </span>
						  </div>
			         </div>


</div>

</body>

</html>
