/**
 * 초기 화면의 기본 값 입니다.
 */



function reviewClick(){
	var reviewradio = $("input[name='vrreviewradio']:checked").val();
	var restaurantName = $("#restaurantNameis"+reviewradio).val();
	var restaurantTableId=$("#restaurantrealid"+reviewradio).val();
	console.log("내간식 id"+reviewradio);
	console.log("찐식 id"+restaurantTableId);
	console.log("레스토랑 명 : " + restaurantName);
	$("#reviewRName").val(restaurantName+" 식당리뷰!");
	$("#submititem").val(restaurantTableId);
	$("#visitedRestaurantId").val(reviewradio);
	
}

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

function removeClick(){
	var removeradio = $("input[name='removeradio']:checked").val();
	console.log("삭제할 아이디 : "+ removeradio);
	
	$.ajax({
		url: "http://localhost:8080/pickmeal/removeVisited",
		type:"post",
		data: JSON.stringify({"id" : removeradio}),
		contentType:'application/json; charset=utf-8',
		
		success: function(data){
			$("#vrdivs"+removeradio).remove();
		}
	});
}

//체크박스 5개 까지만 고르고 값 변경 해주기 완료
$(document).ready(function() {
	$("input:checkbox").on('click', function(){
		let count = $("input:checked[type='checkbox']").length;
		
		if(count>5){
			$(this).prop("checked",false);
			alert("5개 까지만 선택 할 수 있습니다.");
		}else{
			if ( $(this).prop('checked') ){
				$(this).val(1);
				$(this).next().next().css('color','red');
				$(this).parent().css({'backgroundColor' : '#f5f5f5', 'border' : '3px solid ##f23f3f'});
	 		}else{
				$(this).val(0);
				$(this).css('border','0px');
				$(this).next().next().css('color','black');
				$(this).parent().css({'backgroundColor' : 'white', 'border' : '0px'});
			}
		}
		
	}); 
});


