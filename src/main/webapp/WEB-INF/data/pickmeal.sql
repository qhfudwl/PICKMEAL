CREATE TABLE Member (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	memberType		CHAR(1)			NOT NULL,
	email			VARCHAR(30)		NOT NULL	UNIQUE,
	passwd			VARCHAR(30)		NOT NULL,
	nickName		VARCHAR(30)		NOT NULL	UNIQUE,
	birth			CHAR(8)			NOT NULL,
	gender			CHAR(1)			NOT NULL,
	profileImgPath	VARCHAR(100)	NOT NULL,
	regDate			TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP
)
INSERT INTO Member(memberType,email,passwd,nickName,birth,gender,profileImgPath) VALUES('A',"google@naver.com","12341234","정두환","19960630",'M',"프로필주소");

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

SELECT * FROM Member;
SELECT * FROM FoodPowerPoint;
SELECT * FROM MannerTemperature;
SELECT * FROM Attendance;



CREATE TABLE Menu (
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,					--SQL 아이디
	menuName		VARCHAR(20),												--메뉴의 이름
	weather			INT,														--메뉴의 날씨값
	imgPath			VARCHAR(100),												--메뉴의 사진
	soupy			INT,														--메뉴의 국있/없
	hot_ice			INT,														--메뉴의 뜨거움/차가움
	carbohydrate	INT,														--메뉴의 밥/빵/면/떡
	mainFood		INT, 														--메뉴의 고기/해물/채소
	spicy			INT															--메뉴의 맵기/안맵기
)
SELECT * FROM Menu; 

CREATE TABLE Restaurant (
	id			BIGINT 			PRIMARY KEY AUTO_INCREMENT,						--SQL 아이디
	apiId		BIGINT			UNIQUE KEY, 									--식당 고유 아이디
	rType		BOOLEAN			DEFAULT FALSE,									--제휴/비제휴 제휴:true
	lat			DOUBLE			NOT NULL,										--위도 값
	lng			DOUBLE			NOT	NULL,										--경도 값
	address		VARCHAR(100)	NOT NULL,										--식당 주소 값 
	rName		VARCHAR(50)		NOT NULL										--식당 이름 값
)
DROP TABLE Restaurant;
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(1,true,31.0000,24.0000,"원식이네집","원식이네집");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(1,false,31.0000,24.0000,"주소값2에욤","식당2름이에욤");
SELECT * FROM Restaurant;

CREATE TABLE CouponCategory (
	id			BIGINT			PRIMARY KEY AUTO_INCREMENT,						--SQL 아이디
	couponName	VARCHAR(20)		NOT NULL,										--쿠폰 카테고리 명
	couponType	CHAR(1)			NOT NULL										--쿠폰 카테고리 타입
)
INSERT INTO CouponCategory(couponName,couponType) VALUES("2000원 쿠폰",'A');
INSERT INTO CouponCategory(couponName,couponType) VALUES("3000원 쿠폰",'B');
INSERT INTO CouponCategory(couponName,couponType) VALUES("5000원 쿠폰",'C');
SELECT * FROM CouponCategory;


CREATE TABLE Coupon(
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,					--SQL 아이디
	memberId		BIGINT			NOT NULL,									--Member 테이블 SQL 아이디
	couponId		BIGINT			NOT NULL,									--CouponCategory 테이블 SQL 아이디
	restaurantId	BIGINT			NOT NULL,									--Restaurant 테이블 SQL 아이디
	couponNumber	VARCHAR(13)		NOT NULL,									--쿠폰번호 13자리
	used			BOOLEAN			DEFAULT FALSE,								--사용/미사용 사용:true
	regDate			TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP,		--쿠폰 발급 일자
	FOREIGN KEY (memberId) REFERENCES Member (id),								--아래 쭉 포링키
	FOREIGN KEY (couponId) REFERENCES CouponCategory (id),
	FOREIGN KEY (restaurantId) REFERENCES Restaurant (id)
)
DROP TABLE Coupon;
SELECT * FROM Coupon;
INSERT INTO Coupon(memberId,couponId,restaurantId,couponNumber) VALUES(1,1,1,"11111111111");
DELETE FROM Coupon WHERE id =1;


CREATE TABLE RestaurantPreference (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	restaurantId	BIGINT,
	gender CHAR(1),
	age CHAR(3)
)

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