package pickmeal.dream.pj.weather.domain;

public class MyLocation {
	private String nx;
	private String ny;
	
	public MyLocation() {
		
	}
	
	public MyLocation(String nx, String ny) {
		this.nx = nx;
		this.ny = ny;
	}

	public String getNx() {
		return nx;
	}

	public void setNx(String nx) {
		this.nx = nx;
	}

	public String getNy() {
		return ny;
	}

	public void setNy(String ny) {
		this.ny = ny;
	}
	
	
}
