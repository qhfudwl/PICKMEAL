var nowLat, nowLng, map, geocoder, ps, nowAddress, infowindow, 
	locPosition;
let arrLength;
let ranNum;
let ranNumArr= [];
let categorySwitch;
let lengthOfTotalArr;

let gameWrap = document.getElementById("gameWrap");
//let cardUl = document.getElementById("cardUl");

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

let aNumberOfRandomNumer;
let totalArr = new Array();
let finalArr = [];
// 배열에 값 추가할 때: restaurantArr.push("");

//	팝업 뜨고나서 안내메세지 3초 fadeOut
	//$(document).on('load', function(){
//		$('.firstMsgContent').fadeOut(3000);
//	});
	
	$(window).ready(function(){
		$('#gameBtnWrap').hide();
		$('#submitWrap').hide();
		$('.firstMsgContent').fadeOut(2000, function(){
			$('#gameBtnWrap').fadeIn(1000); // fadeOut 이후 Fadein. 
		});
	})
	
	//$(window).on('load', function(){
	//	$('.firstMsgContent').fadeOut(3000);
	//})


	var keyword = document.getElementById('keyword').value;
	var radius = document.getElementById('hRadius').value;
	nowLat = document.getElementById('nowLat').value; 
	nowLng = document.getElementById('nowLng').value;
	
	if(keyword == "밥집"){
		for(var j=0; j< categoryArr.length; j++){
			searchResList(radius, categoryArr[j], nowLat, nowLng);	
		}
	} else{
		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		console.log(nowLat);
		console.log(nowLng);
		searchResList(radius, keyword, nowLat, nowLng);
		
		/*	
		while(allnum.length > 5){
		var movenum = allnum.splice(Math.floor(Math.random() * allnum.length),1)[0]	
		newnum.push(movenum)
		}*/
	}
	
	let diffOfDate = $("diffOfDate").val();
	
	if(diffOfDate == 0){
		// 메세지 날리기.
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
			searchResList(radius, categoryArr[j]);
			console.log(categoryArr);
			console.log(options);
			categorySwitch = 1;
		ps.keywordSearch(categoryArr[j], placesSearchCB, options);	
		}
	} else{
		// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
		categorySwitch = 0;
	    ps.keywordSearch(keyword, placesSearchCB, options);
		console.log(categoryArr); 
		console.log(rLatArr);
		console.log(rLngArr);
	}
}


let categoryCnt = 0;
function searchResList(radius, keyword, lat, lng){		
	console.log(radius);		
	console.log(lat);
	console.log(lng);
	$.ajax({
		url: "https://dapi.kakao.com/v2/local/search/keyword.json?query="
		 + keyword + "&x=" + lng + "&y=" + lat + "&radius=" + radius,
		type: "get",
		headers: {"Authorization" : "KakaoAK f3ae310b0340ac2069e5e0685938a62b"},
		dataType: "json",
		success: function(data){
			categoryCnt++;
			if(data["documents"].length == 0){ // 검색결과가 없는 경우 alert 띄워주고 이후 팝업도 닫기.
				console.log(keyword + " 카테고리에 해당하는 결과가 없습니다. : " + categoryCnt);
			} else{
				for(let m=0; m<data["documents"].length; m++){
					totalArr.push(data["documents"][m]);
				}
				console.log(totalArr);
				console.log(totalArr.length);
				lengthOfTotalArr = totalArr.length;
				console.log(lengthOfTotalArr);
				
				//밥집이든 아니든 안쪽에 겹치는 내용이 많은데 코드 어떻게 하면 좋을지 생각해볼 것.
				if(keyword == "카페" || keyword == "술집" || keyword == "혼밥"){
					console.log("카페, 술집, 혼밥 선택 하면 이리로 온다.");
					console.log(totalArr.length);
					if(totalArr.length >= 5){
						for(let g=1; g<=5; g++){
						var moveData = totalArr.splice(Math.floor(Math.random() * totalArr.length),1)[0];
							console.log(moveData);
							finalArr.push(moveData);
						}
					} else {
						if(totalArr.length == 0){
							console.log("검색 결과가 없습니다.");
						} else{
							for(let g=1; g<=totalArr.length==0; g++){
								var moveData = totalArr.splice(Math.floor(Math.random() * totalArr.length),1)[0];
								console.log(moveData);
								finalArr.push(moveData);
							}
						}
					}
				}
				//12로 바꿔야 함.
				if(categoryCnt == 2){
					console.log("밥집인 경우 이리로 온다.");
					console.log(totalArr.length);
					if(totalArr.length >= 5){
						for(let g=1; g<=5; g++){
							var moveData = totalArr.splice(Math.floor(Math.random() * totalArr.length),1)[0];
							console.log(moveData);
							finalArr.push(moveData);
						}
					} else {
						if(totalArr.length == 0){
							console.log("검색 결과가 없습니다.");
						} else{
							for(let g=1; g<=totalArr.length==0; g++){
								var moveData = totalArr.splice(Math.floor(Math.random() * totalArr.length),1)[0];
								console.log(moveData);
								finalArr.push(moveData);
							}
						}
					}
				}
			}
		}
	})
}

