<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>home화면</title>
</head>
<body>
	<h2>Spring boot 프로젝트(JSP설정)</h2>
	<ul>
		<li><a href="/">최상위루트</a></li>
		<li><a href="/crud/index.html">React CRUD</a></li>		
		<li><a href="./boardList.do">게시판</a></li>	
		<li><a href="./react/index.html">React Board</a></li>	
	</ul>
	<h2>Rest API</h2>
	<ul>
		<li><a href="./restBoardList.do?pageNum=1" target="_blank">목록</a></li>
		<li><a href="./restBoardSearch.do?searchField=title&searchWord=봄 여름 가을" 
				target="_blank">검색</a></li>
		<li><a href="./restBoardView.do?num=5" target="_blank">보기</a></li>
		<li>
			<fieldset>
				<legend>작성하기</legend>
				<form method="post" action="restBoardWrite.do">
					아이디:<input type="text" name="id" value="musthave" /><br />
					제목:<input type="text" name="title" /><br />
					내용: <textarea name="content" cols="30" rows="10"></textarea><br />
					<input type="submit" value="요청하기" />
				</form>
			</fieldset>
		</li>
	</ul>
</body>
</html>