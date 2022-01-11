<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 발급 회원/비회원</title>
</head>
<body>
<!-- 3번
	쿠폰 카테고리 발급 후 생기는 상자를 클릭 했을 때 뜨는 창
	회원 / 비회원이 보는 창을 다르게 해주어야 한다.
	회원 : 받기 버튼을 클릭하면 창을 닫아주면서 쿠폰 발행완료와 동시에 세션에서 쿠폰카테고리를 지워준다. 
	비회원 : 로그인하기 / 취소버튼 로그인하기 버튼을 누르면 로그인폼으로 이동하면서 쿠폰 카테고리를 계속 세션에 저장 시켜두고,
		취소하기 버튼을 누르면 쿠폰을 그 즉시 창을 닫아주고 쿠폰카테고리를 세션에서 삭제해준다.-->
	
	<input type="text" value="${couponCategory.couponName}">
	<c:choose>
			<c:when test="${empty member}">
				<form action="member/viewSignIn" method="GET">
					<p>로그인을 하면 쿠폰을 받아갈 수 있습니다.</p>
					<input type="submit" value="로그인 하기">
					<input type="submit" value="취소">
				</form>
			</c:when>
			<c:otherwise>
				<form action="genericCoupon" method="GET">
					<input type="submit" value="확인">
				</form>
			</c:otherwise>
	</c:choose>
</body>
</html>