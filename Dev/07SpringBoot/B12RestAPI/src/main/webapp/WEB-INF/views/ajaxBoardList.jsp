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
	//목록 가져오기
	$('#btnBoard').click(function(){
		$.ajax({
			type : 'get',  
			contentType : "text/html;charset:utf-8", 
			dataType : "json",
			url : './restBoardList.do', 
			data : { pageNum : $('#pageNum').val() },
			success : sucCallBack, 
			error : errCallBack 
		});
	});
	//버튼 자동 클릭
	$('#btnBoard').trigger('click');
});
function sucCallBack(resData){
	let tableData = "";
	$(resData).each(function(index, data){		
		tableData += ""
		+"<tr>"
		+"    <td>"+data.num+"</td>"
		+"    <td><a href='ajaxBoardView.do?num="+data.num+"'>"+data.title+"</a></td>"             
		+"    <td>"+data.id+"</td>"
		+"    <td>"+data.postdate+"</td>"
		+"    <td>"+data.visitcount+"</td>"
		+"</tr>";
	});
	$('#show_data').html(tableData);
} 
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