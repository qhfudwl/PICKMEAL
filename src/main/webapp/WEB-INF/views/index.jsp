<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<!--  그래프 자료 - 윤효심-->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<!--  가게 부가 정보 - 윤효심-->
<link href="${pageContext.request.contextPath}/resources/css/incl/chart.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/index_map_b.css" />

<!-- 익명채팅방, 날씨 - 김재익 -->
<link href="${pageContext.request.contextPath}/resources/css/weather/weather.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/chat/chat.css" rel="stylesheet" type="text/css">
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/chart.js" defer></script>

<!--  포춘쿠키 - 윤효심-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/2.0.2/TweenMax.min.js"></script>

<!-- 김보령  -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<script src="${pageContext.request.contextPath}/resources/js/incl/index_map.js" defer></script>



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
	<!-- 날씨영역 - 김재익 -->
			<div id="weatherWrap">
		        <div id="weather">
		            <c:choose>
		        		<c:when test="${weather.sky eq 1 }">
		            		<img src="${pageContext.request.contextPath}/resources/img/weather/icons8_sun.gif"/>
		        		</c:when>
		        		<c:when test="${weather.sky eq 2 }">
		        			<img src="${pageContext.request.contextPath}/resources/img/weather/icons8_cloud_64.png"/>
		        		</c:when>
		        		<c:when test="${weather.sky eq 3 }">
		        			<img src="${pageContext.request.contextPath}/resources/img/weather/icons8_rain.gif"/>
		        		</c:when>
		        		<c:when test="${weather.sky eq 4 }">
		        			<img src="${pageContext.request.contextPath}/resources/img/weather/icons8_snow_64.png"/>
		        		</c:when>
		        	</c:choose>
		            <div class="temperatureWrap"><span class="temperature">${weather.temperature }</span><span class="symbol">&#8451;</span></div>
		        </div>
		        <div id="forecast">
		            <div class="forecastItem">
		                <span>오전 8시</span>
		                <img src="${pageContext.request.contextPath}/resources/img/weather/icons8_sun.gif"/>
		                <div class="temperatureWrap"><span class="temperature">2</span><span class="symbol">&#8451;</span></div>
		            </div>
		            <div class="forecastItem">
		                <span>오후 12시</span>
		                <img src="${pageContext.request.contextPath}/resources/img/weather/icons8_sun.gif"/>
		                <div class="temperatureWrap"><span class="temperature">13</span><span class="symbol">&#8451;</span></div>
		            </div>
		            <div class="forecastItem">
		                <span>오후 18시</span>
		                <img src="${pageContext.request.contextPath}/resources/img/weather/icons8_cloud_64.png"/>
		                <div class="temperatureWrap"><span class="temperature">11</span><span class="symbol">&#8451;</span></div>
		            </div>
		            <div class="forecastItem">
		                <span>오후 22시</span>
		                <img src="${pageContext.request.contextPath}/resources/img/weather/icons8_rain.gif"/>
		                <div class="temperatureWrap"><span class="temperature">3</span><span class="symbol">&#8451;</span></div>
		            </div>
		        </div>
		        <div id="howAboutThis">
		        	<c:choose>
		        		<c:when test="${weather.sky eq 1 }">
		        			맑고
		        		</c:when>
		        		<c:when test="${weather.sky eq 2 }">
		        			흐리고
		        		</c:when>
		        		<c:when test="${weather.sky eq 3 }">
		        			비오고
		        		</c:when>
		        		<c:when test="${weather.sky eq 4 }">
		        			눈오고
		        		</c:when>
		        	</c:choose>
		        	<c:choose>
		        		<c:when test="${weather.temperature lt 10 }">
		        			춥네
		        		</c:when>
		        		<c:when test="${weather.temperature gt 25 }">
		        			덥네
		        		</c:when>
		        		<c:otherwise>
		        			적당하네
		        		</c:otherwise>
		        	</c:choose>
		        	이거 어때?
		        </div>
		        <div id="wetherMenu">
		            <img src="https://img.icons8.com/dusk/64/000000/sun--v2.png"/>
		            <span class="weatherMenuName">음식이름</span>
		        </div>
		    </div>
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
	
	<!-- 채팅영역 - 김재익 -->
	<section id="chatWrap">
		<div id="msgArea">
            <div id="msgWrap">
                
            </div>
        </div>
        <div id="writeArea">
            <textarea name="msg" id="write_msg"></textarea>
            <div id="input-group-append">
                <button type="button" id="button_send">전송</button>
            </div>
        </div>
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

