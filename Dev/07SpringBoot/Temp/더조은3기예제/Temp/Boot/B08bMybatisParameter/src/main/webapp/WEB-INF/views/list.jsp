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
	<!-- 검색폼추가 -->	
	<!-- 퀴즈] 현재 체크항목에 체크하지 않은 상태에서 검색어를 입력하면 
			에러가 발생한다. 따라서 하나의 항목 이상을 체크한 후 검색할 수 
			있도록 Javascript를 추가하시오. -->
	<script>
	function validateForm(fm){
		//체크된 항목의 갯수 카운트
		let sFieldCnt = 0;
		//체크박스 항목의 갯수만큼 반복
		for(let i=0 ; i<fm.searchField.length ; i++){
			//체크된 항목이 있는지 체크하여 1씩 증가
			if(fm.searchField[i].checked==true)
				sFieldCnt++;
		}
		//체크된 항목이 없다면 경고창을 띄우고 리스너쪽으로 false반환
		if(sFieldCnt==0){
			alert("한개 이상의 항목을 체크하셔야 합니다.");
			return false;
		}
	}
	</script>
	<form onsubmit="return validateForm(this);">
	<table>
	<tr>
		<td>
			<!-- 검색을 위한 필드(컬럼)를 2개 이상 선택하기 위해 체크박스로
			구성한다. 폼값은 배열로 전송된다.  -->
			<input type="checkbox" name="searchField" value="id" />아이디
			<input type="checkbox" name="searchField" value="name" />이름
			<input type="checkbox" name="searchField" value="pass" />패스워드
			<!-- 검색어는 일반적인 문자열로 전송된다.  -->
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