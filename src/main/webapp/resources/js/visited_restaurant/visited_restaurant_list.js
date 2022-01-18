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