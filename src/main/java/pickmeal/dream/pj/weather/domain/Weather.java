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
}
