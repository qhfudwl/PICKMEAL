<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰 발급 회원/비회원</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/coupon/coupon_generate_popup.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<!-- 3번
	쿠폰 카테고리 발급 후 생기는 상자를 클릭 했을 때 뜨는 창
	회원 / 비회원이 보는 창을 다르게 해주어야 한다.
	회원 : 받기 버튼을 클릭하면 창을 닫아주면서 쿠폰 발행완료와 동시에 세션에서 쿠폰카테고리를 지워준다. 
	비회원 : 로그인하기 / 취소버튼 로그인하기 버튼을 누르면 로그인폼으로 이동하면서 쿠폰 카테고리를 계속 세션에 저장 시켜두고,
		취소하기 버튼을 누르면 쿠폰을 그 즉시 창을 닫아주고 쿠폰카테고리를 세션에서 삭제해준다.-->
	
	<section id="couponPopupWrap">
        <h2 id="mainName">"<span class="mainNameSpan1">밥</span><span class="mainNameSpan2">찡</span><span class="mainNameSpan3">코</span>" 식사 할인권!</h2>
        <div id="couponWrap">
            <div id="couponInfoWrap">
                <div id="couponPriceWrap">
                	<input type="hidden" value="${member.id}" id="memberIdWS" name="memberIdWS">
                	<input type="hidden" value="${restaurant.id}" id="restaurantWS" name="restaurantWS">
                	<input type="hidden" value="${couponCategory.id}" id="couponCategoryWS" name="restaurantWS"">
                	<p id="restaurantName"><span id="restaurantNameSpan">'${restaurant.RName}'</span> 쿠폰</p>
                    <p id="couponPrice"><span id="couponPriceSpan">${couponCategory.couponName}</span></p>
                    <p id="orderPrice"><span id="limitPrice">${couponCategory.limitPrice}</span> 원 이상 주문시 사용가능</p>
                </div>
                <div id="logoPlay">
                    <p id="logo1"><span class="mainNameSpan1">밥</span></p>
                    <p id="logo2"><span class="mainNameSpan2">찡</span></p>
                    <p id="logo3"><span class="mainNameSpan3">코</span></p>
                </div>
            </div>
            <div id="limitationWrap">
                <p id="ment1" class="mentAll"><span class="menuAllspan">※</span>"발급 받기"를 누르지 않으면</p>
                <p id="ment2" class="mentAll"><span class="menuAllspan">※</span>위 쿠폰 미발급시 한시간 뒤 자동 소멸됩니다.</p>
                <p id="ment3" class="mentAll"><span class="menuAllspan">※</span>유효기간 : 발급 일 당일</p>
            </div>
        </div>
        <c:choose>
            <c:when test="${empty member}">
                <form name="goLogin"action="" method="GET">
                    <div id="loginbtnWrap">
                        <button id="loginBtn" onclick="goLoginSubmit()">로그인</button>
                    </div>
                    <div id="cancleBtnWrap">
                        <button id="cancleBtn" onclick="test()">취소</button>
                        <input type="hidden" id="closeBBtn" onclick="goClose()">
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                    <button id="successBtn" onclick="indexPopupCoupon()">발급 받기</button>
                    <input type="hidden" id=closeBtn onclick="goClose()">
            </c:otherwise>
        </c:choose>
    </section>
    <script>
    function goLoginSubmit() {
    	window.opener.name = "parentPage"; // 부모창의 이름 설정
    	document.goLogin.target = "parentPage"; // 타켓을 부모창으로 설정
    	document.goLogin.action = "member/viewSignIn";
    	document.goLogin.submit();
    	self.close();
    }
    function goClose(){
    	self.close();
    }
    function test(){
    	$("#closeBBtn").click();
    }
    
    /*
    $("#successBtn").click.function(e){
    	e.preventDefault();
    	var memberId = $("input[name='memberIdWS']").val();
    	var restaurantId = $("input[name='restaurantIdWS']").val();
    	var couponCategoryId = $("input[name='couponCategoryIdWS']").val();
    	
    	$.ajax({
    		url : "http://localhost:8080/pickmeal/genericJincoupon",
    		type : "post",
    		data : JSON.stringify,
    		contentType:'application/json; charset=utf-8',
    		success: function(data){
    			console.log("여기 들어오냐?구요");
    			opener.parent.setCafterIsempty();
    			opener.parent.removeGenerisCoupon();
    			
    			self.close();
    			}
    	})

    }
    */
    
    function indexPopupCoupon(){
    	var memberId = $("input[name='memberIdWS']").val();
    	var restaurantId = $("input[name='restaurantIdWS']").val();
    	var couponCategoryId = $("input[name='couponCategoryIdWS']").val();
    	console.log("왜 안됨??");
    	$.ajax({
    		url : "http://localhost:8080/pickmeal/genericJincoupon",
    		type : "post",
    		data : JSON.stringify,
    		contentType:'application/json; charset=utf-8',
    		success: function(){
    			//console.log("여기 들어오냐?구요");
    			opener.parent.setCafterIsempty();
    			opener.parent.removeGenerisCoupon();
    			
    			self.close();
    			}
    	})
    }
    </script>
</body>
</html>