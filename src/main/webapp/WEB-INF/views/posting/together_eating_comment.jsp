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
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
	<section id="commentsWrap">
		<h2 class="hidden">댓글</h2>
		<%--
		<div class="commentWrap ${c.id}div">
			<img alt="${c.member.nickName}님의 프로필 이미지" src="${c.member.profileImgPath}">
			<div class="memberContent">
				<div class="memberInfo">
					<p class="nickName">${c.member.nickName}</p>
					<p class="mannerTemp">${c.member.mannerTemperature}</p>
					<time datetime="${c.regDate}">${c.regDate}</time>
				</div>
				<input type="hidden" class="comment${c.id}" name="member.id" value="${c.member.id}" disabled />
				<input type="hidden" class="comment${c.id}" name="posting.id" value="${c.posting.id}" disabled />
				<input type="text" class="comment${c.id}" name="content" value="${c.content}" disabled />
			</div>
			<div class="moreWrap">
				<div class="popUpdate">
					<c:if test="${member.id eq c.member.id}">
						<label for="choiceCmt${c.id}">
							<span class="point1"></span>
							<span class="point2"></span>
							<span class="point3"></span>
						</label>
						<input type="radio" name="id" value="${c.id}" id="choiceCmt${c.id}" /> 
					</c:if>
				</div>
				<div class="updateWrap">
					<input type="button" name="update" class="update" value="수정하기" />
					<input type="button" name="update" class="update" value="삭제하기" />
				</div>
				<button class="chat">채팅하기</button>
			</div>
		</div>
		<section id="writeCommentWrap">
			<h3 class="hidden">댓글 작성란</h3>
			<p>${member.nickName}</p>
			<input type="text" name="content" />
			<input type="button" name="update" value="등록" />
		</section>
		 --%>
		 <form>
			 <div class="commentWrap">
				<img alt="님의 프로필 이미지" src="${pageContext.request.contextPath}/resources/img/profile/nonUser.png">
				<div class="memberContent">
					<div class="memberInfo">
						<p class="nickName">아루 누나</p>
						<p class="mannerTemp">36.5&deg;</p>
						<time datetime="2022-01-05T11:11:11TZD">2022-01-05 11:11:11</time>
					</div>
					<input type="hidden" class="comment1 viewCmt" name="member.id" value="1" disabled />
					<input type="hidden" class="comment1 viewCmt" name="posting.id" value="1" disabled />
					<input type="text" class="comment1 viewCmt viewCmtContent" name="content" value="도리집은 오늘도 닫았다." disabled />
				</div>
				<div class="moreWrap">
					<div class="popUpdate">
							<input class="choiceCmt" type="radio" name="id" value="" id="choiceCmt" /> 
							<label for="choiceCmt">
								<span class="point1"></span>
								<span class="point2"></span>
								<span class="point3"></span>
							</label>
					</div>
					<div class="updateWrap">
						<input type="button" name="update" class="update" value="수정하기" />
						<input type="button" name="update" class="update" value="삭제하기" />
					</div>
					<button type="submit" class="chat" value="${c.member.id}">채팅하기</button>
				</div>
			</div>
		</form>
		 <div class="commentWrap">
			<img alt="님의 프로필 이미지" src="${pageContext.request.contextPath}/resources/img/profile/nonUser.png">
			<div class="memberContent">
				<div class="memberInfo">
					<p class="nickName">아루 누나</p>
					<p class="mannerTemp">36.5&deg;</p>
					<time datetime="2022-01-05T11:11:11TZD">2022-01-05 11:11:11</time>
				</div>
				<input type="hidden" class="comment1 viewCmt" name="member.id" value="1" disabled />
				<input type="hidden" class="comment1 viewCmt" name="posting.id" value="1" disabled />
				<input type="text" class="comment1 viewCmt viewCmtContent" name="content" value="도리집은 오늘도 닫았다." disabled />
			</div>
			<div class="moreWrap">
				<div class="popUpdate">
						<input class="choiceCmt" type="radio" name="id" value="" id="choiceCmt" /> 
						<label for="choiceCmt">
							<span class="point1"></span>
							<span class="point2"></span>
							<span class="point3"></span>
						</label>
				</div>
				<div class="updateWrap">
					<input type="button" name="update" class="update" value="수정하기" />
					<input type="button" name="update" class="update" value="삭제하기" />
				</div>
				<button type="submit" class="chat" value="${c.member.id}">채팅하기</button>
			</div>
		</div>
		<section id="writeCommentWrap">
			<h3 class="hidden">댓글 작성란</h3>
			<p id="writerNickName">향기 언니</p>
			<textarea id="writeCmt" name="content" rows="4" cols="50" placeholder="댓글을 등록해주세요."></textarea>
			<input type="button" name="update" value="등록" id="writeOk" />
		</section>
	</section>
</body>
</html>