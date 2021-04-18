<%@page import="model.ManagerVO"%>
<%@page import="model.LocationVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	form{border : 5px dotted green; }
</style>
</head>
<body>
<h1>부서 신규 등록</h1>
<form action = "deptInsert" method = "post">
부서번호: <input type = "number"  name = "Dept_id"><br>
부서이름: <input type = "text"  name = "Dept_name"><br>
매니저ID: 
<select name = "manager_id">
<% 
 List<ManagerVO> mlist = (List<ManagerVO>)request.getAttribute("mlist");
for(ManagerVO m:mlist){
%>
	<option value ="<%=m.getManager_id()%>"><%=m.getFullname() %></option>
<% } %>

</select>

LocationID: 
<select name = "location_id">
<% 
 List<LocationVO> loclist = (List<LocationVO>)request.getAttribute("loclist");
for(LocationVO loc:loclist){
%>
	<option value ="<%=loc.getLocation_id()%>"><%=loc.getCity() %></option>
<% } %>

</select>




<input type = "submit" value = "입력하기">

</form>
</body>
</html>