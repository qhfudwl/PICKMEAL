<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
<%--
<script src="${pageContext.request.contextPath}/resources/js/incl/index_map.js" defer></script>
<link  rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/incl/index_map_b.css" /> --%>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
<section id="content">
	<h2 class="hidden">회원가입</h2>
	<section id="signUpForm">
		<h3 class="hidden">회원가입 입력</h3>
		<form:form>
		
		</form:form>
	</section>
</section>
</body>
</html>