//done 으로 바꿔야함. 
/*
		setTimeout(function() { 
			console.log(totalArr);
			console.log(totalArr[0]);
			console.log(lengthOfTotalArr);
			console.log(totalArr);
			console.log(totalArr.length);
			
			if(totalArr.length >= 5){
				for(let g=1; g<=5; g++){
					var moveData = totalArr.splice(Math.floor(Math.random() * totalArr.length),1)[0];
					console.log(moveData);
					finalArr.push(moveData);
				}
			} else {
				if(totalArr.length == 0){
					console.log("검색결과가 없습니다 From setTimeout");
				} else{
					for(let g=1; g<=totalArr.length==0; g++){
						var moveData = totalArr.splice(Math.floor(Math.random() * totalArr.length),1)[0];
						console.log(moveData);
						finalArr.push(moveData);
					}
				}
			}
			console.log(finalArr);
			console.log(finalArr.length);
			
			
		}, 300)*/

let gameType;
//$.when
$('.gameBtn').on('click', function(e){
	$('#gameBtnWrap').addClass('hidden'); // 클릭하면 카드들 혹은 사다리가 뜨게끔.
	$('#submitWrap').show();
	
	console.log(finalArr);
	var jsonOfFinalArr = JSON.stringify(finalArr);
	console.log(jsonOfFinalArr);
	var sendData = {
		"jsonOfFinalArr" : jsonOfFinalArr,
		"gameType" : $(this).val()
	};
	gameType = $(this).val();
	
	console.log(sendData);
	console.log(jsonOfFinalArr);
	let controllerUrl;
	
	if(gameType == 'L'){
		controllerUrl = "/bringResListForLadderGame";
	} else {
		controllerUrl = "/bringResListForCardGame";
	}
	$.ajax({
		url: getContextPath() + controllerUrl,
		type: "POST",
		data: {"jsonOfFinalArr" : jsonOfFinalArr, "gameType" : $(this).val()},
		dataType: 'json',
		contentType: 'application/x-www-form-urlencoded; charset=euc-kr',
		success: function(data) {
			let indexNum;
			
			// ladder 변수들 
			//let ladder = data.ladder;
			//console.log(ladder);
			let numOfHorLines;
			let numOfVerLines; 	
			
			let sizeOfWidth = 0;
			let sizeOfHeight = 0;
			let posOfHorLine = 0; 
			
			// 카드 게임일 때 카드 생성
			if(gameType == 'C'){
				console.log("what????");
				console.log(data);
			 	
				makeCard(data);
				
			} else if(gameType == 'L'){
				numOfHorLines = data.ladder.length - 2; // 실질 가로줄 갯수
				numOfVerLines = data.ladder[0].length;
				
				if(numOfVerLines == 1 || numOfVerLines == 3 || numOfVerLines == 5){
					sizeOfWidth = 840 / 4; // 210 = 가로줄의 폭
				} else{
					sizeOfWidth = 840 / 3; // 240 = 가로줄의 폭
				}
				
				sizeOfHeight = 500 / (numOfHorLines + 1);
				console.log("세로 : " + data.ladder[0].length);
				console.log("실질 가로줄 갯수 : " + numOfHorLines);
				console.log(data);
				console.log(data.ladder);
				console.log("가로 : " + data.ladder.length);
				console.log("세로 : " + data.ladder[0].length);
				console.log(data.setOfRoute);
				
				console.log(data.ladder[0][0])
				console.log(data.ladder[0][1])
				console.log(data.ladder[0][2])
				console.log(data.ladder[0][3])
				console.log(data.ladder[0][4])
				
				
 				makeLadder(data, sizeOfWidth, sizeOfHeight);
				
				//createDivForCover(); svg1 을 가리는 가리개.
			
				$("#gameWrap").append('<div class="svg2Div"></div>'); // 움직이는 Path svg 를 담기 위한 svgDiv 
				//Collection<List<String>> values = setOfRoute.values();
			}
			
			let firstClick = 0;
			$('.cardUl li').on('click', function(e){
			//	e.prevnetDefault();
				console.log(data[0]);
				$(this).children('.BackSideOfCard').addClass("disappearCard");
						
				if(firstClick == 0){
					$(this).children('.cardDiv').removeClass('cardDivStyle');
					$(this).children('.cardDiv').addClass('pick');
					indexNum = $(this).index();
					makeInputForSubmit(indexNum, data);
					
					// test.....!!!
					let resultRes = data[indexNum];
					console.log(resultRes);
					$.ajax({
						url: getContextPath() + "/bringResResultOfGame",
						type: "POST",
						data: resultRes,
						// contentType 있으면 안된다고 했는데 언제 였지 객체를 보낼때였나.
						//contentType: 'application/x-www-form-urlencoded; charset=euc-kr', 
						success: function(data){
							console.log("됐다.");
							console.log(resultRes);
							console.log(resultRes.lat, resultRes.lng);
							console.log(opener.parent);
						
							// 식당 정보를 띄우기 위해서 결과 식당의 좌표를 부모 함수에 넣고 호출.
							console.log("hihihihihihihihiihihihihihihi");
							opener.parent.displayRestaurantInfo(resultRes.lat, resultRes.lng, resultRes.rname);
							var data0 = data.rid;
							var data1 = data.couponCategory+"1";
							var data2 = data.restaurant+"1";
								
							opener.parent.setrestaurantId(data0);
							opener.parent.setcouponIsempty(data1);
							opener.parent.setrestaurantIsempty(data2);
							opener.parent.couponAndFavoriteShow();
							
						}
					})
				}
				firstClick = 1;
			})
			
			var svg2 = document.createElementNS("http://www.w3.org/2000/svg",'svg');
            svg2.setAttribute("width","850");
            svg2.setAttribute("height","500");
            //svg2.style.border="1px solid black";
            svg2.setAttribute("id","svg2");
			svg2.setAttribute("class", "ladderSvg2");
			// 이 svg는 어케 할지 생각을 좀 해봐야 함.
			console.log("test2");
			
			
			let ladderBtnClickCnt = 0;
			let ladderPick = 0;
			$('.ladderLi').on('click', function(e){ // 사다리 버튼 클릭할 때. 
				
				
				if($(this).children("button").prop("disabled")== true){
					
				}else {
					ladderBtnClickCnt++;
					$(this).children("button").attr("disabled", true);
				}				
				console.log(ladderBtnClickCnt);
				
				
				$("#svg2 path").removeClass("routeAnimation");
				$("#svg2 path").addClass("notAnimation"); // 애니메이션 적용 안되게 하려고 만든 클래스.
				
				$('.notAnimation').css('stroke-dasharray', 0).css('stroke-dashoffset', 0);
				$('.notAnimation').css('animation', 'none');
				$('.notAnimation').attr('stroke', 'grey'); 
				
				//$('.ladderSvg2').empty(); // 그전에 것들 그냥 지워버리는 것.				
				
				let setOfRoute = data.setOfRoute;
				let setOfRouteVerSize = Object.keys(setOfRoute).length; 
				let ladderIndex = $(".ladderUl li").index(this); // li의 크기를 버튼과 같게 해야 함.
				console.log(ladderIndex);
				let routeString= "";
				let setOfRouteHorSize = setOfRoute[ladderIndex].length;
				
				console.log("ladderIndex : " + ladderIndex); //2, 3, 4가 뜸.
				let routePath = document.createElementNS("http://www.w3.org/2000/svg",'path'); 
				//let verPath = document.createElementNS("http://www.w3.org/2000/svg",'path');
			    //$(verPath).attr('stroke-width', 5).attr('stroke', 'black').attr('d', 'm'+(i*sizeOfWidth)+',0 v600');
				
				// 이제 얘네를 Path에 붙여야 한다.
				for(let k=0; k<setOfRouteHorSize-1; k++){
					if(setOfRoute[ladderIndex][k] == '10'){ //오른쪽
						routeString = routeString + " h" + sizeOfWidth; // k를 곱하나 k+1를 곱하나. 
					} else if(setOfRoute[ladderIndex][k] == '20'){ //왼쪽
						routeString = routeString + " h" + (-sizeOfWidth); // h-100 이런식으로 값이 나와야 함.
					} else { // 아래
						routeString = routeString + " v" + sizeOfHeight; //아래로 한칸
					}	
				}
				// 루트를 다 더해서 사다리 선택에 따른 루트 설정.
				$(routePath).attr('fill', 'none').attr('stroke-width', 10).attr('stroke', 'red').attr('d', 'm'+ ((ladderIndex * sizeOfWidth)+5)+ ',0 '+ routeString);
				console.log(routeString); // 값들 잘 들어갔는지 경로 출력 값이랑 비교해볼 것. 
				 
				$('#gameWrap .svg2Div').append(svg2);
				
				let pathLength = routePath.getTotalLength();
				console.log(pathLength);
				$('.ladderSvg2').append(routePath);
				//$(routePath).attr('stroke-dasharray', pathLength).attr('stroke-dashoffset', pathLength);
				$("#svg2 path").not("#svg2 path.notAnimation").addClass("routeAnimation");
				$('.routeAnimation').css('stroke-dasharray', pathLength).css('stroke-dashoffset', pathLength);
				$('.routeAnimation').css('animation', 'moving 3s linear forwards')
				
				
				//animation-fill-mode: forwards;
				// 루트 설정하고 새로운 Svg에 append시켜 줘야 한다.
				console.log(setOfRoute[ladderIndex][0]);
				console.log(setOfRoute[ladderIndex][setOfRouteHorSize-1]);
				var resOfChoice = document.getElementById('choice'+ ladderIndex + '').value;
				console.log(resOfChoice);
				console.log(data.resList[ladderIndex].rname);
				console.log(data.resList[ladderIndex]);
				
				if(setOfRoute[ladderIndex][setOfRouteHorSize-1] == "X0"){ // X일 경우.
					
				} else{ // O 일 경우.
				console.log(" 당첨~~~ ");
					if(ladderPick == 0){
						let resultResOfLadder = data.resList[ladderIndex];
						// 결과로 보낼 레스토랑 하나를 저장해야 함
						
						setTimeout(function() { 
							$(".svg2Div").hide();
							$(".svgDiv").show();
							//svg2를 안보이게 하고 svg2를 보여준다. 그리고 애니메이션은 이제 안되도록.?
							$('.ladderLi').children("button").attr('disabled', true);
							//$(this).addClass("ladderpick");
							$(".ladderLi").eq(ladderIndex).children('button').addClass("ladderpick");
							
							
						}, 4000) // 애니메이션 끝나고 1초 후에 바뀜 
					
						$.ajax({
							url: getContextPath() + "/bringResResultOfGame",
							type: "POST",
							data: resultResOfLadder,
							// contentType 있으면 안된다고 했는데 언제 였지 객체를 보낼때였나.
							//contentType: 'application/x-www-form-urlencoded; charset=euc-kr', 
							success: function(){
								console.log("됐다.");
								console.log(resultResOfLadder);
								console.log(resultResOfLadder.lat, resultResOfLadder.lng);
								console.log(opener.parent);
								
								var data0 = data.rid;
								var data1 = data.couponCategory+"1";
								var data2 = data.restaurant+"1";
								
								opener.parent.setrestaurantId(data0);
								opener.parent.setcouponIsempty(data1);
								opener.parent.setrestaurantIsempty(data2);
								opener.parent.couponAndFavoriteShow();
								// 식당 정보를 띄우기 위해서 결과 식당의 좌표를 부모 함수에 넣고 호출.
								opener.parent.displayRestaurantInfo(resultResOfLadder.lat, resultResOfLadder.lng, resultResOfLadder.rname);
							}
						})						
					}
					ladderPick = 1;
				}
			})
			
			$("#submitBtn").on("click", function(e){
				e.preventDefault();
				self.close();
				// 팝업 닫기.
				/*
				let resultRes = data[indexNum];
				console.log(resultRes);
				$.ajax({
					url: getContextPath() + "/bringResResultOfGame",
					type: "POST",
					data: resultRes,
					//contentType: 'application/x-www-form-urlencoded; charset=euc-kr', 
					success: function(){
						console.log("됐다.");
						console.log(resultRes);
						console.log(resultRes.lat, resultRes.lng);
						console.log(opener.parent);
						
						//opener.parent.displayRestaurantInfo(resultRes.lat, resultRes.lng);

					}
				}) */
			})
			

		}, error: function(){
			console.log("error");
		}
		
	})
})

