<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 카테고리 발급 후에는?</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #couponGenerateWrap{width: 100px; height: 100px; border-radius: 100px; background-color: gold;}
        #couponGenerateWrap:hover{cursor: pointer;}
        /*#couponGenerate{display: none;}*/
        #couponGenerateName{text-align: center; line-height: 100px;}
    </style>
</head>
	<!-- 2번
		 식당이 골라지고 나서 쿠폰카테고리 발행 성공시 // 실패시 
		 성공시 : 화면에 버튼 만들어주기
		 실패시 : 화면에 버튼 숨겨서 없는 상대로 만들어주기-->
<body>
	<form method="get" action="couponPopupCreate">
		<!-- null 일 경우 true -->
		<c:choose>
			<c:when test="${empty couponCategory}">
			</c:when>
			<c:otherwise>
				<div id="couponGenerateWrap">
        			<p id="couponGenerateName">쿠폰</p>
        			<input type="submit" id="couponGenerate"/>
    			</div>
			</c:otherwise>
		</c:choose>
	</form>
	<script>
        $('#couponGenerateWrap').click(function(){  
            $('#couponGenerate').click();
        })

    </script>
</body>
</html>