<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인-쿠폰 리스트</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
        body, html {height: 100%;}
        body, h1, h2, h3, h4, h5, h6, p, ul, dl, dd, figure, fieldset, input, th, td,img,div,p {margin: 0; padding: 0;}
        body, input, button {font-family: 'Noto Sans KR';}
        input[type="text"] {text-indent: 5px;}
        input[type="submit"] {cursor: pointer;}
        li {list-style: none;}
        a {text-decoration: none; color: #000;}
        address, small, em, th {font: normal normal 1em 'Noto Sans KR', sans-serif;}
        .hidden {position: absolute; left: -9999px;}
        .text_hidden {text-indent: -9999px;}
        fieldset, img {border: 0;}
        table {border-collapse: collapse;}
        body{
        font-family: 'Noto Sans KR', sans-serif;    
        }
        @font-face {
            font-family: 'DungGeunMo';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/DungGeunMo.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        .hiddenclass{display: none;}
        #CouponList{width: 60%; height: 100%; margin: 0 auto; background-color: #a5a5a5;}
        #unusedCouponsWrap{width: 335px; height: 800px; float: left; overflow: scroll;}
        #usedCouponsWrap{width: 670px; height: 800px;float: left; overflow: scroll; border-left: 1px solid #000; margin-left:20px;}
        #nickName{font-size: 30px; margin-top: 20px; margin-bottom: 20px;}
        .couponboolean{ font-size: 25px;  height: 40px; line-height: 50px;  margin-top: 30px; margin-bottom: 10px;}
        .couponWrap{ position: relative; width: 270px; height: 130px;  padding: 10px; float: left; margin-left: 25px; margin-top: 20px; border:1px solid #5a5a5a; border-radius: 25px;}
        .rName{width: 270px; height: 30px;  margin-top: 10px; font-family: 'DungGeunMo';}
        .cName{width: 100px; height: 30px;  float: left; margin-top: 10px; font-family: 'DungGeunMo';}
        .cNumber{margin-left: 15px; width: 140px; height: 30px;  float: left; margin-top: 10px; font-family: 'Noto Sans KR', sans-serif;border: 1px solid #000}
        .cLimit{width: 160px; height: 30px; float: left; margin-top: 10px; font-family: 'DungGeunMo';}
        .cregDate{margin-left: 15px; width: 85px; height: 30px;  float: left; margin-top: 10px; font-family: 'DungGeunMo';}
        .couponIncubate{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
    </style>
</head>
<body>
	<section id="CouponList">
            <h2>${member.nickName}회원님 쿠폰</h2>
            	<div id="unusedCouponsWrap">
            		<form method ="get" name="usedCouponPopup">
                	<h3>미사용 쿠폰</h3>
                        <c:forEach var="unusedcoupons" items="${unusedcoupons}">
                            <div class="couponWrap">
                        		<p class="rName">"${unusedcoupons.getRestaurant().getRName()}"</p>
                        		<p class="cName">${unusedcoupons.getCouponCategory().getCouponName()} 쿠폰</p>
                        		<p class="cNumber">${unusedcoupons.getCouponNumber()}</p>
                        		<p class="cLimit">${unusedcoupons.getCouponCategory().getLimitPrice()} 이상 사용가능</p>
                        		<p class="cregDate">${unusedcoupons.getRegDate()}</p>
                        		<label id="inlabel${unusedcoupons.id}" for="label${unusedcoupons.id}" class="couponIncubate"></label>
                        		<input id="label${unusedcoupons.id}" class="hiddenclass" type="radio" name="couponId" value="${unusedcoupons.id}" />
                    		</div>
                    		
                        </c:forEach>
                	</form>
            	</div>
            <div id="usedCouponsWrap">
                <h3>사용 쿠폰</h3>
                <table id="userSaleslisttable">
                    <tr class="ColumnWrap">
	                    <th class="Column CouponName">쿠폰명</th>
	                    <th class="Column RestaurantName">식당명</th>
	                    <th class="Column CouponNumber">쿠폰번호</th>
	                    <th class="Column CouponregDate">사용가능일자</th>
                    </tr>
                    <c:forEach var="usedcoupons" items="${usedcoupons}">
                        <tr class="ColumnInWrap">
                            <td class="CouponNameIn ColumnIn">${usedcoupons.getCouponCategory().getCouponName()}</td>
                            <td class="RestaurantNameIn ColumnIn">${usedcoupons.getRestaurant().getRName()}</td>
                            <td class="CouponNumberIn ColumnIn">${usedcoupons.getCouponNumber()}</td>
                            <td class="CouponregDateIn ColumnIn">${usedcoupons.getRegDate()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>
</body>
</html>

<script>
		/*
		$(".couponIncubate").click(function(){
			$("#submitBtn").click();
		})*/
		
      	$(".hiddenclass").click(function(e){
    		e.preventDefault();
    		console.log($('.hiddenclass').val());
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
            	
            window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
            
            
            let usedCouponPopup = document.usedCouponPopup;
            
            usedCouponPopup.target = popUpdateTitle;
            usedCouponPopup.action = popUpdateUrl;
            usedCouponPopup.method ="get";
            
            usedCouponPopup.submit();
            
        }
		
      	function reloadPage() {
            location.reload();
        }
</script>