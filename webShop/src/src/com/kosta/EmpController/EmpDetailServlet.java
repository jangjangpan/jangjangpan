package com.kosta.EmpController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeptDAO;
import model.EmpDAO;

/**
 * Servlet implementation class EmpDetailServlet
 */
@WebServlet("/emp/empDetail")
public class EmpDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String obj = request.getParameter("empid");
		if(obj == null) throw new ServletException("empid가 없음");
		
		int empid = Integer.parseInt(obj);
		EmpDAO dao = new EmpDAO();
		DeptDAO dao2 = new DeptDAO();
		request.setAttribute("emp", dao.selectById(empid));
		request.setAttribute("mlist", dao2.selectAllManager());
		request.setAttribute("dlist", dao2.selectAll());
		request.setAttribute("jlist", dao.selectAllJobs());
	
		
		RequestDispatcher rd = request.getRequestDispatcher("empDetail.jsp");
		rd.forward(request, response);
		
	}

	

}
