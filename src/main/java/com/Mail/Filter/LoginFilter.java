package com.Mail.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {


	public void destroy() {
		
		System.out.println("EncodingFilter destroy");
		
	}
	
	/**
	 * 登录过滤器:防止未登录者进入
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rep = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		String requestPath = req.getRequestURI().toString();
		String requestPage = requestPath.substring(requestPath.lastIndexOf("/")+1);
		
		System.out.println("请求页面:"+requestPage);
		
		if("login.jsp".equals(requestPage)||"reg.jsp".equals(requestPage)||"checkCode.jsp".equals(requestPage)){//放过请求 注册 登录 验证码 的页面
			
			chain.doFilter(request,response);
			return ;
			
		}else{
			
			if(session.getAttribute("user_id")==null&&session.getAttribute("username")==null){//代表没登录
				request.setAttribute("Info", "pleaseLogin");
				request.getRequestDispatcher("/login.jsp").forward(request, response);//转发到首页
				return ;
			}else{
				
				chain.doFilter(request,response);
				return ;
			}
			
			
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
