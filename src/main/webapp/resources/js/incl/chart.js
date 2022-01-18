
//접혔을 때 마지막으로 보이는 li 순서
let last_liNum;
let li_count;
let li_margin;
let li_initHeight;
let li_height;
let li_totalHeight;



/**
 * 	성별별 파이차트
 */
function drawGenderChartWrap() {
	google.charts.load("current", { packages: ["corechart"] });
	google.charts.setOnLoadCallback(drawGenderChart);
	function drawGenderChart() {
		var data = google.visualization.arrayToDataTable([
			['Task', 'count'],
			['여자', femaleCount],
			['남자', maleCount]
		]);

		var options = {
			pieHole: 0.4,
			chartArea: { width: '80%', height: '70%' },
			legend: { position: 'bottom', alignment: 'center', textStyle: { color: '#c2c9cd', fontsize: 14 } },
			slices: { 0: { color: '#ff6d6d' }, 1: { color: '#78c1ff' } },
			tooltip: { trigger: 'none' },
		};

		var chart = new google.visualization.PieChart(document.getElementById('genderChart'));
		chart.draw(data, options);

		
	}
}



/**
 * 	나이별 막대차트
 */
function drawAgeChartWrap() {
	google.charts.load('current', { packages: ['corechart', 'bar'] });
	google.charts.setOnLoadCallback(drawAgeChart);
	function drawAgeChart() {
		var data = google.visualization.arrayToDataTable([
			['Element', 'Age', { role: 'style' }],
			['10대', ages[0], 'color:' + color[0]],
			['20대', ages[1], 'color:' + color[1]],
			['30대', ages[2], 'color:' + color[2]],
			['40대', ages[3], 'color:' + color[3]],
			['50대', ages[4], 'color:' + color[4]],
			['60대', ages[5], 'color:' + color[5]]
		]);

		var options = {
			legend: { position: "none" },
			titlePosition: 'none', axisTitlesPosition: 'none',
			vAxis: {
				textPosition: 'none', baselineColor: '#fff', gridlines: { color: '#fff', minSpacing: 10 },
			},
			hAxis: { textStyle: { color: '#c2c9cd', fontsize: 8 } },
			chartArea: { width: '90%', height: '70%' },
			tooltip: { trigger: 'none' },
		};


		var chart = new google.visualization.ColumnChart(
			document.getElementById('ageChart'));

		chart.draw(data, options);
	}
}


let restaurantId = 1;
let femaleCount, maleCount;
let ages = [];
let userCount;
let bathroom, specialDay, clean, parkinig, goodgroup, alone, big, interior;
let mBathroom, mSpecialDay, mClean, mParkinig, mGoodgroup, mAlone, mBig, mInterior;
//배열예정
let reviews;
let color = new Array(6);
$(document).ready(function() {

	/*
		RestaurantReference & RestaurantReview
		ModelAndView 값(서버측)이 JSP 단에 뿌려주고 JS(클라이언트측)에서 그 값을 끌어 사용해야한다
		input type hidden으로 RestaurantReference 관련 값들을 다 넣어주기가 좀 그래서 ajax 사용해서 
		그래프 그리기에 필요한 값들을 셋팅해본다
	*/

	let promise = $.ajax({
		url: "restaurant_sub_info",
		type: "post",
		data: $('#restaurantId').serialize(),
		success: function(data) {

			//성별 데이터 받기
			femaleCount = data["restReference"].femaleCount;
			maleCount = data["restReference"].maleCount;

			//연령별 데이터 받기
			for (let i in data["restReference"].ageCount) {
				ages[i] = data["restReference"].ageCount[i];
			}

			//리뷰 데이터 받기
			userCount = data["review"].userCount;

			let reviewCount = data["review"].reviewItem.length;
			//rKeyword = new Array(reviewCount);
			//rMessage = new Array(reviewCount);
			let review;
			reviews = new Array(reviewCount);

			for (let i = 0; i < reviewCount; i++) {
				review = new Object();
				review.rCount = data["review"].reviewItem[i].reviewCount;
				review.rMessage = data["review"].reviewItem[i].message;
				review.rImgPath = data["review"].reviewItem[i].imgPath;

				reviews[i] = review;

			}

		}
	});
	/*
		ajax - promise 패턴이 있다. 
		비동기방식인만큼, 실행되는 순서를 보장해 주지 않는데 순서가 필요할 때 사용하는게 promise
		ajax -> 성공하면 실행되는 success 함수 fail함수(순서보장)
		
		제이쿼리에서 여러개의 promise 패턴을 처리하는 방법으로 when이라는 함수를 제공한다
		참고 : https://bemeal2.tistory.com/246
		
		=> 순서를 보장해주기 때문에 쿼리 내의 전역변수 사용이 가능하다
		
	*/
	$.when(promise).done(function(data) {
		//성별 차트그리기
		drawGenderChartWrap()

		//연령별 차트그리기
		//1.컬러초기화
		setAgeChartColor()

		//2.1등 컬러 셋팅하기
		setBestAgeChartColor()

		//3.연령별 차트 그리기
		drawAgeChartWrap()

		//리뷰 그리기
		drawReviews()

		//리뷰 디자인 적용 전 값 셋팅
		setReviewVariable()

		//리뷰 디자인
		setReviewView()
	});

	
	//포춘쿠키 화면에 랜덤 위치잡아주기
	let fortune_left = Math.floor(Math.random() * (window.innerWidth-510)); 
	let fortune_top = Math.floor(Math.random() * (window.innerHeight-210));
	$('.fortune').css({top:fortune_top,left:fortune_left});
	showFortune();
	
})
function setReviewVariable() {

	last_liNum = 3;		//초기에 보여지는 li 갯수
	li_margin = 3;
	li_count = Number($('.userReviewGraph ul li').length);
	li_height = Number($('.userReviewGraph ul li').eq(0).height());
	li_totalHeight = (li_margin + li_height) * ($('.userReviewGraph ul li').length);
}


