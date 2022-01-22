<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/chat/chat_list.css" rel="stylesheet" type="text/css">
<title>밥찡코</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/incl/header.jsp"%>
	<section id="chatContentWrap">
		<h2 class="hidden">채팅 목록 화면</h2>
		<section id="chatListWrap">
			<h3 class="hidden">채팅 목록</h3>
			<c:forEach var="c" items="${chats}"> 
				<div class="chatterWrap" id="chatterWrap${c.id}">
				<c:choose>
					<c:when test="${member.id eq c.writer.id}">
						<img alt="${c.commenter.profileImgPath}님의 프로필 이미지" src="${pageContext.request.contextPath}${c.commenter.profileImgPath}">
						<p class="chatterMT">${c.commenter.mannerTemperature}&deg;</p>
						<p class="chatterNickName">${c.commenter.nickName}</p>
					</c:when>
					<c:otherwise>
						<img alt="${c.writer.profileImgPath}님의 프로필 이미지" src="${pageContext.request.contextPath}${c.writer.profileImgPath}">
						<p class="chatterMT">${c.writer.mannerTemperature}&deg;</p>
						<p class="chatterNickName">${c.writer.nickName}</p>
					</c:otherwise>
				</c:choose>
					<time datetime="${c.regDate}"><fmt:formatDate value="${c.regDate}" pattern="yyyy년 MM월 dd일" /></time>
					<button type="button" name="goChatting" class="choiceChatter" data-writernick="${c.writer.nickName}" data-commenternick="${c.commenter.nickName}"
					 data-writer="${c.writer.id}" data-commenter="${c.commenter.id}" data-member="${member.id}" id="choiceChatter_${c.id}" value="${c.id}" onclick="downloadFile(this)">채팅</button>
				</div>
			</c:forEach>
		</section>
		<section id="chatWrap">
			<h3 class="hidden">채팅 화면</h3>
		<!-- 실제 채팅 방이 들어갈 공간 -->
		
			<!-- <button id="exitChat">나가기</button> -->
			<div id="msgArea">
	            <div id="msgWrap">
	                <!-- 채팅 내용이 들어가는 공간 -->
	            </div>
	        </div>
	        <div id="writeArea">
	            <textarea name="msg" id="write_msg"></textarea>
	            <div id="input-group-append">
	                <button type="button" id="button_send">전송</button>
	            </div>
	        </div>
			
			<script type="text/javascript">
			
			// 페이지가 로드될 때 목록에 이미 있는 상대와 채팅이라면 해당 채팅방을 클릭한다
			$(document).ready(function() {
				let chatId = "${chat.id}";
				if (chatId != "0") { // 있는 채팅이라면
					$("#choiceChatter_" + chatId).trigger("click");
				}
			})
			
			let writerNick = "${writer.nickName}";
			let commenterNick = "${commenter.nickName}";
			
			let writerId = "${writer.id}";
			let commenterId = "${commenter.id}";
			
			// 기존의 파일 다운로드 해서 태그 생성
			function downloadFile(a) {
				$(".choiceChatter").removeClass("on");
				
				writerNick = $(a).data("writernick")
				commenterNick = $(a).data("commenternick")
				writerId = $(a).data("writer")
				commenterId = $(a).data("commenter")
				
				$(a).addClass("on");
				$.ajax({
					url: "downloadFile",
					type: "post",
					data: {
						"writerId" : writerId,
						"commenterId": commenterId
					},
					success: function(data) {
						$("#msgWrap").html("");
						//$("#chatStart").val("true"); // 채팅을 누르면 한다는 뜻이므로 값을 true 로 준다
						connectSockJs();
						let typeArr = data[0].split("[LI_PICKMEAL_PI]")
						let writer = typeArr[0].substring(typeArr[0].indexOf(":")+1, typeArr[0].length); // writer nickName
						let commenter = typeArr[1].substring(typeArr[1].indexOf(":")+1, typeArr[1].length); // commenter nickName
						$(".removeMsg").remove();
						for (let i=1; i<data.length; i++) {
							let arr = data[i].split("[LI_PICKMEAL_PI]");
							let type = arr[0]; // writer 내용인지 commenter 내용인지
							let time = arr[1]; // 시간
							let content = arr[2]; // 내용
							if (writer == "${member.nickName}") { // 사용자가 writer 일 경우
								if (type == "writer") { // writer 내용의 경우 (자기 자신)
									makeMeTag(writer, time, content);
								} else if (type == "commenter") { // commenter 내용의 경우
									makeOpponentTag(commenter, time, content);
								}
							} else if (commenter == "${member.nickName}") { // 사용자가 commenter 일 경우
								if (type == "writer") { // 상대방
									makeOpponentTag(writer, time, content);
								} else if (type == "commenter") { // 자기 자신
									makeMeTag(commenter, time, content);
								}
							}
						}
					}
				})
			}
			// 사용자가 입력한 메세지 태그
			function makeMeTag(nickName, time, content) {
				let str = "<div class='textBoxArea me'>";
				str += "<span class='name'>" + nickName + "</span>";
				str += "<div class='textField'>";
				str += "<span class='time'>"+ time +"</span>";
				str += "<div class='textBox me'>";
				str += "<span class='msgBox'>" + content + "</span>";
				str += "</div></div></div>";
				
				$("#msgWrap").append(str);
				$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
			}
			// 상대방이 입력한 메세지 태그
			function makeOpponentTag(nickName, time, content) {
				let scroll = $('#msgArea').prop('scrollHeight') - $('#msgArea').scrollTop(); // defaultScrollHeight이상의 값
				
				let str = "<div class='textBoxArea'>";
				str += "<span class='name'>" + nickName + "</span>";
				str += "<div class='textField'>";
				str += "<div class='textBox'>";
				str += "<span class='msgBox'>" + content + "</span>";
				str += "</div>";
				str += "<span class='time'>"+ time +"</span>";
				str += "</div></div>";
				
				$("#msgWrap").append(str);
				if(nowScroll(scroll)) {
					$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
				}
			}
			
			
			
			
			$('#write_msg').on("keyup", function(key) {
				if(key.keyCode==13) {
					sendMessage();
					$('#write_msg').val('')
					$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
				}
			});
			
			//전송 버튼 누르는 이벤트
			$("#button_send").on("click", function(e) {
				sendMessage();
				$('#write_msg').val('')
			});

			let chatStart = "${chatStart}"; // 채팅하기를 누르거나 댓글로부터 들어올 경우 true 로 변경되면서 sockJs와 연결해야한다.
			let sock;
			if (chatStart == "true") {
				connectSockJs();
			}
			
			function connectSockJs() {
				sock = new SockJS("<c:url value="/oneChatting"/>");
				sock.onmessage = onMessage;
				sock.onclose = onClose;
				sock.onopen = onOpen;
			}
			
			
			function sendMessage() {	
				var msg = $("#write_msg").val();
				var blank_pattern = /^\s+|\s+$/g;
				if(msg == '' || msg.replace(blank_pattern, '') == "" ) {
					return;
				}
				msg = '${member.nickName}:' + msg;
				sock.send(msg);
			}
			
			var defaultScrollHeight = $('#msgArea').prop('scrollHeight');
			//서버에서 메시지를 받았을 때
			function onMessage(msg) {
			
				var data = msg.data;
				var sessionId = null; //데이터를 보낸 사람
				var message = null;
				
				var name = data.substring(0, data.indexOf(":"));
				message = data.substring(data.indexOf(":")+1, data.length);
				
				console.log("name : " + name)
				console.log("message : " + message)
				
				message = message.replace(/\s+$/,'').replace(/\s/g, "&nbsp;");
				
				var cur_session = '${member.nickName}'; //현재 세션에 로그인 한 사람
				console.log("cur_session : " + cur_session);
				
				sessionId = name;
				
				//현재시간 ex) 오전 10:50
				var today = new Date();	
				var time = today.nowHour() + ":" + today.nowMinute();
				
				// 댓글 작성자가 로그인 상태인지 표시
				if (message.includes("댓글&nbsp;작성자&nbsp;로그인&nbsp;중") || message.includes("댓글&nbsp;작성자&nbsp;비로그인&nbsp;중")) {
					$("#msgWrap").append("<p class='removeMsg'>" + message + "</p>");
					$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
					return;
				}
				//로그인 한(본인) 클라이언트와 타 클라이언트를 분류하기 위함
				if (message.includes("와&nbsp;채팅이&nbsp;시작되었습니다.<br>채팅에&nbsp;참석을&nbsp;원한다면&nbsp"
						+ ";아래의&nbsp;채팅&nbsp;바로가기&nbsp;버튼을&nbsp;눌러주세요.<br>참석을&nbsp;원하지&nbsp;않을&nbsp;시&nbsp;창을&nbsp;닫아주세요.")) {
					return;
				}
				let member = "${member.id}";
				
				if (name != writerNick && name != commenterNick) { // 현재 선택된 닉네임들과 name 값이 다르다면 메세지를 띄우면 안된다.
					return;
				}
				if (message.includes("]님이&nbsp;입장했습니다.")) {
					$("#msgWrap").append("<p class='removeMsg'>" + message + "</p>");
					$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
					return;
				}
				
				// 메세지 시작
				$(".removeMsg").remove();
				if (message.includes("]님은&nbsp;시간이&nbsp;부족하네요ㅠㅠ")){
					makeOpponentTag(commenterNick, time, message);
					return;
				}
				if(sessionId == cur_session){
					makeMeTag(sessionId, time, message);
				} else {
					makeOpponentTag(sessionId, time, message);
				}
				
				let fileText = tagToFileText();

				$.ajax({
					url: "uploadFile",
					type: "post",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8",
					data: {
						"writerId" : writerId,
						"commenterId": commenterId,
						"fileText": fileText
					}, success: function(data) {
						console.log("success")
					}, error: function(data) {
						console.log("error")
					}
				})
			}
			let addChat = 0;
			
			Date.prototype.nowMinute = function() {
				return this.getMinutes() < 10 ? "0" + this.getMinutes() : this.getMinutes();
			};
			
			Date.prototype.nowHour = function() {
				var nh = this.getHours();
				var hour = nh < 12 ? "오전 " : "오후 ";
				
				if(nh == 12 || nh == 0) {
					hour += 12;
				} else if(nh > 12) {
					hour += nh-12;
				} else {
					hour += nh;
				}
				
				return hour;
			};
			
			//현재 스크롤위치가 하단 근처라면 true
			function nowScroll(scroll) {
				return (scroll < defaultScrollHeight+230)
			};
			
			//채팅창에 들어왔을 때
			function onOpen(evt) {
				var user = '${member.nickName}';
				var str = '채팅창에 입장했습니다 [' + user + ']';
				
				let writerNick = $(".choiceChatter.on").data("writernick");
				let commenterNick = $(".choiceChatter.on").data("commenternick");
				let memberNick = "${member.nickName}";
				
				if (writerNick == memberNick) {
					sock.send("first send:" + commenterNick);
				} else if (commenterNick == memberNick) {
					sock.send("${member.nickName}:[${member.nickName}]님이 입장했습니다.")
				}
				
				$("#msgWrap").append("<p class='removeMsg'>" + str + "</p>");
				$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
			}
			
			//채팅창에서 나갔을 때
			function onClose(evt) {
			}
			
			$('#button_send').on('mouseenter', function() {
				$('#button_send').css({
				    backgroundColor: '#f7e5e5'
				})
			});
			
			$('#button_send').on('mouseleave', function() {
				$('#button_send').css({
				    backgroundColor: '#f35c5c'
				})
			});
			
			$('#button_send').on('mousedown', function() {
				$('#button_send').css({
				    backgroundColor: '#eedddd'
				})
			});
			
			$('#button_send').on('mouseup', function() {
				$('#button_send').css({
				    backgroundColor: '#f7e5e5'
				})
			});

			// 태그를 파일로 저장하기 위해서 정제를 해야한다.
			function tagToFileText() {
				let msgName = [];
				let msgContent = [];
				let msgTime = [];
				$("span.name").each(function() { // 각 이름들
					if ($(this).text() == writerNick) {
						msgName.push("writer");
					} else if ($(this).text() == commenterNick) {
						msgName.push("commenter");
					}
				})
				$("span.msgBox").each(function() { // 각 내용들
					msgContent.push($(this).text());
				})
				$("span.time").each(function() { // 각 시간들
					msgTime.push($(this).text());
				})
				let fileText = [];
				fileText.push("writer:" + writerNick + "[LI_PICKMEAL_PI]commenter:" + commenterNick); // writer:아루누나 commenter:향기언니
				for (let i=1; i<$("div.textBoxArea").length + 1; i++) { // 메세지 개수만큼 돌린다.
					fileText[i] = msgName[i-1] + "[LI_PICKMEAL_PI]" + msgTime[i-1] + "[LI_PICKMEAL_PI]" + msgContent[i-1];
				}
				
				return fileText;
			}
			</script>
		</section>
	</section>
</body>
</html>