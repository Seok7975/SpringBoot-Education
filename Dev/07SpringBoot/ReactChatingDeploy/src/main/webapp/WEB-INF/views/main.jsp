<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main화면</title>
</head>
<body>
	<h2>JPA로 게시판 제작</h2>
	<ul>
		<li><a href="/">최상위루트</a></li>
		<li>
			<a href="javascript:chat('병원아이디','사용자아이디');">JSP에서 채팅창 띄우기</a>
		</li>
	</ul>
</body>
<script>
function chat(h_id, u_id){
	window.open('/chat/index.html#/chat/talk?roomId='+h_id+'&userId='+u_id, 'hosp1-user1', 'width=300,height=500');
}
</script>
</html>