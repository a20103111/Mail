<%@ page language="java" import="java.sql.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>邮件代收发系统管理</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">	
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<!-- load css -->
<link rel="stylesheet" type="text/css" href="<%=basePath%>common/layui/css/layui.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>common/global.css" media="all">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/adminstyle.css" media="all">
	
</head>
<body>
<div class="layui-layout layui-layout-admin" id="layui_layout">
	<!-- 顶部区域 -->
	<div class="layui-header header header-demo">
		<div class="layui-main">
		    <!-- logo区域 -->
			<div class="admin-logo-box">
				<a class="logo" style="left: 0;" href="">
							<span style="font-size: 22px;color: white;">邮件代收发系统</span>
						</a>
				<div class="larry-side-menu">
					<i class="fa fa-bars" aria-hidden="true"></i>
				</div>
			</div>
            <!-- 顶级菜单区域 -->
            <div class="layui-larry-menu">
                 <ul class="layui-nav clearfix">
                       <li class="layui-nav-item layui-this">
                 	   	   <a href="javascirpt:;"><i class="iconfont icon-wangzhanguanli"></i>内容管理</a>
                 	   </li>
                 	   <li class="layui-nav-item">
                 	   	   <a href="javascirpt:;"><i class="iconfont icon-weixin3"></i>微信公众</a>
                 	   </li>
                 	   <li class="layui-nav-item">
                 	   	   <a href="javascirpt:;"><i class="iconfont icon-ht_expand"></i>扩展模块</a>
                 	   </li>
                 </ul>
            </div>
            <!-- 右侧导航 -->
            <ul class="layui-nav larry-header-item">
            		<li class="layui-nav-item first">
						<a href="javascript:;">
							<img src="images/userimg.jpg" class="userimg">			
							<cite>默认站点</cite>
							<span class="layui-nav-more"></span>
						</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="">站点1</a>
							</dd>
							<dd>
								<a href="">站点2</a>
							</dd>
							
						</dl>
					</li>
					
					<li class="layui-nav-item">
						<a href="UserServlet?action=quit">
                        <i class="iconfont icon-exit"></i>
						退出</a>
					</li>
            </ul>
		</div>
	</div>
	<!-- 左侧侧边导航开始 -->
	<div class="layui-side layui-side-bg layui-larry-side" id="larry-side">
        <div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
		<div class="user-photo">
			<a class="img" title="我的头像" >
				<img src="images/user.jpg" class="userimg1"></a>
			<p>管理员:${sessionScope.username},欢迎登录</p>
		</div>
		<!-- 左侧菜单 -->
		<ul class="layui-nav layui-nav-tree">
			<li class="layui-nav-item layui-this">
				<a href="javascript:;" data-url="main.jsp">
				    <i class="iconfont icon-home1" data-icon='icon-home1'></i>
					<span>后台首页</span>
				</a>
			</li>
			<!-- 个人信息 -->
			<li class="layui-nav-item">
				<a href="javascript:;">
					<i class="iconfont icon-jiaoseguanli" ></i>
					<span>用户信息</span>
					<em class="layui-nav-more"></em>
				</a>
				<dl class="layui-nav-child">

                    <dd>
                        <a href="javascript:;" data-url="adminUser.jsp">
                            <i class="iconfont icon-iconfuzhi01" data-icon='icon-iconfuzhi01'></i>
                            <span>用户管理</span>
                        </a>
                    </dd>

					<dd>
						<a href="javascript:;" data-url="adminBind.jsp">
							<i class="iconfont icon-database" data-icon='icon-database'></i>
							<span>绑定邮箱管理</span>
						</a>
					</dd>

                </dl>
			</li>
			<!-- 用户管理 -->

			<!-- 内容管理 -->
			<li class="layui-nav-item">
					<a href="javascript:;">
					   <i class="iconfont icon-wenzhang1" ></i>
					   <span>日志管理</span>
					   <em class="layui-nav-more"></em>
					</a>
					   <dl class="layui-nav-child">
					   	   <dd>
					    		<a href="javascript:;" data-url="adminAction.jsp">
					    		   <i class="iconfont icon-lanmuguanli" data-icon='icon-lanmuguanli'></i>
					    		   <span>查看日志</span>
					    		</a>
					    	</dd>

					    	
					   </dl>
			   </li>
			


		</ul>
	    </div>
	</div>

	<!-- 左侧侧边导航结束 -->
	<!-- 右侧主体内容 -->
	<div class="layui-body" id="larry-body" style="bottom: 0;border-left: solid 2px #1AA094;">
		<div class="layui-tab layui-tab-card larry-tab-box" id="larry-tab" lay-filter="demo" lay-allowclose="true">
			<ul class="layui-tab-title" style="background-color: white;">
				<li class="layui-this" id="admin-home"><i class="iconfont icon-diannao1"></i><em>首页</em></li>
			</ul>
			<div class="layui-tab-content" style="min-height: 150px; ">
				<div class="layui-tab-item layui-show">
					<iframe class="larry-iframe" data-id='0' src="adminmain.jsp"></iframe>
				</div>
			</div>
		</div>

        
	</div>
	<!-- 底部区域 -->
	<div class="layui-footer layui-larry-foot" id="larry-footer">
		<div class="layui-mian">
		    
	</div>
</div>
<!-- 加载js文件-->
	<script type="text/javascript" src="<%=basePath%>common/layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/larry.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/index.js"></script>
<!-- 锁屏 -->
<div class="lock-screen" style="display: none;">
	<div id="locker" class="lock-wrapper">
		<div id="time"></div>
		<div class="lock-box center">
			<img src="images/userimg.jpg" alt="">
			<h1>admin</h1>
			<div class="form-group col-lg-12">
				<input type="password" placeholder='锁屏状态，请输入密码解锁' id="lock_password" class="form-control lock-input" autofocus name="lock_password">
				<button id="unlock" class="btn btn-lock">解锁</button>
			</div>
		</div>
	</div>
</div>
	</div>
<!-- 菜单控件 -->
<!-- <div class="larry-tab-menu">
	<span class="layui-btn larry-test">刷新</span>
</div> -->
<!-- iframe框架刷新操作 -->
<!-- <div id="refresh_iframe" class="layui-btn refresh_iframe">刷新</div> -->
</body>
</html>