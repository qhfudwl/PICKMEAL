<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<!--  그래프 자료 -->
<link href="${pageContext.request.contextPath}/resources/lib/graph/nv.d3.css" rel="stylesheet" type="text/css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.17/d3.min.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/resources/lib/graph/nv.d3.js"></script>
<script src="${pageContext.request.contextPath}/resources/lib/graph/stream_layers.js"></script>
<!--  가게 부가 정보 -->
<link href="${pageContext.request.contextPath}/resources/css/incl/chart.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/js/incl/chart.js" defer></script>

<title>밥찡코</title>
</head>
<body>
<%@ include file="/WEB-INF/views/incl/header.jsp" %>
<section id = "indexContent">
	<div class="storeSubInfoWrap">
		<h3>연령별 / 성별 방문수</h3>
		<div class="storeSubInfo_genderWrap storeSubInfoCom">
			<svg id="ageChart" class="mydiscreteChart"></svg>
		</div>
		<div class="storeSubInfo_ageWrap storeSubInfoCom">
			<svg id="genderChart" class="mypieChart"></svg>
		</div>
		
		
	</div>
</section>
</body>
</html>