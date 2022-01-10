package pickmeal.dream.pj.restaurant.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
	private long id;
	private boolean rType;
	private double lat;
	private double lng;
	private String address;
	private String rName;
	
	public Restaurant() {
		
	}
	public Restaurant(long id, boolean rType, double lat, double lng, String address, String rName) {
		super();
		this.id = id;
		this.rType = rType;
		this.lat = lat;
		this.lng = lng;
		this.address = address;
		this.rName = rName;
	}
}
