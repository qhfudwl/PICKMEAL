package pickmeal.dream.pj.web.constant;

public enum ReviewIconImgPath {
	BATHROOM("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	KIND("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	SPECIALDAY("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	CLEAN("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	PARKINIG("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	GOODGROUP("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	ALONE("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	BIG("/pickmeal/resources/img/restaurant/review/icon_heart.png"),
	INTERIOR("/pickmeal/resources/img/restaurant/review/icon_heart.png");
	

	private final String getReviewMessage;
	
	ReviewIconImgPath(String reviewMessage){
		getReviewMessage = reviewMessage;
	}
	
	@Override
	public String toString() {
		return getReviewMessage;
	}
}
