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
		frm.id.value = user_id;
		frm.action = "delete.do";
		frm.method = "post";
		frm.submit();
	}
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
//jQuery의 엔트리 포인트 
$(function(){
		
});
function ajaxDelete(delete_id){
	console.log("삭제id", delete_id);
	if(confirm('삭제할까요?')){
		$.ajax({
			url : 'delete.do',
	        type : 'post',
	        data : {'id' : delete_id},
	        dataType : 'json',
	        success : function(rData){
	            console.log(rData);
	            if(rData.result=='success'){
		            alert('삭제되었습니다.');
		            location.reload();	            	
	            }
	            else{
	            	alert('삭제실패');
	            }
	        },
	        error : function(eData){
	            console.error(eData);
	        }
		});
	}
}
</script>
<body>
	<!-- 회원정보 삭제를 post방식으로 처리하기 위한 form태그 추가 -->
	<form name="frm">
		<!-- 삭제를 위한 아이디를 Javascript를 통해 값을 입력할 예정 -->
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
		<!--  
		컨트롤러에서 Model에 저장한 List<MemberDTO>를 갯수만큼 반복해서 출력한다.
		저장한 속성명은 memberList로 지정하였다. 
		-->
		<c:forEach items="${memberList }" var="row" varStatus="loop">
		<tr>
			<td>${row.id }</td>
			<td>${row.pass }</td>
			<td>${row.name }</td>
			<td>${row.regidate }</td>
			<td>
				<a href="edit.do?id=${row.id }">수정</a>
				<%-- <a href="delete.do?id=${row.id }">삭제</a> --%>
				<%-- <a href="javascript:deletePost('${row.id }');">삭제</a> --%>
				<a href="javascript:ajaxDelete('${row.id }');">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
</body>
</html>