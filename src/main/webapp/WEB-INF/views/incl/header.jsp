<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <header>
      <h1 class="logo">밥찡코</h1>
      <nav id="gnb">
        <h2 class="hidden">게시판메뉴</h2>
        <ul>
          <li>공지사항</li>
          <li>식당추천</li>
          <li>밥친구</li>
        </ul>
      </nav>
      <nav id="snb">
        <h2 class="hidden">유저메뉴</h2>
        <ul>
        
          <li>
            <span class="navi_nickname">보보님 맛점하세요:)</span>
          </li>
          <li class="snbProfileLine">
            <div class="profileImgWrap">
              <img src="/pickmeal/resources/img/profile/nonUser.png" alt="프로필아이콘" class="profileImg">
            </div>
            <div class="profileAreaWrap navAlarmTextCom">
              <ul class="profileArea">
                <li>로그아웃</li>
                <li>회원가입</li>
                <li>마이페이지</li>
              </ul>
            </div>
          </li>
          
          <li>
            <div class="alarmIconWrap comIconWrap">
              <img src="/pickmeal/resources/img/header/bell_alarm.png" alt="알림아이콘" class="alarmImg comIconImg">
              <div class="alarmMark comIconMark">1</div>
            </div>
            <div class="alarmAreaWrap navAlarmTextCom">
              <div class="alarmTitle">알림내역</div>
              <ul class="alarmArea">
                <li>
                  <img src="/pickmeal/resources/img/header/store.png" alt="프로필사진" class="alarmProfileImg">
                  <span class="alarmTextBold">화무비도</span>에서 이러쿵저러쿵 먹으셨나요?<span class="alarmTextClock">2시간전</span></li>
                <li>
                  <img src="/pickmeal/resources/img/profile/nonUser.png" alt="프로필사진" class="alarmProfileImg">
                  <span class="alarmTextBold">보보님</span>과나요? 식사매너는 쩔었나요?<span class="alarmTextClock">18시간전</span></li>
              </ul>
            </div>
          </li>
            <li>
              <div class="chatIconWrap comIconWrap">
                <img src="/pickmeal/resources/img/header/chat_alarm.png" alt="알림아이콘" class="chatImg comIconImg">
                <div class="chatAlarmMark comIconMark">1</div>
              </div>
            </li>
        </ul>
      </nav>
    </header>