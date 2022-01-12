<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥 친구 게시판 댓글</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/posting/together_eating_comment.css" />
<script src="${pageContext.request.contextPath}/resources/js/posting/together_eating_comment.js" defer></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
	<section id="commentsWrap">
		<h2 class="hidden">댓글</h2>
		<form action="" method="get">
			<c:forEach var="c" items="${comments}">
				<div class="commentWrap div${c.id}">
					<img alt="${c.member.nickName}님의 프로필 이미지" src="${pageContext.request.contextPath}${c.member.profileImgPath}">
					<div class="memberContent">
						<div class="memberInfo">
							<p class="nickName">${c.member.nickName}</p>
							<p class="mannerTemp">${c.member.mannerTemperature}&deg;</p>
							<time datetime="${c.regDate}">${c.regDate}</time>
						</div>
						<input type="text" class="comment${c.id} viewCmt viewCmtContent" name="cmtContent" value="${c.content}" disabled />
					</div>
					<div class="moreWrap">
						<div class="popUpdate">
							<c:if test="${member.id eq c.member.id}">
								<input onclick="oneChkBox(this); displayUpdate(this)" class="choiceCmt" type="checkbox" name="id" value="${c.id}" id="choiceCmt${c.id}" /> 
								<label for="choiceCmt${c.id}">
									<span class="point1"></span>
									<span class="point2"></span>
									<span class="point3"></span>
								</label>
							</c:if>
						</div>
						<div class="updateWrap updateWrap${c.id}">
							<button name="update" class="update" value="modify${c.id}">수정하기</button>
							<button name="update" class="update" value="delete${c.id}">삭제하기</button>
						</div>
						<c:if test="${member.id eq posting.member.id}">
							<button type="submit" class="chat" value="${c.member.id}">채팅하기</button>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</form>
			<section id="writeCommentWrap">
				<h3 class="hidden">댓글 작성란</h3>
				<p id="writerNickName">${member.nickName}</p>
				<input type="hidden" value="${member.id}" id="cmtWrtier"/>
				<input type="hidden" value="${posting.id}" id="cmtPosting"/>
				<textarea id="writeCmt" rows="4" cols="50" placeholder="댓글을 등록해주세요."></textarea>
				<input type="button" name="update" value="등록" id="writeOk" />
			</section>
	</section>
</body>
</html>