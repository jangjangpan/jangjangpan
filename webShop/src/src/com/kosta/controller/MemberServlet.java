package com.kosta.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/memberInsert") // html의 action과 명칭이 동일해야 한다!!!!!
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		// Parameter 값이 많은 경우에는 동적 적용을 통해 더 유용하게 돌릴 수 있다!
		Enumeration<String> names = request.getParameterNames(); 
		while(names.hasMoreElements()) {
			String parmName = names.nextElement();
			String [] paramValues = request.getParameterValues(parmName);
			for(String val:paramValues) {
				out.println("<h3>" + parmName + " ===> " + val + "</h3>");
			}
		}
		
		
		/*
		 * String name = request.getParameter("name"); String phonenumber =
		 * request.getParameter("phonenumber"); String id = request.getParameter("id");
		 * String passwd = request.getParameter("passwd"); String[] subject =
		 * request.getParameterValues("subject");
		 * 
		 * System.out.println(name); System.out.println(phonenumber);
		 * System.out.println(id); System.out.println(passwd);
		 * System.out.println(Arrays.toString(subject)); // 배열 roop 안 돌고 찍기 // => 콘솔에만
		 * 찍은 것!!!!!!!!!!!
		 */	}

	
}

