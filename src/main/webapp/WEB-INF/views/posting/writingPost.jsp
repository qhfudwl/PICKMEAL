<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<title>게시글쓰기</title>
<style>
#writeArea {
	background: #f2f2f2;
	width: 600px;
	height: 800px;
}

</style>
<script type="text/javascript">
function onClickBtn(){
	console.log($('#writeArea').html())
}

</script>
</head>
<body>
<div id="writeArea"	contentEditable="true">
안녕하세용
<b>반가워용</b>
<img src="/pickmeal/resources/img/posting/sky.jpg" alt="">
</div>
<button type="button" onclick="onClickBtn()">버튼</button>
</body>
</html>