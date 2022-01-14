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
	$(".updateWrap").stop().fadeOut("fast");
	if(a.checked == true){
		$(".updateWrap" + id).stop().fadeIn("fast");
	} else {
		$(".updateWrap" + id).stop().fadeOut("fast");
	}
}

// 댓글 쓰기
let allCmtNum = Number($("#allCmtNum").val()); // 총 댓글 수
let allPageNum = Number($("#allPageNum").val()); // 모든 페이지 개수
$("#writeOk").click(function(e) {
	e.preventDefault();
	let formData = $("form[name=writeCmtForm]").serialize(); // 폼 데이터
	let memberId = $("input[name=memberId]").val(); // 로그인한 유저 아이디
	let postMemberId = $("input[name=post_memberId]").val(); // 글쓴이 아이디
	let postId = $("input[name=postId]").val(); // 게시물 아이디
	let postCategory = $("input[name=category]").val(); // 게시물 카테고리
	let pageNum = $("button[name=pageNum]").val(); // 현재 페이지 넘버
	let commentWrapNum = $(".commentWrap").length; // 현재 표시된 댓글 수
	let viewPageNum = $("#viewPageNum").val(); // 표시해야할 댓글 목록 수
	let pageNumber = $(".pageNum").length; // 버튼의 개수
	
// 마지막 페이지가 아니라면 추가하면 안된다.
// 만일 현재 페이지에 댓글이 댓글 목록 수만큼 있다면 추가하면 안된다.
// 댓글을 쓸 때 현재 페이지가 마지막 페이지라면 밑에 추가해도 되지만

	$.ajax({
		url: "addComment",
		type: "post",
		data: formData,
		success: function(data) {
			if (pageNum <= allPageNum && commentWrapNum < viewPageNum) {
				if (data != "empty") {
					let moreHtml;
					let chatHtml;
					if (memberId == data.member.id) {
						moreHtml = '<input onclick="oneChkBox(this); displayUpdate(this)" class="choiceCmt" type="checkbox" name="id" value="' + data.id + '" id="choiceCmt' + data.id + 'New" />' + 
						'<label for="choiceCmt' + data.id + '">' + 
							'<span class="point1"></span>' + 
							'<span class="point2"></span>' + 
							'<span class="point3"></span>' + 
						'</label>'
					} else {
						moreHtml = "";
					}
					if (postMemberId == memberId && memberId != 0 && postCategory == 'E') {
						chatHtml = '<button type="submit" class="chat" value="' + data.member.id + '">채팅하기</button>';
					} else {
						chatHtml = "";
					}
					$("form[name=viewCmtForm]").append(
						'<div class="commentWrap commentWrapNew" id="commentWrap' + data.id + '">' + 
						'<img alt="' + data.member.nickName + '님의 프로필 이미지" src="' + getContextPath() + data.member.profileImgPath + '">' + 
						'<div class="memberContent">' + 
							'<div class="memberInfo">' + 
								'<p class="nickName">' + data.member.nickName + '</p>' + 
								'<p class="mannerTemp">' + data.member.mannerTemperature + '&deg;</p>' + 
								'<time datetime="' + data.regDate + '">' + data.regDate + '</time>' + 
							'</div>' + 
							'<input type="hidden" id="cmtMemberId' + data.id + '" value="' + data.member.id + '"/>' + 
							'<input type="text" id="comment' + data.id + '" class="viewCmt viewCmtContent" name="cmtContent" value="' + data.content + '" disabled />' + 
							'<button type="button" id="modifyContent' + data.id + '" value="' + data.id + 'New" class="modifyContent" onclick="modifyComment(this)">수정하기</button>' + 
						'</div>' + 
						'<div class="moreWrap">' + 
							'<div class="popUpdate">' + moreHtml + 
							'</div>' + 
							'<div class="updateWrap updateWrap' + data.id + '">' + 
								'<button type="button" name="update" class="update" value="modify' + data.id + '" onclick="modifyCommentOpen(this)">수정하기</button>' + 
								'<button type="button" name="update" class="update" value="delete' + data.id + '" onclick="deleteComment(this)">삭제하기</button>' + 
							'</div>' + chatHtml +
						'</div>' + 
					'</div>'
					);
				}
			} else if (commentWrapNum == viewPageNum && allCmtNum % 5 == 0) {
				console.log("버튼 새로 만들기 모든 페이지 수 : " + allPageNum)
				let addPageNum = ++allPageNum;
				$("#pageWrap").append(
					'<button onclick="changePageNumBtnColor(this); moveCommentPage(this)" type="button" name="pageNum" class="pageNum pageNum' + addPageNum +  
					'" value="' + addPageNum + '">' + addPageNum + '</button>'
				)
				movePageNumber("plus");
				if (pageNumber != 0 && pageNumber % 10 == 0) {
					$("#rightPage").show();
				}
			}
			allCmtNum++;
			console.log("현재 댓글 수 : " + allCmtNum)
			console.log("모든 페이지 수 : " + allPageNum)
			
			$("#writeCmt").val("");
		},
		error: function() {
			console.log("error")
		}
	})
})

