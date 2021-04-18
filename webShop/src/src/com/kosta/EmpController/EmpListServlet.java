package com.kosta.EmpController;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Servlet implementation class EmpListServlet
 */
@WebServlet("/emp/emplist")
public class EmpListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cp = request.getContextPath();
		System.out.println("application 이름 : " + cp);
		System.out.println("IP : " + request.getLocalAddr());
		System.out.println("Port : " + request.getLocalPort());
		System.out.println("URI : " + request.getRequestURI()); // URI 정보 -> /webShop/emp/emplist
		System.out.println("URL : " + request.getRequestURL()); // URL 정보 -> http://127.0.0.1:9090/webShop/emp/emplist
		
		EmpDAO dao= new EmpDAO();
		List<EmpVO> emplist = dao.selectAll();
		request.setAttribute("emplist", emplist);
		
		
		/*
		 * Servlet이 요청을 받아서 JSP에게 위임한다
		 */
		request.setAttribute("myname", "jun");
		request.setAttribute("myscore", "100");
		EmpVO emp = new EmpVO();
		emp.setFirst_name("직원이름 : 홍길동");
		emp.setSalary(500);
		request.setAttribute("myemp", emp);
		
		/* Servletcontext : application당 하나 */
		ServletContext app = getServletContext(); // 서버가 중지되면 다시 insert되기 전까지는 재생성되지 않음
		app.setAttribute("appInfo", "우리 사이트에 오신 것을 환영합니다");
		
		/*
		// 1. 쿠키 얻기
		Cookie[] cs = request.getCookies();
		for(Cookie c:cs) {
			String name = c.getName();
			String value = c.getValue();
			if(name.equals("email")) {
				request.setAttribute("loginEmail", URLDecoder.decode(value, "utf-8"));
			}
			System.out.println(name + " ===> " + value);
			System.out.println("====================");
		}
		
		*/
		
		// 2. Session 이용하기
		HttpSession session = request.getSession(); 
		Object obj = session.getAttribute("empid");
		if(obj == null){
			response.sendRedirect("../login/loginChk.kosta");
			return;
		}
		
		
		
		
		/*
		System.out.println("info : " + app.getAttribute("info"));
		System.out.println("dbname : " + app.getInitParameter("dbname"));
		System.out.println("userid : " + app.getInitParameter("userid"));
		*/

		// Servlet이 받은 요청을 JSP에 넘기기 (위임)
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("emplist.jsp");
		rd.forward(request, response);
		
		
	}



}
