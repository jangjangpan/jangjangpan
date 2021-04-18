package com.kosta.EmpController;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmpDAO;
import model.EmpVO;
import util.ConvertUtil;

/**
 * Servlet implementation class EmpUpdateServlet
 */
@WebServlet("/emp/empUpdate")
public class EmpUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
	
		
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
		int result = dao.updateEmp(emp);
		String message = result>0?"수정성공":"수정실패";
		request.setAttribute("message", message);
		
		
//		response.setHeader("refresh","3;url=emplist"); // 응답문서의 header 정보 변경하기 -> 키, 값 형태
//		RequestDispatcher rd = request.getRequestDispatcher("resultInfo.jsp");
//		rd.forward(request, response);
		
		// 주소창이 변경됨
		// 요청이 재지정됨(변경)
		ServletContext app = getServletContext();
		app.setAttribute("info", "요청 재지정시 request는 전달 안 됨, app은 가능");
		response.sendRedirect("emplist");
		
	}

	
	
}
