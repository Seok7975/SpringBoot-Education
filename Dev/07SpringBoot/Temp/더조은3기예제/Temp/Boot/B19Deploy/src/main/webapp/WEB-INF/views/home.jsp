<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>스프링 부트 프로젝트</h2>
	<ul>
		<li><a href="/">루트</a></li>
	</ul>

	<h2>목록보기</h2>
	<c:forEach items="${rows}" var="row">
		<p>${row.idx} - ${row.name}</p>
	</c:forEach>
	
</body>
</html>