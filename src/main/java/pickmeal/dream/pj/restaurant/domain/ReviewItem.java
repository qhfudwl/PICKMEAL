package pickmeal.dream.pj.restaurant.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewItem implements Comparable<ReviewItem> {
	private String keyword;
	private String message;
	private String imgPath;
	private int reviewCount;

	public ReviewItem() {
	}

	public ReviewItem(String keyword, String message, String imgPath, int reviewCount) {
		this.keyword = keyword;
		this.message = message;
		this.imgPath = imgPath;
		this.reviewCount = reviewCount;
	}

	@Override
	public int compareTo(ReviewItem r) {
		if (r.reviewCount > reviewCount) {
			return 1;
		} else if (r.reviewCount < reviewCount) {
			return -1;
		}
		return 0;

	}
}
