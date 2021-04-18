<%@page import="model.DeptVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>



<meta charset="UTF-8">
<title>부서조회</title>
<%
List<DeptVO> dlist = (List<DeptVO>)request.getAttribute("deptall");
%>
<style>
	table, td{ border:1px solid black; border-collapse:collapse;}
	td{ padding: 5px; }
	tr:first-of-type { background-color : hotpink;}
</style>
</head>
<body>
<h1>부서목록</h1>

<br>
<a href = "deptInsert">신규등록</a>


<table>
 <tr>
 	<td>부서코드</td><td>이름</td><td>매니저</td><td>지역코드</td><td></td>
 </tr>

<%for(DeptVO dept:dlist){%>
 <tr>
 	<td><a href = "deptDetail?deptid=<%=dept.getDept_id() %>"><%=dept.getDept_id() %></a></td>
 	<td><%=dept.getDept_name() %></td>
 	<td><%=dept.getManager_id() %></td>
 	<td><%=dept.getLocation_id() %></td>
 	<td><button onclick = "location.href='deptDelete?deptid=<%=dept.getDept_id() %>'">삭제</button></td>
 </tr>
<%} %>

</table>

</body>
</html>