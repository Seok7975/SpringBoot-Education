<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- jQuery, Bootstrap 사용을 위한 CDN 추가 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function(){
	//목록 불러오기 버튼을 누를때 ajax() 함수를 실행 
	$('#btnBoard').click(function(){
		$.ajax({
			type : 'get', //전송방식(form태그의 method)  	
			url : './restBoardList.do', //요청Url 
			data : { pageNum : $('#pageNum').val() }, //파라미터 
			contentType : "text/html;charset:utf-8", //컨텐츠타입
			dataType : "json", //콜백데이터의 타입(형식)
			success : sucCallBack, //성공시 호출할 콜백함수
			error : errCallBack //실패시 호출할 콜백함수 
		});
	});
	/* 이벤트를 자동으로 실행하고 싶을때 trigger() 를 사용한다. 페이지가
	로드되었을때 사용자가 버튼을 클릭한것과 동일한 동작을 수행한다. */
	$('#btnBoard').trigger('click');
});
//성공시 콜백메소드. 
function sucCallBack(resData){
	let tableData = "";
	/* 현재 콜백 데이터는 JSON 배열이므로 each()를 통해 즉시 반복할 수 있다.
	갯수만큼 반복하여 출력할 목록의 <tr>태그를 만든다. */
	$(resData).each(function(index, data){		
		tableData += ""
		+"<tr>"
		+"    <td>"+data.num+"</td>"
		+"    <td>"+data.title+"</td>"             
		+"    <td>"+data.id+"</td>"
		+"    <td>"+data.postdate+"</td>"
		+"    <td>"+data.visitcount+"</td>"
		+"</tr>";
	});
	//앞에서 만든 <tr>태그를 table에 적용한다. 
	$('#show_data').html(tableData);
}
//실패시 호출될 콜백함수 
function errCallBack(errData){
	console.log(errData.status+":"+errData.statusText);
}
</script>
</head>
<body>
<div class="container">
	<h2>게시판 API 활용하여 목록 출력하기</h2>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>제목</th>			
			<th>아이디</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<tbody id="show_data"></tbody>
	</table>
	<div>
		<select id="pageNum">
		<c:forEach begin="1" end="20" var="num">
			<option value="${num}">${num}page</option>
		</c:forEach>
		</select>
		<input type="button" value="목록불러오기" id="btnBoard" />
	</div>
</div>
</body>
</html>