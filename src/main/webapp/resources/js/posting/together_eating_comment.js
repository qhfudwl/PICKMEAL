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
function addComment(a) {
	
}