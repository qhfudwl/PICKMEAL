<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<!--  그래프 자료 -->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<!--  가게 부가 정보 -->
<link
	href="${pageContext.request.contextPath}/resources/css/incl/chart.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath}/resources/js/incl/chart.js"
	defer></script>
<!--  포춘쿠키 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.2/TweenMax.min.js"></script>

<title>밥찡코</title>


</head>
<body>
	<%@ include file="/WEB-INF/views/incl/header.jsp"%>
	<input type="hidden" name="restaurantId" value="${restaurantId }"
		id="restaurantId">
	<section id="indexContent">
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