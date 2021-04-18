package com.kosta.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmpDAO;
import model.EmpVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login/loginChk.kosta")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("loginForm.html");
		rd.forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost..............");
		
		request.setCharacterEncoding("utf-8");
		
		int empid = Integer.parseInt(request.getParameter("userid"));
		String email = request.getParameter("userpw");
		EmpDAO dao = new EmpDAO();
		EmpVO emp = dao.loginChk(empid, email);
		
		request.setAttribute("emp", emp);
		request.setAttribute("username", emp.getFirst_name() + "" + emp.getLast_name());
		
		/*
		// 1. 쿠키 이용
		Cookie c1 = new Cookie("empid", empid+"");
		Cookie c2 = new Cookie("email", email);
		Cookie c3 = new Cookie("username", URLEncoder.encode(emp.getFirst_name(), "utf-8"));
		c1.setMaxAge(60*1);
		c2.setMaxAge(60*5);
		c3.setMaxAge(60*60*24);
		String path = getServletContext().getContextPath();
		c1.setPath(path);
		c2.setPath(path);
		c3.setPath(path);
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		
		*/
		
		
		//2. Session 이용하기 (보안에 더 능함며, 사이즈 제한 X, 브라우저에서 쿠키 차단하는 경우와 무관하게 사용 가능 -> 더 많이 사용)
		// but Session은 쿠키 기술을 이용하여 Session ID를 쿠키로 만듦 -> Browser에 저장
		// 정보는 서버 메모리에 저장
		
		HttpSession session = request.getSession(); // 있으면 값을 얻고, 없으면 새로 만든다.
		System.out.println(session.isNew());
		session.setAttribute("empid", empid);
		session.setAttribute("email", email);
		session.setAttribute("emp", emp);
		session.setAttribute("username", emp.getFirst_name() + emp.getLast_name());
		//session.setMaxInactiveInterval(60); //초 단위 기록 -> 60초 간 유지하고자 한다
		
		
		
		
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("loginResult.jsp");
		rd.forward(request, response);
		
		
		
		/*
		response.setContentType("text/html;charset=utf-8"); // 한글 설정
		PrintWriter out = response.getWriter();
		out.append(String.format("<h1>아이디는 %s</h1>", uid));
		out.append(String.format("<h1>비밀번호는 %s</h1>", upw));
		*/
		
		
	}

}

