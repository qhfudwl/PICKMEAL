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

// 현재 마지막 페이지에 몇개가 추가되었는지 알 수 있도록 하는 변수
let addCmtLastPage = 0;
// 댓글 쓰기
$("#writeOk").click(function(e) {
	e.preventDefault();
	let formData = $("form[name=writeCmtForm]").serialize(); // 폼 데이터
	let memberId = $("input[name=memberId]").val(); // 로그인한 유저 아이디
	let postMemberId = $("input[name=post_memberId]").val(); // 글쓴이 아이디
	let postId = $("input[name=postId]").val(); // 게시물 아이디
	let postCategory = $("input[name=category]").val(); // 게시물 카테고리
	let allPageNum = $("#allPageNum").val(); // 모든 페이지 개수
	let pageNum = $("button[name=pageNum]").val(); // 현재 페이지 넘버
	let commentWrapNum = $(".commentWrap").length; // 현재 표시된 댓글 수
	let viewPageNum = $("#viewPageNum").val(); // 표시해야할 댓글 목록 수
	
// 마지막 페이지가 아니라면 추가하면 안된다.
// 만일 현재 페이지에 댓글이 댓글 목록 수만큼 있다면 추가하면 안된다.
// 댓글을 쓸 때 현재 페이지가 마지막 페이지라면 밑에 추가해도 되지만

	$.ajax({
		url: "addComment",
		type: "post",
		data: formData,
		success: function(data) {
			if (pageNum < allPageNum && commentWrapNum < viewPageNum) {
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
			} else if (commentWrapNum == viewPageNum && addCmtLastPage == 0) {
				addCmtLastPage++;
				let addPageNum = Number(allPageNum) + 1;
				$("#pageWrap").append(
					'<button onclick="changePageNumBtnColor(this); moveCommentPage(this)" type="button" name="pageNum" class="pageNum pageNum' + addPageNum +  
					'" value="' + addPageNum + '">' + addPageNum + '</button>'
				)
				if (addCmtLastPage == viewPageNum) {
					addCmtLastPage = 0;
				}
			}
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
	let json = {"id": id, "category": category, "postId": postId, "memberId": memberId};
	$.ajax({
		url: "deleteComment",
		type: "get",
		data: json,
		contentType : "application/json; charset:UTF-8",
		success: function(data) {
			if (data == true) {
				$("#commentWrap" + id).remove();
				let viewCmt = $(".commentWrap").length; // 현재 화면에 보이는 댓글 수
				if (viewCmt == 0) {
					$(".pageNum:last-of-type").remove();
					if (clickPageBtn == 1) { // 현재 페이지가 1이면
						$(".pageNum:first-of-type").trigger("click");
					} else if (clickPageBtn == (Number($(".pageNum:last-of-type").val())+1)) { // 현재 페이지가 마지막 페이지이면
						$(".pageNum:last-of-type").trigger("click");
					} else {
						$(".pageNum:nth-of-type( " + clickPageBtn + ")").trigger("click");
					}
				}
			}
		}
	})
}

// 페이지 로드 시 항상 댓글의 첫번 째 페이지가 들어온다.
// 그렇기 때문에 버튼도 1번을 on
window.addEventListener("load", function() {
	$(".pageNum1").css({"text-decoration": "underline", "color":"#f23f3f"});
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
// 등록 버튼
$("#writeOk").click(function() {
	movePageNumber("write");
})

function movePageNumber(dir) {
	let pageNumber = $(".pageNum").length; // 버튼의 개수
	console.log(pageNumber)
	let allBtnW = btnW * pageNumber; // 현재 모든 버튼의 너비
	// move 는 moveNumber 보다 커져서는 안된다.
	let moveNumber = Math.floor(pageNumber / 10.0); // 이동해야하는 횟수
	if (pageNumber > 10 && pageNumber % 10.0 == 0) { // 만일 페이지 수가 딱 떨어진다면 -1 해준다
		moveNumber--;
	}
	$("#pageWrap").css({"width":allBtnW + "px"})
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
	} else if (dir == "normal") {
		$("#pageWrap").css({"left": "0"});
		move = 0;
	}
}








