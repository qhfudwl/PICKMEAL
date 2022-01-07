/**
 * 	성별별 파이차트
 */
/*
// 성별별 파이차트 데이터
 var genderdata = [
        {key: "여자", y: 5, color: "#ff6d6d"},
        {key: "남자", y: 6, color: "#78c1ff"},

    ];

//연령별 
var agedata = [
        {
            key: "Cumulative Return",
            values: [
                {
                    "label" : "10대" ,
                    "value" : 30
                } ,
                {
                    "label" : "20대" ,
                    "value" : 20
                } ,
                {
                    "label" : "30대" ,
                    "value" : 10
                } ,
                {
                    "label" : "40대" ,
                    "value" : 5
                } ,
                {
                    "label" : "50대" ,
                    "value" : 1
                } ,
                {
                    "label" : "60대" ,
                    "value" : 0
                }
            ]
        }
    ];

//성별별 파이차트 크기
var height = 180;
var width = 155;


nv.addGraph(function() {
        var chart = nv.models.pieChart()
            .x(function(d) { return d.key })
            .y(function(d) { return d.y })
            .width(width)
            .height(height)
            .showTooltipPercent(true);

        d3.select("#genderChart2")
            .datum(genderdata)
            .transition().duration(1200)
            .attr('width', width)
            .attr('height', height)
            .call(chart);

        return chart;
    });

//연령별 이산형차트 

nv.addGraph(function() {
        var chart = nv.models.discreteBarChart()
            .x(function(d) { return d.label })
            .y(function(d) { return d.value })
            .staggerLabels(false)
            //.staggerLabels(historicalBarChart[0].values.length > 8)
            .showValues(false)
            .duration(250)
			
            ;

        d3.select('#ageChart')
            .datum(agedata)
            .call(chart);

        nv.utils.windowResize(chart.update);
        return chart;
    });


*/

google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawGenderChart);
      function drawGenderChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'count'],
          ['여자',     11],
          ['남자',    7]
        ]);

        var options = {
          	pieHole: 0.4,
			backgroundColor:'pink',
			chartArea:{width:'80%',height:'80%'},
			legend:{position:'bottom',alignment:'center'},
			slices: {0: {color: '#ff6d6d'}, 1: {color: '#78c1ff'}}
        };

        var chart = new google.visualization.PieChart(document.getElementById('genderChart2'));
        chart.draw(data, options);
      }




google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawMultSeries);
var color1 = "green";
var color2 = "blue";
var color3 = "red";
var color4 = "yellow";
var color5 = "pink";
var color6 = "black";
function drawMultSeries() {
      var data = google.visualization.arrayToDataTable([
          ['Element','Age',{ role: 'style' }],
          ['10대', 10, 'color:'+color1],
          ['20대', 25, 'color:'+color2],
          ['30대', 18, 'color:'+color3],
		  		['40대', 5, 'color:'+color4],
		  		['50대', 3, 'color:'+color5],
          ['60대', 1, 'color:'+color6]
        ]);

        var options = {
         legend: { position: "none" },
		  titlePosition: 'none', axisTitlesPosition: 'none',
		  vAxis : { textPosition: 'none',baselineColor:'#fff', gridlines:{color:'#fff',minSpacing:10}},
     
        };

    

      var chart = new google.visualization.ColumnChart(
        document.getElementById('ageChart2'));

      chart.draw(data, options);
    }




