<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<!-- 우편번호서비스 -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>게시글쓰기</title>
<style>
#writeArea {
	background: #f2f2f2;
	width: 600px;
	height: 800px;
}
</style>
<script type="text/javascript">
	function onClickBtn() {
		console.log($('#writeArea').html())
	}

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
    position: new daum.maps.LatLng(37.537187, 127.005476),
    map: map
});


function sample5_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = data.address; // 최종 주소 변수

            // 주소 정보를 해당 필드에 넣는다.
            document.getElementById("sample5_address").value = addr;
            // 주소로 상세 정보를 검색
            geocoder.addressSearch(data.address, function(results, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    var coords = new daum.maps.LatLng(result.y, result.x);
                    // 지도를 보여준다.
                    mapContainer.style.display = "block";
                    map.relayout();
                    // 지도 중심을 변경한다.
                    map.setCenter(coords);
                    // 마커를 결과값으로 받은 위치로 옮긴다.
                    marker.setPosition(coords)
                }
            });
        }
    }).open();
}
</script>
</head>
<body>
	<div id="writeArea" contentEditable="true">
		안녕하세용 <img src="/pickmeal/resources/img/posting/sky.jpg" alt="">
	</div>
	<form action="completeWritingPost" method="post">
		<input type="text" id="sample5_address" placeholder="주소"> 
		<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
		
		<input type="time" name="time" /> 
		<button type="submit" onclick="onClickBtn()">버튼</button>
	</form>
		<div id="map" style="width: 800px; height: 600px; margin-top: 10px; display: none"></div>
</body>
</html>