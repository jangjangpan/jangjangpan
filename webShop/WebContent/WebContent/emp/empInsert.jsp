<%@page import="model.DeptVO"%>
<%@page import="model.ManagerVO"%>
<%@page import="model.EmpVO"%>
<%@page import="model.JobVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>직원 신규등록</h1>
<h1>${appInfo }</h1>

<jsp:include page="../common/header.jsp"></jsp:include>



<form action="empInsert" method = "post"> 
직원번호:<input type = "number" name = "employee_id"  ><br>
성:<input type = "text" name = "last_name" ><br>
이름:<input type = "text" name = "first_name" ><br>
이메일:<input type = "text" name = "email" ><br>
전화번호:<input type = "text" name = "phone_number" ><br>
입사일:<input type = "text" name = "hire_date" ><br>
직책:
<select name = "job_id">
 <%

 List<JobVO> jlist = (List<JobVO>)request.getAttribute("jlist");
 for(JobVO j:jlist){
	 out.print("<option value = '" + j.getJob_id() + "'>" + j.getJob_title() + "</option>");
	 
 }
 
 %>
</select><br>


연봉:<input type = "number" name = "salary" ><br>
커미션:<input type = "text" name = "commission_pct" ><br>
매니저:
<select name = "manager_id">
 <%

 List<ManagerVO> mlist = (List<ManagerVO>)request.getAttribute("mlist");
 for(ManagerVO m:mlist){
	 
	 out.print("<option value = '" + m.getManager_id() + "'>" + m.getFullname() + "</option>");
	 
 }
 
 %>


</select><br>

부서번호:
<select name = "department_id">
 <% 
 	List<DeptVO> dlist = (List<DeptVO>)request.getAttribute("dlist"); 
 	for(DeptVO dept:dlist){ 
 		int d = dept.getDept_id();
 	%>
	<option value = "<%=dept.getDept_id()%>"><%=dept.getDept_name() %></option>

 <% } %>

</select><br>


<input type = "submit" value = "입력하기">
</form>
</body>
</html>