function drawReviews() {
	//리뷰그리기
	for (let i = 0; i < reviews.length; i++) {
		if (reviews[i].rCount != '' && reviews != null && reviews[i].rCount > 0) {
			$('.userReviewGraph ul').append(
				'<li>'
				+ '<div class="userReviewDeepArea"></div>'
				+ '<div class="userReviewLeftSide">'
				+ '<img src="' + reviews[i].rImgPath + '" alt="icon" class="userReviewOneRow">'
				+ '<p class="reviewText userReviewOneRow">"' + reviews[i].rMessage + '"</p>'
				+ '</div>'
				+ '<p class="countNum userReviewOneRow">' + reviews[i].rCount + '</p>'
				+  '</li>'
			)
		}
	}

}



//연령별 그래프 컬러 초기화
function setAgeChartColor() {
	//향상된 for문 안됨 왜?? 추측1) 배열이 빈값이라서 (사이즈와상관없이)
	for (let i = 0; i < color.length; i++) {
		color[i] = "#c2c9cd";
	}
}

/*
	연령별 그래프에서 1등 컬러 셋팅하기
		[규칙] 1등이 여러개 값일 때는 색깔을 따로 지정해주지 않는다 
 
*/
function setBestAgeChartColor() {
	//1. 배열내에 최대값 찾기
	let maxNum = Math.max.apply(null, ages);

	//2. 최대값 중복검사
	//2-1. 최대값이 포함되어 있는 배열의 index값 리턴
	let maxNumIndex = ages.indexOf(maxNum);

	//2-2. 배열 복사후 배열에서 최대값을 빼준다
	let newAges = ages.filter(() => true);
	newAges.splice(maxNumIndex, 1);

	//2-3. 최대값을 빼준 배열에서 똑같은 값이 있다면, 최대값이 중복이라 색깔 지정 x,
	//없으면 최대값은 유일한 값을 가짐으로 색깔을 준다.
	if (newAges.includes(maxNum)) {
		return;
	}
	else {
		//2-4. 연령별 최대값을 가지는 1개 열에만 컬러를 준다
		color[maxNumIndex] = "#508fe8";
	}


}


/**
	
	디자인 부분
	
 */


