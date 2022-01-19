<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/member/my_page.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
	<div id="my_page_header">
        <h2>마이페이지</h2>
    </div>
	<nav id="nav">
	    <ul>
	        <li><a href="#">내 정보</a></li>
	        <li><a href="#">내 게시글</a></li>
	        <li><a href="#">내 댓글</a></li>
	        <li><a href="#">찜 식당</a></li>
	        <li><a href="#">내가 간 식당</a></li>
	        <li><a href="#">쿠폰</a></li>
	        <li><a href="#">업적</a></li>
	    </ul>
	</nav>
</body>
</html>