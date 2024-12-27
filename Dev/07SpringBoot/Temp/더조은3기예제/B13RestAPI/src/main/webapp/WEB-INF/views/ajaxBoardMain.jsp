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
	$.ajax({
		type : 'get',  
		contentType : "text/html;charset:utf-8", 
		dataType : "html",
		url : './innerListHTML.do', 
		data : {},
		success : function(resData){
			console.log('resData', resData);
			$('#root').html(resData);
		}, 
		error : function(errData){
			console.log(errData.status+":"+errData.statusText);
		}  
	}); 
});
function view(bNum){
	console.log('bNum', bNum);
	$.ajax({
		type : 'get',  
		contentType : "text/html;charset:utf-8", 
		dataType : "html",
		url : './innerViewHTML.do', 
		data : {num : bNum},
		success : function(resData){
			console.log('resData', resData);
			$('#root').html(resData);
		}, 
		error : function(errData){
			console.log(errData.status+":"+errData.statusText);
		}  
	}); 
}
</script>
</head> 
<body>
<div class="container">	
	<div id="root">
		게시판은 여기에 출력됩니다.
	</div>
</div>
</body>
</html>