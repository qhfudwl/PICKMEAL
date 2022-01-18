<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/gamePlay_SJW.css" />
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/gamePlay_SJW.js" defer></script>
</head>
<body>
 <!-- firstGameMsg 조건을 만들어야함. 이거 안되면 diffofdate 가 0인지 아닌지 확인해서 하면 됨. num == 5 eq 5 -->
	<c:choose>
		<c:when test="${not empty firstGameMsg}">
			<div class="firstMsgContent">${firstGameMsg}</div>
		</c:when>
		<c:otherwise>
			<div class="firstMsgContent">하이?!</div>
		</c:otherwise>
	</c:choose>
	<input type="hidden" id="hRadius" value="${radius}">
	<input type="hidden" id="nowLat" value="${nowLat}">
	<input type="hidden" id="nowLng" value="${nowLng}">
	<input type="hidden" id="diffOfDate" value="${diffOfDate}">
	<!-- 얘를 가지고 0이면 암것도 안하고 1이면 화면 띄우기. -->
	
	<div id="gameBtnWrap">
		<ul>
			<li><button class="gameBtn ladderBtn" name="ladder" value="L"></button></li>
			<li><button class="gameBtn cardBtn" name="card" value="C"></button></li>
		</ul>
	</div>
	
	<%-- <input type="hidden" id="nowLat" value="${nowLat}">
	<input type="hidden" id="nowLng" value="${nowLng}"> --%>
	
	<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

	
    <div id="menu_wrap" class="bg_white">
        <div class="option">
            <div>
                <form onsubmit="searchPlaces(); return false;">
                    키워드 : <input type="text" value="${category}" id="keyword" size="15"> 
                    <button type="submit">검색하기</button> 
                </form>
            </div>
        </div>
        <hr>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
    </div>
</div>
<p id="memberPosition"></p>

<div id="gameWrap" name="gameWrap">
	<!-- <ul></ul> -->
		
</div>

<div id="submitWrap">
	<form action="" id="submitForm" method="post">
		<button type="submit" id="submitBtn">확인</button>
		<!-- input hidden -->
	</form>
</div>
</body>
</html>