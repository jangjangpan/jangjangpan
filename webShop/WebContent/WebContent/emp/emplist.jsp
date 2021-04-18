<%@page import="model.EmpVO"%>
<%@page import="model.EmpDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>



<meta charset="UTF-8">
<title>직원목록</title>

<jsp:include page="../common/header.jsp"></jsp:include>


<style>
	table, td{ border:1px solid black; border-collapse:collapse;}
	td{ padding: 5px; }
	tr:first-of-type { background-color : green;}
</style>
</head>
<body>
<h1>직원목록 -> 로그인 정보 : ${loginEmail}</h1>

<br>
<a href = "empInsert">신규등록</a>
<br><br>

<ul>
	<li>문자값 : ${myname }</li>
	<li>숫자값 : <%=request.getAttribute("myscore") %></li>
	<li>emp객체 : ${myemp }</li>
	<li>info객체 : ${info }</li>
</ul>


<table>
 <tr>
 	<td>직원번호</td>
 	<td>성</td>
 	<td>이름</td>
 	<td>이메일</td>
 	<td>전화번호</td>
 	<td>입사일</td>
 	<td>직책</td>
 	<td>연봉</td>
 	<td>커미션</td>
 	<td>매니저</td>
 	<td>부서번호</td>
 	<td></td>
 </tr>

<%
List<EmpVO> emplist = (List<EmpVO> )request.getAttribute("emplist");
for(EmpVO emp:emplist){
	request.setAttribute("emp", emp);
%>
  <tr>
 	<td><%=emp.getEmployee_id() %></td>
 	<td><%=emp.getLast_name() %></td>
 	<td><a href="empDetail?empid=<%=emp.getEmployee_id() %>"><%=emp.getFirst_name() %></a></td>
 	<td><%=emp.getEmail() %></td>
 	<td><%=emp.getPhone_number() %></td>
 	<td><%=emp.getHire_date() %></td>
 	<td><%=emp.getJob_id() %></td>
 	<td><%=emp.getSalary() %></td>
 	<td><%=emp.getCommission_pct() %></td>
 	<td><%=emp.getManager_id() %></td>
 	<td><%=emp.getDepartment_id() %></td>
 	<td><button onclick = "call(<%=emp.getEmployee_id() %>);">삭제</button></td>
 </tr>
<%} %>

</table>
<script>
function call(empid){
	location.href = "empDelete?empid=" + empid;
}
</script>
</body>
</html>