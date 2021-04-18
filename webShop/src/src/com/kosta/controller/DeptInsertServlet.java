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
import model.LocationVO;

/**
 * Servlet implementation class DeptInsertServlet
 */
@WebServlet("/dept/deptInsert")
public class DeptInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력하는 페이지를 보여주기
		DeptDAO dao = new DeptDAO();
		request.setAttribute("loclist", dao.selectAllLocation());
		request.setAttribute("mlist", dao.selectAllManager());
		RequestDispatcher rd = request.getRequestDispatcher("deptInsert.jsp");
		rd.forward(request, response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력 목적
		int deptid = convertInt(request.getParameter("Dept_id"));
		String dname = request.getParameter("Dept_name");
		int mid = convertInt(request.getParameter("manager_id"));
		int locid = convertInt(request.getParameter("location_id"));
		
		DeptVO dept = new DeptVO(deptid, dname, mid, locid);
		// System.out.println(dept);
		DeptDAO dao = new DeptDAO();
		int result = dao.insertDept(dept);
		System.out.println(result + "건 입력");
		request.setAttribute("message", result + "건 입력되었습니다");
		// JSP에게 위임한다
		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
		rd.forward(request, response);
	}

	private int convertInt(String param) {
		if(param==null ||param.trim() == "") return 0;
		return Integer.parseInt(param);
	}

}