// 수정하기 열기
let beforeContent; // 원래 텍스트
function modifyCommentOpen(a) {
	$(".updateWrap").stop().fadeOut("fast");
	let id = a.value.substr(6);
	// 다른 것들은 다시 원복해야한다
	let contents = document.getElementsByClassName("viewCmtContent");
	for(let i=0; i<contents.length; i++) {
		contents[i].disabled = true;
		contents[i].style.backgroundColor = "#fff";
	}
	// 클릭한 댓글 내용
	let content = document.getElementById("comment" + id);
	beforeContent = content.value; // 원래 내용 저장해놓는다.
	content.disabled = false;
	content.style.backgroundColor = "#f5f5f5";
}
// 수정하기
function modifyComment(a) {
	let id = a.value;
	let content = document.getElementById("comment" + id);
	let category = $("input[name=category]").val();
	let postId = $("input[name=postId]").val();
	let memberId = $("#cmtMemberId" + id).val();
	let json = {"id": id, "content": content.value, "beforeContent": beforeContent, "category": category, "postId": postId, "memberId": memberId};
	$.ajax({
		url: "modifyComment",
		type: "get",
		data: json,
		contentType : "application/json; charset:UTF-8",
		success: function(data) {
			if (content.value == "") {
				alert("빈 문자열은 넣을 수 없습니다.");
			} else if (data == beforeContent){
				alert("문자열이 같습니다.");
			}
			content.value = data;
			content.disabled = true;
			content.style.backgroundColor = "#fff";
		}
	})
}

let clickPageBtn = 1;
// 삭제하기
function deleteComment(a) {
	let id = a.value.substr(6);
	let category = $("input[name=category]").val();
	let postId = $("input[name=postId]").val();
	let memberId = $("#cmtMemberId" + id).val();	
	let pageNumber = $(".pageNum").length; // 버튼의 개수
	let json = {"id": id, "category": category, "postId": postId, "memberId": memberId};
	$.ajax({
		url: "deleteComment",
		type: "get",
		data: json,
		contentType : "application/json; charset:UTF-8",
		success: function(data) {
			if (data == true) {
				$("#commentWrap" + id).remove();
				allCmtNum--;
				console.log("현재 댓글 수 : " + allCmtNum)
				let viewCmt = $(".commentWrap").length; // 현재 화면에 보이는 댓글 수
				if (viewCmt == 0) {
					$(".pageNum:last-of-type").remove();
					if (clickPageBtn == 1) { // 현재 페이지가 1이면
						$(".pageNum:first-of-type").trigger("click");
					} else if (clickPageBtn == (Number($(".pageNum:last-of-type").val())+1)) { // 현재 페이지가 마지막 페이지이면
						$(".pageNum:last-of-type").trigger("click");
						allPageNum--;
					} else {
						$(".pageNum:nth-of-type( " + clickPageBtn + ")").trigger("click");
					}
					if (pageNumber != 1 && pageNumber % 10 == 1) {
						$("#leftPage").trigger("click");
					}
				} else {
					$(".pageNum:nth-of-type( " + clickPageBtn + ")").trigger("click");
				}
				
				movePageNumber("minus");
			}
		}
	})
}

// 페이지 로드 시 항상 댓글의 첫번 째 페이지가 들어온다.
// 하지만 사용자가 직접 번호를 적었을 때도 불러올 수 있어야가힉 때문에 
// url의 pageNum 을 받아서 해당 버튼으로 갈 수 있도록 해야한다
window.addEventListener("load", function() {
	let pageNum = $("#pageNum").val() // 현재 페이지 번호를 받아서 
	let pageNumber = $(".pageNum").length; // 버튼의 개수
	$(".pageNum" + pageNum).trigger("click");
	if (pageNum > 10 && pageNumber > 10) {
		for (let i=0; i<(Math.floor(pageNum / 10.0)); i++) {
			$("#rightPage").trigger("click");
		}
	}
})

