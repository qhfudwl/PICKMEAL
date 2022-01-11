let infoChkArr = [0, 0];
$("#signInBtn").click(function(e) {
	let email = $("#email").val();
	let passwd = $("#passwd").val();
	let submitChk = false;
	
	if (email != "") { // 이메일을 적었는가?
		infoChkArr[0] = 1;
	} else {
		infoChkArr[0] = 0;
	}
	if (passwd != "") { // 비밀번호를 적었는가?
		infoChkArr[1] = 1;
	} else {
		infoChkArr[1] = 0;
	}
	
	let arr = [1, 1];
	if(JSON.stringify(infoChkArr) === JSON.stringify(arr)) { // 값을 둘 다 적었는가?
		submitChk = true;
	} else { // 안적었을 경우 메세지를 띄어준다.
		for (let i = 0; i < infoChkArr.length; i++){
			if (infoChkArr[i] == 0) {
				$(".errMsg").eq(i).text("값을 입력해주세요.");
			} else {
				$(".errMsg").eq(i).text("");
			}
		}
		submitChk = false;
	}
	
	if(submitChk == false){
		e.preventDefault();
	}
})

$("#email").focus(function() {
	$("#email").keyup(function() {
		let email = $(this).val();
		let errMsg = $(this).next();
		if (email != "") {
			errMsg.text("");
		} else {
			errMsg.text("값을 입력해주세요.");
		}
	})
})
$("#passwd").focus(function() {
	$("#passwd").keyup(function() {
		let email = $(this).val();
		let errMsg = $(this).next();
		if (email != "") {
			errMsg.text("");
		} else {
			errMsg.text("값을 입력해주세요.");
		}
	})
})