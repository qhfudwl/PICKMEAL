package pickmeal.dream.pj.restaurant.domain;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Restaurant {
	private long id;
	private long apiId;
	private boolean rType;
	private double lat;
	private double lng;
	private String address;
	private String rName;
	
	public Restaurant() {
		
	}
	public Restaurant(long id,long apiId, boolean rType, double lat, double lng, String address, String rName) {
		super();
		this.id = id;
		this.apiId = apiId;
		this.rType = rType;
		this.lat = lat;
		this.lng = lng;
		this.address = address;
		this.rName = rName;
	}
	@Override
	public String toString() {
		return "Restaurant [id=" + id + " apiId=" + apiId + ", rType=" + rType + ", lat=" + lat + ", lng=" + lng + ", address=" + address
				+ ", rName=" + rName + "]";
	}
	
	public boolean isRType() {
		return rType;
	}
	public void setRType(boolean rType) {
		this.rType = rType;
	}
	public String getRName() {
		return rName;
	}
	public void setRName(String rName) {
		this.rName = rName;
	}
	
}
