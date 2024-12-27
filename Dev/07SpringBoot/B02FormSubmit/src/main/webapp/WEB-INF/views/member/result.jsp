<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과1</title>
</head>
<body>
	<h2>퀴즈] 회원가입폼에서 전송된 값</h2>
	<ul>
		<li>아이디 : ${quizVO.id }</li>
		<li>비밀번호 : ${quizVO.pass1 }</li>
		<li>이름 : ${quizVO.name }</li>
		<li>성별 : ${quizVO.sex }</li>
		<li>이메일 : ${quizVO.email1 }@${quizVO.email2 }</li>
		<li>이메일 수신여부 : ${quizVO.mailing }</li>
		<li>우편번호 : ${quizVO.zipcode }</li>
		<li>주소 : ${quizVO.addr1 } ${quizVO.addr2 }</li>
		<li>핸드폰 : ${quizVO.mobile1 }-${quizVO.mobile2 }-${quizVO.mobile3 }</li>
		<li>SMS 수신여부 : ${quizVO.sms }</li>
		<li>관심분야 : ${quizVO.etc_no1 }</li>
		<li>가입경로 : ${quizVO.etc_no2 }</li>
	</ul>
</body>
</html>