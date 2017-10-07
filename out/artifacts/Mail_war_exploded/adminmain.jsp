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
	<title>LarryBlogCMS-Home</title>
  <meta name="renderer" content="webkit"> 
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> 
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">  
  <meta name="format-detection" content="telephone=no"> 
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/bootstrap/css/bootstrap.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css" media="all">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	<!--<script type="text/javascript" src="<%=basePath%>jsplug/echarts.min.js"></script>-->
	<script type="text/javascript" src="<%=basePath%>js/echarts.common.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/main.js"></script>
	<script>
		$("document").ready(function() {
			var myChart = echarts.init(document.getElementById("count"));
			myChart.setOption({
				title: {
					text: '绑定邮箱统计',
					subtext: '后台数据',
					x: 'center'
				},
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					left: 'left',
					data: []
				},
				series: [
					{
						name: '邮箱',
						type: 'pie',
						radius: '55%',
						center: ['50%', '60%'],
						data: [],
						itemStyle: {
							emphasis: {
								shadowBlur: 10,
								shadowOffsetX: 0,
								shadowColor: 'rgba(0, 0, 0, 0.5)'
							}
						}
					}
				]
			});
			myChart.showLoading();// 加载动画
			// 异步加载数据
			$.post('MailServlet?action=countbind').done(function (data) {//jQuery触发ajax 从后台异步获取数据
				var str = eval('(' + data + ')');   //解析json
				var array = [];

				for (var i = 0; i < 21; i++) {
//alert(str.name[i]+"===========》"+str.id[i]);
					var map = {};
					map.name = str.name[i];
					map.value = str.count[i];
					array[i] = map;
				}
				myChart.hideLoading();
				// 填入数据
				myChart.setOption({
					legend: {
						data: str.name
					},
					series: [{
						// 根据名字对应到相应的系列
						name: '邮箱',
						type: 'pie',

						data: array


					}],
				});
			});
		})
	</script>

</head>
<body>
<section class="larry-wrapper">
    <!-- overview -->
	<div class="row state-overview">
		<div class="col-lg-3 col-sm-6">
			<section class="panel">
				<div class="symbol userblue"> <i class="iconpx-users"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1 id="count1">${sessionScope.bindcount}</h1>
					</a>
					<p>绑定邮箱数</p>
				</div>
			</section>
		</div>
		<div class="col-lg-3 col-sm-6">
			<section class="panel">
				<div class="symbol commred"> <i class="iconpx-user-add"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1 id="count2">${sessionScope.usercount}</h1>
					</a>
					<p>用户数量</p>
				</div>
			</section>
		</div>
		<div class="col-lg-3 col-sm-6">
			<section class="panel">
				<div class="symbol articlegreen">
					<i class="iconpx-file-word-o"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1 id="count3">${sessionScope.actioncount}</h1>
					</a>
					<p>日志数量</p>
				</div>
			</section>
		</div>
		<div class="col-lg-3 col-sm-6">
			<section class="panel">
				<div class="symbol rsswet">
					<i class="iconpx-check-circle"></i>
				</div>
				<div class="value">
					<a href="#">
						<h1 id="count4">${sessionScope.mailcount}</h1>
					</a>
					<p>邮箱服务器</p>
				</div>
			</section>
		</div>
	</div>
	<!-- overview end -->
	<div class="row">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading bm0">
					<span class='span-title'>系统概览</span>
					<span class="tools pull-right"><a href="javascript:;" class="iconpx-chevron-down"></a></span>
				</header>
				<div class="panel-body" >
					<table class="table table-hover personal-task">
                         <tbody>
                         	<tr>
                         		<td>
                         			<strong>版本信息</strong>： 版本名称：<a href="">Mail</a> 版本号: V01

                         		</td>
                         		<td>

                         		</td>
                         	</tr>
                         	<tr>
                         		<td>
                                <strong>开发作者</strong>： Lhy

                                </td>
                                <td></td>
                         	</tr>
                         	<tr>
                         		<td>
                                <strong>网站域名</strong>:59.110.137.252:8080/Mail
                                </td>
                                <td></td>
                         	</tr>

                         	<tr>
                         		<td>
                                <strong>服务器IP</strong>：59.110.137.252
                                </td>
                                <td></td>
                         	</tr>
                         	<tr>
                         		<td>
                                <strong>服务器环境</strong>：CentOS
                                </td>
                                <td></td>
                         	</tr>
                         	<tr>
                         		<td>
                                <strong>数据库版本</strong>： MySql 5.1

                                </td>
                                <td></td>
                         	</tr>


                         </tbody>
					</table>
				</div>
			</section>

		</div>
		<div class="col-lg-6">

          <!-- 系统公告 -->
             <section class="panel">
                 <header class="panel-heading bm0">
                        <span class='span-title'>使用说明</span>
                        <span class="tools pull-right"><a href="javascript:;" class="iconpx-chevron-down"></a></span>
                  </header>
                  <div class="panel-body" style="padding: 5%;">


									欢迎来到邮件代收发系统！





                  </div>
             </section>

			<section class="panel">
				<header class="panel-heading bm0">
					<span class='span-title'>邮箱绑定统计</span>
					<span class="tools pull-right"><a href="javascript:;" class="iconpx-chevron-down"></a></span>
				</header>
				<div class="panel-body laery-seo-box">
					<div  style="width: 600px;height: 500px;padding-left: 20%;padding-top: 5%" id="count"></div>
	</div>
			</section>

			<script type="text/javascript">
				layui.use(['jquery','layer','element'],function(){
					window.jQuery = window.$ = layui.jquery;
					window.layer = layui.layer;
					window.element = layui.element();

					$('.panel .tools .iconpx-chevron-down').click(function(){
						var el = $(this).parents(".panel").children(".panel-body");
						if($(this).hasClass("iconpx-chevron-down")){
							$(this).removeClass("iconpx-chevron-down").addClass("iconpx-chevron-up");
							el.slideUp(200);
						}else{
							$(this).removeClass("iconpx-chevron-up").addClass("iconpx-chevron-down");
							el.slideDown(200);
						}
					})

				});
			</script>
</body>
</html>