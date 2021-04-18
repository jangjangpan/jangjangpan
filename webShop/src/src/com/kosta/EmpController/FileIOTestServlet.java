package com.kosta.EmpController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileIOTestServlet
 */
@WebServlet("/FileIOTestServlet")
public class FileIOTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileIOTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ServletContext app = getServletContext();
		InputStream is = app.getResourceAsStream("WEB-INF/Book.java");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s;
		while((s=br.readLine())!=null) {
			System.out.println(s);
		}
		
		
	}


}
