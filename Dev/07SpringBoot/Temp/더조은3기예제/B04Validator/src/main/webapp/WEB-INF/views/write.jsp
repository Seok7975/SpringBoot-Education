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
	//매개변수 f는 <form>의 DOM을 전달받는다. 
	function sending(f, gubun){
		//gubun에 의해 action속성을 변경한다. 
		if(gubun==1)
			f.action="./writeAction1.do"			
		else
			f.action="./writeAction2.do"
		//여기서 폼값이 전송된다. 
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