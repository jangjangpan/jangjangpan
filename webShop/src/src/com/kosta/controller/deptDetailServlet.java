package com.kosta.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeptDAO;
import model.DeptVO;

/**
 * Servlet implementation class deptDetailServlet
 */
@WebServlet("/dept/deptDetail")
public class deptDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deptDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상세보기 : 조회목적
		String s_deptid = request.getParameter("deptid");
		int i_deptid = Integer.parseInt(s_deptid);
		DeptDAO dao = new DeptDAO();
		DeptVO dept = dao.selectById(i_deptid);
		request.setAttribute("dept", dept);
		request.setAttribute("myname", "jun");
		// JSP에게 위임한다.
		RequestDispatcher rd = request.getRequestDispatcher("deptDetail.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 목적
		int deptid = convertInt(request.getParameter("Dept_id"));
		String dname = request.getParameter("Dept_name");
		int mid = convertInt(request.getParameter("manager_id"));
		int locid = convertInt(request.getParameter("location_id"));
		
		DeptVO dept = new DeptVO(deptid, dname, mid, locid);
		DeptDAO dao = new DeptDAO();
		int result = dao.updateDept(dept);
		System.out.println(result + "건 수정");
		request.setAttribute("message", result + "건 수정");
		// JSP에게 위임한다
		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
		rd.forward(request, response);
	}

	private int convertInt(String param) {
		if(param==null) return 0;
		return Integer.parseInt(param);
	}
	
}

