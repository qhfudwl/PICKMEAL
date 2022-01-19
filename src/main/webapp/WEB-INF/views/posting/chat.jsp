<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/chat/chat.css" rel="stylesheet" type="text/css">
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>



<title>밥찡코</title>


</head>
<body>
	<%@ include file="/WEB-INF/views/incl/header.jsp"%>
	<section id="chatWrap">
	<button id="exitChat">나가기</button>
		<div id="msgArea">
            <div id="msgWrap">
                
            </div>
        </div>
        <div id="writeArea">
            <textarea name="msg" id="write_msg"></textarea>
            <div id="input-group-append">
                <button type="button" id="button_send">전송</button>
            </div>
        </div>
	</section>
	
	<input type="hidden" name="commenter" value="${commenter.email}"/>
	<input type="hidden" name="writer" value="${writer.email}"/>
	<input type="hidden" name="member" value="${member.email}"/>

<script type="text/javascript">


$(document).ready(function() {
	$.ajax({
		url: "downloadFile",
		type: "post",
		dataType: "text",
		success: function(data) {
			console.log(data)
			$("#msgWrap").html(data);
			$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
		}
	})
})

let commenter = $("input[name=commenter]").val();
let writer = $("input[name=writer]").val();
let member = $("input[name=member]").val();


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

var sock = new SockJS("<c:url value="/oneChatting"/>");
sock.onmessage = onMessage;
sock.onclose = onClose;
sock.onopen = onOpen;

$("#exitChat").click(function() {
	$.ajax({
		url: "uploadFile",
		type: "post",
		data: {
			"tagData": $("#msgWrap").html()
		}
	})
})

function sendMessage() {	
	var msg = $("#write_msg").val();
	console.log("msg : " + msg);
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
	
	
	//var arr = data.split(":");
	
	var name = data.substring(0, data.indexOf(":"));
	message = data.substring(data.indexOf(":")+1, data.length);
	
	message = message.replace(/\s+$/,'').replace(/\s/g, "&nbsp;");
	
	var cur_session = '${member.nickName}'; //현재 세션에 로그인 한 사람
	console.log("cur_session : " + cur_session);
	
	sessionId = name;
	//sessionId = arr[0];
	//message = arr[1];
	
	//현재시간 ex) 오전 10:50
	var today = new Date();	
	var time = today.nowHour() + ":" + today.nowMinute();
	
	console.log(message)
	
	//로그인 한(본인) 클라이언트와 타 클라이언트를 분류하기 위함
	if (!message.includes("와&nbsp;채팅이&nbsp;시작되었습니다.")) {
		if(sessionId == cur_session){
			var str = "<div class='textBoxArea me'>";
			str += "<span class='name'>" + sessionId + "</span>";
			str += "<div class='textField'>";
			str += "<span class='time'>"+ time +"</span>";
			str += "<div class='textBox me'>";
			str += "<span class='msgBox'>" + message + "</span>";
			str += "</div></div></div>";
			
			$("#msgWrap").append(str);
			$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
		} else {
			var scroll = $('#msgArea').prop('scrollHeight') - $('#msgArea').scrollTop(); // defaultScrollHeight이상의 값
			
			var str = "<div class='textBoxArea'>";
			str += "<span class='name'>" + sessionId + "</span>";
			str += "<div class='textField'>";
			str += "<div class='textBox'>";
			str += "<span class='msgBox'>" + message + "</span>";
			str += "</div>";
			str += "<span class='time'>"+ time +"</span>";
			str += "</div></div>";
			
			$("#msgWrap").append(str);
			if(nowScroll(scroll)) {
				$('#msgArea').scrollTop($('#msgArea').prop('scrollHeight'));
			}
		}
	}
}

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
	
	if (writer == member) {
		sock.send("first send:" + commenter);
	} else if (commenter == member) {
		sock.send("${member.email}:[${member.nickName}]님이 입장했습니다.")
	}
	
	$("#msgWrap").append(str);
}

//채팅창에서 나갔을 때
function onClose(evt) {
	var user = '${member.nickName}';
	var str = '퇴장했습니다.';
	
	$("#msgWrap").append(str);
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
</script>
</html>