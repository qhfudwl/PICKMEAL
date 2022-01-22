package pickmeal.dream.pj.posting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.repository.PostingDao;
import pickmeal.dream.pj.posting.util.Criteria;

@Service("postingService")
@Log
public class PostingServiceImpl implements PostingService {

	@Autowired
	PostingDao pd;
	
	@Autowired
	CommentService cs;
	
	@Override
	public void addPosting(Posting posting) {
		pd.addPosting(posting);
	}

	@Override
	public void updatePosting(Posting posting) {
		pd.updatePosting(posting);
	}

	@Override
	public void deletePosting(Posting posting) {
		pd.deletePosting(posting);
	}
	
	@Override
	public int getPostingCountByCategory(char category) {
		return pd.getPostingCountByCategory(category);
	}
	
	@Override
	public List<Posting> findPostingsPerPageByCategory(Criteria criteria) {
		
		/*
		 * (공통)
		 * 페이지별로 12개씩 들고온다
		 * 1page / 0-11
		 * 2page / 12-23
		 * 3page / 24-35
		 * 4page / 36-47
		 * 5page / 48-59
		 */
		int pageStart = (criteria.getPage()-1)*criteria.getCntPerPage();		//0, 12, 24, 36, 48...
		int pageReadCnt = criteria.getCntPerPage();
		log.info("pageStart : "+pageStart);
		
		List<Posting> postList = pd.findPostingsPerPageByCategory(criteria.getType(),pageStart,pageReadCnt);
		log.info("postingCount(default:12) : "+postList.size());
		
		/*
		 * (공통)
		 * 게시글별 댓글 갯수 불러오기
		 * 		- CommentService를 이용한다
		 * 		- 공지사항 게시판은 제외한다
		 */
		if(!(postList.get(0).getCategory()=='N')) {
			for(Posting post : postList) {
				post.setCommentsNumber(cs.countCommentByPostId(post.getId(),post.getCategory()));
			}
		}
		
		return postList;
	}


	

}
