package pickmeal.dream.pj.restaurant.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantCommand {
	private long id;
	private boolean rtype;
	private double lat;
	private double lng;
	private String address;
	private String rname;
	private long apiId;
}
