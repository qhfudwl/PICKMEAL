CREATE TABLE Member (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	memberType		CHAR(1)			NOT NULL,
	email			VARCHAR(30)		NOT NULL	UNIQUE,
	passwd			VARCHAR(60)		NOT NULL,
	nickName		VARCHAR(30)		NOT NULL	UNIQUE,
	birth			CHAR(8)			NOT NULL,
	gender			CHAR(1)			NOT NULL,
	profileImgPath	VARCHAR(100)	NOT NULL,
	regDate			TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP
)
ALTER TABLE Member MODIFY COLUMN passwd VARCHAR(60) NOT NULL;

CREATE TABLE FoodPowerPoint (
	id			BIGINT		PRIMARY KEY	AUTO_INCREMENT,
	memberId	BIGINT		NOT NULL,
	point		INT			NOT NULL,
	detail		INT			NOT NULL,
	regDate		TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT	FoodPowerPoint_memberId_FK	FOREIGN KEY(memberId)	REFERENCES Member(id)
)

CREATE TABLE MannerTemperature (
	id			BIGINT		PRIMARY KEY	AUTO_INCREMENT,
	memberId	BIGINT		NOT NULL,
	temperature	DOUBLE		NOT NULL,
	CONSTRAINT	MannerTemperature_memberId_FK	FOREIGN KEY(memberId)	REFERENCES Member(id)
)

CREATE TABLE Attendance (
	id			BIGINT		PRIMARY KEY	AUTO_INCREMENT,
	memberId	BIGINT		NOT NULL,
	attendance	INT			NOT NULL,
	regDate		TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT	Attendance_memberId_FK	FOREIGN KEY(memberId)	REFERENCES Member(id)
)



DROP TABLE Member;
DROP TABLE FoodPowerPoint;
DROP TABLE MannerTemperature;
DROP TABLE Attendance;
DROP TABLE Coupon;

SELECT * FROM Member;
SELECT * FROM FoodPowerPoint;
SELECT * FROM MannerTemperature;
SELECT * FROM Attendance;



CREATE TABLE Menu (
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	menuName		VARCHAR(20),
	weather			INT,	
	imgPath			VARCHAR(100),
	soupy			INT,
	hot_ice			INT,
	carbohydrate	INT,
	mainFood		INT, 
	spicy			INT
)
SELECT * FROM Menu; 

CREATE TABLE Restaurant (
	id			BIGINT 			PRIMARY KEY AUTO_INCREMENT,
	rType		BOOLEAN			DEFAULT FALSE,
	lat			DOUBLE			NOT NULL,
	lng			DOUBLE			NOT	NULL,
	address		VARCHAR(100)	NOT NULL,
	rName		VARCHAR(50)		NOT NULL,
	apiId		BIGINT			UNIQUE KEY
)
SELECT * FROM Restaurant;

CREATE TABLE CouponCategory (
	id			BIGINT			PRIMARY KEY AUTO_INCREMENT,
	couponName	VARCHAR(20)		NOT NULL,
	couponType	CHAR(1)			NOT NULL
)
INSERT INTO CouponCategory(couponName,couponType) VALUES("2000원 쿠폰",'A');
INSERT INTO CouponCategory(couponName,couponType) VALUES("3000원 쿠폰",'B');
INSERT INTO CouponCategory(couponName,couponType) VALUES("5000원 쿠폰",'C');
SELECT * FROM CouponCategory;

CREATE TABLE Coupon(
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	memberId		BIGINT			NOT NULL,
	couponId		BIGINT			NOT NULL,
	restaurantId	BIGINT			NOT NULL,
	couponNumber	BIGINT			NOT NULL,
	used			BOOLEAN			NOT NULL,
	regDate			TIMESTAMP		NOT NULL,
	FOREIGN KEY (memberId) REFERENCES Member (id),
	FOREIGN KEY (couponId) REFERENCES CouponCategory (id),
	FOREIGN KEY (restaurantId) REFERENCES Restaurant (id)
)

