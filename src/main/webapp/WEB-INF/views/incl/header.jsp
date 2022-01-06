<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String contentName = (String)session.getAttribute("contentName"); %>
    <header>
      <h1 class="hidden">PJCCAFE</h1>
      <form action="${pageContext.request.contextPath}/user/logout" method="get">
      	<input id="logout" type="submit" value="로그아웃" />
      </form>
      <nav id="gnb">
       	<h2 class="hidden">주요메뉴</h2>
        <ul>
           <li<% if("주문현황".equals(contentName)) {%> class="on"<%} %>>
            <form name="indexView" action="${pageContext.request.contextPath}/indexView" method="get">
            	<input type="submit" value="주문현황"/>
            </form>
           </li>
           <li<% if("주문하기".equals(contentName)) {%> class="on"<%} %>>
            <form name="viewOrder" action="${pageContext.request.contextPath}/order" method="get">
            	<input type="submit" value="주문하기"/>
            </form>
           </li>
           <li<% if("회원목록".equals(contentName)) {%> class="on"<%} %>>
            <form name="viewUserMain" action="${pageContext.request.contextPath}/user/viewUserMain" method="get">
            	<input type="submit" value="회원목록"/>
            </form>
           </li>
           <li<% if("메뉴목록".equals(contentName)) {%> class="on"<%} %>>
            <form name="viewMenuList" action="${pageContext.request.contextPath}/menu/viewMenuList" method="get">
            	<input class="display_none" type="text" value="C" name="choiceMenu" />
            	<input type="submit" value="메뉴목록"/>
            </form>
           </li>
           <li<% if("판매내역".equals(contentName)) {%> class="on"<%} %>>
            <form name="viewSaleslist" action ="${pageContext.request.contextPath}/sales/viewSalesMain">
            	<input type="submit" value="판매내역"/>
            </form>
           </li>
           <li<% if("메뉴별 판매내역".equals(contentName)) {%> class="on"<%} %>>
			<form name="orderRecordByMenu" action="${pageContext.request.contextPath}/sales/viewOrderRecordByMenu" method="get">
				<input class="display_none" type="text" value="D" name="periodType"/>
				<input class="display_none" type="text" value="T" name="menuType"/>			
            	<input type="submit" value="메뉴별 판매내역"/>
            </form>
           </li>
        </ul>
      </nav>
    </header>
