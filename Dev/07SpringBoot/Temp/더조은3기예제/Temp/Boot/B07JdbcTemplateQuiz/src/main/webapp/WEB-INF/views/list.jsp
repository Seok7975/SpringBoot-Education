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
let formValidate = function(fm){
	if(fm.searchKeyword.value==''){
		alert('검색어를 입력하세요');
		fm.searchKeyword.focus();
		return false;
	}
}
</script>
<body>
	<!-- 퀴즈] 검색어가 없는 경우 경고창이 뜰수 있도록 Javascript 혹은 jQuery를
				통해 구현하시오. -->
	<h2>회원리스트</h2>
	<!-- 속성값이 없는 경우 action은 현재페이지로, method는 get으로 설정된다. -->
	<form onsubmit="return formValidate(this);">
	<table border="1">
		<tr>
			<td>
				<select name="searchField">
					<option value="id">아이디</option>
					<option value="name">이름</option>
				</select>
				<input type="text" name="searchKeyword" />
				<input type="submit" value="검색" />
			</td>
		</tr>
	</table>
	</form>
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