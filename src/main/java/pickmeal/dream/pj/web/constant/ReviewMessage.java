package pickmeal.dream.pj.web.constant;

public enum ReviewMessage {
	BATHROOM("3시간에 한 번씩 화장실 청소하는 듯"),
	KIND("너무너무 친절해서 부담스러움"),
	SPECIALDAY("너와 나의 특별한 날에"),
	CLEAN("걍 먼지 한톨 없음 ㅇㅇ"),
	PARKINIG("초보운전 주차 쌉가능"),
	GOODGROUP("친구들끼리 단체로 수다기 좋아요"),
	ALONE("혼자가서 둘이 나올 수 있어요"),
	BIG("직원이 날 못찾을 정도의 크기"),
	INTERIOR("사진찍기 조아욤");
	

	private final String getReviewMessage;
	
	ReviewMessage(String reviewMessage){
		getReviewMessage = reviewMessage;
	}
	
	@Override
	public String toString() {
		return getReviewMessage;
	}
}
