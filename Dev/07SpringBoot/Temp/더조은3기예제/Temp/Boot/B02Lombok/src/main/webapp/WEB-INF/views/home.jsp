<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home화면</title>
</head>
<body>
	<h2>Spring boot 프로젝트(JSP설정)</h2>
	<ul>
		<li><a href="/">최상위루트</a></li>
	</ul>
	<p>
		아이디 : ${dto.id } <br>
		비밀번호 : ${dto.pass } <br>
		이름 : ${dto.name } <br>
		가입일 : ${dto.regidate }
	</p>
</body>
</html>