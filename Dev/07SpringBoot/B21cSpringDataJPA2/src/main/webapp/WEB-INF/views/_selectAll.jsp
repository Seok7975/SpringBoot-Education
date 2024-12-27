<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Spring boot 프로젝트</h2>
	<ul>
		<li><a href="/">루트</a></li>
	</ul>
	
	<h2>Spring Data JPA - Select All</h2>
	<c:forEach items="${members}" var="row">
	<p>		
		아이디 : ${row.id}, 
		이름 : ${row.name}, 
		이메일 : ${row.email}	
	</p>
	</c:forEach>
</body>
</html>