//이게 있어야만 하는지. 없어도 되는지 확인해볼 것.
function makeInputForSubmit(index, data){
	$('#submitForm').append("<input type='hidden' id='resultLat' name='resultLat' value='"+ data[index].lat +"'  />");
	console.log("makeInputForSubmit test");
	$('#submitForm').append("<input type='hidden' id='resultLng' name='resultLng' value='"+ data[index].lng +"'  />");
	
}

function makeCard(data){
	$("#gameWrap").append('<ul></ul>');
	
	console.log(data.length);
	for(let p=0; p<data.length; p++){
		$("#gameWrap ul").addClass("cardUl");
		let divText = document.createTextNode(data[p].rname);
		
		$(".cardUl").append('<li class="cardLi cardLiStyle"><div class="cardDiv cardDivStyle"></div><div class="BackSideOfCard"></div></li>')
		
		let newLi = $(".cardUl li:nth-child(" + (p+1) + ") div:nth-child(1)");
		
		newLi.append(divText);
	}
}

// 사다리를 만드는 함수
function makeLadder(data, sizeOfWidth, sizeOfHeight){
	let ladder = data.ladder;
	let resList = data.resList;
	
	console.log(ladder);
	console.log("세로 : " + ladder[0].length);
	let numOfHorLines = ladder.length - 2; // 실질 가로줄 갯수
		console.log("실질 가로줄 갯수 : " + numOfHorLines);
	let numOfVerLines = ladder[0].length;
	let posOfHorLine = 0;
	
	
	$("#gameWrap").append('<ul class="ladderUl"></ul>');
	$("#gameWrap").append('<div class="svgDiv"></div>');
	$("#gameWrap").append('<ul class="oxUl"></ul>');
	
	var svg = document.createElementNS("http://www.w3.org/2000/svg",'svg');
            svg.setAttribute("width","850");
            svg.setAttribute("height","500");
            //svg.style.border="1px solid black";
            svg.setAttribute("id","svg");
			svg.setAttribute("class", "ladderSvg");	
				
		$('#gameWrap .svgDiv').append(svg);
		
		/*
		if(numOfVerLines == 1 || numOfVerLines == 3 || numOfVerLines == 5){
			sizeOfWidth = 840 / 4; // 210 = 가로줄의 폭
		} else{
			sizeOfWidth = 840 / 3; // 240 = 가로줄의 폭
		}
		
		sizeOfHeight = 600 / (numOfHorLines + 1);
		*/
		
		for(let p=1; p<ladder.length-1; p++){
			console.log("가로줄 포지션 : " + ladder[p].indexOf("1")) // -1인 경우 어떻게 할 것인가. 
		}
		
		for(let i=0; i<numOfVerLines; i++){
			// 버튼을 만들어야 함. 사다리랑 경로에서 + 레스토랑까지 프론트로 보내야 할 수도 있다. 아니 보내야겠는걸.
			console.log(data.resList[i].rname);
			let choiceBtn = $('#gameWrap .ladderUl').append('<li class="ladderLi"><button class="choice" id="choice'+ i +'" value="'+ data.resList[i].rname +'" >' + data.resList[i].rname + '</button></li>');
			// 일단 버튼 생성 되는지 보고. 버튼의 위치는 다음에 잡자.
			
			console.log(ladder[ladder.length-1][i]);
			if(ladder[ladder.length-1][i] == "O"){
				let ansBtn = $('#gameWrap .oxUl').append('<li class="oxLi">O</li>');	
			}else{ // XO
				let ansBtn = $('#gameWrap .oxUl').append('<li class="oxLi">X</li>');	
			}
			
			
			let verPath = document.createElementNS("http://www.w3.org/2000/svg",'path');
			$(verPath).attr('stroke-width', 10).attr('stroke', 'black').attr('d', 'm'+((i*sizeOfWidth)+5)+',0 v600');
				
			$('.ladderSvg').append(verPath);	
		}
		
		// 가로줄 그리기.
		for(let j=1; j<=numOfHorLines; j++){
			posOfHorLine = ladder[j].indexOf("1");
			let xPos = sizeOfWidth * posOfHorLine+5;
			
			let horPath = document.createElementNS("http://www.w3.org/2000/svg",'path');
			$(horPath).attr('stroke-width', 10).attr('stroke', 'black').attr('d', 'm'+ xPos +','+(j * sizeOfHeight) + ' h'+ sizeOfWidth+ '');
			
			$('.ladderSvg').append(horPath);
		}
		
	
	
	
	$(".svgDiv").hide();		
}
function createDivForCover(){
	$("#gameWrap").prepend("<div class='coverDiv'></div>");
}



