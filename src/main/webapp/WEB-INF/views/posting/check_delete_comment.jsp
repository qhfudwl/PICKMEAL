<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 삭제 확인</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/posting/check_delete_comment.css" />
<%--<script src="${pageContext.request.contextPath}/resources/js/posting/check_delete_comment.js" defer></script> --%>
</head>
<body>
	<section class="chkDeleteSection">
		<div class="chkDeleteQst">
			<h3>댓글 삭제</h3>
			<p>댓글을 완전히 삭제하시겠습니까?</p>
		</div>
		<div class="chkDeleteBtn">
			<button type="button">취소</button>
			<button type="button">삭제</button>
		</div>
	</section>
</body>
</html>