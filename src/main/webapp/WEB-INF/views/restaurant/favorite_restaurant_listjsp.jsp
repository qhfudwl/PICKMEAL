<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/incl/link.jsp" %>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=001358587c4d106ce5a3702588b8ce85&libraries=services"></script>

</head>
<body>
	<section>
		<div>
			<h3>찜 식당 목록</h3>
			<form>
				<div>
					<div>${frlist.getRestaurant().getId()}
						<p>${frlist.getRestaurant().getRName()}</p>
						<input type="radio" id="label${frlist.getId()} value="${frlist.getId()}">
						<label id="inlabel${frlist.getId()}" for="label${frlist.getId()}"></label>
					</div>
					<div>
						<input type="button">
					</div>
				</div>
			</form>
		</div>
	</section>
</body>
</html>