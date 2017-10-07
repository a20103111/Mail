package com.Mail.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	/**
	 * 编码过滤器
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
		System.out.println("EncodingFilter destroy");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
		System.out.println("EncodingFilter Start");
		
	}


}
