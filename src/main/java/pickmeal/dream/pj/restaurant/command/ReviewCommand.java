package pickmeal.dream.pj.restaurant.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewCommand {

	private long restaurantId;
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
	
	public ReviewCommand() {
		
	}
	
	public ReviewCommand(long restaurantId, int bathroom, int kind, int specialDay, int clean, int parking,
			int goodgroup, int alone, int big, int interior, int count) {
		super();
		this.restaurantId = restaurantId;
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

	public ReviewCommand(long restaurantId, int bathroom, int kind, int specialDay, int clean, int parking,
			int goodgroup, int alone, int big, int interior) {
		super();
		this.restaurantId = restaurantId;
		this.bathroom = bathroom;
		this.kind = kind;
		this.specialDay = specialDay;
		this.clean = clean;
		this.parking = parking;
		this.goodgroup = goodgroup;
		this.alone = alone;
		this.big = big;
		this.interior = interior;
	}
}
