package com.kosta.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeptDAO;
import model.DeptVO;

/**
 * Model2(MVC2)
 * Servlet = JAVA + HTML이지만, HTML이 적어야 좋음!
 * JSP 	   = HTML + JAVA이지만, JAVA는 안 쓰는 게 좋음! 
 */
@WebServlet("/dept/deptlist")
public class DeptListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptDAO dao= new DeptDAO();
		List<DeptVO> dlist = dao.selectAll();
		request.setAttribute("deptall", dlist);
		// Servlet이 받은 요청을 JSP에 넘기기 (위임)
		RequestDispatcher rd = request.getRequestDispatcher("dept_retrieve.jsp");
		rd.forward(request, response);
		
	}

}