// 버튼 누를 시 해당 버튼이 on 되도록 한다.
function changePageNumBtnColor(a) {
	$(".pageNum").css({"text-decoration": "none", "color":"#000"});
	$(a).css({"text-decoration": "underline", "color":"#f23f3f"});
}


//let state = 0;
// 버튼 누를 시 화면 전환이 되듯이 ajax로 다음 행부터 DB에서 가져오기
function moveCommentPage(a) {
	//let i = 0;
	let pageNum = $(a).val(); // 현재 페이지 넘버
	clickPageBtn = pageNum;
	let category = $("input[name=category]").val(); // 게시물 카테고리
	let memberId = $("input[name=memberId]").val(); // 현재 로그인 아이디
	let postId = $("input[name=postId]").val(); // 현재 게시글 아이디
	let postMemberId = $("input[name=post_memberId]").val(); // 게시물 글쓴이 아이디
	//let scrollY = $(this).scrollTop(); // 현재 스크롤 위치
	//let documentH = $(document).innerHeight(); // 페이지 높이
	//let wHeight = $(window).innerHeight(); // 윈도우 높이
	// let lastId = lastCmt.attr("id").substr(11); // 마지막 댓글의 아이디 (보내야한다.)
	// let lastCmt = $(".commentWrap:last-of-type"); // 현재 페이지의 마지막 댓글
	// 문서 높이 - 윈도우 높이를 하면 현재 스크롤의 마지막 위치값이 나온다.
	//let lastScroll = documentH - wHeight;
	// 마지막 위치값에서 - 50 위치값이 되면 새롭게 불러와야한다.
	let json = {"postId": postId, "category": category, "pageNum":pageNum};
	//if (state == 0 && i == 0) {
		//state = 1;
			$.ajax({
			url: "changeCommentPage",
			type: "get",
			data: json,
			success: function(data) {
				$(".commentWrap").remove();
				for (let i=0; i<data.length; i++){
					let moreHtml;
					let chatHtml;
					if (memberId == data[i].member.id) {
						moreHtml = '<input onclick="oneChkBox(this); displayUpdate(this)" class="choiceCmt" type="checkbox" name="id" value="' + data[i].id + '" id="choiceCmt' + data[i].id + '" />' + 
						'<label for="choiceCmt' + data[i].id + '">' + 
							'<span class="point1"></span>' + 
							'<span class="point2"></span>' + 
							'<span class="point3"></span>' + 
						'</label>'
					} else {
						moreHtml = "";
					}
					if (postMemberId == memberId && memberId != 0 && postCategory == 'E') {
						chatHtml = '<button type="submit" class="chat" value="' + data[i].member.id + '">채팅하기</button>';
					} else {
						chatHtml = "";
					}
					$("form[name=viewCmtForm]").append(
						'<div class="commentWrap" id="commentWrap' + data[i].id + '">' + 
						'<img alt="' + data[i].member.nickName + '님의 프로필 이미지" src="' + getContextPath() + data[i].member.profileImgPath + '">' + 
						'<div class="memberContent">' + 
							'<div class="memberInfo">' + 
								'<p class="nickName">' + data[i].member.nickName + '</p>' + 
								'<p class="mannerTemp">' + data[i].member.mannerTemperature + '&deg;</p>' + 
								'<time datetime="' + data[i].regDate + '">' + data[i].regDate + '</time>' + 
							'</div>' + 
							'<input type="hidden" id="cmtMemberId' + data[i].id + '" value="' + data[i].member.id + '"/>' + 
							'<input type="text" id="comment' + data[i].id + '" class="viewCmt viewCmtContent" name="cmtContent" value="' + data[i].content + '" disabled="true" />' + 
							'<button type="button" id="modifyContent' + data[i].id + '" value="' + data[i].id + '" class="modifyContent" onclick="modifyComment(this)">수정하기</button>' + 
						'</div>' + 
						'<div class="moreWrap">' + 
							'<div class="popUpdate">' + moreHtml + 
							'</div>' + 
							'<div class="updateWrap updateWrap' + data[i].id + '">' + 
								'<button type="button" name="update" class="update" value="modify' + data[i].id + '" onclick="modifyCommentOpen(this)">수정하기</button>' + 
								'<button type="button" name="update" class="update" value="delete' + data[i].id + '" onclick="deleteComment(this)">삭제하기</button>' + 
							'</div>' + chatHtml +
						'</div>' + 
					'</div>'
					);
				}
				//if (i == data.length) {
				//	state = 0;
				//}
			},
			error: function() {
				console.log("error")
			}
		})
//}
}

