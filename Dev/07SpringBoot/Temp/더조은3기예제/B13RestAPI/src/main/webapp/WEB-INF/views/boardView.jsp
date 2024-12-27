<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function(){
	$('#btnBoard').click(function(){
		$.ajax({
			type : 'get',		
			url : './restBoardView.do',
			data : {num : $('#num').val()},
			contentType : "text/html;charset:utf-8",
			dataType : "json",
			success : sucCallBack,
			error : errCallBack
		});
	});
	$('#btnBoard').trigger('click');
});
function sucCallBack(resData){
	let tableData = "";
	$('#td1').html(resData.num);
	$('#td2').html(resData.id);
	$('#td3').html(resData.postdate);
	$('#td4').html(resData.visitcount);
	$('#td5').html(resData.title);
	$('#td6').html(resData.content);	
}
function errCallBack(errData){
	console.log(errData.status+":"+errData.statusText);
}
</script>
</head>
<body>
<div class="container">
	<h2>게시판 API 활용하여 내용 출력하기</h2>
	<table class="table table-bordered">
		<tr>
			<th>번호</th><td id="td1"></td>
			<th>아이디</th><td id="td2"></td>
		</tr>
		<tr>			
			<th>작성일</th><td id="td3"></td>
			<th>조회수</th><td id="td4"></td>
		</tr>		
			<th>제목</th><td colspan="3" id="td5"></td>
		</tr>
		</tr>		
			<th>내용</th><td colspan="3" id="td6"></td>
		</tr>
	</table>
	<div>
		<input type="number" id="num" value="47">
		<input type="button" value="내용불러오기" id="btnBoard" />
	</div>
</div>
</body>
</html>