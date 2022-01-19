/**
 * 초기 화면의 기본 값 입니다.
 */


function jjimrestaurant(){
	var radiojjim = $("input[name='vrfavoriteradio']:checked").val();
	
	console.log(radiojjim);
	
	$.ajax({
		url: "http://localhost:8080/pickmeal/jjimclickBtn",
		type: "post",
		data: JSON.stringify({"id" : radiojjim}),
		contentType:'application/json; charset=utf-8',
		
		success: function(data){
			$("#jjimdiv"+radiojjim).children().remove();
		}
		
	});
}

function reviewClick(){
	var reviewradio = $("input[name='vrreviewradio']:checked").val();
	var restaurantName = $("#restaurantNameis"+reviewradio).val();
	var restaurantTableId=$("#restaurantrealid"+reviewradio).val();
	console.log("내간식 id"+reviewradio);
	console.log("찐식 id"+restaurantTableId);
	console.log("레스토랑 명 : " + restaurantName);
	$("#reviewRName").val(restaurantName+" 식당리뷰!");
	$("#submititem").val(restaurantTableId);
	
}