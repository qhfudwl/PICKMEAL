package pickmeal.dream.pj.posting.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.domain.TogetherEatingPosting;

@Repository("postingDao")
public class PostingDaoImpl implements PostingDao {

	@Autowired
	JdbcTemplate jt;

	@Override
	public void addPosting(Posting posting) {
		if (posting.getCategory() == 'N') {
			String sql ="INSERT INTO NoticePosting(memberId, title, content, views) "
			+" VALUES(?,?,?,?)";
			jt.update(sql, posting.getMember().getId(), posting.getTitle(), posting.getContent(), posting.getViews());
			
		} else if (posting.getCategory() == 'R') {
			String sql ="INSERT INTO RecommendRestaurantPosting(memberId, restaurantId, title, content, likes, views) "
					+" VALUES(?,?,?,?,?,?)";
			jt.update(sql, posting.getMember().getId(), posting.getRestaurant().getId(), posting.getTitle(), posting.getContent(), posting.getLikes(), posting.getViews());
					
		} else {
			TogetherEatingPosting tep = (TogetherEatingPosting)posting;
			String sql ="INSERT INTO TogetherEatingPosting(memberId, restaurantId, title, content, likes, views, mealTime, recruitment, mealChk ) "
					+" VALUES(?,?,?,?,?,?,?,?,?)";
			jt.update(sql, tep.getMember().getId(), tep.getRestaurant().getId(), tep.getTitle(), tep.getContent(), tep.getLikes(), tep.getViews(), tep.getMealTime(), tep.isRecruitment(), tep.isMealChk());
			
		}

	}

	@Override
	public void updatePosting(Posting posting) {
		if (posting.getCategory() == 'N') {
			String sql ="UPDATE NoticePosting SET title=?, content=?, views=?"
					+" WHERE id=?";
			jt.update(sql, posting.getTitle(), posting.getContent(), posting.getViews(), posting.getId());
		} else if (posting.getCategory() == 'R') {
			String sql ="UPDATE RecommendRestaurantPosting SET restaurantId=?, title=?, content=?, likes=?, views=?"
					+" WHERE id=?";
			jt.update(sql, posting.getRestaurant().getId(), posting.getTitle(), posting.getContent(), posting.getLikes(), posting.getViews(), posting.getId());
		} else {
			TogetherEatingPosting tep = (TogetherEatingPosting) posting;
			String sql ="UPDATE TogetherEatingPosting SET restaurantId=?, title=?, content=?, likes=?, views=?, mealTime=?, recruitment=?, mealChk=?"
					+" WHERE id=?";
			jt.update(sql, tep.getRestaurant().getId(), tep.getTitle(), tep.getContent(), tep.getLikes(), tep.getViews(), tep.getMealTime(), tep.isRecruitment(), tep.isMealChk(), tep.getId());
		}

	}

	@Override
	public void deletePosting(Posting posting) {
		if (posting.getCategory() == 'N') {
			String sql = "DELETE FROM NoticePosting WHERE id=?";
			jt.update(sql, posting.getId());
		} else if (posting.getCategory() == 'R') {
			String sql = "DELETE FROM RecommendRestaurantPosting WHERE id=?";
			jt.update(sql, posting.getId());
		} else {
			TogetherEatingPosting tep = (TogetherEatingPosting) posting;
			String sql = "DELETE FROM TogetherEatingPosting WHERE id=?";
			jt.update(sql, tep.getId());
		}

	}
	
	
	@Override
	public int getPostingCountByCategory(char category) {
		if (category == 'N') {
			String sql = "SELECT COUNT(id) as NoticeCnt FROM NoticePosting";
			return jt.queryForObject(sql, Integer.class);
		} else if (category == 'R') {
			String sql = "SELECT COUNT(id) as RecommendRestaurantCnt FROM RecommendRestaurantPosting";
			return jt.queryForObject(sql, Integer.class);
		} else {
			String sql = "SELECT COUNT(id) as TogetherEatingCnt FROM TogetherEatingPosting";
			return jt.queryForObject(sql, Integer.class);
		}
		
	}
	
	/**
	 * 
	 * 	각 게시판의 게시물을 id별로 내림차순으로 정렬 후, 
	 * 	LIMIT를 사용해 pageStart 부터 pageReadCnt만큼 불러온다
	 *  (ex. pageStart = 시작 행(0부터시작), pageReadCnt = 불러올 갯수 ) 
	 * 
	 */
	@Override
	public List<Posting> findPostingsPerPageByCategory(char category, int pageStart, int pageReadCnt) {
		if (category == 'N') {
			String sql ="SELECT id, memberId, title, content, views, regDate "
					+" FROM NoticePosting"
					+" ORDER BY id DESC "
					+" LIMIT ?,?";
			return jt.query(sql, new NoticePostingRowMapper(),pageStart,pageReadCnt);
			
		} else if (category == 'R') {
			String sql ="SELECT id, memberId, restaurantId, title, content, likes, views, regDate "
					+" FROM RecommendRestaurantPosting"
					+" ORDER BY id DESC "
					+" LIMIT ?,?";
			return jt.query(sql, new RecommendRestaurantPostingRowMapper(),pageStart,pageReadCnt);
		} else {
			String sql ="SELECT id, memberId, restaurantId, title, content, likes, views, mealTime, recruitment, mealChk, regDate "
					+" FROM TogetherEatingPosting"
					+" ORDER BY id DESC "
					+" LIMIT ?,?";
			return jt.query(sql, new TogetherEatingPostingRowMapper(),pageStart,pageReadCnt);
		}

	}

	

	
	
	
}
