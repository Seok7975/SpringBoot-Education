<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>폼값전송3 : 커맨드객체</h2>
	<p>
		이름 : ${personDTO.name} <br>
		나이 : ${personDTO.age}
	</p> 
	<!-- 요청명이 /form3.do 이므로 최상위경로에서 실행되는 형태이다.
	따라서 아래와 같이 상대경로를 설정할 수 있다.  -->
	<img src="./images/SpringBoot.png" />
</body>
</html>








