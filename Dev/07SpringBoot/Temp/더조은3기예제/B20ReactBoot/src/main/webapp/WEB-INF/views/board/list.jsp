<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis게시판</title>
</head>
<body>
	<%@ include file="/link.jsp" %>
	<h2>게시판 목록(Mybatis)</h2>

    <!-- 목록 테이블 -->
    <table border="1" width="90%">
        <tr>
            <th width="10%">번호</th>
            <th width="*">제목</th>
            <th width="15%">작성자</th>
            <th width="10%">조회수</th>
            <th width="15%">작성일</th>
        </tr>
<c:choose>
    <c:when test="${ empty boardList }"> 
        <tr>
            <td colspan="5" align="center">
                등록된 게시물이 없습니다^^*
            </td>
        </tr>
    </c:when> 
    <c:otherwise> 
        <c:forEach items="${ boardList }" var="row" varStatus="loop">    
        <tr align="center">
            <td> 
            <!-- 게시물의갯수, 페이지번호, 페이지사이즈를 통해 가상번호를 계산해서
            출력한다. -->
            ${ maps.totalCount - 
                (((maps.pageNum-1) * maps.pageSize)	+ loop.index)}
            </td>
            <td align="left"> 
                <a href="./boardView.do?num=${ row.num }">${ row.title }</a> 
            </td> 
            <td>${ row.id }</td> 
            <td>${ row.visitcount }</td> 
            <td>${ row.postdate }</td> 
        </tr>
        </c:forEach>        
    </c:otherwise>    
</c:choose>
    </table>
    
    <!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table border="1" width="90%">
        <tr align="center">
            <td>
                ${ pagingImg }
            </td>
            <td width="100"><button type="button"
                onclick="location.href='./boardWrite.do';">글쓰기</button></td>
        </tr>
    </table>
</body>
</html>


