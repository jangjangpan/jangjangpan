package com.kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeptDAO;

/**
 * Servlet implementation class DeptDeleteServlet
 */
@WebServlet("/dept/deptDelete")
public class DeptDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deptid = Integer.parseInt(request.getParameter("deptid"));
		
		DeptDAO dao = new DeptDAO();
		int result = dao.deleteDept(deptid);
		System.out.println(result + "건 삭제");
		request.setAttribute("message", result + "건 삭제되었습니다");
		// JSP에게 위임한다
		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
		rd.forward(request, response);
		
	}
		
		

}