// 페이지 버튼들
let btnW = $(".pageNum:first-of-type").outerWidth(); // 버튼 너비
let move = 0;
movePageNumber("normal");
// < 버튼
$("#leftPage").click(function() {
	movePageNumber("left");
})
// > 버튼
$("#rightPage").click(function() {
	movePageNumber("right");
})
// << 버튼
$("#firstPage").click(function() {
	movePageNumber("first");
})
// >> 버튼
$("#lastPage").click(function() {
	movePageNumber("last");
})
// 등록 버튼
$("#writeOk").click(function() {
	movePageNumber("write");
})

function movePageNumber(dir) {
	let pageNumber = $(".pageNum").length; // 버튼의 개수
	let allBtnW = btnW * pageNumber; // 현재 모든 버튼의 너비
	// move 는 moveNumber 보다 커져서는 안된다.
	console.log("버튼 개수 : " + pageNumber)
	let moveNumber = Math.floor(pageNumber / 10.0); // 이동해야하는 횟수
	if (pageNumber > 10 && pageNumber % 10.0 == 0) { // 만일 페이지 수가 딱 떨어진다면 -1 해준다
		moveNumber--;
	}
	$("#pageWrap").css({"width":allBtnW + "px"})
	if (dir == "plus" || dir == "minus") {
		$("#pageWrap").css({"width":btnW*allPageNum + "px"})
	}
	
	if (dir == "left") {
		// move 상태가 0 이면 좌측으로 움직이면 안된다.
		// 움직인다면 좌측으로 300px만큼 가야한다.
		if (move > 0) {
				$("#pageWrap").css({"left":"+=300"});
			move--;
		}
	} else if (dir == "right") {
		if (move < moveNumber) {
				$("#pageWrap").css({"left": "-=300"});
			move++;
		}
	} else if (dir == "normal" || dir == "first") {
		$("#pageWrap").css({"left": "0"});
		move = 0;
	} else if (dir == "last") {
		$("#pageWrap").css({"left": "-" + (300 * moveNumber) + "px"});
		move = moveNumber;
	}
	// 페이지 개수가 10 이하이면 모두 숨김
	if(pageNumber <= 10) {
		$("#rightPage").hide();
		$("#lastPage").hide();
		$("#leftPage").hide();
		$("#firstPage").hide();
	} else { // 페이지 개수 10 넘을 때
		// 좌 / 우 / 처음 / 마지막 버튼
		if (move > 0) { // 2 페이지 이상
			$("#firstPage").show();
			$("#leftPage").show();
		}
		if (move < moveNumber) { // 마지막 페이지보다 작을 때
			$("#rightPage").show();
			$("#lastPage").show();
		}
		if (move == 0) { // 첫 페이지
			$("#firstPage").hide();
			$("#leftPage").hide();
			$("#rightPage").show();
			$("#lastPage").show();
		}
		if (move == moveNumber) { // 마지막 페이지
			$("#firstPage").show();
			$("#leftPage").show();
			$("#rightPage").hide();
			$("#lastPage").hide();
		}
	}
	let wrapW = pageNumber % 10;
	console.log("moveNumber : " + moveNumber)
	console.log("move : " + move)
	if (wrapW != 0 && move == moveNumber) {
		$("#cmtPageNumWrap").css({"width":btnW*wrapW + "px"})
	} else {
		$("#cmtPageNumWrap").css({"width":"300px"})
	}
}


if($(".pageNum").length <= 10) {
	$("#rightPage").hide();
	$("#lastPage").hide();
	$("#leftPage").hide();
	$("#firstPage").hide();
} else {
	$("#rightPage").show();
	$("#lastPage").show();
	$("#leftPage").hide();
	$("#firstPage").hide();
}






