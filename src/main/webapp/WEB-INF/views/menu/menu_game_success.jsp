<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코 메뉴 추천결과</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu/menu_game_success.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<section>
        <h2>"<span class="logo">밥찡코</span>"가 뽑아조써용</h2>
        <div id="imgtage">
            <img width="300px" height="300px" src="${menu.imgPath}"/>
        </div>
        <div>
            <h3><span>${menu.menuName}</span>입니당</h3>
            
            <input type="button" value="쳐묵!" class="gogo" onClick="window.close()"/>
        </div>
    </section>
    <script>
    </script>
</body>
</html>