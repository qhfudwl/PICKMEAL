<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<!--  그래프 자료 - 윤효심-->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<!--  가게 부가 정보 - 윤효심-->
<link
	href="${pageContext.request.contextPath}/resources/css/incl/chart.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/resources/js/incl/chart.js"
	defer></script>
<!--  포춘쿠키 - 윤효심-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.2/TweenMax.min.js"></script>
<!-- 김보령  -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/index_map.js" defer></script>
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/index_map_b.css" />



<title>밥찡코</title>


</head>
<body>
	<%@ include file="/WEB-INF/views/incl/header.jsp"%>
	<!-- 김보령 -->
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
		<!-- 식당차트정보 시작 - 윤효심 -->
		<input type="hidden" name="restaurantId" value="${restaurantId }"
		id="restaurantId">
		<div class="storeSubInfoWrap">
			<div class="ageAndGenderGraphArea">
				<h3>연령별 / 성별 방문수</h3>
				<div class="storeSubInfo_ageWrap storeSubInfoCom">
					<div id="ageChart" class="mypieChart myChartCom"></div>
				</div>
				<div class="storeSubInfo_genderWrap storeSubInfoCom">
					<div id="genderChart" class="mydiscreteChart myChartCom"></div>
				</div>
			</div>
			<div class="userReviewGraphArea">
				<h3>방문자키워드리뷰</h3>
				<div class="userReviewGraph">
					<ul>

					</ul>
					<div class="openReviewGraphWrap">
						<a href="#" class="openBtn"><img
							src="/pickmeal/resources/img/restaurant/review/down-arrow.png"
							alt="내리기버튼"></a>
					</div>
					<div class="closeReviewGraphWrap">
						<a href="#" class="closeBtn">접기</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 식당차트정보 끝 - 윤효심 -->
		
	</section>
	
	<section id="chatWrap">
	
	</section>
</section>
	
	
	

	
	
	
	
	
	
	
	<!-- 포춘쿠키 -->
			<c:if test="${not empty fortuneMessage }">
			<div class="fortune">
				<div class="fortune-left"></div>
				<div class="fortune-right"></div>
				<div class="fortune-message">
					<span>${fortuneMessage }</span>
				</div>
			</div>
			</c:if>

</body>
</html>