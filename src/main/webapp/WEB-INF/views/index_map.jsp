<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<style>
#map, #direction, #restaurantInfo {
	width: 650px; height: 500px;
	box-shadow: 0 0 5px #ffecec; border-radius: 10px;
}
#sInfoW, #eInfoW {
	font-family: 'DungGeunMo'; font-size: 14px; position: relative; bottom:50px;
	padding: 8px; border-radius: 10px;
}
#sInfoW {background-color: #ffecec; border: 1px solid #f23f3f;}
#eInfoW {background-color: #d4ffe7; border: 1px solid #2ecc71;}
#findUrl, #restaurantUrl {
	width: 100%; height: 100%;
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
		<button name="radius" value="900">900m</button>
		<button id="gameDone">게임끝</button>
	</div>
	<p id="memberPosition"></p>
	<div id="map"></div>
	<%--<div id="direction"><iframe id="findUrl"></iframe></div> --%>
	<div id="restaurantInfo"><iframe id="restaurantUrl"></iframe></div>
</section>
</body>
</html>