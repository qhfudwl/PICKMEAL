package pickmeal.dream.pj.posting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.repository.PostingDao;

@Service("postingService")
public class PostingServiceImpl implements PostingService {

	@Autowired
	PostingDao pd;

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
	public List<Posting> findAllPostingsByCategory(char category) {
		return pd.findAllPostingsByCategory(category);
	}

	@Override
	public List<Posting> findPostingsByLastIndex(Posting posting) {
		return pd.findPostingsByLastIndex(posting);
	}

	

}
