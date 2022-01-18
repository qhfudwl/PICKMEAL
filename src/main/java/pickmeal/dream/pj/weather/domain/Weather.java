package pickmeal.dream.pj.weather.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Weather {
	private double t1h;
	private int sky;
	private int pty;
	
	public Weather() {
		
	}

	public Weather(double t1h, int sky, int pty) {
		super();
		this.t1h = t1h;
		this.sky = sky;
		this.pty = pty;
	}
}
