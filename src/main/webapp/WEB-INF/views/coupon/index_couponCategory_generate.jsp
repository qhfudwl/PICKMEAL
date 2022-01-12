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
        #couponGenerate{display: none;}
        #couponGenerateName{text-align: center; line-height: 100px;}
    </style>
</head>
	<!-- 2번
		 식당이 골라지고 나서 쿠폰카테고리 발행 성공시 // 실패시 
		 성공시 : 화면에 버튼 만들어주기
		 실패시 : 화면에 버튼 숨겨서 없는 상대로 만들어주기-->
<body>
	<form method="get" id="couponPopupCreatebtn" action="">
		<!-- null 일 경우 true -->
		<c:choose>
			<c:when test="${empty couponCategory}">
			</c:when>
			<c:otherwise>
				<div id="couponGenerateWrap" onclick="document.forms['couponPopupCreatebtn'].submit();">
        			<p id="couponGenerateName">쿠폰</p>
        			<input type="submit" id="couponGenerate"/>
    			</div>
			</c:otherwise>
		</c:choose>
	</form>
	<script>
        $('#couponGenerateWrap').click(function(e){
        	e.preventDefault();
            $('#couponGenerate').click();
        })
        
        $("#couponGenerate").click(function(e){
		e.preventDefault();
		popupCoupon("couponPopupCreate");
		})
	function popupCoupon(Url){
        let windowWidth = window.screen.width;
        let windowHeight = window.screen.height;
        
        let popupX = (windowWidth/2) - 630;
        let popupY = (windowHeight/2) -275;
        
        let popUpdateUrl = "http://localhost:8080/pickmeal/" + Url;
        let popUpdateOption = "width=630px, height=550px, top=" + popupY + "px, left=" + popupX + "px";
        let popUpdateTitle = "쿠폰 발급"
        
        if(Url == "couponPopupCreate") {
            window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
        }
        let couponPopupCreate = document.couponPopupCreate;
        couponPopupCreate.target = popUpdateTitle;
        couponPopupCreate.action = popUpdateUrl;
        couponPopupCreate.method ="get";
        
        couponPopupCreate.submit();
    }

    </script>
</body>
</html>