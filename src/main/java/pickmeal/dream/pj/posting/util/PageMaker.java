package pickmeal.dream.pj.posting.util;

import lombok.Getter;
import lombok.Setter;
/**
 * 하단 페이지 메이커
 * 		- 게시판 페이징 처리를 위해 필요한 클래스이다.
 * 		- 게시판 하단에 보여지는 페이징 처리에 대한 정보를 가지고 있다.
 * 		- Criteria 클래스를 가진다
 * 
 * 
 * @author 윤효심
 *
 */


@Getter
@Setter
public class PageMaker {

	private int total;			//게시물 수
	private int maxPage = 5;	//보여질 최대 페이지수
	
	private int startNum;		//하단 첫번째페이지수 (ex. 1, 6, 11...)
	private int endNum;			//하단 마지막페이지수 (ex. 5, 10, 15...)
	
	private boolean prevBtn = false;	//이전버튼유무
	private boolean nextBtn = false ;	//다음버튼유무
	
	private Criteria criteria;
	
	public PageMaker(int total, Criteria criteria) {
		this.total = total;
		this.criteria = criteria;
		calcPage();
	}

	
	private void calcPage(){
		
		/*
		 *  endNum, statNum  구하기
		 */
		
		
		/*
		 * 1) endNum 구하기
		 * 		- 현재페이지의 하단이 가르키는 마지막페이지수
		 * 총 게시물이 121이고, 현재 페이지가 1이면, Math.ceil(1/5) = 1, 1*5 = 5;
		 * 총 게시물이 121이고, 현재 페이지가 6이면, Math.ceil(6/5) = 2, 2*5 = 10;
		 * 
		 * 총 게시물이 121이고, 현재 페이지가 11이면, Math.ceil(11/5) =3, 3*5 = 15;	//초과값
		 * 총 게시물이 4이고, 현패 페이지가 1이면, Math.ceil(4/5) = 1, 1*5 = 5			//초과값
		 */
		endNum = (int)Math.ceil(criteria.getPage()/(double)maxPage)*maxPage;
		System.out.println("endNum is "+endNum);
		
		//초과값일 때 처리
		//총 게시물이 121일 때, 실제 맨 끝 페이지는 11이 되어야 함으로, Math.ceil(121/12)= 11 
		//총 게시물이 4일 때, 실제 맨 끝 페이지는, Math.ceil(4/12)= 1
		int totalEndNum = (int)Math.ceil(total/(double)criteria.getCntPerPage()); 
		
		//기존에 계산한 마지막페이지가 실제 끝 페이지보다 크다면 바꿔준다. 
		if(endNum>totalEndNum) {
			endNum = totalEndNum;
		}
		
		//endNum 기준으로 startNum을 구할 수 있다.
		startNum = (endNum - maxPage) +1;
		
		
		/*
		 *	이전, 다음 버튼의 유무 
		 */
		
		nextBtn = (endNum < totalEndNum ? true : false );
		prevBtn = (startNum > 1 ? true : false );
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
