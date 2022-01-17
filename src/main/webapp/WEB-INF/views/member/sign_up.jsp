<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member/sign_up.css" />
<script src="${pageContext.request.contextPath}/resources/js/member/sign_up.js" defer></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
<section id="content">
	<h2 class="hidden">회원가입</h2>
	<section id="signUpFormWrap">
		<h3 class="hidden">회원가입 입력</h3>
		<form:form name="signUpForm" action="generateMember" method="post" modelAttribute="memberCommand">
			<div class="signUpInputWrap">
				<label class="labelValue" for="email">이메일</label><span class="required"> *</span><br>
				<form:input path="email" class="inputValue" placeholder="ex) abcde@naver.com" />
				<p class="errMsg"></p>
			</div>
			<div class="signUpInputWrap">
				<label class="labelValue" for="passwd">비밀번호</label><span class="required"> *</span><br>
				<form:password path="passwd" class="inputValue" placeholder="4자 이상 20자 이하 (허용 특수문자 : !@#$%^~*+=&-)" maxlength="20" />
				<p class="errMsg"><c:if test="${not empty errMsg}">${errMsg}</c:if></p>
			</div>
			<div class="signUpInputWrap">
				<label class="labelValue" for="passwdChk">비밀번호확인</label><span class="required"> *</span><br>
				<input type="password" name="passwdChk" id="passwdChk" class="inputValue" placeholder="" />
				<p class="errMsg"></p>
			</div>
			<div class="signUpInputWrap">
				<label class="labelValue" for="nickName">닉네임</label><span class="required"> *</span><br>
				<form:input path="nickName" class="inputValue" placeholder="ex) 아루누나" />
				<p class="errMsg"></p>
			</div>
			<div class="signUpInputWrap">
				<label class="labelValue" for="birth">생년월일</label><span class="required"> *</span><br>
				<form:input path="birth" class="inputValue" placeholder="ex) 19990101" />
				<p class="errMsg"></p>
			</div>
			<div class="signUpInputWrap">
				<span class="labelValue">성별</span><span class="required"> *</span><br>
				<input type="radio" id="male" name="gender" value="M" />
				<label class="genderLabel" for="male">남자</label>
				<input type="radio" id="female" name="gender" value="F" />
				<label class="genderLabel" for="female">여자</label>
				<p class="errMsg"></p>
			</div>
			<div class="signUpInputWrap">
				<input type="submit" id="signUpBtn" value="가입하기" />
			</div>
		</form:form>
	</section>
</section>
</body>
</html>