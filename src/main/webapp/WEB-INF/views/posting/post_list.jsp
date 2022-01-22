<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/posting/post_list.js" defer></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/posting/post.css" />
<!-- 
	java char type은  jstl eq로 비교시 에러남!!
	fn:contain로 비교할 것	
 -->
 <c:set var="postType" value="${pageMaker.criteria.type }"/>
<c:choose>
	<c:when test="${fn:contains(postType,'N')}"><title>밥찡코 - 공지사항</title></c:when>
	<c:when test="${fn:contains(postType,'R')}"><title>밥찡코 - 식당추천</title></c:when>
	<c:when test="${fn:contains(postType,'E')}"><title>밥찡코 - 밥친구</title></c:when>
	<c:otherwise><title>밥찡코</title></c:otherwise>
</c:choose>

</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
 <section id="totalPostContainer">
    <h2 class="hidden">게시판목록</h2>
    <c:if test="${not empty postType }">
    <div id="postListContainer">
      <div id="postListTitleContainer">
        <div class="postListTitleWrap">
          <c:if test="${fn:contains(postType,'N')}">
          	<h3>공지사항</h3>
          	<p>읽어주시면 밥찡코를 더 잘 활용할 수 있어요</p>
          </c:if>
          <c:if test="${fn:contains(postType,'R')}">
          	<h3>식당추천</h3>
          	<p>나만 알고 있는 찐 맛집을 추천해주세요!</p>
          </c:if>
          <c:if test="${fn:contains(postType,'E')}">
          	<h3>밥친구</h3>
          	<p>우리는 칭구칭구 밥칭구~ 혼자라도 2인세트 먹을 수 있지!</p>
          </c:if>
        </div>
        <div class="postListTitleBtnWrap">
          <ul>
            <li><a href="#" class="postListTitleBtnOn">식당</a></li>
            <li><a href="#">카페</a></li>
            <li><a href="#">술집</a></li>
          </ul>
        </div>
      </div>
      <div id="postListContentContainer">
        <table class="postListContentTable">
          <tr>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
            <th>좋아요</th>
          </tr>
          <tr>
            <td>
              <ul class="postListContentTitleTagsWrap">
                <li>
                  <p>모집중</p>
                  <div class="postListConTagTogetherIng"></div>
                </li>
                <li>대구 광역시 중구 중안로</li>
                <li>01/02 13:00</li>
              </ul>
              <p class="postListContentTitle">화무비도에서 같이 쟁반짜장 먹으실 칭구칭구 구해요~! 1분 </p>
            </td>
            <td>나는뉸뉸</td>
            <td>22.01.14</td>
            <td>1001</td>
            <td>501</td>
          </tr>
          <tr>
            <td>
              <ul class="postListContentTitleTagsWrap">
                <li>
                  <p>모집중</p>
                  <div class="postListConTagTogetherIng"></div>
                </li>
                <li>대구 광역시 중구 중안로</li>
                <li>01/02 13:00</li>
              </ul>
              <p class="postListContentTitle">화무비도에서 같이 쟁반짜장 먹으실 칭구칭구 구해요~! 1분 </p>
            </td>
            <td>나는뉸뉸</td>
            <td>22.01.14</td>
            <td>1001</td>
            <td>501</td>
          </tr>

        </table>
      </div>
      <div id="postListSubInfoContainer">
        <div class="postListSortWrap">
          <ul>
            <li><a href="#" class="postListSortOn">모집중</a></li>
            <li><a href="#">최신순</a></li>
            <li><a href="#">조회순</a></li>
            <li><a href="#">좋아요순</a></li>
          </ul>
        </div>
        <div class="postListSearchWrap">
          <ul>
            <li>
              <select class="postListSearchSelectBox postInputTCom" name="">
                <option value="pTitle">제목</option>
                <option value="PWriter">작성자</option>
              </select>
            </li>
            <li>
              <input type="text" class="postListSearchInput postInputTCom">
            </li>
            <li><a href="#" class="postListSearchBtn postBtnCom">검색</a></li>
          </ul>
        </div>
      </div>
      <div id="postListNaviContainer">
        <div class="postListNaviWrap">
          <ul>
            <li><a href="#">이전</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">6</a></li>
            <li><a href="#">7</a></li>
            <li><a href="#">8</a></li>
            <li><a href="#">9</a></li>
            <li><a href="#">10</a></li>
            <li><a href="#">이후</a></li>
          </ul>
        </div>
      </div>
    </div>
	</c:if>
  </section>
</body>
</html>