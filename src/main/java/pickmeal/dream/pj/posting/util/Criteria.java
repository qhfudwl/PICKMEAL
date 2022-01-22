package pickmeal.dream.pj.posting.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 페이징 처리 클래스
 * 		- 만든 이유 : 게시판 별 페이지 처리에 필요한 정보들이 많고, 
 * 					중복 코드들이 너무 많이 생겨서 아래 클래스를 찾았다.
 * 
 * 		- 게시판 페이징 처리를 위해 필요한 클래스이다.
 * 		- 현재 페이지에 대한 정보를 가지고 있다.
 * 		- 해당 클래스를 가지고, DB에 접근한다
 * 
 * 		- 들어가는 정보1. 페이징 
 * 			1) 현재 게시판 카테고리 ( 공지사항, 식당추천, 밥친구 )
 * 			3) 현재 페이지 
 * 			4) 1페이지당 보여질 게시물 갯수
 * 
 * 	    - 들어가는 정보2. 정렬
 * 			1) 정렬타입 ( 모집중, 최신순, 조회순, 좋아요순)
 * 			2) 정렬기준 ( 오름차순, 내림차순 )
 * 
 * 		- 들어가는 정보3. 검색
 * 		    1) 검색타입 ( 제목, 작성자 )
 * 			2) 검색어
 * 		
 * 		- 위 정보들로, View단에서 페이징 처리를 해준다.
 * 		
 * 		- 사용하는 이유
 * 			1) 사용하는 많은 인자들을 한번에 관리할 수 잇다.
 * 			2) (확장성)타임리프등에서 사용하기 편리하다
 * 			3) (확장성)sql문을 바로 제어할 수도 있다.
 * 
 * 		
 * @author 윤효심
 *
 */

@Getter
@Setter
public class Criteria {
	
	//페이징
	private char type;		//게시판 종류
	private int page;			//현재페이지
	private int cntPerPage=12;	//1페이지당 게시물 수 
	
	//정렬
	private String sortType;	//정렬타입
	private String sort;		//정렬기준
	
	//검색
	private String searchType;	//검색타입
	private String search;		//검색어
	
	public Criteria() {
		this.page=1;
	}
	
	public void setType(String type) {
		if(type.equals("notice")) {
			this.type = 'N';
		}else if(type.equals("recommend")) {
			this.type = 'R';
		}else {
			this.type = 'E';	//togher
		}
	}



}
