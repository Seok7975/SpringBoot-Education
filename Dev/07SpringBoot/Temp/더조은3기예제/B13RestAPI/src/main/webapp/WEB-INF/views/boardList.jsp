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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
$(function(){
	//버튼을 클릭하면 ajax함수를 호출한다. 
	$('#btnBoard').click(function(){
		/* 버튼을 클릭하면 새로운 데이터를 요청하는 것이므로 로딩 
		이미지가 화면에 출력되어야 한다. */
		$('#loading').show();		
		setTimeout(()=>{
			$.ajax({
				//전송방식
				type : 'get',
				//요청URL(요청명)
				url : './restBoardList.do',
				//파라미터(페이지번호:<select>에 지정되어 있음)
				data : { pageNum : $('#pageNum').val() },
				//컨텐츠타입 
				contentType : "text/html;charset:utf-8",
				//콜백데이터 형식 
				dataType : "json",
				//성공/실패시 호출할 콜백함수 
				success : sucCallBack,
				error : errCallBack 
			});
	    }, 1000);
	});
	//HTML페이지가 로드되었을때 자동으로 이벤트 실행 
	$('#btnBoard').trigger('click');
});
//요청에 성공했을때 호출되는 콜백함수 정의 
function sucCallBack(resData){
	//매개변수 resData로 JSON 배열이 전달된다. 
	let tableData = "";
	//배열의 크기만큼 반복한다. 
	$(resData).each(function(index, data){
		//파싱한 데이터는 <tr>태그로 누적해서 저장한다. 
		tableData += ""
		+"<tr>"
		+"    <td>"+data.num+"</td>"
		+"    <td>"+data.title+"</td>"             
		+"    <td>"+data.id+"</td>"
		+"    <td>"+data.postdate+"</td>"
		+"    <td>"+data.visitcount+"</td>"
		+"</tr>";
	});
	//누적된 <tr>데이터를 테이블에 삽입한다. 
	$('#show_data').html(tableData);
	//모든 데이터가 출력되면 로딩 이미지를 숨김처리한다.
	$('#loading').hide();
}
//요청에 실패했을때 호출되는 콜백함수 
function errCallBack(errData){
	console.log(errData.status+":"+errData.statusText);
}
</script>
</head>
<body>
<div class="container">
	<h2>게시판 API 활용하여 목록 출력하기</h2>
	<div id="loading">
		<img src="./loading.gif" />
	</div>
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