/**

		글쓰기 JavaScript

 */
console.log('post_write in')

/**
		이미지 첨부
			1) 이미지 첨부하면 리스트에 띄워준다
			2) 이미지 첨부하면 글쓰기 칸에 넣어준다
			
		- 제약사항
			1) 파일은 10개까지
			2) jpg, png, gif만 가능
			3) 중복된 파일은 올릴 수 없다
		
		- 설명
			1) input type file을 불러온다(멀티파일)
				$('#multiFileInput')[0] : HTMLInputElement 객체
				$('#multiFileInput')[0].files : fileList
			2) fileList를 Ajax로 보내야한다
				이유 : form 형태로 보내버리면, 단순 파일첨부형태가 되며, 
				이미지를 1개씩 추가할 수가 없다 ( 첨부하기 누르면 이전에 첨부했던거 날라감 )
				기존의 $('#multiFileInput')[0].files의  fileList는 
				readOnly라 수정삭제도 불가능하다
				그래서 따로 가공한 형태의 fileList를 ajax로 보내줘야하는데 
			3) 따로 만든 fileList를 보내주기 위하여 fileBuffer를 만든다
				
 */


let fileBuffer= [];
//파일 첨부하면
$('#multiFileInput').change(function() {
	
	const target = $('#multiFileInput');
	
	//총 file 갯수
	let totalFileCnt = target[0].files.length + fileBuffer.length;
	console.log('총 파일 갯수는 ?'+totalFileCnt);
	
	//제약사항 - 파일 10개까지 등록
	if(totalFileCnt>10){
		alert('파일은 최대 10개까지 등록가능합니다');
		//파일리셋
		resetFileToPwrite();
		return false;
	}
	$.each(target[0].files, function(index, file){
		//제약사항 - 이미지파일은 jpg, png, gif만
		const fileName = file.name;
		const fileEx = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
		if(fileEx != "jpg" && fileEx != "png" &&  fileEx != "gif"){
	        alert("파일은 (jpg, png, gif) 형식만 등록 가능합니다.");
	        resetFileToPwrite();
	        return false;
	     }
	});
	
	
	//fileBuffer에 첨부된 파일들을 붙인다
	//2번째 인자의 배열을 1번째 인자의 배열에 이어서 붙이기
	Array.prototype.push.apply(fileBuffer, target[0].files);
	
	
	//첨부파일 리스트에 추가해주기
	addAttachedImgList(fileBuffer);
	

});

//현재 파일첨부한거 초기화
function resetFileToPwrite(){
	$('#multiFileInput').val("");
}

//정적 이미지 파일들 경로 지정
let wPostAttachedImgIconSrc="posting/attached_picture.png";
let wPostattachedImgDelIconSrc="posting/close.png";

//파일리스트 화면에 보여주기
function addAttachedImgList(fileBuffer){
	//기존 DOM에있는 파일리스트를 전부 지워준다
	$('.wPostAttachedImgList li').remove();
	
	
	$.each(fileBuffer, function(index, file){
		let post_html = '';
		const fileName = file.name;
		post_html += '<li>';
		post_html += '<img src="'+wPostAttachedImgIconSrc+'" class="wPostAttachImgIcon" alt="이미지파일 아이콘">';
		post_html += '<p class="wPostAttachImgTitle">';
		post_html += fileName;
		post_html += '<span>("'+file.size+'")</span></p>';
		post_html += '<img src="'+wPostattachedImgDelIconSrc+'" class="wPostAttachImgDelIcon" alt="이미지파일 삭제아이콘">';
		post_html += '</li>'; 
		console.log('fileBuffer Counter'+index);
		$('.wPostAttachedImgList').append(post_html);
	 });
}

/*
	
		파일리스트 디자인
			- document on 쓰는이유
				1) on click 함수는 script 코드로 append 추가한 tag들에 관해서는
				변화를 일으킬 수 없다.
				2) document on을 쓰면 화면을 한 번 읽은 후에 이벤트를 발생시킴으로
				변화를 줄 수 있다.
				
			- hover, onclick css이벤트 쉽게주기
				과거) 1개의 selected 태그를 클래스로 추가함으로서 조건문이 많이 들어가고
				코드가 길어졌다.
				현재) hover시 변화주는 class와 onclick시 변화는 class를 따로두어 
				관리하니 코드가 간단해졌다.

*/
$(document).on("mouseenter", ".wPostAttachedImgList li", function() {
//$('.wPostAttachedImgList li').click(function(){
	//다른 리스트 선택된 효과 지우기
	$('.wPostAttachedImgList li').removeClass('wPostHoverImg');
	//지금 선택된 리스트 선택 효과 주기
	$(this).addClass('wPostHoverImg');
})

$(document).on("click", ".wPostAttachedImgList li", function() {
	//다른 리스트 선택된 효과 지우기
	$('.wPostAttachedImgList li').removeClass('wPostSelectedImg');
	//지금 선택된 리스트 선택 효과 주기
	$(this).addClass('wPostSelectedImg');
})

/**

		이미지삭제
			1) 리스트에서 이미지를 삭제하면, 리스트에서도 지워주고 fileBuffer에서도 지워준다
			2) 글작성 div에서 이미지를 삭제해준다 


 */





