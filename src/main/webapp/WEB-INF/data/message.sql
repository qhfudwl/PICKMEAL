CREATE TABLE Message(
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	messageType		CHAR(1)			NOT NULL,
	content			VARCHAR(200)	
)

SELECT * FROM Message;

SELECT id, messageType, content FROM Message WHERE messageType='F';

INSERT INTO Message(messageType, content)
VALUES
('F',"오늘은 당신에게 날씨의 행운이 깃들까?"),
('F',"길가다가 넘어 질 듯"),
('F',"새똥이 눈앞에 떨어질지도"),
('F',"오늘은 밥 맛 떨어지는 날"),
('F',"3일 안에 비즈니즈 행운 +100")