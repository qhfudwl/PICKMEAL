<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" dir="ltr">

<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/incl/link.jsp"%>
<script src="${pageContext.request.contextPath}/resources/js/posting/post_write.js" defer></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/posting/post.css" />
<title>게시글쓰기</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/incl/header.jsp"/>
<section id="totalPostContainer">
        <h2 class="hidden">글쓰기</h2>
        <div id="rwPostContainer">
            <div id="rwPostTitleContainer">
                <div class="rwPostTitleWrap">
                    <h3>공지사항 글쓰기</h3>
                </div>
            </div>
           
            <div id="wPostContentContainer">
                <div class="wPostSubTitleWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle">제목</p>
                    <div class="wPostSubTitleInputWrap wPostComInputArea">
                        <input type="text" class="wPostSubTitleConInput postInputTCom" placeholder="식당이름과 메뉴를 적어주시면 좋아요">
                    </div>
                </div>
                <div class="wPostContentWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle">본문</p>
                    <div class="wPostContentInputWrap wPostComInputArea">
                        <div class="wPostContentInput postInputTCom" contentEditable="true" >

                        </div>
                    </div>
                </div>
                <div class="wPostImgWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle">사진첨부</p>
                    <div class="wPostImgInputWrap wPostComInputArea">
                        <div class="wPostAttachImgBtnWrap">
							<label for="multiFileInput" class="wPostAttachBtn postBtnCom">첨부하기</label>
                        	<input multiple="multiple"  type="file" name="postImgFiles" required="required" id="multiFileInput" >
                            <!-- <a href="#" class="wPostAttachBtn postBtnCom">첨부하기</a> -->
                            <p class="wPostAttachBtnRefer">(지원 포맷 : jpg, jpeg, png / 최대 20개까지 첨부 가능)</p>
                        </div>

                        <div class="wPostAttachedImgListWrap postInputTCom" >
                            <ul class="wPostAttachedImgList">
                                <li class="wPostSelectedImg">
                                    <img src="posting/attached_picture.png" class="wPostAttachImgIcon" alt="이미지파일 아이콘">
                                    <p class="wPostAttachImgTitle">sky.jpg<span>(20Mbyte)</span></p>
                                    <img src="posting/close.png" class="wPostAttachImgDelIcon" alt="이미지파일 삭제아이콘">
                                </li>
                            
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="wPostCateWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle">카테고리</p>
                    <div class="wPostCateInputWrap wPostComInputArea">
                        <!-- select 박스 만들기-->
                        <div class="wPostCateSelectBoxWrap">
                            <!-- select 박스 만들 예정
                            <span>식당</span>
                            <ul>
                                <li>식당</li>
                                <li>카페</li>
                                <li>술집</li>
                            </ul>-->
                            <select class="wPostCateSelectBox postInputTCom" name="category">
                                <option value="">식당</option>
                                <option value="">카페</option>
                                <option value="">술집</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="wPostDateTimeWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle">날짜/시간</p>
                    <div class="wPostDateTimeInputWrap wPostComInputArea">
                        <!-- datepicker-->
                        <input type="date" class="wPostDateInput postInputTCom">
                        <input type="time" class="wPostTimeInput postInputTCom">
                    </div>
                </div>
                <div class="wPostMapWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle">장소</p>
                    <div class="wPostMapInputWrap wPostComInputArea">
                        <div class="wPostMapBtnWrap">
                            <a href="#" class="wPostMapSetAddressBtn postBtnCom postBtnComUnClick">주소 입력하기</a>
                            <a href="#" class="wPostMapCurrentPlaceBtn postBtnCom">현재 위치에서 찾기</a>
                        </div>
                        <input type="text" class="wPostMapDetailAddressInput postInputTCom" readonly>
                        <div class="wPostMapArea postInputTCom">
                            <div id="wPostMap"></div>
                        </div>
                    </div>
                </div>
                <div class="wPostSubmitBtnWrap wPostLineCommon">
                    <p class="wPostLeftSideSubTitle"></p>
                    <div class="wPostSubmitBtnInputWrap wPostComInputArea">
                        <a href="#" class="wPostSubmitBtn postBtnCom">작성완료</a>
                    </div>
                </div>

            </div>
		
        </div>

    </section>
</body>
</html>