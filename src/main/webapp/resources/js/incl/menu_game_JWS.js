/**
 * 
 */
/* 메뉴게임 클릭시 메뉴게임 시작*/
$("#menuchoicebtn").click(function(e){
		e.preventDefault();
		popupMenu("menuGamePopup");
	})
	function popupMenu(Url){
        let windowWidth = window.screen.width;
        let windowHeight = window.screen.height;
        
        let popupX = (windowWidth/2) - 550;
        let popupY = (windowHeight/2) -225;
        
        let popUpdateUrl = "http://localhost:8080/pickmeal/" + Url;
        let popUpdateOption = "width=570px, height=470px, top=" + popupY + "px, left=" + popupX + "px";
        let popUpdateTitle = "메뉴 게임"
        
        if(Url == "menuGamePopup") {
            window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
        }
        let menuGamePopup = document.menuGamePopup;
        menuGamePopup.target = popUpdateTitle;
        menuGamePopup.action = popUpdateUrl;
        menuGamePopup.method ="get";
        
        menuGamePopup.submit();
    }
    function reloadPage() {
        location.reload();
    }

/* 쿠폰발급 클릭시 쿠폰발급 시작*/
$('#couponGenerateWrap').click(function(e){
        	e.preventDefault();
            $('#couponGenerate').click();
        })
        
        $("#couponGenerate").click(function(e){
		e.preventDefault();
		popupCoupon("couponPopupCreate");
		})
	function popupCoupon(Url){
        let windowWidth = window.screen.width;
        let windowHeight = window.screen.height;
        
        let popupX = (windowWidth/2) - 630;
        let popupY = (windowHeight/2) -275;
        
        let popUpdateUrl = "http://localhost:8080/pickmeal/" + Url;
        let popUpdateOption = "width=630px, height=550px, top=" + popupY + "px, left=" + popupX + "px";
        let popUpdateTitle = "쿠폰 발급"
        
        if(Url == "couponPopupCreate") {
            window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
        }
        let couponPopupCreate = document.couponPopupCreate;
        couponPopupCreate.target = popUpdateTitle;
        couponPopupCreate.action = popUpdateUrl;
        couponPopupCreate.method ="get";
        
        couponPopupCreate.submit();
    }

function setcouponIsempty(name){
document.getElementById("couponIsempty").value = name;
console.log($("#couponIsempty").val());
}

function setrestaurantIsempty(name){
document.getElementById("restaurantIsempty").value = name;
console.log($("#restaurantIsempty").val());
}

function setrestaurantId(name){
	document.getElementById("restaurantRId").value = name;
}

function couponAndFavoriteShow(){
	//console.log("함수돌릴 때 쿠폰 값"+$("#couponIsempty").val());
	//console.log($("#restaurantIsempty").val());
	//console.log($("#defaultCNJ").val());
	//console.log($("#defaultCNJ").val() == $("#restaurantIsempty").val());
	
	
	/*둘다 없을 때*/
	if($("#couponIsempty").val() == $("#defaultCNJ").val() && $("#restaurantIsempty").val() == $("#defaultCNJ").val()){	
	
	/*쿠폰발급은 됐고, 찜목록에안에 해당 식당이 있으면 undefinded*/	
	}else if($("#restaurantIsempty").val() == $("#defaultCNJ").val()){
		$("#couponGenerateWrap").show();
	
	/*쿠폰발급이 안됐고, 찜목록에 해당 식당이 없으면*/
	}else if($("#couponIsempty").val() == $("#defaultCNJ").val()){
		$("#jjimdiv").show();
	
	/*쿠폰도 발급 됐고 찜목록에 해당 식당이 없으면*/
	}else{
		$("#jjimdiv").show();
		$("#couponGenerateWrap").show();
	}

}

function indexFrestaurant(){
	var restaurantId = $("input[name='restaurantIdJWS']").val();
	var memberId = $("input[name='memberIdJWS']").val();
	console.log(restaurantId);
	let json = { "restaurantId": restaurantId, "memberId": memberId};
	$.ajax({
		url: "http://localhost:8080/pickmeal/indexFavorite",
		type: "post",
		data: JSON.stringify(json),
		contentType:'application/json; charset=utf-8',
		
		success: function(data){
			$("#jjimdiv").remove();
			}
	})
}