package com.kosta.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {


    public EncodingFilter() {
       System.out.println("");
    }


	public void destroy() {
		System.out.println("");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		request.setCharacterEncoding("utf-8");
		// pass the request along the filter chain\
		// Servlet으로 가기
		System.out.println("EncodingFilter: Servlet 수행 전");
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		System.out.println("EncodingFilter: Servlet 수행 후(걸린 시간) => " + (end-start) + "ms");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("EncodingFilter init-------------");
		System.out.println(fConfig.getFilterName());
		System.out.println(fConfig.getServletContext().getRealPath("."));
		System.out.println(fConfig.getServletContext().getContextPath());
	}

}
