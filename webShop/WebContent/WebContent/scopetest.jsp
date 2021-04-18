<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
ServletContext : ${scopeMessage1}<br>
Session : ${scopeMessage2}<br>
Request : ${scopeMessage3}<br> <!-- request를 가져오지 않고 바로 열어버림 -> 실행 시 값이 없음! 서버를 갔다 오는 것은 servlet! jsp는 단지 open만!-->

<h1>나의 이름은 ${myname }</h1>
<h1>나의 이름은 ${applicationScope.myname }</h1>
<h1>나의 이름은 ${sessionScope.myname }</h1>
<h1>나의 이름은 ${requestScope.myname }</h1>
</body>
</html>