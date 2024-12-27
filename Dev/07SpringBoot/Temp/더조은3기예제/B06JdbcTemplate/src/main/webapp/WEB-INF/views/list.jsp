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
		<c:forEach items="${memberList }" var="row" varStatus="loop">
		<tr>
			<td>${row.id }</td>
			<td>${row.pass }</td>
			<td>${row.name }</td>
			<td>${row.regidate }</td>
			<td>
				<a href="edit.do?id=${row.id }">수정</a>
				<!--  
				<a>태그에서 자바스크립트의 함수를 호출하는 방법
				1.href에 입력하는경우 javascript:을 반드시 붙여야한다.
					링크로 인식하므로 없는경우 404에러가 발생하게된다. 
				2.onclick 이벤트 리스너에 입력하는 경우에는 함수명만 
					기술하면된다. 
				-->
				<a href="javascript:deleteMember('${row.id }');">삭제</a>
			</td>
		</tr>		
		</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
</body>
</html>
<script>
function deleteMember(id){
	if(confirm('삭제할까요?')){
		location.href="delete.do?id="+id;		
	}
}
</script>
