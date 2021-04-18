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
import model.EmpVO;
import util.ConvertUtil;

/**
 * Servlet implementation class EmpInsertServlet
 */
@WebServlet("/emp/empInsert")
public class EmpInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDAO dao = new EmpDAO();
		DeptDAO dao2 = new DeptDAO();
		request.setAttribute("mlist", dao2.selectAllManager());
		request.setAttribute("dlist", dao2.selectAll());
		request.setAttribute("jlist", dao.selectAllJobs());
		
		
		RequestDispatcher rd = request.getRequestDispatcher("empInsert.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에 insert
		// request.setCharacterEncoding("utf-8");
	
		
		EmpVO emp = new EmpVO();
		emp.setCommission_pct(ConvertUtil.convertDouble(request.getParameter("commission_pct")));
		emp.setDepartment_id(ConvertUtil.convertInt(request.getParameter("department_id")));
		emp.setEmail(request.getParameter("email"));
		emp.setEmployee_id(ConvertUtil.convertInt(request.getParameter("employee_id")));
		emp.setFirst_name(request.getParameter("first_name"));
		emp.setHire_date(ConvertUtil.convertDate(request.getParameter("hire_date")));
		emp.setJob_id(request.getParameter("job_id"));
		emp.setLast_name(request.getParameter("last_name"));
		emp.setManager_id(ConvertUtil.convertInt(request.getParameter("manager_id")));
		emp.setPhone_number(request.getParameter("phone_number"));
		emp.setSalary(ConvertUtil.convertInt(request.getParameter("salary")));
		
		EmpDAO dao = new EmpDAO();
		int result = dao.insertEmp(emp);
		String message = result>0?"입력성공":"입력실패";
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
		rd.forward(request, response);
		
	}
}
