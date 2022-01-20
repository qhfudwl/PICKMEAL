package pickmeal.dream.pj.restaurant.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewCommand {

	private long retaurantId;
	private int bathroom;
	private int kind;
	private int specialDay;
	private int clean;
	private int parking;
	private int goodgroup;
	private int alone;
	private int big;
	private int interior;
	private int count;
	
	public ReviewCommand(long retaurantId, int bathroom, int kind, int specialDay, int clean, int parking,
			int goodgroup, int alone, int big, int interior, int count) {
		super();
		this.retaurantId = retaurantId;
		this.bathroom = bathroom;
		this.kind = kind;
		this.specialDay = specialDay;
		this.clean = clean;
		this.parking = parking;
		this.goodgroup = goodgroup;
		this.alone = alone;
		this.big = big;
		this.interior = interior;
		this.count = count;
	}
}
