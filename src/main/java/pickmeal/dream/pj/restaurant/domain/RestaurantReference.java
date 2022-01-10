package pickmeal.dream.pj.restaurant.domain;
import java.util.List;

/**
 * 레스토랑 성별 선호도 
 * @author 윤효심
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantReference {
	
	private long restaurantId;
	private int femaleCount;
	private int maleCount;
	
	private List<Integer> ageCount;
	
	public RestaurantReference() {}
	public RestaurantReference(long restaurantId, int femaleCount, int maleCount, List<Integer> ageCount) {
		this.restaurantId = restaurantId;
		this.femaleCount = femaleCount;
		this.maleCount = maleCount;
		this.ageCount = ageCount;
	}
	
	@Override
	public String toString() {
		return "RestaurantReference [restaurantId=" + restaurantId + ", femaleCount=" + femaleCount + ", maleCount="
				+ maleCount + ", ageCount=" + ageCount + "]";
	}
	
	
	
	
	
	
	
}
