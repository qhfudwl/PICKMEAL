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
			for(let i in data["restReference"].ageCount){
				ages[i] = data["restReference"].ageCount[i];
			}
			console.log("ages[0] is"+ages[0]);
			
			//리뷰 데이터 받기
			/* 추후 변경 가능성 있음 */
			userCount = data["review"].userCount;

			bathroom = data["review"].bathroom;
			specialDay = data["review"].specialDay;
			clean = data["review"].clean;
			parkinig = data["review"].parkinig;
			goodgroup = data["review"].goodgroup;
			alone = data["review"].alone;
			big = data["review"].big;
			interior = data["review"].interior;

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
	});



})

//연령별 그래프 컬러 초기화
function setAgeChartColor(){
	//향상된 for문 안됨 왜?? 추측1) 배열이 빈값이라서 (사이즈와상관없이)
	for(let i=0;i<color.length;i++){
		color[i] = "#c2c9cd";
	}
}

/*
	연령별 그래프에서 1등 컬러 셋팅하기
		[규칙] 1등이 여러개 값일 때는 색깔을 따로 지정해주지 않는다 
 
*/
function setBestAgeChartColor(){
	//1. 배열내에 최대값 찾기
	let maxNum = Math.max.apply(null, ages);

	//2. 최대값 중복검사
	//2-1. 최대값이 포함되어 있는 배열의 index값 리턴
	let maxNumIndex = ages.indexOf(maxNum);

	//2-2. 배열 복사후 배열에서 최대값을 빼준다
	let newAges = ages.filter(()=>true);
	newAges.splice(maxNumIndex,1);

	//2-3. 최대값을 빼준 배열에서 똑같은 값이 있다면, 최대값이 중복이라 색깔 지정 x,
	//없으면 최대값은 유일한 값을 가짐으로 색깔을 준다.
	if(newAges.includes(maxNum)){
		return;
	}
	else{
		//2-4. 연령별 최대값을 가지는 1개 열에만 컬러를 준다
		color[maxNumIndex]= "#508fe8";
	}
	

}







