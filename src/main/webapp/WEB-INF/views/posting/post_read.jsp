<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/posting/post.css" />
<title>게시글읽기</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
<section id="totalPostContainer">
        <h2 class="hidden">글읽기</h2>
        <div id="rwPostContainer">
            <div id="rwPostTitleContainer">
                <div class="rwPostTitleWrap">
                    <h3>밥찡코</h3>
                </div>
            </div>
            <div id="rPostContentContainer">
                <div id="rPostHeaderWrap">
                    <div class="rPostTagListWrap">
                        <ul class="rPostTagStaticWrap">
                            <li>
                                <p>모집중</p>
                                <div class="rPostTagTogetherIng rPostTagTogetherComp"></div>
                            </li>
                            <li>대구광역시 중구 중앙로</li>
                            <li>1월 14일 13:00</li>
                        </ul>
                        <ul class="rPostTagDynamicWrap">
                            <li>
                                <p class="rPostCheckOn rPostCheck">모집중</p>
                                <p class="rPostCheckOFF rPostCheck" style="display:none;">모집완료</p>
                                <label class="switch">
                                    <input type="checkbox" class="rPostCheckbox">
                                    <span class="slider round"></span>
                                </label>
                            </li>
                            <li><a href="#">식사완료</a></li>
                        </ul>
                    </div>
                    <div class="rPostTitleWrap">
                        <p class="rPostTitle">같이 쟁반짜장 먹으실 칭구칭구 구해요 1명!</p>
                    </div>
                    <div class="rPostProfileWrap">
                        <ul>
                            <li><img class="rPostProfileImg" src="../nonUser.png" alt="회원프로필아이콘"></li>
                            <li class="rPostProfileNickName">
                                나는뉴뉸
                            </li>
                            <li class="rPostProfileTempArea">
                                <div class="rPostProfileTempAreaSub1">
                                    <div class="rPostProfileTempAreaSub2"></div>
                                </div>
                                
                            </li>
                            <li class="rPostProfileTempText">
                                36.5˚
                            </li>
                            <li class="rPostContentRegDate">
                                2022.01.14 <span>11:00</span>
                            </li>
                            <li class="rPostContenHitCount">
                                조회<span>501</span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div id="rPostContentWrap">
                    <div class="rPostContentMapWrap">
                        <div id="rPostContentMap"></div>
                        <div class="rPostContentMapDetails">
                            <table>
                                <tr>
                                    <th>카테고리</th>
                                    <td>식당</td>
                                </tr>
                                <tr>
                                    <th>식사장소</th>
                                    <td>대구 광역시 중구 중앙로77 101호</td>
                                </tr>
                                <tr>
                                    <th>식사시간</th>
                                    <td>2022년 1월 14일 오후 13:00</td>
                                </tr>

                            </table>
                        </div>
                    </div>
                    <div class="rPostContentBody">
                        <!-- 적은거 그대로 보여주기 -->
                        <p>쟁반짜장이 너무 먹고싶은데
                            화무비도는 쟁반짜장이 2인분 이상부터 가능하다네용~
                            조금있다 점심시간에 같이 드실 분 계실까요?? 1분 구해봐욤
                            후식으로는 나랑 홀케이크 1판 먹어야함♥ </p>
                    </div>
                </div>
                <div id="rPostAdditionalInfoWrap">

                    <ul class="rPostAdditioanInfoLeftSide">
                        <li>
                            <img class="rPostLikesImg" src="../heart.png" alt="좋아요아이콘">
                            <p class="rPostLikesText">좋아요<span>101</span></p>
                        </li>
                        <li>
                            <img class="rPostCommentCountImg" src="../bubble-chat.png" alt="댓글아이콘">
                            <p class="rPostCommentCountText">댓글<span>2</span></p>
                        </li>
                    </ul>
                    <ul class="rPostAdditioanInfoRightSide">
                        <li>수정</li>
                        <li>삭제</li>
                    </ul>


                </div>
                <div id="rPostCommentWrap">
                    <!-- 코멘트 들어갈 영역 -->
                </div>
            </div>

        </div>

    </section>
</body>
</html>