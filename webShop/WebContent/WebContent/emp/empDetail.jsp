<%@page import="model.JobVO"%>
<%@page import="model.ManagerVO"%>
<%@page import="model.EmpVO"%>
<%@page import="model.DeptVO"%>
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
<h1>직원정보 상세보기</h1>
<h1>${appInfo }</h1>

<jsp:include page="../common/header.jsp"></jsp:include>



<form id="myfrm" action="empUpdate" method = "post"> 
직원번호:<input type = "number" name = "employee_id" value = "${emp.employee_id }"><br>
성:<input type = "text" name = "last_name" value = "${emp.last_name }"><br>
이름:<input type = "text" name = "first_name" value = "${emp.first_name }"><br>
이메일:<input type = "text" name = "email" value = "${emp.email }"><br>
전화번호:<input type = "text" name = "phone_number" value = "${emp.phone_number }"><br>
입사일:<input type = "text" name = "hire_date" value = "${emp.hire_date }"><br>
직책:
<select name = "job_id">
 <%
 String myJob = ((EmpVO)request.getAttribute("emp")).getJob_id();
 List<JobVO> jlist = (List<JobVO>)request.getAttribute("jlist");
 for(JobVO j:jlist){
	 String s = myJob.equals(j.getJob_id())?"selected":"";
	 out.print("<option " + s + " value = '" + j.getJob_id() + "'>" + j.getJob_title() + "</option>");
	 
 }
 
 %>
</select><br>
연봉:<input type = "number" name = "salary" value = "${emp.salary }"><br>
커미션:<input type = "text" name = "commission_pct" value = "${emp.commission_pct }"><br>
매니저:
<select name = "manager_id">
 <%
 int myManager = ((EmpVO)request.getAttribute("emp")).getManager_id();
 List<ManagerVO> mlist = (List<ManagerVO>)request.getAttribute("mlist");
 for(ManagerVO m:mlist){
	 String s = myManager==m.getManager_id()?"selected":"";
	 out.print("<option " + s + " value = '" + m.getManager_id() + "'>" + m.getFullname() + "</option>");
	 
 }
 
 %>


</select><br>




부서번호:
<select name = "department_id">
 <% 
 	int mydept = ((EmpVO)request.getAttribute("emp")).getDepartment_id();
 	List<DeptVO> dlist = (List<DeptVO>)request.getAttribute("dlist"); 
 	for(DeptVO dept:dlist){ 
 		int d = dept.getDept_id();
 	%>
	<option <%=mydept==d?"selected":"" %> value = "<%=dept.getDept_id()%>"><%=dept.getDept_name() %></option>

 <% } %>

</select><br>


<input type = "submit" value = "수정하기">

<input type = "button" id = "btnUpdate" value = "수정하기2">
<input type = "button" id = "btnRetrieve" value = "조회하기">
<input type = "button" id = "btnDelete" value = "삭제하기" mydata = "${emp.employee_id }">

</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){
	$("#btnUpdate").on("click", function(){
		$("#myfrm").submit();
	});
	$("#btnRetrieve").on("click", function(){
		location.href = "emplist";
	});
	$("#btnDelete").on("click", function(){
		alert($(this).attr("mydata"));
		location.href = "empDelete?empid=" + $(this).attr("mydata");
	});
});
</script>
</body>
</html>