SELECT * FROM Coupon;


CREATE TABLE RestaurantPreference (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	restaurantId	BIGINT,
	gender CHAR(1),
	age CHAR(3)
)

CREATE TABLE LastGameRecord (
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	restaurantId	BIGINT			NOT NULL,
	memberID		BIGINT			UNIQUE KEY,
	regDate			TIMESTAMP		DEFAULT CURRENT_TIMESTAMP	
)
SELECT * FROM LastGameRecord;

INSERT INTO LastGameRecord(restaurantId, memberId) VALUES(1, 1);
SELECT TIMESTAMPDIFF(DAY, regDate, CURDATE()) AS DIFF_DAY FROM LastGameRecord WHERE memberId = 1;
SELECT id, memberId FROM LastGameRecord WHERE memberId IN (1);
SELECT id, memberId FROM LastGameRecord WHERE memberId IN (1);
select EXISTS (select memberID from LastGameRecord where memberId = 1);


SELECT IF((select EXISTS (select memberID from LastGameRecord where memberId = 2)) = 0 , 0, (SELECT TIMESTAMPDIFF(DAY, regDate, CURDATE()) AS DIFF_DAY FROM LastGameRecord WHERE memberId = 1));




CREATE TABLE Message (
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	messageType		CHAR(1)			NOT NULL,
	content			VARCHAR(200)	NOT NULL
)
INSERT INTO Message(messageType, content) VALUES('F', "점심 맛있게 먹어");
INSERT INTO Message(messageType, content) VALUES('F', "또 왔네~");
INSERT INTO Message(messageType, content) VALUES('F', "좋은 하루 보내");

SELECT * FROM Message;
SELECT * FROM LastGameRecord;
SELECT * FROM RestaurantPreference;
SELECT * FROM Menu;



SELECT * FROM RestaurantPreference;
SELECT * FROM Menu;
SELECT id, menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy FROM Menu WHERE soupy = 1 AND hot_ice = 0 AND carbohydrate = 2 AND mainFood = 1 AND spicy = 0;
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말일",1,1,0,2,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이",2,1,1,3,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말삼",0,0,1,3,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말사",1,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말오",2,1,1,0,1,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말육",1,1,1,2,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말칠",0,0,1,3,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말팔",0,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말구",1,0,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십",2,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십일",1,0,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십이",2,0,0,2,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십삼",0,0,0,0,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십사",0,1,0,3,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십오",0,0,0,3,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십육",1,1,0,3,0,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십칠",2,0,1,3,0,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십팔",3,0,0,3,0,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십구",3,1,1,3,0,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십",2,0,1,3,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십일",3,1,0,1,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십이",3,1,1,2,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십삼",3,1,0,1,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십사",4,1,1,0,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십오",4,1,0,2,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십육",4,1,1,1,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십칠",4,1,0,2,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십팔",4,1,1,0,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십구",4,1,1,2,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이삼십",4,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이삼십일",1,1,0,0,2,1);
INSERT INTO Menu(menuName,weather,imgPath,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("찜닭",1,"/pickmeal/resources/img/menu/찜닭.jpg",1,1,3,2,1);
INSERT INTO Menu(menuName,weather,imgPath,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("찜닭",1,"/pickmeal/resources/img/menu/찜닭.jpg",1,1,2,2,1);
INSERT INTO Menu(menuName,weather,imgPath,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("오마카세",1,"/pickmeal/resources/img/menu/오마카세.jpg",2,2,4,3,2);
DELETE FROM Menu WHERE id =45;

INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',10);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',11);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',12);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',13);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',14);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',15);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',11);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',11);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',11);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',19);

INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',20);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',21);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',21);

INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',30);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'F',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',31);
INSERT INTO RestaurantPreference(restaurantId,gender,age) VALUES (1,'M',30);