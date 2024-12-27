<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 API</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function() {
	//검색어를 입력한 후 ...
	$('#keyword').keydown(function(e) {
		if(e.keyCode==13){
			//엔터키를 누르면 함수를 호출한다.
			runAjax();
			e.preventDefault();
		}
		else{
			//엔터키가 아니라면 콘솔에 키코드를 출력한다.
			console.log(e.keyCode);
		}
	});	
	//검색어 입력후 '검색요청' 버튼을 누르면 함수를 호출한다.
	$('#searchBtn').click(function() {
		runAjax();
	});
});
function runAjax(){
	//비동기로 요청한다. 
	$.ajax({
		//요청URL
		url : "./NaverSearchRequest.do",
		//요청방식은 get
		type : "get", 
		//서버로 전송할 파라미터 
		data : {
			//검색어 
		    keyword : $('#keyword').val(),
		    //시작번호(페이지번호)
		    startNum : $('#startNum').val()
		},
		//콜백데이터의 타입은 JSON형식 
		dataType : "json",
		//성공/실패시 호출되는 콜백함수 정의 
		success : sucFuncJson,
		error : errFunc
	});
}
//요청에 성공한 경우 호출되는 콜백함수 
function sucFuncJson(d) {
	//Naver에서 콜백해준 데이터를 출력 
	console.log("결과", d);
    var str = "";
    //검색결과 배열의 크기만큼 반복하여 파싱한다. 
    $.each(d.items, function(index, item) {
        str += "<ul>";
        str += "    <li>" + (index + 1) + "</li>";
        str += "    <li>" + item.title + "</li>";
        str += "    <li>" + item.description + "</li>";
        str += "    <li>" + item.bloggername + "</li>";
        str += "    <li>" + item.bloggerlink + "</li>";
        str += "    <li>" + item.postdate + "</li>";
        str += "    <li><a href='" + item.link + "' target='_blank'>바로가기</a></li>";
        str += "</ul>";
    });
    //파싱된 결과를 html함수로 페이지에 삽입한다. 
    $('#searchResult').html(str);
}
// 실패 시 경고창을 띄워줍니다.
function errFunc(e) {
    alert("실패: " + e.status);
}
</script>

<style>
    ul{border:2px #cccccc solid;}
</style>
</head>
<body>
<div>
    <div>
        <form id="searchFrm">
            한 페이지에 10개씩 출력됨 <br />
            
            <!-- <select id="startNum">
                <option value="1">1페이지</option>
                <option value="11">2페이지</option>
                <option value="21">3페이지</option>
                <option value="31">4페이지</option>
                <option value="41">5페이지</option>
            </select> -->
            <!-- 
            파라미터1 display : 검색결과갯수. 디폴트값이 10
            이므로 그대로 사용한다. 
            파라미터2 sort : 디폴트값을 그대로 사용한다.
            -->
            <!-- 파라미터3 start : 검색 시작위치 -->
            <input type="number" id="startNum" step="10" 
            			value="1">
            <!-- 파라미터4 query : 검색어 -->
            <input type="text" id="keyword" placeholder="검색어를 입력하세요." />
            <button type="button" id="searchBtn">검색 요청</button>
        </form>
    </div>
    <div class="row" id="searchResult">
        여기에 검색 결과가 출력됩니다.
    </div>
</div>
</body>
</html>
