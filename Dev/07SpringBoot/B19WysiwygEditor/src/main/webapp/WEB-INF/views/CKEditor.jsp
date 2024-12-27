<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/43.0.0/ckeditor5.css" />
<style>
.ck-editor__editable { height: 200px; }
.ck-editor__editable p { margin: 0px; }
.ck-content { font-size: 13px; }
.contentsArea p {margin:0}
</style>
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
	<h2>CK에디터</h2>
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
<script type="importmap">
    {
        "imports": {
            "ckeditor5": "https://cdn.ckeditor.com/ckeditor5/43.0.0/ckeditor5.js",
            "ckeditor5/": "https://cdn.ckeditor.com/ckeditor5/43.0.0/"
        }
    }
</script>
<script type="module">
    import {
        ClassicEditor,
        Essentials,
        Bold,
        Italic,
        Font,
        Paragraph
    } from 'ckeditor5';

    ClassicEditor
        .create( document.querySelector( '#contents' ), {
            plugins: [ Essentials, Bold, Italic, Font, Paragraph ],
            toolbar: {
                items: [
                    'undo', 'redo', '|', 'bold', 'italic', '|',
                    'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor',
					'|', 'link', 'uploadImage', 'blockQuote', 'codeBlock'
                ]
            }
        })
        .then((r)=>{
			console.log('r', r);
		})
        .catch((e)=>{
			console.log('e', e);
		});
</script>	
</html>


