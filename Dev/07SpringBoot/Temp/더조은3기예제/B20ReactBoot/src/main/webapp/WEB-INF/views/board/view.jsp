<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/link.jsp" %>
	<h2>게시판 읽기(Mybatis)</h2>	
	<form name="writeFrm">
		<input type="hid-den" name="num" value="${boardDTO.num }" />
	</form>
	<table border="1" width="90%">
	    <colgroup>
	        <col width="15%"/> <col width="35%"/>
	        <col width="15%"/> <col width="*"/>
	    </colgroup>	
	    <!-- 게시글 정보 -->
	    <tr>
	        <td>번호</td> <td>${ boardDTO.num }</td>
	        <td>작성자</td> <td>${ boardDTO.id }</td>
	    </tr>
	    <tr>
	        <td>작성일</td> <td>${ boardDTO.postdate }</td>
	        <td>조회수</td> <td>${ boardDTO.visitcount }</td>
	    </tr>
	    <tr>
	        <td>제목</td>
	        <td colspan="3">${ boardDTO.title }</td>
	    </tr>
	    <tr>
	        <td>내용</td>
	        <td colspan="3" height="100">
	        	${ boardDTO.content }	        	
	        </td>
	    </tr>
	    <!-- 하단 메뉴(버튼) -->
	    <tr>
	        <td colspan="4" align="center">	            
	            <button type="button" onclick="location.href='./boardList.do';">
	                목록 바로가기
	            </button>
	        </td>
	    </tr>
	</table>
</body>
</html>