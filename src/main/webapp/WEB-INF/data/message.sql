CREATE TABLE Message(
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	messageType		CHAR(1)			NOT NULL,
	content			VARCHAR(200)	
)

INSERT INTO Message(messageType, content)
VALUES
('F',"오늘은 당신에게 날씨의 행운이 "),
('F',"1길가다가 넘어 질 듯"),
('F',""),
('F',"오늘은 밥 맛 떨어지는 날"),
('F',"오늘은 밥 맛 떨어지는 날"),