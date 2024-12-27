<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/5.3.2/css/bootstrap.css" />
<script src="/webjars/bootstrap/5.3.2/js/bootstrap.bundle.js"></script>
<script src="/webjars/jquery/3.7.1/jquery.js"></script>
</head>
<body>
<div class="container">
	<h2>Spring boot Project 세팅하기</h2>
	<ul>
		<li><a href="/">루트</a></li>
		<li><a href="/json.do">simple-json 라이브러리 사용하기</a></li>
		<li><a href="/jsonQuiz.do">복잡한 JSON 만들어보기</a></li>
	</ul>	
	
	<h2>Webjars - 부트스트랩</h2>
	<button type="button" class="btn">Basic</button>
	<button type="button" class="btn btn-primary">Primary</button>
	<button type="button" class="btn btn-secondary">Secondary</button>
	<button type="button" class="btn btn-success">Success</button>
	<button type="button" class="btn btn-info">Info</button>
	<button type="button" class="btn btn-warning">Warning</button>
	<button type="button" class="btn btn-danger">Danger</button>
	<button type="button" class="btn btn-dark">Dark</button>
	<button type="button" class="btn btn-light">Light</button>
	<button type="button" class="btn btn-link">Link</button>
	
	<h2>Webjars - jQuery</h2>
	<button type="button" id="myBtn" class="btn btn-warning">
		클릭하세요
	</button>
	<script>
	$(function(){
		$('#myBtn').click(function(){
			alert("jQuery 동작하나요?");
		});
	});
	</script>
</div>
</body>
</html>