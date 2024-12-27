<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>티켓 구매하기</h2>	
	<form action="buyTicket.do" method="post">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="userid" value="" /></td>			
		</tr>
		<tr>
			<th>수량</th>
			<td>
				<select name="t_count">
				<c:forEach begin="1" end="10" step="1" var="num">
					<option value="${num }">${num }</option>
				</c:forEach>
				</select>
			</td>			
		</tr>
		<tr>
			<th>에러발생</th>
			<td>
				<input type="checkbox" name="err_flag" value="1" />
				체크하면 예외가 발생합니다. 
			</td>			
		</tr>
	</table>
	<input type="submit" value="전송하기" />
	</form>
</body>
</html> 