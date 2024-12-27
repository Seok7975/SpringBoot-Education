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
	<h2>회원리스트</h2>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>패스워드</th>
			<th>이름</th>
			<th>가입일</th>
			<th></th>
		</tr>
		<!-- 컨트롤러에서 Model에 저장한 List의 갯수만큼 반복하여
		회원목록을 출력한다. varStatus은 실제 반복에 관련된 여러가지
		정보를 반환하므로 가상번호등을 출력할때 사용할 수 있다. -->
		<c:forEach items="${memberList }" var="row" varStatus="loop">
		<tr>
			<td>${row.id }</td>
			<td>${row.pass }</td>
			<td>${row.name }</td>
			<td>${row.regidate }</td>
			<td>
				<a href="edit.do?id=${row.id }">수정</a>
				<a href="delete.do?id=${row.id }">삭제</a>
			</td>
		</tr>		
		</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
</body>
</html>