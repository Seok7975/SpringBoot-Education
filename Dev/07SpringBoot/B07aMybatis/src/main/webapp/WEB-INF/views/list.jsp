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
<!-- jQuery의 CDN 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script> 
function ajaxDelete(delete_id){
	//삭제할 아이디를 콘솔에서 확인 
	console.log("삭제id", delete_id);
	if(confirm('삭제할까요?')){
		//confirm에서 '확인'을 눌러 true가 반환되면 ajax함수 호출
		$.ajax({
			//요청URL(혹은 경로)
			url : 'delete.do',
			//전송방식
	        type : 'post',
	        //서버로 전송할 파라미터(JSON객체 형식으로 제작해야함)
	        data : {'id' : delete_id},
	        //콜백데이터의 형식(text, html, script, xml 등)
	        dataType : 'json',
	        //요청성공시 호출할 콜백 함수 정의 
	        success : function(rData){
	        	//서버에서 작업을 종료한 후 웹브라우저에 출력해주는 내용을 콜백받게됨.
	            console.log(rData);
	        	//삭제에 성공한 경우에는 alert를 띄우고 화면을 새로고침
	            if(rData.result=='success'){
		            alert('삭제되었습니다.');
		            //location.reload();
		            //$('#member_'+delete_id).hide();
		            $('#member_'+delete_id).remove();
	            } else {
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
		<!-- <tr>태그에 중복되지 않는 아이디를 부여한다. -->
		<tr id="member_${row.id }">
			<td>${row.id }</td>
			<td>${row.pass }</td>
			<td>${row.name }</td>
			<td>${row.regidate }</td>
			<td>
				<a href="edit.do?id=${row.id }">수정</a>
				<%-- <a href="delete.do?id=${row.id }">삭제</a> --%>
				<%-- <a href="javascript:;" onclick="deletePost('${row.id }');">삭제</a> --%>
				<a href="javascript:ajaxDelete('${row.id}');">삭제</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<a href="regist.do">회원등록</a>
	
	<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
	
	<a href="#">Top으로바로가기</a>
	&nbsp;&nbsp;
	<a href="javascript:;">javascript접두어쓰기</a>
	&nbsp;&nbsp;
	<a href="deletePost();">잘못된호출방식</a>
</body>
</html>