<!-- 익명채팅방 - 김재익 -->
<script type="text/javascript">
$('#write_msg').on("keyup", function(key) {
	if(key.keyCode==13) {
		sendMessage();
		$('#write_msg').val('')
		$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
	}
});

//전송 버튼 누르는 이벤트
$("#button_send").on("click", function(e) {
	sendMessage();
	$('#write_msg').val('')
});

var sock = new SockJS("<c:url value="/chatting"/>");
sock.onmessage = onMessage;
sock.onclose = onClose;
sock.onopen = onOpen;

function sendMessage() {	
	var msg = $("#write_msg").val();
	console.log("msg : " + msg);
	var blank_pattern = /^\s+|\s+$/g;
	if(msg == '' || msg.replace(blank_pattern, '') == "" ) {
		return;
	}
	msg = '익명${AnonymousNumber}:' + msg;
	sock.send(msg);
}

var defaultScrollHeight = $('#msgArea').prop('scrollHeight');
//서버에서 메시지를 받았을 때
function onMessage(msg) {

	var data = msg.data;
	var sessionId = null; //데이터를 보낸 사람
	var message = null;
	
	
	//var arr = data.split(":");
	
	var name = data.substring(0, data.indexOf(":"));
	message = data.substring(data.indexOf(":")+1, data.length);
	
	message = message.replace(/\s+$/,'').replace(/\s/g, "&nbsp;");
	
	var cur_session = '익명${AnonymousNumber}'; //현재 세션에 로그인 한 사람
	console.log("cur_session : " + cur_session);
	
	sessionId = name;
	//sessionId = arr[0];
	//message = arr[1];
	
	//현재시간 ex) 오전 10:50
	var today = new Date();	
	var time = today.nowHour() + ":" + today.nowMinute();
	
	//로그인 한(본인) 클라이언트와 타 클라이언트를 분류하기 위함
	if(sessionId == cur_session){
		var str = "<div class='textBoxArea me'>";
		str += "<span class='name'>" + sessionId + "</span>";
		str += "<div class='textField'>";
		str += "<span class='time'>"+ time +"</span>";
		str += "<div class='textBox me'>";
		str += "<span class='msgBox'>" + message + "</span>";
		str += "</div></div></div>";
		
		$("#msgWrap").append(str);
		$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
	}
	else{
		var scroll = $('#msgArea').prop('scrollHeight') - $('#msgArea').scrollTop(); // defaultScrollHeight이상의 값
		
		var str = "<div class='textBoxArea'>";
		str += "<span class='name'>" + sessionId + "</span>";
		str += "<div class='textField'>";
		str += "<div class='textBox'>";
		str += "<span class='msgBox'>" + message + "</span>";
		str += "</div>";
		str += "<span class='time'>"+ time +"</span>";
		str += "</div></div>";
		
		$("#msgWrap").append(str);
		if(nowScroll(scroll)) {
			$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
		}
	}
}

Date.prototype.nowMinute = function() {
	return this.getMinutes() < 10 ? "0" + this.getMinutes() : this.getMinutes();
};

Date.prototype.nowHour = function() {
	var nh = this.getHours();
	var hour = nh < 12 ? "오전 " : "오후 ";
	
	if(nh == 12 || nh == 0) {
		hour += 12;
	} else if(nh > 12) {
		hour += nh-12;
	} else {
		hour += nh;
	}
	
	return hour;
};

//현재 스크롤위치가 하단 근처라면 true
function nowScroll(scroll) {
	return (scroll < defaultScrollHeight+230)
};

//채팅창에 들어왔을 때
function onOpen(evt) {
	var user = '익명${AnonymousNumber}';
	var str = '채팅창에 입장했습니다 [' + user + ']';
	
	$("#msgWrap").append(str);
}

//채팅창에서 나갔을 때
function onClose(evt) {
	var user = '익명${AnonymousNumber}';
	var str = '퇴장했습니다.';
	
	$("#msgWrap").append(str);
}

$('#button_send').on('mouseenter', function() {
	$('#button_send').css({
	    backgroundColor: '#f7e5e5'
	})
});

$('#button_send').on('mouseleave', function() {
	$('#button_send').css({
	    backgroundColor: '#ffecec'
	})
});

$('#button_send').on('mousedown', function() {
	$('#button_send').css({
	    backgroundColor: '#eedddd'
	})
});

$('#button_send').on('mouseup', function() {
	$('#button_send').css({
	    backgroundColor: '#f7e5e5'
	})
});
</script>
</html>