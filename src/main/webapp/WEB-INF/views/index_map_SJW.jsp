<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<style>
#map {
	width: 500px; height: 500px;
	border: 5px solid #ffecec;
}
</style>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/index_map_SJW.js" defer></script>

</head>˙
<body>
<section id="mapContent">
	<h2>사용자 위치 표시</h2>
	<form id="gameDataForm" name="gameDataForm" method="GET">
	
		<c:choose>
			<c:when test="${not empty cntForRetry}">
				<input type="hidden" id="cntForRetry" name="cntForRetry" value="${cntForRetry}">	
				<span id="retryMsg" name="retryMsg">${retryMsg}</span>
			</c:when>
		</c:choose>
	
	 <!-- action="viewOrderRecordByMenu" id="periodForm" method="GET" -->
		<div id="mapRadius">
			<input type="radio" class="radius" name="radius" value="300">300m
			<input type="radio" class="radius" name="radius" value="600">600m
			<input type="radio" class="radius" name="radius" value="1000">1000m
			 
		</div>
		<div id="resCategory">
			<input type="radio" class="category" name="category" value="혼밥">혼밥
			<input type="radio" class="category" name="category" value="카페">카페
			<input type="radio" class="category" name="category" value="술집">술집
			<input type="radio" class="category" name="category" value="밥집">밥집
			
			<input type="submit" class="gamePlayBtn" value="게임하기">
			
		</div>
	</form>
		
	
	<p id="memberPosition"></p>
	<div id="map"></div>
</section>
<section id="restaurantWrap">
		<h3 class="hidden">식당 정보 표시</h3>
		<div id="restaurantInfo"><iframe id="restaurantUrl"></iframe></div>
	</section>
</body>
</html>