//10: 오른쪽, 20: 왼쪽, 나머지 다 아래.
function makeRouteOfEachChoice(data, widthSize, heightSize){
	let setOfRoute = data.setOfRoute;
	let setOfRouteVerSize = Object.keys(setOfRoute).length; 
	let tempString = "";
	
	// svg는 위에서 만들어져 있어서 여기서는 필요 없을 거 같은데. 일단 Path는 꼭 필요함 
	var svg = document.createElementNS("http://www.w3.org/2000/svg",'svg');
            svg.setAttribute("width","840");
            svg.setAttribute("height","700");
            svg.style.border="1px solid black";
            svg.setAttribute("id","svg");
			svg.setAttribute("class", "ladderSvg");
	
	console.log(setOfRoute)
	console.log("선택의 종류(세로줄의 갯수) : " + setOfRoute.size);
	console.log("testtttttttttt : " + Object.keys(setOfRoute).length);
	console.log("1번 선택에 대한 경로의 수 : " + setOfRoute[0].length);
	console.log("aiefuhaweiufhawukfhawkjfhawkfh : " + setOfRoute[0][1]);
	//Object.keys(ex_obj).length;
	
	// 시작점인 m을 i에 따라 미리 설정한다. / 10이든 20이든 각각의 위치의 값을 만났을 때에는 h와 v를 이용하여 패스를 설정해준다. 
	for(let i=0; i<setOfRouteVerSize; i++){
		let setOfRouteHorSize = setOfRoute[i].length; // i 번째 선택에 대한 루트의 길이. (마지막 O or X 까지 들어있기 때문에 실질 루트의 개수는 -1 해줘야 함.)
		for(let j=0; j<setOfRouteHorSize-1; j++){ // j<setOfRouteHorSize-1 로 조건 걸어야 함.
			if(setOfRoute[i][j] == "10"){ //오른쪽
				
			}else if(setOfRoute[i][j] == "20"){ //왼쪽
				tempString = tempString + "l"+ widthSize+ " 0"; 
				
			}else { //아래로 움직인다.
				
			}
		}
	}
	
	/*
	$('.ladderLi').on('click', function(e){
		let ladderIndex = $("li").index(this);
		let routeString= "";
		
		console.log("ladderIndex : " + ladderIndex);
		let routePath = document.createElementNS("http://www.w3.org/2000/svg",'path'); 
		//$(verPath).attr('stroke-width', 5).attr('stroke', 'black').attr('d', 'm'+(i*sizeOfWidth)+',0 v600');
		
		// 이제 얘네를 Path에 붙여야 한다.
		for(let k=0; k<setOfRouteHorSize-1; k++){
			if(setOfRoute[ladderIndex][k] == '10'){ //오른쪽
				routeString = routeString + "h" + widthSize; // k를 곱하나 k+1를 곱하나. 
			} else if(setOfRoute[ladderIndex][k] == '20'){ //왼쪽
				routeString = routeString + "h" + (-widthSize); // h-100 이런식으로 값이 나와야 함.
				console.log(routeString);
			} else { // 아래
				routeString = routeString + "v" + widthSize; //아래로 한칸
			}	
		}
		// 루트를 다 더해서 사다리 선택에 따른 루트 설정.
		$(routePath).attr('stroke-width', 7).attr('stroke', 'red').attr('d', 'm'+ (ladderIndex * widthSize)+ ',0 '+ routeString);
		
		// 루트 설정하고 새로운 Svg에 append시켜 줘야 한다.
		
		if(setOfRoute[ladderIndex][setOfRouteHorSize-1] == "X0"){ // X일 경우.
			
		} else{ // O 일 경우.
			
		}
	})
	*/
	
}
// button으로 식당 선택 할 수 있게 해. 어떻게 해야할까.
// $('.gameBtn').on('click', function(e){





// 얘를 활
// 랜덤으로 5개를 뽑기 위한 함수
function extractFiveIndex(number){
	if(number == 0){
		console.log("검색된 식당이 없습니다. from extractFiveIndex")
	}
	else{
		for(let k=0; k<number; k++){
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
	}
			 // 5개를 뽑기 위한 난수.
		console.log(ranNumArr);
//	return ranNumArr;
			
}

/*
// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	temp++;
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);
		// 다른거 다 지우고 얘만 살려도 될지도? 
		
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
*/



