package pickmeal.dream.pj.posting.service;

import java.util.List;

import pickmeal.dream.pj.posting.domain.Posting;

/**
 * 
 * @author 윤효심
 *
 */
public interface PostingService {

	/**
	 * 포스팅 추가
	 * @param posting
	 */
	public void addPosting(Posting posting);
	
	/**
	 * 포스팅 업데이트
	 * @param posting
	 */
	public void updatePosting(Posting posting);
	
	/**
	 * 포스팅 삭제
	 * @param posting
	 * @return
	 */
	public void deletePosting(Posting posting);
	
	/**
	 * 카테고리별 포스팅 불러오기
	 * @param category
	 * @return
	 */
	public List<Posting> findAllPostingsByCategory(char category);
	
	/**
	 * 특정 인덱스로부터 특정 갯수의 포스팅들을 불러오기
	 * 		- 게시판 목록을 위해서
	 * @param id
	 * @return
	 */
	public List<Posting> findPostingsByLastIndex(Posting posting);
	
}
