package pickmeal.dream.pj.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;

@Controller
@Log
public class MapController_SJW {
	
	static final double EARTH = 6371000; // 단위 m
	static final double RADIUS = (Math.PI / 180);
	
	//도메인 입력하면 매핑되어 들어감.
	@GetMapping("/viewIndexMap_SJW")
	public String viewIndexMap_SJW() {
		return "index_map_SJW";
	}
	
	@GetMapping("/viewTestMap_SJW")
	public String viewTestMap_SJW() {
		return "mapTest_SJW";
	}
//	
//	@ResponseBody
//	@GetMapping("/sendDataToPopUp")
//	public ModelAndView sendDataToPopUp(@RequestParam("radius")String radius, @RequestParam("category")String category) {
//		
//		ModelAndView mav = new ModelAndView();
//		System.out.println("test");
//		System.out.println("radius : " + radius + " / category : " + category);
//		
//		mav.addObject("radius", radius);
//		mav.addObject("category", category);
//		
//		mav.setViewName("gamePlay_SJW");
//		
//		return mav;
//	}
//	
	
	@GetMapping("/calculateMap_SJW")
	@ResponseBody
	public ResponseEntity<?> calculateMap_SJW(@RequestParam("nowLat") double nowLat, 
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
