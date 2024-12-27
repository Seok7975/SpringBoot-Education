<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script>
$(document).ready(function() {
	$('#contents').summernote({
		placeholder: '내용을 입력하세요',
        tabsize: 2,
        height: 200
	});	
});
</script>
<script>
function validateForm(f){
	if(f.subject.value==''){
		alert('제목을 입력하세요');
		f.subject.focus();
		return false;
	}
	/* 동작하지 않음. 
	if(f.contents.value==''){
		alert('내용을 입력하세요');
		f.contents.focus();
		return false;
	}
	*/
}
</script>
</head>
<body>
	<h2>SummerNote</h2>
	<form method="post" onsubmit="return validateForm(this);">
	<span style="color:red;">${submit }</span>
	<table border="1" style="width:700px;">
	<colgroup>
		<col width="100px" />
		<col width="*" />
	</colgroup>
		<tr>
			<td>제목</td>
			<td><input type="text" name="subject" style="width:400px;"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="contents" id="contents" rows="10" cols="70"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송하기" />
			</td>
		</tr>
	</table>
	</form>
</body>

</html>