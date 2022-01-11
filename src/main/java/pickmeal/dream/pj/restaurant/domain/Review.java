package pickmeal.dream.pj.restaurant.domain;
import java.util.List;

/**
 * 레스토랑리뷰 
 * @author 윤효심
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
	
	private long restaurantId;
	private int userCount;
	
	private List<ReviewItem> reviewItem;
	
	public Review() {}

	@Override
	public String toString() {
		return "Review [restaurantId=" + restaurantId + ", userCount=" + userCount + ", reviewItem=" + reviewItem + "]";
	}
	
	/*
	 * 추후 변경가능성 있음
	 * 
	 
	private int bathroom;
	private int kind;
	private int specialDay;
	private int clean;
	private int parking;
	private int goodgroup;
	private int alone;
	private int big;
	private int interior;
	
	public Review() {}

	@Override
	public String toString() {
		return "Review [restaurantId=" + restaurantId + ", userCount=" + userCount + ", bathroom=" + bathroom
				+ ", kind=" + kind + ", specialDay=" + specialDay + ", clean=" + clean + ", parking=" + parking
				+ ", goodgroup=" + goodgroup + ", alone=" + alone + ", big=" + big + ", interior=" + interior + "]";
	}

	*/
	
}
