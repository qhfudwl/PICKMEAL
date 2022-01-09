package pickmeal.dream.pj.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.java.Log;

@Controller
@Log
public class MapController {
	
	static final double EARTH = 6371000; // 단위 m
	static final double RADIUS = (Math.PI / 180);
	
	@GetMapping("/viewIndexMap")
	public String viewIndexMap() {
		return "index_map";
	}
	
	@GetMapping("/calculateMap")
	@ResponseBody
	public ResponseEntity<?> calculateMap(@RequestParam("nowLat") double nowLat, 
			@RequestParam("nowLng") double nowLng, @RequestParam("diffM") double diffM) {
		

		double diffLat = LatitudeInDifference(diffM);
		double diffLng = LongitudeInDifference(nowLat, diffM);
		
		double lat1 = nowLat - diffLat;
		double lng1 = nowLng - diffLng;
		double lat2 = nowLat + diffLat;
		double lng2 = nowLng + diffLng;
		
		double[] latLng = {lat1, lng1, lat2, lng2};
		
		return ResponseEntity.ok(latLng);
	}
	
    //반경 m이내의 위도차(degree)
    public double LatitudeInDifference(double diff){
 
        return (diff*360.0) / (2*Math.PI*EARTH);
    }
 
    //반경 m이내의 경도차(degree)
    public double LongitudeInDifference(double _latitude, double diff){
 
        return (diff*360.0) / (2*Math.PI*EARTH*Math.cos(Math.toRadians(_latitude)));
    }
}
