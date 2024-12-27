<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Boot Project</title>
</head>
<body>
	<h2>스프링 부트 프로젝트</h2>
	<ul>
		<li><a href="/">루트</a></li>		
	</ul>
	
	<img src="/images/SpringBoot.png" alt="" />
	
	<!-- Spring 프레임워크에서는 HTML, CSS, JS, 이미지와 같은
	정적파일을 static 디렉토리 하위에 저장해야한다. 정적파일이란 JSP와
	같이 동적으로 변할수없는 파일을 말한다.  --> 
	<h2>정적 파일</h2>
	<ul>
		<li><a href="./index.html">index.html</a></li>
		<li><a href="./sub/sub.html">sub.html</a></li>		
	</ul>
		
	<h2>View 매핑</h2>
	<ul>		
		<li><a href="./index.do">index.do</a></li>
		<li><a href="./sub.do">sub.do</a></li>
	</ul>






	<!-- 스프링부트에서 폼값을 전송할 수 있는 4가지 방법을 알아보기
	위해 아래와같이 요청명을 먼저 결정한다. 쿼리스트링을 통해 파라미터를
	전달하므로 get방식의 요청이된다.  -->
	<h2>폼값전송</h2>
	<ul>		
		<li><a href="form1.do?name=손오공&age=11">내장객체</a></li>
		<li><a href="form2.do?name=저팔계&age=22">어노테이션</a></li>
		<li><a href="form3.do?name=사오정&age=33">커맨드객체</a></li>
		<li><a href="form4/삼장법사/44">경로변수</a></li>
	</ul>
	
	<h2>퀴즈</h2>
	<ul>
		<li><a href="memberRegist.do">회원가입</a></li>
		<li><a href="memberLogin.do">회원로그인</a></li>
	</ul>
</body>
</html>



