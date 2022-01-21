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
        #leftdiv{width: 400px; height: 750px; background-color: chocolate; margin-top: 20px; float: left; overflow:scroll;}
        #leftdiv h3{margin-top: 10px; font-size: 30px; line-height: 35px;}
        
        /*왼쪽 메인 div*/
        .vrdiv{margin-left: 40px; margin-top: 30px; width: 300px; height: 170px; background-color: cornflowerblue;}
        /*왼쪽 중 식당정보*/
        .vrmain{position: relative; width: 300px; height: 130px; background-color: crimson; float: left;}
        .mainInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
        
        /*버튼 Wrap*/
        .buttonWrap{width: 300px;height: 40px; background-color: gold; float: left;}
        
        /*찜버튼*/
        .favoritebuttonWrap{position: relative; width: 100px; height: 40px; background-color: darkblue; float: left; text-align: center; line-height: 40px; overflow: hidden;}
        .favoriteInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
        
        /*리뷰버튼*/
        .reviewbuttonWrap{position: relative; width: 100px; height: 40px; background-color: darkcyan; float: left; text-align: center; line-height: 40px; overflow: hidden;}
        .reviewInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
        
        /*삭제버튼*/
        .removebuttonWrap{position: relative; width: 100px; height: 40px; background-color: gold; float: left; text-align: center; line-height: 40px; overflow: hidden;}
        .removebuttonWrap:hover{background-color: white;}
        .removeInlabel{position: absolute; top: 0; left: 0; width: 100%; height: 100%;}
        
        /*오른쪽 화면*/
        #rightdiv{width: 780px; height: 750px; background-color: green; margin-top: 20px; margin-left:30px; float: left;}
        #rightdiv h3{margin-top: 10px; font-size: 30px; line-height: 35px;}
        /*리뷰하기 내부*/
        #Reviewcheck{width: 100%; height: 525px; background-color: greenyellow;}
        #reviewRName{margin-top: 20px; text-align: center; width: 100%; height: 50px;}
        /*리뷰 한개 한개*/
        .checkboxWrap{position: relative; margin : 0 auto; width: 330px; margin-top: 10px; height: 50px; 
                background-color: honeydew; margin-left: 45px; margin-top: 30px; float: left; background-color: beige;}
        /*리뷰 이모티콘*/
        .reviewImg{height: 40px; width: 40px; margin-top: 5px; margin-left: 10px; float: left;}
        /*체크박스*/
        .reviewCheckbox{display: none;}
        /*체크박스 외부 라벨*/
        .checklabel{position: absolute; top: 0; left: 0; width: 300px; height: 50px;}
        .reviewMessage{width: 250px; height: 50px; line-height: 50px; float: left; margin-left: 10px; font-size: 15px;}
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
                <form action="" method="post">
                	<c:forEach var="vrlist" items="${vrlist}" varStatus="status">
	                    <div class="vrdiv" id="vrdivs${vrlist.getId()}">
	                        <div class="vrmain">
	                            <input id="restaurantNameis${vrlist.getId()}" type="text" class="vrName" value="${vrlist.getRestaurant().getRName()}" disabled/>
	                            <input type="text" class="vrRegDate" value="${vrlist.getRegDate()}" disabled/>
	                            <input type="radio" id="mainlabel${vrlist.getId()}" class="vrRadio" name="vrmainradio"  value="${vrlist.getId()}">
	                            <label id="mainInlabel${vrlist.getId()}" for="mainlabel${vrlist.getId()}" class="mainInlabel"></label>
	                        </div>
	                        <div class="buttonWrap">
		                        <div class="favoritebuttonWrap" id="jjimdiv${vrlist.getId()}">
			                        <c:choose>
			                        	<c:when test="${flist.get(status.index) eq 'false'}">
				                        	<input type="hidden" value="${flist.get(status.index)}">
				                            <input type="text" class="gofavorite" value="찜하기" disabled/>
				                            <input type="radio" id="favoritelabel${vrlist.getId()}" class="vrfavoriteradio" name="vrfavoriteradio" value="${vrlist.getId()}" onclick="jjimrestaurant(this)">
				                            <label id="favoriteInlabel${vrlist.getId()}" for="favoritelabel${vrlist.getId()}" class="favoriteInlabel"></label>
			                        	</c:when>
			                        </c:choose>
		                        </div>
		                        <div class="reviewbuttonWrap" id="reviewdiv${vrlist.getId()}">
		                        	<c:choose>
		                        		<c:when test="${vrlist.isReview() eq 'false'}">
		                        			<input type="hidden" id="restaurantrealid${vrlist.getId()}"value="${vrlist.getRestaurant().getId()}"/>
			                            	<input type="text" class="goreview" value="리뷰하기" disabled/>
			                            	<input type="radio" id="reviewlabel${vrlist.getId()}" class="reviewBtn" name="vrreviewradio" value="${vrlist.getId()}" onclick="reviewClick(this)">
			                            	<label id="reviewInlabel${vrlist.getId()}" for="reviewlabel${vrlist.getId()}" class="reviewInlabel"></label>
		                            	</c:when>
		                            </c:choose>
		                        </div>
		                        <div class="removebuttonWrap" id="removediv${vrlist.getId()}">
			                        <input type="text" class="goremove" value="삭제하기" disabled/>
			                        <input type="radio" id="removelabel${vrlist.getId()}" class="reviewBtn" name="removeradio" value="${vrlist.getId()}" onclick="removeClick(this)">
			                    	<label id="removeInlabel${vrlist.getId()}" for="removelabel${vrlist.getId()}" class="removeInlabel"></label>
		                        </div>
	                        </div>
	                    </div>
                    </c:forEach>
                </form>
            </div>
            <div id="rightdiv">
            	<h3 >리뷰 하기</h3>
                <form action="reviewSeccess" method="post">
                    <div id="Reviewcheck">
                    	<input type="hidden" id="visitedRestaurantId" name="visitedRestaurantId" value=""/>
                    	<input type="hidden" id="submititem" name="restaurantId" value=""/>
                        <input type="text" id="reviewRName" value=""/>
                        <div id="bathroomWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="bathroomBtn" class="reviewCheckbox" name="bathroom" value="0" />
                            <label id="bathroomin" for="bathroomBtn" class="checklabel"></label>
                            <p class="reviewMessage">3시간에 한 번씩 화장실 청소하는 듯</p>
                        </div>
                        <div id="kindWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="kindBtn" class="reviewCheckbox" name="kind" value="0" />
                            <label id="kindin" for="kindBtn" class="checklabel"></label>
                            <p class="reviewMessage">너무너무 친절해서 부담스러움</p>
                        </div>
                        <div id="specialdayWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="specialdayBtn" class="reviewCheckbox" name="specialDay" value="0" />
                            <label id="specialdayin" for="specialdayBtn" class="checklabel"></label>
                            <p class="reviewMessage">너와 나의 특별한 날에</p>
                        </div>
                        <div id="cleanWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="cleanBtn" class="reviewCheckbox" name="clean" value="0" />
                            <label id="cleanin" for="cleanBtn" class="checklabel"></label>
                            <p class="reviewMessage">걍 먼지 한톨 없음ㅇㅇ</p>
                        </div>
                        <div id="parkingWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="parkingBtn" class="reviewCheckbox" name="parking" value="0" />
                            <label id="parkingin" for="parkingBtn" class="checklabel"></label>
                            <p class="reviewMessage">초보운전 주차 쌉가능</p>
                        </div>
                        <div id="goodgroupWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="goodgroupBtn" class="reviewCheckbox" name="goodgroup" value="0"/>
                            <label id="goodgroupin" for="goodgroupBtn" class="checklabel"></label>
                            <p class="reviewMessage">친구들끼리 단체로 수다떨기 좋아요</p>
                        </div>
                        <div id="aloneWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="aloneBtn" class="reviewCheckbox" name="alone" value="0" "/>
                            <label id="alonein" for="aloneBtn" class="checklabel"></label>
                            <p class="reviewMessage">혼자가서 둘이 나올 수 있어요</p>
                        </div>
                        <div id="bigWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="bigBtn" class="reviewCheckbox" name="big" value="0" />
                            <label id="bigin" for="bigBtn" class="checklabel"></label>
                            <p class="reviewMessage">직원이 날 못찾을 정도의 크기</p>
                        </div>
                        <div id="interiorWrap" class= "checkboxWrap">
                            <img src="/pickmeal/resources/img/restaurant/review/icon_heart.png" alt="" class="reviewImg">
                            <input type="checkbox" id="interiorBtn" class="reviewCheckbox" name="interior" value="0"/>
                            <label id="interiorin" for="interiorBtn" class="checklabel"></label>
                            <p class="reviewMessage">사진찍기 조아욤</p>
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