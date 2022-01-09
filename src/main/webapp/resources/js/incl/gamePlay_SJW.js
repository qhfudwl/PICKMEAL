var nowLat, nowLng, map, geocoder, ps, nowAddress, infowindow, 
	locPosition;
let arrLength;
let ranNum;
let ranNumArr= [];
let categorySwitch;

var temp = 0;
let json;

// 마커를 담을 배열입니다
var markers = [];

//var categoryArr = ['간식', '분식', '뷔페', '아시아음식', '양식', '일식', '중식', '패스트푸드', '패밀리레스토랑', '피자', '치킨', '한식'];
var categoryArr = ['한식', '중식'];
var rNameArr = ['이름'];
var	rAddressArr = ['주소'];
var rLatArr = []; //y;
var rLngArr = []; //x;
var rIdArr = []; // 좌표.
// 배열에 값 추가할 때: restaurantArr.push("");


if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
		nowLat = position.coords.latitude, // 위도
		nowLng = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(nowLat, nowLng), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다

		let mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			mapOption = { 
		    center: new kakao.maps.LatLng(nowLat, nowLng), // 지도의 중심좌표
		    level: 3 // 지도의 확대 레벨
			};
		map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
			
			
		geocoder = new kakao.maps.services.Geocoder(); // 주소-좌표 변환 객체를 생성합니다
			
		markers = []; 
		
		// 장소 검색 객체를 생성합니다
		ps = new kakao.maps.services.Places();  
		
		// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
		infowindow = new kakao.maps.InfoWindow({zIndex:1});
		
		// 키워드로 장소를 검색합니다
		searchPlaces(locPosition);

		
		
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
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'
        
    // displayMarker(locPosition, message);
}

// 키워드 검색을 요청하는 함수입니다
function searchPlaces(locPosition) {
	console.log(locPosition);
	console.log(radius);

    var keyword = document.getElementById('keyword').value;
	var radius = document.getElementById('hRadius').value;
	
	var options = {
		//useMapCenter : true,
		location : locPosition,
		radius : radius
	}
	
	console.log(categoryArr);
	
	 if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }
	
	if(keyword == "밥집"){
		for(var j=0; j< categoryArr.length; j++){
			console.log(categoryArr);
			console.log(options);
			categorySwitch = 1;
		ps.keywordSearch(categoryArr[j], placesSearchCB, options);	
		}
	} else{
		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		categorySwitch = 0;
	    ps.keywordSearch( keyword, placesSearchCB, options);
		console.log(categoryArr); 
		console.log(rLatArr);
		console.log(rLngArr);
	}
  
}

// 랜덤으로 5개를 뽑기 위한 함수
function extractFiveIndex(arrLength){
	let aNumberOfRandomNumer;
	
	if(arrLength >= 5){ // 0일 때를 생각해야 함. 
		aNumberOfRandomNumber = 5;
	} else {
		aNumberOfRandomNumber = arrLength;
	}
	for(let k=0; k<arrLength; k++){
				ranNum = Math.floor(Math.random() * arrLength);
				if(ranNumArr.includes(ranNum) == false){
					ranNumArr.push(ranNum);
					if(ranNumArr.length == 5){ // 결과값이 5미만일 경우에는 그 갯수만큼 나와야함.
						break;
					}
				} else {
					k--;
				}
				
			}
			 // 5개를 뽑기 위한 난수.
		console.log(ranNumArr);
	return ranNumArr;
			
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	temp++;
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);
		// 다른거 다 지우고 얘만 살려도 될지도? 

		// console.log(rNameArr);
		// console.log(rAddressArr);
		// 한번만 호출하는 경우에는 여기서 ajax를 쓰든 걍 넘기든 넘기면 됨.
		// 문제는 밥집을 골랐을 때 여러번 호출해야할 경우 어떻게 해야할지를 모르겠어.
		// console.log(rLatArr);
		// console.log(rLngArr);
		
		if(temp == 12){ // 12로 바꿔야 함. 2는 For test.
			console.log(rNameArr);
			console.log(rAddressArr);
			console.log(rLatArr);
			console.log(rLngArr);
			// 잘 들어가는 것 확인 => 여기서 컨트롤러로 넘겨야 한다.
			
			arrLength = rNameArr.length;
			
			console.log(arrLength);
			ranNumArr = extractFiveIndex(arrLength);	
		}
		if(categorySwitch == 0){
			arrLength = rNameArr.length
			ranNumArr = extractFiveIndex(arrLength);
			console.log(ranNumArr);
			console.log(rIdArr);
		}
		
		console.log(ranNumArr);
		console.log(rIdArr);
		
		
		
        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
	//alert 는 없어야 하는게 맞고 return 은 빼야 되지 싶은데. 
        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('menu_wrap'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거합니다
    removeMarker();
    
    for ( var i=0; i<places.length; i++ ) {
		rNameArr.push(places[i].place_name);
		rAddressArr.push(places[i].address_name);
		rLatArr.push(places[i].y);
		rLngArr.push(places[i].x);
		rIdArr.push(places[i].id);
		
		
		
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addMarker(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
            };

            itemEl.onmouseout =  function () {
                infowindow.close();
            };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
		
    }			
	console.log(rNameArr);
	console.log(rAddressArr);

    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}

// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addMarker(position, idx, title) {
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
            marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    marker.setMap(map); // 지도 위에 마커를 표출합니다
    markers.push(marker);  // 배열에 생성된 마커를 추가합니다

    return marker;
}

// 지도 위에 표시되고 있는 마커를 모두 제거합니다
function removeMarker() {
    for( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}

 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }


}

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

//var radius = '<c:out value="${radius}"/>';

