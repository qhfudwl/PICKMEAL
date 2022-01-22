let infoChk = false;
let infoChkArr = [0, 0, 0, 0, 0, 0];

$("#signUpBtn").click(function(e) {
	// 성별 체크했는지
	let gender = $("input[name=gender]:checked").val();
	let errMsg = $("#female + label").next();
	if (gender == null){
		infoChkArr[5] = 0;
		errMsg.text("성별을 선택해주세요.")
		infoChk = false;
	} else {
		infoChkArr[5] = 1;
		errMsg.text("")
		infoChk = true;
	}
	let submitChk = false;
	let arr = [1, 1, 1, 1, 1, 1];
	if (JSON.stringify(infoChkArr) === JSON.stringify(arr)) {
		submitChk = true;
	} else {
		for (let i = 0; i < infoChkArr.length; i++){
			if (infoChkArr[i] == 0) {
				$(".errMsg").eq(i).text("필수 입력값입니다.");
			} else {
				$(".errMsg").eq(i).text("");
			}
		}
		submitChk = false;
	}
	
	// 모든 걸 통과하고 나서 다시 비교 후 폼 파라미터를 넘긴다
	if (infoChk == false || submitChk == false) {
		e.preventDefault();
	}
})

$("#email").focus(function() {
	$("#email").keyup(function() {
		let email = $(this).val();
		let errMsg = $(this).next();
		checkSignUpInfo(email, "email", errMsg);
	})
})

$("#nickName").focus(function() {
	$("#nickName").keyup(function() {
		let nickName = $(this).val();
		let errMsg = $(this).next();
		let pattern = new RegExp("^[A-Za-z0-9ㄱ-ㅎ가-힣]*$"); // 대문자, 소문자, 숫자, 한글만 가능
		if (pattern.test(nickName)) { // 닉네임이 패턴에 맞는다면
			errMsg.text("");
			infoChk = true;
			infoChkArr[3] = 1;
		} else {
			errMsg.text("닉네임은 대문자, 소문자, 숫자, 한글만 허용합니다.");
			infoChk = false;
			infoChkArr[3] = 0;
			return;
		}
		checkSignUpInfo(nickName, "nickName", errMsg);
	})
})

// 이메일, 닉네임 중복확인
function checkSignUpInfo(signInfo, type, errMsg){
	let json = {"signInfo" : signInfo, "type" : type};
	$.ajax({
		url: "checkSignUpInfo",
		type: "get",
		data: json,
		dataType: 'text',
		success: function(data){
			if (data == "ok") {
				errMsg.text("");
				infoChk = true;
				if (type == "email") {
					let pattern = new RegExp("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$");
					if(pattern.test(signInfo)) { // 패턴에 맞을 때
						infoChkArr[0] = 1;
						errMsg.text("");
					} else { // 패턴에 맞지 않을 때
						infoChkArr[0] = 0;
						errMsg.text("이메일 형식에 맞게 입력해주세요.");
					}
				} else if (type == "nickName"){
					infoChkArr[3] = 1;
				}
			} else {
				errMsg.text(data);
				infoChk = false;
				if (type == "email") {
					infoChkArr[0] = 0;
				} else if (type == "nickName"){
					infoChkArr[3] = 0;
				}
			}
		}
	})
}

// 비밀번호 체크
$("#passwdChk").focusin(function() {
	let pattern = new RegExp("^[A-Za-z0-9!@#$%^~*+=&-]*$");
	let passwd = $("#passwd").val();
	let errMsg = $("#passwd").next();
	if (passwd.length < 4 || passwd.length > 20) { // 비밀번호 자릿수가 맞지 않을 때
		errMsg.text("비밀번호는 4자리 이상 20자리 이하로 입력해주세요.");
		infoChk = false;
		infoChkArr[1] = 0;
		return;
	} else { // 비밀번호를 적었다면
		errMsg.text("");
		infoChk = true;
		infoChkArr[1] = 1;
	}
	if (pattern.test(passwd)) { // 비밀번호가 패턴에 맞는다면
		errMsg.text("");
		infoChk = true;
		infoChkArr[1] = 1;
	} else {
		errMsg.text("비밀번호는 대문자, 소문자, 숫자, 특수문자(!@#$%^~*+=&-)만 허용합니다.");
		infoChk = false;
		infoChkArr[1] = 0;
		return;
	}
})

// 비밀번호와 비밀번호확인의 일치 확인
$("#passwdChk").focus(function() {
	$("#passwdChk").keyup(function() {
		let passwd = $("#passwd").val();
		let passwdChk = $(this).val();
		let errMsg = $(this).next();
		if (passwd == passwdChk){
			infoChk = true;
			errMsg.text("");
			infoChkArr[2] = 1;
		} else {
			errMsg.text("비밀번호가 일치하지 않습니다.");
			infoChk = false;
			infoChkArr[2] = 0;
		}
	})
})

// 생년월일 확인
$("#birth").focus(function() {
	$("#birth").keyup(function() {
		let birth = $(this).val();
		let errMsg = $(this).next();
		if (isNaN(birth)){ // 숫자가 아니라면
			infoChk = false;
			errMsg.text("숫자만 입력해주세요.");
			infoChkArr[4] = 0;
		} else { // 숫자일 경우
			if (birth.length == 8) { // 8자리로 잘 적었다면
				infoChk = true;
				errMsg.text("");
				infoChkArr[4] = 1;
			} else { // 8자리가 아니라면
				infoChk = false;
				errMsg.text("생년월일은 8자리로 적어주세요.");
				infoChkArr[4] = 0;
			}
		}
	})
})
// 화면 새로 고침 시 value 들을 모두 지워준다.
$(document).ready(function(){
	$("#email").val("");
	$("#passwd").val("");
	$("#passwdChk").val("");
	$("#nickName").val("");
	$("#birth").val("");
});