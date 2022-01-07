/**
 * 	성별별 파이차트
 */

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

        d3.select("#genderChart")
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