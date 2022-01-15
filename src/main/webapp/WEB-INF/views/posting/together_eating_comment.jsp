<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<form name="viewCmtForm">
			<c:forEach var="c" items="${comments}">
				<div class="commentWrap" id="commentWrap${c.id}">
					<img alt="${c.member.nickName}님의 프로필 이미지" src="${pageContext.request.contextPath}${c.member.profileImgPath}">
					<div class="memberContent">
						<div class="memberInfo">
							<p class="nickName">${c.member.nickName}</p>
							<p class="mannerTemp">${c.member.mannerTemperature}&deg;</p>
							<time datetime="${c.regDate}"><fmt:formatDate value="${c.regDate}" pattern="yyyy-MM-dd hh:mm:ss" /></time>
						</div>
						<input type="hidden" id="cmtMemberId${c.id}" value="${c.member.id}"/>
						<input type="text" id="comment${c.id}" class="viewCmt viewCmtContent" name="cmtContent" value="${c.content}" disabled />
						<button type="button" id="modifyContent${c.id}" value="${c.id}" class="modifyContent" onclick="modifyComment(this)">수정하기</button>
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
							<button type="button" name="update" class="update" value="modify${c.id}" onclick="modifyCommentOpen(this)">수정하기</button>
							<button type="button" name="update" class="update" value="delete${c.id}" onclick="deleteComment(this)">삭제하기</button>
						</div>
						<c:if test="${member.id eq posting.member.id}">
							<c:if test="${not empty member}">
								<c:if test="${posting.category eq 'E'.charAt(0) }">
									<button type="submit" class="chat" value="${c.member.id}">채팅하기</button>
								</c:if>
							</c:if>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</form>
		<input type="hidden" value="${viewPageNum}" id="viewPageNum" />
		<input type="hidden" value="${allPageNum}" id="allPageNum"/>
		<input type="hidden" value="${allCmtNum}" id="allCmtNum"/>
		<input type="hidden" value="${pageNum}" id="pageNum"/>
		<section id="cmtBtnWrap">
			<button type="button" id="firstPage">&lt;&lt;</button>
			<button type="button" id="leftPage">&lt;</button>
			<div id="cmtPageNumWrap">
				<div id="pageWrap">
					<c:forEach begin="1" end="${allPageNum}" varStatus="status">
						<button onclick="changePageNumBtnColor(this); moveCommentPage(this)" type="button" name="pageNum" class="pageNum pageNum${status.count}" value="${status.count}">${status.count}</button>
					</c:forEach>
				</div>
			</div>
			<button type="button" id="rightPage">&gt;</button>
			<button type="button" id="lastPage">&gt;&gt;</button>
		</section>
		<form:form name="writeCmtForm" modelAttribute="commentCommand">
			<input type="hidden" name="memberId" value="${member.id}"/>
			<input type="hidden" name="postId" value="${posting.id}"/>
			<input type="hidden" name="post_memberId" value="${posting.member.id}"/>
			<input type="hidden" name="category" value="${posting.category}"/>
			<c:if test="${not empty member }">
				<section id="writeCommentWrap">
					<h3 class="hidden">댓글 작성란</h3>
					<p id="writerNickName">${member.nickName}</p>
					<textarea id="writeCmt" name="content" rows="4" cols="50" placeholder="댓글을 등록해주세요."></textarea>
					<input type="button" name="update" value="등록" id="writeOk" />
				</section>
			</c:if>
		</form:form>
	</section>
</body>
</html>