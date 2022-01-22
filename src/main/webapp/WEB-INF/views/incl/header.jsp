<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<form action="index" method="get" class="logoWrap"><h1 class="logo">밥찡코</h1></form>
	<nav id="gnb">
		<h2 class="hidden">게시판메뉴</h2>
		<ul>
			<li><a href="${pageContext.request.contextPath}/posting/notice">공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/posting/recommend">식당추천</a></li>
			<li><a href="${pageContext.request.contextPath}/posting/together">밥친구</a></li>
			<!--  
            <form name="indexView" action="${pageContext.request.contextPath}/posting/viewTogetherEatingComment" method="get">
            	<button type="submit" name="pageNum" value="1">밥친구</button>
            	밥친구
            </form>-->
		</ul>
	</nav>
	<nav id="snb">
		<h2 class="hidden">유저메뉴</h2>
		<c:if test="${not empty member }">
			<ul>
				<li><span class="navi_nickname">${member.nickName }님 맛점하세요:)</span></li>
				<li class="snbProfileLine">
					<div class="profileImgWrap">
						<img src="/pickmeal/resources/img/profile/nonUser.png"
							alt="프로필아이콘" class="profileImg">
					</div>
					<div class="profileAreaWrap navAlarmTextCom">
						<ul class="profileArea">
							<li><form action="" method="">로그아웃</form></li>
							<li><form action="" method="">마이페이지</form></li>
							<li><a href="${pageContext.request.contextPath}/member/myPage">마이페이지</a></li>
						</ul>
					</div>
				</li>
				<li>
					<div class="alarmIconWrap comIconWrap">
						<img src="/pickmeal/resources/img/header/bell_alarm.png"
							alt="알림아이콘" class="alarmImg comIconImg">
						<div class="alarmMark comIconMark">1</div>
					</div>
					<div class="alarmAreaWrap navAlarmTextCom">
						<div class="alarmTitle">알림내역</div>
						<ul class="alarmArea">
							<li><img src="/pickmeal/resources/img/header/store.png"
								alt="프로필사진" class="alarmProfileImg"> <span
								class="alarmTextBold">화무비도</span>에서 이러쿵저러쿵 먹으셨나요?<span
								class="alarmTextClock">2시간전</span></li>
							<li><img src="/pickmeal/resources/img/profile/nonUser.png"
								alt="프로필사진" class="alarmProfileImg"> <span
								class="alarmTextBold">보보님</span>과나요? 식사매너는 쩔었나요?<span
								class="alarmTextClock">18시간전</span></li>
						</ul>
					</div>
				</li>
				<li>
					<div class="chatIconWrap comIconWrap">
						<img src="/pickmeal/resources/img/header/chat_alarm.png"
							alt="알림아이콘" class="chatImg comIconImg">
						<div class="chatAlarmMark comIconMark">1</div>
					</div>
				</li>
			</ul>
		</c:if>
		<c:if test="${empty member }">
			<ul>
				<li><span class="navi_nickname">오늘은 뭐 먹지?</span></li>
				<li class="snbProfileLine">
					<div class="profileImgWrap">
						<img src="/pickmeal/resources/img/profile/nonUser.png"
							alt="프로필아이콘" class="profileImg">
					</div>
					<div class="profileAreaWrap navAlarmTextCom">
						<ul class="profileArea">
							<li>
            <form name="indexView" action="${pageContext.request.contextPath}/member/viewSignIn" method="get">
            	<input type="submit" value="로그인"/>
            	로그인
            </form></li>
							<li><form action="" method="">회원가입</form></li>
						</ul>
					</div>
				</li>
			</ul>
		</c:if>
	</nav>
</header>