<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인한 결과 출력하기 : ${username }</h1> <!-- request.getAttribute("username", " ");와 같은 문장! -->
<%-- <h2>${emp}</h2> --%>
<p>${param.userid}</p> <!-- String uid = request.getParameter("userid");와 같은 문장꼴! -->
<p>${param.userpw}</p>
<p>${param.address}</p>
<p>${param.phone}</p>
</body>
</html>