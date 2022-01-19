<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/visited_restaurant/visited_restaurant_list.js" defer></script>
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
        /*페이지*/
        section{width: 70%; height: 800px; background-color: aqua; margin: 0 auto;}
        #divName{width: 100%; height: 30px; font-size: 35px; line-height: 40px;}
        /*왼쪽*/
        #leftdiv{width: 40%; height: 750px; background-color: chocolate; margin-top: 20px; float: left;}
        #leftdiv h3{margin-top: 10px; font-size: 30px; line-height: 35px;}
        
        /*왼쪽 메인 div*/
        .vrdiv{margin-left: 30px; margin-top: 30px; width: 300px; height: 150px; background-color: cornflowerblue;}
        /*왼쪽 중 식당정보*/
        .vrmain{position: relative; width: 200px; height: 150px; background-color: crimson; float: left;}
        .mainInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
        
        /*왼쪽 중 위*/
        .vrrighttop{position: relative; width: 100px; height: 75px; background-color: darkblue; float: left;}
        .favoriteInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
        
        /*왼쪽 중 아래*/
        .vrrightbottom{position: relative; width: 100px; height: 75px; background-color: darkcyan; float: left;}
        .reviewInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}

        /*오른쪽 화면*/
        #rightdiv{width: 60%; height: 750px; background-color: green; margin-top: 20px; float: left;}
        #rightdiv h3{margin-top: 10px; font-size: 30px; line-height: 35px;}
        /*리뷰하기 내부*/
        #Reviewcheck{width: 100%; height: 525px; background-color: greenyellow;}
        #reviewRName{margin-top: 20px; text-align: center; width: 100%; height: 50px;}
        /*리뷰 한개 한개*/
        .checkboxWrap{position: relative; margin : 0 auto; width: 300px; margin-top: 10px; height: 50px; 
                background-color: honeydew; margin-left: 40px; margin-top: 30px; float: left; background-color: beige;}
        /*리뷰 이모티콘*/
        .reviewImg{height: 40px; width: 40px; margin-top: 5px; margin-left: 10px;}
        /*체크박스*/
        .reviewCheckbox{display: none;}
        /*체크박스 외부 라벨*/
        .checklabel{position: absolute; top: 0; left: 0; width: 300px; height: 50px;}
        #reviewButtonWrap{width: 100px; height: 50px; margin:0 auto}
        #reviewButton{width: 100px; height: 50px;}
    </style>
</head>
<body>
	<header></header>
    <main>
        <section>
            <h3 id="divName">누구님 방문리스트</h3>
            <div id="leftdiv">
                <h3>내가 간 식당</h3>
                <form action="">
                	<c:forEach var="vrlist" items="${vrlist}" varStatus="status">
	                    <div class="vrdiv">
	                        <div class="vrmain">
	                            <input id="restaurantNameis${vrlist.getId()}" type="text" class="vrName" value="${vrlist.getRestaurant().getRName()}" disabled/>
	                            <input type="text" class="vrRegDate" value="${vrlist.getRegDate()}" disabled/>
	                            <input type="radio" id="mainlabel${vrlist.getId()}" class="vrRadio" name="vrmainradio"  value="${vrlist.getId()}">
	                            <label id="mainInlabel${vrlist.getId()}" for="mainlabel${vrlist.getId()}" class="mainInlabel"></label>
	                        </div>
	                        <div class="vrrighttop" id="jjimdiv${vrlist.getId()}">
		                        <c:choose>
		                        	<c:when test="${flist.get(status.index) eq 'false'}">
			                        	<input type="hidden" value="${flist.get(status.index)}">
			                            <input type="text" class="JJimbutton" value="찜하기" disabled/>
			                            <input type="radio" id="favoritelabel${vrlist.getId()}" class="vrfavoriteradio" name="vrfavoriteradio" value="${vrlist.getId()}" onclick="jjimrestaurant(this)">
			                            <label id="favoriteInlabel${vrlist.getId()}" for="favoritelabel${vrlist.getId()}" class="favoriteInlabel"></label>
		                        	</c:when>
		                        </c:choose>
	                        </div>
	                        <div class="vrrightbottom" id="reviewdiv${vrlist.getId()}">
	                        	<c:choose>
	                        		<c:when test="${vrlist.isReview() eq 'false'}">
	                        			<input type="hidden" id="restaurantrealid${vrlist.getId()}"value="${vrlist.getRestaurant().getId()}"/>
		                            	<input type="text" class="리뷰하기" value="리뷰하기" disabled/>
		                            	<input type="radio" id="reviewlabel${vrlist.getId()}" class="reviewBtn" name="vrreviewradio" value="${vrlist.getId()}" onclick="reviewClick(this)">
		                            	<label id="reviewInlabel${vrlist.getId()}" for="reviewlabel${vrlist.getId()}" class="reviewInlabel"></label>
	                            	</c:when>
	                            </c:choose>
	                        </div>
	                    </div>
                    </c:forEach>
                </form>
            </div>
            <div id="rightdiv">
            	<h3 >리뷰 하기</h3>
                <form action="">
                    <div id="Reviewcheck">
                    	<input type="hidden" id="submititem"value=""/>
                        <input type="text" id="reviewRName" value=""/>
                        <div id="bathroomWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="bathroomBtn" class="reviewCheckbox" name="BATHROOM" value="0"/>
                            <label id="bathroomin" for="bathroomBtn" class="checklabel"></label>
                        </div>
                        <div id="kindWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="kindBtn" class="reviewCheckbox" name="KIND" value="0"/>
                            <label id="kindin" for="kindBtn" class="checklabel"></label>
                        </div>
                        <div id="specialdayWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="specialdayBtn" class="reviewCheckbox" name="SPECIALDAY" value="0"/>
                            <label id="specialdayin" for="specialdayBtn" class="checklabel"></label>
                        </div>
                        <div id="cleanWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="cleanBtn" class="reviewCheckbox" name="CLEAN" value="0"/>
                            <label id="cleanin" for="cleanBtn" class="checklabel"></label>
                        </div>
                        <div id="parkingWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="parkingBtn" class="reviewCheckbox" name="PARKING" value="0"/>
                            <label id="parkingin" for="parkingBtn" class="checklabel"></label>
                        </div>
                        <div id="goodgroupWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="goodgroupBtn" class="reviewCheckbox" name="GOODGROUP" value="0"/>
                            <label id="goodgroupin" for="goodgroupBtn" class="checklabel"></label>
                        </div>
                        <div id="aloneWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="aloneBtn" class="reviewCheckbox" name="ALONE" value="0"/>
                            <label id="alonein" for="aloneBtn" class="checklabel"></label>
                        </div>
                        <div id="bigWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="bigBtn" class="reviewCheckbox" name="BIG" value="0"/>
                            <label id="bigin" for="bigBtn" class="checklabel"></label>
                        </div>
                        <div id="interiorWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="interiorBtn" class="reviewCheckbox" name="INTERIOR" value="0"/>
                            <label id="interiorin" for="interiorBtn" class="checklabel"></label>
                        </div>
                    </div>
                    <div id="reviewButtonWrap">
                    	<input type="hidden" value="" id="clickVal">
                        <button type="submit" id="reviewButton">리뷰제출</button>
                    </div>
                </form>
            </div>
        </section>
    </main>
    <footer></footer>
</body>
</html>