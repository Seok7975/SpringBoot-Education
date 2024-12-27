<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
let deletePost = function(user_id){
	let frm = document.frm;
	if(confirm('정말 삭제할까요?')){
		//인수로 전달된 아이디를 hidden박스에 추가 
		frm.id.value = user_id;
		//action과 method속성값 추가
		frm.action = "delete.do";
		frm.method = "post";
		//여기서 전송(submit)
		frm.submit();
	}
}
</script>
<body>
<!-- 퀴즈] 현재 삭제 버튼을 누르는 경우 즉시 삭제된다. Javascript를 통해 
	confirm으로 물어본 후 '확인'을 누를때만 삭제될 수 있도록 구현하시오.
	단, 전송방식은 post로 처리한다. -->
	<form name="frm">
		<input type="hid-den" name="id" />
	</form>
	<h2>회원리스트(Mybatis)</h2>
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
				<%-- <a href="delete.do?id=${row.id }">삭제</a> --%>
				<a href="javascript:deletePost('${row.id }');">삭제</a>
			</td>
		</tr>		
		</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
</body>
</html>