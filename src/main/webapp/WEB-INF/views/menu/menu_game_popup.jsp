<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>밥찡코 메뉴추천</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/menu/menu_game_popup.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <section id="popupScreen">
        <h2>메뉴를 골라뿌자</h2>
        <h3>!묵츠묵츠!</h3>
    <form action="menuGameSuccess" method="get">
        <div id="select1">
            <div class="soupybox buttonbox" id="soupybox1">
                <input class="soupyradio" type="radio" id="soupy0" name="soupy" value="0">국(물)있
                <label for="soupy0"></label>
            </div>
            <div class="soupybox buttonbox" id="soupybox2">
                <input class="soupyradio" type="radio" id="soupy1" name="soupy" value="1">국(물)없
                <label for="soupy1"></label>
            </div>
            <div class="soupybox buttonbox" id="soupybox3">
                <input class="soupyradio" type="radio" id="soupy2" name="soupy" value="2">상관없
                <label for="soupy2"></label>
            </div>
            <button class="select2veiw btn" type="button">선택완료</button>
        </div>
        <div id="select2">
            <div class="hot_icebox buttonbox" id="hot_icebox1">
                <input class="hot_iceradio" type="radio" id="hot_ice0" name="hot_ice" value="0">뜨거운
                <label for="hot_ice0"></label>
            </div>
            <div class="hot_icebox buttonbox" id="hot_icebox2">
                <input class="hot_iceradio" type="radio" id="hot_ice1" name="hot_ice" value="1">차가운
                <label for="hot_ice1"></label>
            </div>
            <div class="hot_icebox buttonbox" id="hot_icebox3">
                <input class="hot_iceradio" type="radio" id="hot_ice2" name="hot_ice" value="2">상관없음
                <label for="hot_ice2"></label>
            </div>
            <button class="select3veiw btn" type="button">선택완료</button>
        </div>
        <div id="select3">
            <div class="carbohydrateboxline1 carbohydratebox" id="carbohydratebox1">
                <input class="carbohydrateradio" type="radio" id="carbohydrate0" name="carbohydrate" value="0">밥
                <label for="carbohydrate0"></label>
            </div>
            <div class="carbohydrateboxline1 carbohydratebox" id="carbohydratebox2">
                <input class="carbohydrateradio" type="radio" id="carbohydrate1" name="carbohydrate" value="1">빵
                <label for="carbohydrate1"></label>
            </div>
            <div class="carbohydrateboxline1 carbohydratebox" id="carbohydratebox3">
                <input class="carbohydrateradio" type="radio" id="carbohydrate2" name="carbohydrate" value="2">면
                <label for="carbohydrate2"></label>
            </div>
            <div class="carbohydrateboxline2 carbohydratebox" id="carbohydratebox4">
                <input class="carbohydrateradio" type="radio" id="carbohydrate3" name="carbohydrate" value="3">떡
                <label for="carbohydrate3"></label>
            </div>
            <div class="carbohydrateboxline2 carbohydratebox" id="carbohydratebox5">
                <input class="carbohydrateradio" type="radio" id="carbohydrate4" name="carbohydrate" value="4">상관없음
                <label for="carbohydrate4"></label>
            </div>
            <button class="select4veiw btn" type="button">선택완료</button>
        </div>
        <div id="select4">
            <div class="mainFoodbox mainFoodboxline1" id="mainFoodbox1">
                <input class="mainFoodradio" type="radio" id="mainFood0" name="mainFood" value="0">육류
                <label for="mainFood0"></label>
            </div>
            <div class="mainFoodbox mainFoodboxline1" id="mainFoodbox2">
                <input class="mainFoodradio" type="radio" id="mainFood1" name="mainFood" value="1">해물
                <label for="mainFood1"></label>
            </div>
            <div class="mainFoodbox mainFoodboxline2" id="mainFoodbox3">
                <input class="mainFoodradio" type="radio" id="mainFood2" name="mainFood" value="2">채소
                <label for="mainFood2"></label>
            </div>
            <div class="mainFoodbox mainFoodboxline2" id="mainFoodbox4">
                <input class="mainFoodradio" type="radio" id="mainFood3" name="mainFood" value="3">상관없음
                <label for="mainFood3"></label>
            </div>
            <button class="select5veiw btn" type="button">선택완료</button>
        </div>
        <div id="select5">
            <div class="spicybox buttonbox" id="spicybox1">
                <input class="spicyradio" type="radio" id="spicy0" name="spicy" value="0">매운거
                <label for="spicy0"></label>
            </div>
            <div class="spicybox buttonbox" id="spicybox2">
                <input class="spicyradio" type="radio" id="spicy1" name="spicy" value="1">안매운거
                <label for="spicy1"></label>
            </div>
            <div class="spicybox buttonbox" id="spicybox3">
                <input class="spicyradio" type="radio" id="spicy2" name="spicy" value="2">상관없음
                <label for="spicy2"></label>
            </div>
            <input  type="submit" class="btn select6veiw" value="선택완료">

        </div>
    </form>
    </section>
    <script>
         $('.soupybox').click(function(e){
            if($(this).hasClass('activeradio')){  
            } else {
                $('.soupybox').removeClass('activeradio');
                $(this).addClass('activeradio');
                }
            });

            $('.hot_icebox').click(function(e){
            if($(this).hasClass('activeradio')){  
            } else {
                $('.hot_icebox').removeClass('activeradio');
                $(this).addClass('activeradio');
                }
            });

            $('.carbohydratebox').click(function(e){
            if($(this).hasClass('activeradio')){  
            } else {
                $('.carbohydratebox').removeClass('activeradio');
                $(this).addClass('activeradio');
                }
            });

            $('.mainFoodbox').click(function(e){
            if($(this).hasClass('activeradio')){  
            } else {
                $('.mainFoodbox').removeClass('activeradio');
                $(this).addClass('activeradio');
                }
            });
            $('.spicybox').click(function(e){
            if($(this).hasClass('activeradio')){  
            } else {
                $('.spicybox').removeClass('activeradio');
                $(this).addClass('activeradio');
                }
            });
        
            $(".select2veiw").click(function(){
           if(!$('input:radio[name=soupy]:checked').val()){
               return false;
           }else{
            console.log($('input:radio[name=soupy]:checked').val());
            $("#select1").hide();
            $("#select2").show();
        }
        
        })
        $(".select3veiw").click(function(){
            if(!$('input:radio[name=hot_ice]:checked').val()){
               return false;
           }else{
                console.log($('input:radio[name=hot_ice]:checked').val());
                $("#select2").hide();
                $("#select3").show();
           }
            })

        $(".select4veiw").click(function(){
            if(!$('input:radio[name=carbohydrate]:checked').val()){
               return false;
           }else{
            console.log($('input:radio[name=carbohydrate]:checked').val());
            $("#select3").hide();
            $("#select4").show();
           }
        })

        $(".select5veiw").click(function(e){
            if(!$('input:radio[name=mainFood]:checked').val()){
                e.preventDefault();
               return false;
           }else{
            console.log($('input:radio[name=mainFood]:checked').val());
            console.log($('input:radio[name=spicy]:checked').val());
            $("#select4").hide();
            $("#select5").show();
           }
        })
    </script>
</html>
<!--             <button  class="select6veiw btn" >선택완료</button> -->