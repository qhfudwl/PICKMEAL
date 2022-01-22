/**
 * 초기 화면의 기본 값 입니다.
 */
var container = document.getElementById('kakaoMap'); //지도를 담을 영역의 DOM 레퍼런스

var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options);

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">현재위치 인가요!?</div>'; // 인포윈도우에 표시될 내용입니다
        
        // 마커와 인포윈도우를 표시합니다
        displayMarker(locPosition, message);
            
      });
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = '현재위치를 알 수 없어요..'
        
    displayMarker(locPosition, message);
}

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}    


//식당 위치를 띄워줄 때 사용합니다.
function goMapRestaurant(){
		var radioVal = $("input[name='frRadioBtn']:checked").val();
		var address = "address"+radioVal;
		var addressInfo = $("#"+address).val();
		var frName = "frName"+radioVal;
		var frNameInfo = $("#"+frName).val();
		$("input[id='detailAddress']").val(addressInfo);
		$("input[id='detailName']").val(frNameInfo);
		console.log($("input[id='detailAddress']").val());
		
		
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch($("input[id='detailAddress']").val(), function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+frNameInfo+'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    }else{
	console.log($("input[id='detailAddress']").val());
	console.log("찾을 수 없습니다.");
} 
});    
}

//찜식당 삭제 할 때 사용합니당
function deletefavorite(){
	var radioVal2 = $("input[name='frdeleteradioBtn']:checked").val();
		
		console.log(radioVal2);
	$.ajax({
		url: "http://localhost:8080/pickmeal/deleteFavoriteRestaurant",
		type: "post",
		data: JSON.stringify({"id":  radioVal2}),
		contentType:'application/json; charset=utf-8',
		
		success: function(data){
			$("#restaurantWrap"+radioVal2).remove();
		}
	});
		
}