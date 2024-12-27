<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main화면</title>
</head>
<body>
	<h2>FCM(Firebase Cloud Messaging)</h2>
	<ul>
		<li><a href="/">최상위루트</a></li>
	</ul>
	
	<style>
	input{width:500px;}
	textarea{width:500px;height:50px;}
	img{max-width:500px;}
	</style>
	
	<h2>푸시 보내기</h2>	
	<form action="FCMSender.do" method="post">
		<textarea name="token" placeholder="토큰">cxST1pmXTCmXnGkTuA7uRK:APA91bFdvhVScEhSgLb-MAO8tNZkQGV7bvJwt0IrH39sn8eNVN1qfmJZ4fJjQhF_dEI3_h5iB8ulphIJJkpHtXUr7zT5xFu_-hWZYiBAcD2awm7u3g9clEt76ZuSyzB64TZ57NtEo1rw</textarea> <br />
		<!-- <textarea name="token" placeholder="토큰">fMGweHclTJyFhVbd-_iEhS:APA91bEDofUvEXzdHcms8nsh9mUcrkGCZCNK2qX6aZR9mtAcSL_m2LcDoBTmE0mKwxSG87-sz4J1jY_C3ZhG-xYGFgdzEtyeGnz6PwwRGb4BU1nclDCNDmMgTBnsUb_oFBdMYstdWjcy</textarea> <br /> -->
		<input type="text" name="title" placeholder="타이틀" /> <br />
		<textarea name="body" placeholder="알림메세지" ></textarea> <br />
		<input type="text" name="image" placeholder="이미지경로" value="https://contents.kyobobook.co.kr/sih/fit-in/200x0/pdt/9791191905311.jpg" /> <br />
		<input type="text" name="add_data" placeholder="추가데이터" /> <br />
		<input type="submit" value="보내기" />
	</form>
	<h2>전송결과</h2>
	<div>
		토큰 : ${messageDTO.token } <br />
		타이틀 : ${messageDTO.title } <br />
		이미지 : <img src='${messageDTO.image }' /> <br />
		메세지 : ${messageDTO.body } <br />
		추가데이터 : ${messageDTO.add_data } <br />
		전송결과 : ${result } 
	</div>
</body>
</html>


