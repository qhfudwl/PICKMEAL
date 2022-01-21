package pickmeal.dream.pj.posting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.repository.PostingDao;

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
	public List<Posting> findPostingsPerPageByCategory(char category, int page) {
		
		/*
		 * (공통)
		 * 페이지별로 12개씩 들고온다
		 * 1page / 0-11
		 * 2page / 12-23
		 * 3page / 24-35
		 * 4page / 36-47
		 * 5page / 48-59
		 */
		int pageStart = (page-1)*12;		//0, 12, 24, 36, 48...
		int pageReadCnt = 12;
		log.info("pageStart : "+pageStart);
		
		List<Posting> postList = pd.findPostingsPerPageByCategory(category,pageStart,pageReadCnt);
		log.info("postingCount : "+postList.size());
		
		/*
		 * (공통)
		 * 게시글별 댓글 갯수 불러오기
		 * 		- CommentService를 이용한다
		 */
		for(Posting post : postList) {
			post.setCommentsNumber(cs.countCommentByPostId(post.getId(),post.getCategory()));
		}
		return postList;
	}


	

}
