<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<section id="chatAlarmWrap">
	<div id="chatShowHide">
		<h4>채팅이 도착했어요!</h4>
		<button id="chatOff" onclick="closeChatAlarm();">X</button>
		<div id="chatMessageWrap">
			<p id="rcvMessage">
				향기 언니님 맞으신가요?<br>
				글 올렷던, 아루 누나입니다!
			</p>
			<form action="${pageContext.request.contextPath}/posting/goChat" method="post" id="goChatForm">
				<input type="hidden" name="commenter" id="commenter" value="${commenter.email}" />
				<input type="hidden" name="writer" id="writer" value="${writer.email}" />
				<input type="submit" name="goChatting" id="goChatting" value="채팅 바로가기" />
			</form>
		</div>
	</div>
</section>
<script>
	/* 김보령 - 1대1 채팅방을 위한 사용자 추가 */
	let check = "<c:out value='${member}'/>";
	let urlChk = window.location.href;
	let urlArr = urlChk.split("/");
	let pageName = urlArr[urlArr.length - 1];
	let sockChat = null;
	if (check != "" && pageName != "goChat"){
		sockChat = new SockJS("<c:url value="/oneChatting"/>");
		sockChat.onmessage = onMessage;
	}
	
	function closeChatAlarm() {
		$("#chatAlarmWrap").fadeOut();
		sockChat.send($("input[name=writer]").val() + ":[${member.nickName}]님은 시간이 부족하네요ㅠㅠ");
	}
	function onMessage(evt) {
		let data = evt.data;
		
		let arr = data.split("//");
		$("#chatAlarmWrap").fadeIn(400);
		$("#rcvMessage").html(arr[0]);
		$("#commenter").val(arr[1]);
		$("#writer").val(arr[2]);
	}
</script>