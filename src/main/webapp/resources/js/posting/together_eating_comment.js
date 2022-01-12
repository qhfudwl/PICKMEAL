// 체크박스 중복을 막기 위해서 있다.
function oneChkBox(a) {
	let choiceCmts = document.getElementsByClassName("choiceCmt");
	for (let i=0; i<choiceCmts.length; i++){
		if(choiceCmts[i] != a){
			choiceCmts[i].checked = false;
		}
	}
}
// 더보기 누르면 수정 / 삭제 div 뜨도록
function displayUpdate(a) {
	let id = a.value;
	if(a.checked == true){
		$(".updateWrap" + id).fadeIn();
	} else {
		$(".updateWrap" + id).fadeOut();
	}
}
// 댓글 쓰기
$("#writeOk").click(function(e) {
	e.preventDefault();
	let formData = $("form[name=writeCmtForm]").serialize();
	$.ajax({
		url: "addComment",
		type: "post",
		data: formData,
		success: function(data) {
			let moreHtml;
			let chatHtml;
			if ($("input[name=memberId]").val() == data.member.id) {
				moreHtml = '<input onclick="oneChkBox(this); displayUpdate(this)" class="choiceCmt" type="checkbox" name="id" value="' + data.member.id + '" id="choiceCmt' + data.member.id + '" />' + 
				'<label for="choiceCmt' + data.member.id + '">' + 
					'<span class="point1"></span>' + 
					'<span class="point2"></span>' + 
					'<span class="point3"></span>' + 
				'</label>';
			} else {
				moreHtml = "";
			}
			if ($("input[name=post_memberId]").val() == $("input[name=memberId]").val()) {
				chatHtml = '<button type="submit" class="chat" value="' + data.member.id + '">채팅하기</button>';
			} else {
				chatHtml = "";
			}
			$("form[name=viewCmtForm]").append(
				'<div class="commentWrap div' + data.id + '">' + 
					'<img alt="' + data.member.nickName + '님의 프로필 이미지" src="' + getContextPath() + data.member.profileImgPath + '">' + 
					'<div class="memberContent">' + 
						'<div class="memberInfo">' + 
							'<p class="nickName">' + data.member.nickName + '</p>' + 
							'<p class="mannerTemp">' + data.member.mannerTemperature + '&deg;</p>' + 
							'<time datetime="' + data.regDate + '">' + data.regDate + '</time>' + 
						'</div>' + 
						'<input type="text" class="comment' + data.id + ' viewCmt viewCmtContent" name="cmtContent" value="' + data.content + '" disabled />' + 
					'</div>' + 
					'<div class="moreWrap">' + 
						'<div class="popUpdate">' + moreHtml +
						'</div>' + 
						'<div class="updateWrap updateWrap' + data.member.id + '">' + 
							'<button name="update" class="update" value="modify' + data.member.id + '">수정하기</button>' + 
							'<button name="update" class="update" value="delete' + data.member.id + '">삭제하기</button>' + 
						'</div>' + chatHtml + 
					'</div>' + 
				'</div>'
			);
			$("#writeCmt").val("");
		},
		error: function() {
			console.log("error")
		}
	})
})