function setReviewView() {



	//처음 리뷰 ul 높이 셋팅
	//처음 li 길이는 4개까지 보여지도록한다.
	let multi_liNum;
	if (li_count > 4) {
		multi_liNum = 4;
	} else {
		multi_liNum = li_count;
	}
	li_initHeight = multi_liNum * (li_margin + li_height);
	$('.userReviewGraph ul').css({ height: li_initHeight });


	//총 투표 수
	let count = userCount;
	//1개열에 따른 디자인 다르게 해주기
	for (let i = 0; i < $('.userReviewGraph ul li').length; i++) {
		//1열에 숫자 불러오기
		let divCount = Number($('.userReviewGraph ul li').eq(i).find('.countNum').text());
		//총 넓이 값이 될 숫자 구하기
		let graphWidth = (divCount / count) * 100;
		//div값에 넓이 설정해주기
		$('.userReviewGraph ul li').eq(i).find('.userReviewDeepArea').css({
			width: graphWidth + '%'
		});
		//div값 배경 순서대로 셋팅해주기
		if (i == 0) {
			$('.userReviewGraph ul li').eq(i).find('.userReviewDeepArea').css({
				backgroundColor: '#ffc5c5'
			});
		} else if (i == 1) {
			$('.userReviewGraph ul li').eq(i).find('.userReviewDeepArea').css({
				backgroundColor: '#ffdada'
			});
		} else if (i == 2) {
			$('.userReviewGraph ul li').eq(i).find('.userReviewDeepArea').css({
				backgroundColor: '#ffe4e4'
			});
		}

	}
	//맨 마지막 li 그라데이션 효과주기
	$('.userReviewGraph ul li').eq(last_liNum).css({
		background: 'linear-gradient(180deg, #fff4f4, rgba(255,255,255,0.5))'
	});
	$('.userReviewGraph ul li').eq(last_liNum).find('.userReviewDeepArea').css({
		background: 'linear-gradient(180deg, #ffe4e4, rgba(255,255,255,0.5))'
	});

}



// 리뷰 리스트 클릭하면 목록 늘어나기
$('.openBtn').click(function() {
	$('.userReviewGraph ul').animate({
		height: li_totalHeight + 'px'
	}, 300, 'linear', function() {
		//접기버튼 생기기
		$('.closeReviewGraphWrap').show();
	});
	//맨 마지막 li 그라데이션 효과 없애주기
	$('.userReviewGraph ul li').eq(last_liNum).css({
		background: '#fff4f4'
	});
	$('.userReviewGraph ul li').eq(last_liNum).find('.userReviewDeepArea').css({
		background: '#ffe4e4'
	});
	//열기버튼숨기기
	$('.openReviewGraphWrap').hide();
})

//접기버튼 누르면 접기
$('.closeBtn').click(function() {
	$('.userReviewGraph ul').animate({
		height: li_initHeight + 'px'
	}, 300, 'linear', function() {
		//열기버튼 생기기
		$('.openReviewGraphWrap').show();

		//맨 마지막 li 그라데이션 효과주기
		$('.userReviewGraph ul li').eq(last_liNum).css({
			background: 'linear-gradient(180deg, #fff4f4, rgba(255,255,255,0.5))'
		});
		$('.userReviewGraph ul li').eq(last_liNum).find('.userReviewDeepArea').css({
			background: 'linear-gradient(180deg, #ffe4e4, rgba(255,255,255,0.5))'
		});
	});
	//접기버튼 숨기기
	$('.closeReviewGraphWrap').hide();
})





$('.fortune').click(function(){
	//포춘쿠키 뿌려주기
	openFortune()
	
})


//포춘쿠키 발생이벤트
function showFortune(){
	var tl = new TimelineMax({yoyo:false, repeat:0, repeatDelay:1});
   $('.fortune-message span').hide();

   tl.to($('.fortune'),0.1,{rotation:5});	
	tl.to($('.fortune'),0.1,{rotation:-5});
}

function openFortune(){
	var tl = new TimelineMax({yoyo:false, repeat:0, repeatDelay:1});
   $('.fortune-message span').show();
   tl.to($('.fortune'),0.1,{rotation:-5, delay:1});
   tl.to($('.fortune'),0.1,{rotation:5});
   tl.to($('.fortune'),0.1,{rotation:-5});
   tl.to($('.fortune'),0.1,{rotation:5});
   tl.to($('.fortune'),0.1,{rotation:-5});
   tl.to($('.fortune'),0.1,{rotation:0});
   tl.addLabel("break", "+=0.3");
   tl.to($('.fortune-left'),0.5,{rotation:-45, x:-70, y:70},"break");
   tl.to($('.fortune-right'),0.5,{rotation:45, x:70, y:70},"break");
   tl.from($('.fortune-message span'),1,{x:'110%'},"break");
   tl.to($('.fortune'),1,{display:'none'});

}
