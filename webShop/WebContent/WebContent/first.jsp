<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
application.setAttribute("myAge1", 10); // 서버 가동 시 계속 실행
session.setAttribute("myAge2", 20); // 브라우저 열리면 계속
request.setAttribute("myAge3", 30);  // 보내면 사라짐
pageContext.setAttribute("myAge4", 40); // 해당 페이지에서만 사용 가능(다른 페이지에서는 사용 불가)

String dbname = application.getInitParameter("dbname");
String userid = application.getInitParameter("userid");


RequestDispatcher rd = null;
rd = request.getRequestDispatcher("second.jsp");
rd.include(request, response);

%>

<h1>first 페이지에서 저장 후 가져오기</h1>
<p>myage1:${myAge1}</p>
<p>myage2:${myAge2}</p>
<p>myage3:${myAge3}</p>
<p>myage4:${myAge4}</p>
<p><%=dbname %></p>
<p><%=userid %></p>
</body>
</html>