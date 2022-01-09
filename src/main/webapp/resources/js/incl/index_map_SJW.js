let nowLat, nowLng, map, geocoder, marker, ps, nowAddress, circle;
let wtmY, wtmX; // y = lat, x = lng
let earth = 6371000;
	
// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
    	nowLat = position.coords.latitude, // 위도
    	nowLng = position.coords.longitude; // 경도

        let locPosition = new kakao.maps.LatLng(nowLat, nowLng), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
        
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
        displayMarker(locPosition, message);  
      });    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    let locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    displayMarker(locPosition, message);
}
/*
$("button[name=radius]").click(function() {
	let diffM = Number($(this).val());
	let json = {"nowLat" : nowLat, "nowLng" : nowLng, "diffM" : diffM};
	
	$.ajax({
		url: getContextPath() + "/calculateMap",
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
*/

// 지도에 마커와 인포윈도우를 표시하는 함수입니다
function displayMarker(locPosition, message) {
    // 마커를 생성합니다
    marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    let iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    let infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
}

//button[name=radius]
$('.gamePlayBtn').on('click', function(e){
	

	e.preventDefault();
	let popupWidth = 600;
	let popupHeight = 600;
	let radius = $(".radius:checked").val();
	let category = $(".category:checked").val();
	
	
	console.log(radius);
	console.log(category);

	let popupX = (document.body.offsetWidth / 2) - (popupWidth / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음

	let popupY = (document.body.offsetHeight / 2) - (popupHeight / 2);
	// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	
	window.open("openGamePopUp", "게임하기", "width=600, height = 600, top=" + popupY + ", left=" + popupX + ""); //선언과 초기화 동시에 해도 됨
	
	
	console.log(radius);
	console.log(category);
	let popUpUrl = getContextPath() + "/sendDataToPopUp";
	
	let gameDataForm = document.gameDataForm;
				gameDataForm.action = popUpUrl;
				gameDataForm.target = "게임하기";
				gameDataForm.method = "get";
				
				gameDataForm.submit();
				
	
	/*
	let json = { "radius": radius, "category": category};
	$.ajax({
		url: getContextPath() + "/sendDataToPopUp",
		type: "get",
		data: json,
		dataType: 'json',
		success: function(){
			console.log("successssssssssssss")
			window.open("openGamePopUp", "게임하기", "width=600, height = 600, top=" + popupY + ", left=" + popupX + ""); //선언과 초기화 동시에 해도 됨
		}
		
	}) */	
})
	
//	window.onload = function() 

	/*
	$("#updateMenu").click(function(e) {
				e.preventDefault();
				if ($("input[name=choiceItem]:checked").val() == null) {
					alert("먼저 메뉴를 선택해주세요!");
				} else {
					popupUpdateMenu("popUpdateMenu", 600, 300);
				}
			})
function popupUpdateMenu(goUrl, popWidth, popHeight) {
	let windowWidth = window.screen.width;
	let windowHeight = window.screen.height;
	
	let popupX = (windowWidth/2) - popWidth/2;
	let popupY = (windowHeight/2) - popHeight/2;
	
	let popUpdateMenuUrl = getContextPath() + "/menu/" + goUrl;
	let popUpdateMenuOption = "width=" + popWidth + "px, height=" + popHeight + "px, top=" + popupY + "px, left=" + popupX + "px";
	let popUpdateMenuTitle = "메뉴 업데이트";
	
	window.open(popUpdateMenuUrl, popUpdateMenuTitle, popUpdateMenuOption);
	
	let updateMenuForm = document.updateMenuForm;
	updateMenuForm.action = popUpdateMenuUrl;
	updateMenuForm.target = popUpdateMenuTitle;
	updateMenuForm.method = "post";
	
	updateMenuForm.submit();
	
} */
	
/*	
function popupUpdateMenu(goUrl, popWidth, popHeight) {
	let windowWidth = window.screen.width;
	let windowHeight = window.screen.height;
	
	let popupX = (windowWidth/2) - popWidth/2;
	let popupY = (windowHeight/2) - popHeight/2;
	
	let popUpdateMenuUrl = getContextPath() + "/menu/" + goUrl;
	let popUpdateMenuOption = "width=" + popWidth + "px, height=" + popHeight + "px, top=" + popupY + "px, left=" + popupX + "px";
	let popUpdateMenuTitle = "메뉴 업데이트";
	
	window.open(popUpdateMenuUrl, popUpdateMenuTitle, popUpdateMenuOption);
	
	let updateMenuForm = document.updateMenuForm;
	updateMenuForm.action = popUpdateMenuUrl;
	updateMenuForm.target = popUpdateMenuTitle;
	updateMenuForm.method = "post";
	
	updateMenuForm.submit();
	}
*/
