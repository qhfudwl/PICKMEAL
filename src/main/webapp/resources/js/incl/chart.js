/**
 * 	성별별 파이차트
 */


google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawGenderChart);
function drawGenderChart() {
	var data = google.visualization.arrayToDataTable([
		['Task', 'count'],
		['여자', 11],
		['남자', 7]
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


/**
 * 	나이별 막대차트
 */


google.charts.load('current', { packages: ['corechart', 'bar'] });
google.charts.setOnLoadCallback(drawMultSeries);
var color1 = "#c2c9cd";
var color2 = "#c2c9cd";
var color3 = "#c2c9cd";
var color4 = "#c2c9cd";
var color5 = "#c2c9cd";
var color6 = "#c2c9cd";
function drawMultSeries() {
	var data = google.visualization.arrayToDataTable([
		['Element', 'Age', { role: 'style' }],
		['10대', 10, 'color:' + color1],
		['20대', 25, 'color:' + color2],
		['30대', 18, 'color:' + color3],
		['40대', 5, 'color:' + color4],
		['50대', 3, 'color:' + color5],
		['60대', 1, 'color:' + color6]
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
$(document).ready(function(){
	let restaurantId=1;
	/*
		RestaurantReference & RestaurantReview
		ModelAndView 값(서버측)이 JSP 단에 뿌려주고 JS(클라이언트측)에서 그 값을 끌어 사용해야한다
		input type hidden으로 RestaurantReference 관련 값들을 다 넣어주기가 좀 그래서 ajax 사용해서 
		그래프 그리기에 필요한 값들을 셋팅해본다
	*/
	$.ajax({
		url:"restaurant_sub_info",
		type:"post",
		data:$('#restaurantId').serialize(),
		success:function(data){
			console.log(data);
		}
	})	
})



