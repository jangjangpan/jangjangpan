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
 * Servlet Filter implementation class SecondFilter
 */
@WebFilter("*.kosta")
public class SecondFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SecondFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("SecondFilter에서 실제 요청 가기 전에 실행");
		// pass the request along the filter chain
		chain.doFilter(request, response);
		System.out.println("SecondFilter에서 실제 요청 후에 실행");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("SecondFilter init-------------");
		System.out.println(fConfig.getFilterName());
		System.out.println(fConfig.getServletContext().getRealPath("."));
		System.out.println(fConfig.getServletContext().getContextPath());
	}

}
