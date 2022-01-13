<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인-쿠폰 리스트</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<section id="CouponList">
            <h2>${member.nickName}회원님 쿠폰</h2>
            <button>돌아가기</button>
            <div id="unusedCouponsWrap">
                <h3>미사용 쿠폰</h3>
                <form method ="get" action="usedCouponPopup">
                    <table id="">
                    	<p>미사용 쿠폰의 경우 당일 안에 사용해야 합니다.</p>
                        <tr><th class="RestaurantName">식당이름</th><th class="CouponName">할인가</th><th class="CouponNumber">쿠폰번호</th><th class="CouponLimit">결제금액</th><th class="CouponregDate">사용가능일자</th><th class="used">쿠폰 사용하기</th></tr>
                        <c:forEach var="unusedcoupons" items="${unusedcoupons}" varStatus = "Status">
                            <tr>
                                <td class="RestaurantNameIn">${unusedcoupons.getRestaurant().getRName()}</td>
                                <td class="CouponNameIn">${unusedcoupons.getCouponCategory().getCouponName()}</td>
                                <td class="CouponNumberIn">${unusedcoupons.getCouponNumber()}</td>
                                <td class="CouponLimitIn">${unusedcoupons.getCouponCategory().getLimitPrice()}이상</td>
                                <td class="CouponregDateIn">${unusedcoupons.getRegDate()}</td>
                                <td class="usedIn"><button type="submit" value="${unusedcoupons.getId()}" name="cId"  id="usedbtn${unusedcoupons.getId()}" class="usedBtn">사용하기</button></td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
            <div id="usedCouponsWrap">
                <h3>사용 쿠폰</h3>
                <input type="submit" value="돌아가기" id="backbtn">
                <table id="userSaleslisttable">
                    <tr><th class="CouponName">쿠폰명</th><th class="RestaurantName">식당명</th><th class="CouponNumber">쿠폰번호</th><th class="CouponLimitIn">제약사항</th><th class="CouponregDate">사용가능일자</th></tr>
                    <c:forEach var="usedcoupons" items="${usedcoupons}" varStatus = "Status">
                        <tr>
                            <td class="CouponNameIn">${usedcoupons.getCouponCategory().getCouponName()}</td>
                            <td class="RestaurantNameIn">${usedcoupons.getRestaurant().getRName()}</td>
                            <td class="CouponNumberIn">${usedcoupons.getCouponNumber()}</td>
                            <td class="CouponLimitIn">${usedcoupons.getCouponCategory().getLimitPrice()}</td>
                            <td class="CouponregDateIn">${usedcoupons.getRegDate()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>
</body>
</html>

<!--<script>
      	$(".usedBtn").click(function(e){
    		e.preventDefault();
    		usedPopupCoupon("usedCouponPopup");
    		})
      	
      	function usedPopupCoupon(Url){
            let windowWidth = window.screen.width;
            let windowHeight = window.screen.height;
            
            let popupX = (windowWidth/2) - 630;
            let popupY = (windowHeight/2) -275;
            
            let popUpdateUrl = "http://localhost:8080/pickmeal/" + Url;
            let popUpdateOption = "width=630px, height=550px, top=" + popupY + "px, left=" + popupX + "px";
            let popUpdateTitle = "쿠폰 사용"
            
            if(Url == "usedCouponPopup" ) {
                window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
            }
            let usedCouponPopup = document.usedCouponPopup;
            usedCouponPopup.target = popUpdateTitle;
            usedCouponPopup.action = popUpdateUrl;
            usedCouponPopup.method ="get";
            
            usedCouponPopup.submit();
        }
      	</script> -->