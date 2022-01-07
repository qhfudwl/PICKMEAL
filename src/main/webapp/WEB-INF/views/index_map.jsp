<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<style>
#map {
	width: 800px; height: 800px;
	border-radius: 50%; border: 5px solid #ffecec;
}
</style>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/index_map.js" defer></script>
</head>
<body>
<section id="mapContent">
	<h2>사용자 위치 표시</h2>
	<div id="mapRadius">
		<button name="radius" value="300">300m</button>
		<button name="radius" value="600">600m</button>
		<button name="radius" value="1000">1000m</button>
	</div>
	<p id="memberPosition"></p>
	<div id="map"></div>
</section>
</body>
</html>