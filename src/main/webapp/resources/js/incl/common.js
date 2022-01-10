function getContextPath() {
	let hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/', hostIndex+1));
}


function updateFoodPowerPoint() {
	$.ajax({
		url: "updateAchievementInfo",
		type: "get",
		data: json,
		dataType: "",
		success: function(data){
			
		}
	})
}

