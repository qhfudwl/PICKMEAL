package pickmeal.dream.pj.posting.repository;

import java.util.List;

import pickmeal.dream.pj.posting.domain.Posting;

/**
 * 
 * @author 윤효심
 *
 */
public interface PostingDao {

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
	 * 카테고리별 게시물 갯수 불러오기
	 * @param category
	 * @return
	 */
	public int getPostingCountByCategory(char category);
	
	/**
	 * 카테고리별 포스팅 불러오기
	 * 		- 게시판 목록에서만 불러올 것
	 * 		- 1페이지당 12개 게시물 불러오기
	 * @param category
	 * @return
	 */
	public List<Posting> findPostingsPerPageByCategory(char category, int pageStart, int pageEnd);
	
	
	
	
}
