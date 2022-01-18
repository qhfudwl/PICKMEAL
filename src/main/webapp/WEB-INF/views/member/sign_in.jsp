<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/sign_in.css" />
<script src="${pageContext.request.contextPath}/resources/js/member/sign_in.js" defer></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
<section id="content">
	<h2 class="hidden">로그인</h2>
	<section id="signInFormWrap">
		<h3 class="hidden">로그인 입력</h3>
		<form:form name="signInForm" action="signInMember" method="post" modelAttribute="memberCommand">
			<c:if test="${not empty invalidInfo}"><p id="invalidInfo">잘못된 정보입니다.</p></c:if>
			<div class="signInInputWrap">
				<label class="labelValue" for="email">이메일</label><br>
				<form:input path="email" class="inputValue" placeholder="ex) abcde@naver.com" />
				<p class="errMsg"></p>
			</div>
			<div class="signInInputWrap">
				<label class="labelValue" for="passwd">비밀번호</label><br>
				<form:input path="passwd" class="inputValue" placeholder="" />
				<p class="errMsg"></p>
			</div>
			<div class="signInBtnWrap">
				<input type="submit" id="signUpBtn" name="chkBtn" value="회원가입" />
				<input type="submit" id="signInBtn" name="chkBtn" value="로그인" />
			</div>
		</form:form>
	</section>
</section>
</body>
</html>