let nowLat, nowLng, map, geocoder, sMarker, eMarker, ps, nowAddress, circle;
let wtmY, wtmX; // y = lat, x = lng
let sInfoW, eInfoW;
let earth = 6371000;

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
    	nowLat = position.coords.latitude, // 위도
    	nowLng = position.coords.longitude; // 경도

		let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
	    center: new kakao.maps.LatLng(nowLat, nowLng), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
		};
		map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		geocoder = new kakao.maps.services.Geocoder(); // 주소-좌표 변환 객체를 생성합니다
		ps = new kakao.maps.services.Places(); // 장소 검색 객체를 생성합니다
		
		geocoder.coord2Address(nowLng, nowLat, function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				nowAddress = result[0].address.address_name;
				$("#memberPosition").text(nowAddress);
			}
		});
		
        // 마커와 인포윈도우를 표시합니다
        displayStartMarker(); // user position
		displayArriveMarker(nowLat, nowLng); // click position
		// click marker hide
		eMarker.setVisible(false);
		$("#eInfoW").hide();
/*
		// 지도에 표시할 원을 생성합니다
		circle = new kakao.maps.Circle({
		    center : new kakao.maps.LatLng(nowLat, nowLng),  // 원의 중심좌표 입니다 
		    radius: 0, // 미터 단위의 원의 반지름입니다 
		    strokeWeight: 1, // 선의 두께입니다 
		    strokeColor: '#f23f3f', // 선의 색깔입니다
		    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		    strokeStyle: 'solid', // 선의 스타일 입니다
		    fillColor: '#ffecec', // 채우기 색깔입니다
		    fillOpacity: 0.5  // 채우기 불투명도 입니다   
		});
		
		circle.setMap(map);
*/    

		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    let latlng = mouseEvent.latLng;

			// 실제 동작 시에는 클릭이벤트 대신 여기다가 좌표값을 넣어서 팝업이 닫힐 때 동작시키면 된다*****************
			displayRestaurantInfo(latlng.getLat(), latlng.getLng());
		});
	});
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    let locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}

$("button[name=radius]").click(function() {
	let diffM = Number($(this).val());
	let json = {"nowLat" : nowLat, "nowLng" : nowLng, "diffM" : diffM};
	
	$.ajax({
		url: "calculateMap",
		type: "get",
		data: json,
		dataType: 'json',
		success: function(data){
			let points = [
			    new kakao.maps.LatLng(data[0], data[1]),
			    new kakao.maps.LatLng(data[2], data[3])
			];
			let bounds = new kakao.maps.LatLngBounds();
			for(i=0; i<points.length; i++){
				bounds.extend(points[i]);
			}
			map.setBounds(bounds);
			//circle.setRadius(diffM);
		}
	})
	
	
})

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function setMarkerImg(imgName, marker) {
	let imageSrc = getContextPath() + '/resources/img/map/' + imgName + '.png', // 마커이미지의 주소입니다    
    imageSize = new kakao.maps.Size(40, 40), // 마커이미지의 크기입니다
    imageOption = {offset: new kakao.maps.Point(20, 35)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
 
	let markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption)
	
    // 마커를 생성합니다

    marker.setImage(markerImage);
}

function displayArriveMarker(lat, lng) {
	let locPosition = new kakao.maps.LatLng(lat, lng), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        message = '<div id="eInfoW">여기로 가시면돼요!!</div>'; // 인포윈도우에 표시될 내용입니다
	eMarker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    });

	// 커스텀 오버레이를 생성합니다
	eInfoW = new kakao.maps.CustomOverlay({
	    position: locPosition,
	    content: message   
	});
	
	// 커스텀 오버레이를 지도에 표시합니다
	eInfoW.setMap(map);
	
	setMarkerImg("emarker", eMarker);
}

function displayStartMarker() {
	let locPosition = new kakao.maps.LatLng(nowLat, nowLng), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
        message = '<div id="sInfoW">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
	sMarker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    });

	// 커스텀 오버레이를 생성합니다
	sInfoW = new kakao.maps.CustomOverlay({
	    position: locPosition,
	    content: message   
	});
	
	// 커스텀 오버레이를 지도에 표시합니다
	sInfoW.setMap(map);
	
	setMarkerImg("smarker", sMarker);
}

// 좌표를 인자로 넣어 식당 정보를 가져와 표시하는 함수
// 이 함수를 발생시키면 된다.===============================================
function displayRestaurantInfo(lat, lng) {
	let latlng = new kakao.maps.LatLng(lat, lng);
	// 마커 위치를 좌표 위치로 옮깁니다
    eMarker.setPosition(latlng);
 	// click marker show
	eMarker.setVisible(true);
	$("#eInfoW").show();
	eInfoW.setPosition(latlng);
	// 식당 정보 페이지 url 가져오기
	let callback = function(result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	        console.log(result[0].address.address_name);
		
			$.ajax({
				url: "https://dapi.kakao.com/v2/local/search/keyword.json?query="
				 + result[0].address.address_name + "&x=" + latlng.getLng() + "&y=" + latlng.getLat(),
				type: "get",
				headers: {"Authorization" : "KakaoAK f3ae310b0340ac2069e5e0685938a62b"},
				dataType: "json",
				success: function(data){
					$("#restaurantUrl").attr("src", data["documents"][0].place_url);
				}
			})
	    }
	};
	geocoder.coord2Address(latlng.getLng(), latlng.getLat(), callback);
}
/*
$("#gameDone").click(function() {
	displayFindWay("서울시청", "광화문");
})

// https://map.kakao.com/?sName=서울시청&eName=광화문
function displayFindWay(sName, eName) {
	let findUrl = 'https://map.kakao.com/?sName=' + sName + '&eName=' + eName;
	$("#findUrl").attr("src", findUrl);
}
*/