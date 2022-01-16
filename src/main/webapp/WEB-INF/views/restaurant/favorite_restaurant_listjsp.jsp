<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>
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
        /*전체 목록*/
        #favoritelistWrap{width: 70%; height: 750px; background-color: aqua; margin: 0 auto;}
        /*전체중에 왼쪽 리스트 목록Wrap*/
        #favoritelist{width: 29%; height: 750px; background-color: red; float:left; display: scroll; overflow: scroll;}
        #favoritelistMName{margin-top: 30px; font-size: 40px; text-indent: 30px; font-family: 'DungGeunMo';}
        /*찜 식당 하나하나 목록*/
        .restaurantWrap{width: 320px; height: 110px; background-color: blue; margin-left: 20px; margin-top: 30px;}
        /*찜 식당 한개 중 식당 정보*/
        .restaurantInfoWrap{overflow: hidden; text-overflow: ellipsis; position: relative; width: 220px; height: 110px; background-color: cadetblue; float: left;}
        .frIncubate{position: absolute; top: 0; left: 0; width: 220px; height: 110px;}
        .frName{overflow: hidden; text-overflow: ellipsis; font-size: 20px; width: 180px; height: 110px; line-height: 110px; text-align: left; font-family: 'DungGeunMo';}
        .frRadio{display: none;}
        /*삭제버튼*/
        .deleteBtnWrap{width: 100px; height: 110px; background-color: cadetblue; float: left;}
        .deleteBtn{width: 100px; height: 60px; margin-top : 25px; background-color: green; border: 0px;}

        /*kakaoMapWrap*/
        #kakaoMapWrap{width: 67%; height: 750px; background-color: crimson; margin-left: 30px; float:left;}
        /*카카오 맵*/
        #kakaoMap{ width: 500px; height: 500px; border-radius: 50%; background-color: darkgreen; margin: 30px auto 30px;}
        #detailMName{margin-top: 30px; font-size: 40px; text-indent: 100px; font-family: 'DungGeunMo';}
        /*식당 디테일*/
        #restaurantDetail{width: 500px; height: 200px; margin: 0 auto;}

        /*식당 디테일 식당 이름*/
        #detailName{text-align: center; font-size: 30px;}
        /*식당 디테일 식당 주소*/
        #detailAddress{text-align: center; font-size: 25px;}


    </style>

</head>
<body>
	<section id="favoritelistWrap">
		<div id="favoritelist">
			<h3 id="favoritelistMName">찜 식땅</h3>
            <c:forEach var="frlist" items="${frlist}">
                <form>
                    <div class="restaurantWrap">
                        <div class="restaurantInfoWrap">
                        	<p class="frName">${frlist.getRestaurant().getRName()}</p>
                            <input type="radio" id="label${frlist.getId()}" value="${frlist.getId()}" class="frRadio">
                            <label id="inlabel${frlist.getId()}" for="label${frlist.getId()}" class="frIncubate" ></label>
                            <input type="hidden" value="${frlist.getRestaurant().getLat()}" id="lat${frlist.getId()}">
                            <input type="hidden" value="${frlist.getRestaurant().getLng()}" id="lng${frlist.getId()}">
                            <input type="hidden" value="${frlist.getRestaurant().getAddress()}"id="address${frlist.getId()}" >
                        </div>
                        <div class="deleteBtnWrap">
                            <input type="button" value="삭제하기" id="deleteBtn${frlist.getId()}" class="deleteBtn">
                        </div>
                    </div>
                </form>
			</c:forEach>
		</div>
        <div id="kakaoMapWrap">
            <h3 id="detailMName">땅 찾기</h3>
            <div id="kakaoMap"></div>
            <div id="restaurantDetail">
                <p id="detailName"></p>
                <p id="detailAddress">식당 주소</p>
            </div>
        </div>
	</section>
</body>
</html>