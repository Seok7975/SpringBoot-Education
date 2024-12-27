<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Spring boot Project(with Lombok)</h2>
	<ul>
		<li><a href="/">Home</a></li>
	</ul>
	<h3>컨트롤러에서 저장한 DTO객체 출력하기</h3>
	<p>
		이름 : ${dto.name }<br>
		아이디 : ${dto.id }<br>
		비밀번호 : ${dto.pass }<br>
		등록일 : ${dto.regidate }	
	</p>
</body>
</html>


