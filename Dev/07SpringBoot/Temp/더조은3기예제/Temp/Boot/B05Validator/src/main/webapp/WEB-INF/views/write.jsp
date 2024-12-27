<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CreatePage</title>
</head>
<body>
	<script>
	/* 매개변수 gubun을 통해 전송될 요청명을 변경하고 있다. 
	자바스크립트를 이용하면 이와같이 요청명, 전송방식등을 자유롭게
	변경할 수 있다. */
	function sending(f, gubun){
		if(gubun==1)
			f.action="./writeAction1.do"			
		else
			f.action="./writeAction2.do"
		f.submit();
	}
	</script>
	<h2>Validator 인터페이스를 통한 유효성검증</h2>
	<form method="post">
		일련번호 : <input type="number" name="idx" value="1"> 
		<br /> 
		아이디 : <input type="text" name="userid" value="${dto.userid}"> 
		<br />		 
		제목 : <input type="text" name="title" value="${dto.title}"> 
		<br />
		내용 : <input type="text" name="content" value="${dto.content}"> 
		<br />
		<input type="button" value="전송1" onclick="sending(this.form, 1);">
		<input type="button" value="전송2" onclick="sending(this.form, 2);">  
	</form>
</body>
</html>