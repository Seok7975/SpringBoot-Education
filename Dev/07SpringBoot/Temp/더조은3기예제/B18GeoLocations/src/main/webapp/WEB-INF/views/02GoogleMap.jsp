<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02GoogleMap.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
/* 맵의 크기 설정 */
#map{
	width:800px; height:600px;
}
</style>
 
<script>
//구글맵 초기화 및 마커설정 
function initMap() {
	//맵의 중심 위치 설정 
	var uluru = {lat:37.5652352, lng:126.9858304};
	//구글맵 초기화(맵 출력을 위한 DOM과 줌레벨 설정) 
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 17,
		center: uluru
	});
	//마커설정 
	var marker = new google.maps.Marker({
		position: uluru,
		map: map
	});
}
window.onload = function(){
	initMap();
}
</script>
</head>
<body>
<div class="container">
	<h2>Google Map 띄워보기</h2>
	<div id="map"></div>
	<!-- 구글맵은 비동기 형식으로 로드된다. -->
	<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=${apiKey }"></script>
</div>
</body>
</html>