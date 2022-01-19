<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>발생완료</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<form name="menuGamePopup" method="GET">
		<input class= "menusubmit" type="submit" value="메뉴골라주기" id="menuchoicebtn">
	</form>
	<script>
	
	$("#menuchoicebtn").click(function(e){
		e.preventDefault();
		popupMenu("menuGamePopup");
	})
	function popupMenu(Url){
        let windowWidth = window.screen.width;
        let windowHeight = window.screen.height;
        
        let popupX = (windowWidth/2) - 550;
        let popupY = (windowHeight/2) -225;
        
        let popUpdateUrl = "http://localhost:8080/pickmeal/" + Url;
        let popUpdateOption = "width=570px, height=470px, top=" + popupY + "px, left=" + popupX + "px";
        let popUpdateTitle = "메뉴 게임"
        
        if(Url == "menuGamePopup") {
            window.open(popUpdateUrl, popUpdateTitle, popUpdateOption);
        }
        let menuGamePopup = document.menuGamePopup;
        menuGamePopup.target = popUpdateTitle;
        menuGamePopup.action = popUpdateUrl;
        menuGamePopup.method ="get";
        
        menuGamePopup.submit();
    }
    function reloadPage() {
        location.reload();
    }
	</script>
</body>
</html>