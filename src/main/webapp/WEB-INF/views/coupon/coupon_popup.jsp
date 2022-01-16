<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠폰사용 팝업창</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<section>
        <div>
        	<form action="usedCoupon" method="Post">
        		<input type="hidden"  name="close" value="${close}"/>
                <p id="RestaurantName">${coupon.getRestaurant().getRName()}</p>
                <img src="" alt="" width="300px" height="300px"/>
                <p>${coupon.getCouponCategory().getCouponName()} 쿠폰</p>
                <p>${coupon.getCouponNumber()}</p>
                <p>${coupon.getCouponCategory().getLimitPrice()}이상 결제가능</p>
                <input type="hidden" value="${coupon.getId()}" name="couponid">
                <button type="submit">USE COUPON</button>
            </form>
        </div>
    </section>
    <script>
    	window.onload = function() {
			if($("input[name=close]").val() == "close"){
				opener.parent.reloadPage();
				self.close();
			}
		}
    </script>
</body>
</html>