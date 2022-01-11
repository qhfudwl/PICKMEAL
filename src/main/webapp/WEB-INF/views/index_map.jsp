<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/index_map.js" defer></script>
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/index_map_b.css" />
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
<h1>${member.nickName}</h1>
<section id="content">
	<h2 class="hidden">게임하기</h2>
	<section id="mapWrap">
		<h3 class="hidden">지도 표시</h3>
		<div id="mapWindow">
			<div id="buttonWrap">
				<button name="radius" class="radius" value="300">300m</button>
				<button name="radius" class="radius" value="600">600m</button>
				<button name="radius" class="radius" value="900">900m</button>
				<button id="gameDone">게임끝</button>
			</div>
			<p id="memberPosition"></p>
			<div id="map"></div>
		</div>
	</section>
	<section id="restaurantWrap">
		<h3 class="hidden">식당 정보 표시</h3>
		<div id="restaurantInfo">
			<div id="restaurantWindow">
				<iframe id="restaurantUrl"></iframe>
				<button id="open" value="open">펼치기</button>
			</div>
		</div>
	</section>
	<section id="chatWrap">
	</section>
</section>
</body>
</html>