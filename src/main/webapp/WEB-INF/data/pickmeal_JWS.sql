CREATE TABLE Member (															# 사용자
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,					# SQL 아이디
	memberType		CHAR(1)			NOT NULL,									# 사용자 타입 (M : 일반 사용자 / A : 관리자)
	email			VARCHAR(30)		NOT NULL	UNIQUE,							# 이메일(아이디 형식)	
	passwd			VARCHAR(60)		NOT NULL,									# 비밀번호
	nickName		VARCHAR(30)		NOT NULL	UNIQUE,							# 닉네임
	birth			CHAR(8)			NOT NULL,									# 생년월일
	gender			CHAR(1)			NOT NULL,									# 성별
	profileImgPath	VARCHAR(100)	NOT NULL,									# 프로필 이미지 경로
	regDate			TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP		# 회원가입 날짜
);

CREATE TABLE FoodPowerPoint (													# 식력 포인트
	id			BIGINT		PRIMARY KEY	AUTO_INCREMENT,							# SQL 아이디
	memberId	BIGINT		NOT NULL,											# 사용자 아이디
	point		INT			NOT NULL,											# 적립 포인트
	detail		INT			NOT NULL,											# 내용
	regDate		TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,				# 등록 날짜
	CONSTRAINT	FoodPowerPoint_memberId_FK	FOREIGN KEY(memberId)	REFERENCES Member(id) ON DELETE CASCADE
);

CREATE TABLE MannerTemperature (												# 신뢰 온도
	id			BIGINT		PRIMARY KEY	AUTO_INCREMENT,							# SQL 아이디
	memberId	BIGINT		NOT NULL,											# 사용자 아이디
	temperature	DOUBLE		NOT NULL,											# 온도
	CONSTRAINT	MannerTemperature_memberId_FK	FOREIGN KEY(memberId)	REFERENCES Member(id) ON DELETE CASCADE
);

CREATE TABLE Attendance (														# 출석
	id			BIGINT		PRIMARY KEY	AUTO_INCREMENT,							# SQL 아이디
	memberId	BIGINT		NOT NULL,											# 사용자 아이디
	attendance	INT			NOT NULL	DEFAULT 1,								# 연속 출석 수
	regDate		TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,				# 마지막 출석 날짜
	CONSTRAINT	Attendance_memberId_FK	FOREIGN KEY(memberId)	REFERENCES Member(id) ON DELETE CASCADE
);

SELECT TIMESTAMPDIFF(DAY, a.regDate, CURDATE()) AS DIFF_DAY FROM Attendance AS a WHERE memberId=36;
SELECT attendance FROM Attendance WHERE memberId=37

DROP TABLE Member;
DROP TABLE FoodPowerPoint;
DROP TABLE MannerTemperature;
DROP TABLE Attendance;

DELETE FROM Member WHERE id=3;
DELETE FROM Attendance WHERE memberId=41;
DELETE FROM FoodPowerPoint WHERE memberId=41;
DELETE FROM MannerTemperature WHERE memberId=41;

SELECT * FROM Member;
SELECT * FROM FoodPowerPoint;
SELECT * FROM MannerTemperature;
SELECT * FROM Attendance;

SELECT SUM(point) FROM FoodPowerPoint;

UPDATE Attendance SET attendance=1, regDate=CURRENT_TIMESTAMP() WHERE memberId=39


CREATE TABLE Menu (
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,					#SQL 아이디
	menuName		VARCHAR(20),												#메뉴의 이름
	weather			INT,														#메뉴의 날씨값
	temperature		INT,														#메뉴의 온도값
	imgPath			VARCHAR(100),												#메뉴의 사진
	soupy			INT,														#메뉴의 국있/없
	hot_ice			INT,														#메뉴의 뜨거움/차가움
	carbohydrate	INT,														#메뉴의 밥/빵/면/떡
	mainFood		INT, 														#메뉴의 고기/해물/채소
	spicy			INT															#메뉴의 맵기/안맵기
)
SELECT * FROM Menu; 
DROP TABLE Menu;

CREATE TABLE Restaurant (
	id			BIGINT 			PRIMARY KEY AUTO_INCREMENT,						#SQL 아이디
	apiId		BIGINT			UNIQUE KEY, 									#식당 고유 아이디
	rType		BOOLEAN			DEFAULT true,									#제휴/비제휴 제휴:true
	lat			DOUBLE			NOT NULL,										#위도 값
	lng			DOUBLE			NOT	NULL,										#경도 값
	address		VARCHAR(100)	NOT NULL,										#식당 주소 값 
	rName		VARCHAR(50)		NOT NULL										#식당 이름 값
)
DROP TABLE Restaurant;
DROP TABLE Favoriterestaurant;
DROP TABLE Visitedrestaurant;
SELECT * FROM Restaurant;
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(8,true,31.0000,24.0000,"대구광역시 중구 동성로 19-3 화무비도","화무비도");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(7,false,31.0000,24.0000,"대구광역시 중구 중앙대로 389","맘스터치");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(3,true,33.450701,126.570667,"서울특별시 카카오","카카오");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(3,true,33.450701,126.570667,"대구 달서구 장산로20","원식이집");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(5,true,33.450701,126.570667,"대구광역시 남구 중앙대로51길 112 1층","해봄치킨");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(5,true,33.450701,126.570667,"대구광역시 중구 중앙대로 366 9층 10층","코리아IT대구점");
INSERT INTO Restaurant(apiId,rType,lat,lng,address,rName) VALUES(6,true,33.450701,126.570667,"대구광역시 수성구 용학로 115 2동","팜테이블");

CREATE TABLE CouponCategory (
	id			BIGINT			PRIMARY KEY AUTO_INCREMENT,						#SQL 아이디
	couponName	VARCHAR(20)		NOT NULL,										#쿠폰 카테고리 네임
	couponType	CHAR(1)			NOT NULL,										#쿠폰 카테고리 타입
	limitPrice	VARCHAR(20)		NOT NULL										#쿠폰 최소 사용금액#
)
INSERT INTO CouponCategory(couponName,couponType,limitPrice) VALUES("2,000원",'A',"10,000");
INSERT INTO CouponCategory(couponName,couponType,limitPrice) VALUES("3,000원",'B',"20,000");
INSERT INTO CouponCategory(couponName,couponType,limitPrice) VALUES("5,000원",'C',"25,000");
SELECT * FROM CouponCategory;
DROP TABLE CouponCategory;
DELETE FROM CouponCategory WHERE id =1;
SELECT id, couponName,couponType,limitPrice FROM CouponCategory WHERE id = 1;

CREATE TABLE Coupon(
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,					#SQL 아이디
	memberId		BIGINT			NOT NULL,									#Member 테이블 SQL 아이디
	couponId		BIGINT			NOT NULL,									#CouponCategory 테이블 SQL 아이디
	restaurantId	BIGINT			NOT NULL,									#Restaurant 테이블 SQL 아이디
	couponNumber	VARCHAR(13)		NOT NULL,									#쿠폰번호 13자리
	used			BOOLEAN			DEFAULT FALSE,								#사용/미사용 사용:true
	regDate			TIMESTAMP		NOT NULL	DEFAULT CURRENT_TIMESTAMP,		#쿠폰 발급 일자
	FOREIGN KEY (memberId) REFERENCES Member (id),								#아래 쭉 포링키
	FOREIGN KEY (couponId) REFERENCES CouponCategory (id),
	FOREIGN KEY (restaurantId) REFERENCES Restaurant (id)
)
DROP TABLE Coupon;

SELECT IF(exists(select COUNT(regDate)  FROM  Coupon WHERE TIMESTAMPDIFF(DAY, regDate, CURDATE())=0 GROUP BY memberId HAVING memberId = 3),1,2) FROM Coupon

/*모든 쿠폰 확인*/
SELECT * FROM Coupon;
/*쿠폰 오늘 발급한적 있냐 없냐 확인*/
SELECT exists(select COUNT(regDate)  FROM  Coupon WHERE TIMESTAMPDIFF(DAY, regDate, CURDATE())=0 GROUP BY memberId HAVING memberId = 3);
/*오늘 하루 발급 개수=?*/
SELECT COUNT(regDate)  FROM  Coupon WHERE TIMESTAMPDIFF(DAY, regDate, CURDATE())=0 GROUP BY memberId HAVING memberId = 3;
/*삭제 메소드*/
delete from Coupon where used=false and TIMESTAMPDIFF(DAY,regDate,CURDATE()) != 0;
/*사용 SQL*/
UPDATE Coupon SET used = true WHERE id = 34;

/*사용한 쿠폰내역*/
SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon WHERE memberId = 1 AND used = true;

/*사용안한 쿠폰내역*/
SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon WHERE memberId = 1 AND used = false;
/*쿠폰 하나 찾아오기*/
SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon WHERE Id = 1;

INSERT INTO Coupon(memberId,couponId,restaurantId,couponNumber) VALUES(4,1,1,"11111111111");
INSERT INTO Coupon(memberId,couponId,restaurantId,couponNumber) VALUES(4,2,1,"11121111111");
INSERT INTO Coupon(memberId,couponId,restaurantId,couponNumber) VALUES(4,3,1,"11211111111");

DELETE FROM Coupon WHERE id =14;
SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon WHERE used = false;
SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon WHERE couponNumber = 'N3O6Q05KUMT8X';

CREATE TABLE FavoriteRestaurant (
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,					#식당고유 아이디
	memberId		BIGINT			NOT NULL,									#멤버 아이디
	restaurantId	BIGINT			NOT NULL,									#레스토랑 아이디
	FOREIGN KEY (memberId) REFERENCES Member (id),								#아래 쭉 포링키
	FOREIGN KEY (restaurantId) REFERENCES Restaurant (id)	
)

DROP TABLE FavoriteRestaurant;
INSERT INTO FavoriteRestaurant(memberId, restaurantId) VALUES(4,9);
SELECT EXISTS (SELECT id FROM FavoriteRestaurant WHERE memberId = 4 and restaurantId = 1); #없으면 0 #있으면 1
SELECT * FROM FavoriteRestaurant;

CREATE TABLE VisitedRestaurant(
	id				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	memberId		BIGINT			NOT NULL,
	restaurantId	BIGINT			NOT NULL,
	Review			boolean			DEFAULT FALSE,
	regDate			TIMESTAMP		NOT NULL		DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (memberId) REFERENCES Member (id),								#아래 쭉 포링키
	FOREIGN KEY (restaurantId) REFERENCES Restaurant (id)
)
INSERT INTO VisitedRestaurant(memberId, restaurantId, Review) VALUES(4,111,true);
INSERT INTO VisitedRestaurant(memberId, restaurantId, Review) VALUES(4,112,false);
SELECT EXISTS (SELECT id FROM VisitedRestaurant WHERE id = 2);
UPDATE VisitedRestaurant SET Review = true WHERE id = 1;
DELETE FROM VisitedRestaurant WHERE id = 10;
SELECT * FROM VisitedRestaurant;
DROP TABLE VisitedRestaurant;
SELECT id, memberId, restaurantId, Review, regDate FROM VisitedRestaurant WHERE memberId = 4 ORDER BY regDate DESC;


CREATE TABLE RestaurantPreference (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	restaurantId	BIGINT,
	gender CHAR(1),
	age CHAR(3)
)

SELECT * FROM RestaurantPreference;
SELECT * FROM Menu;
SELECT id, menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy FROM Menu WHERE soupy = 1 AND hot_ice = 0 AND carbohydrate = 2 AND mainFood = 1 AND spicy = 0;
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말일",1,1,1,0,2,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이",2,1,1,1,3,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말삼",0,0,1,1,3,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말사",1,1,0,1,1,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말오",2,1,1,0,1,1,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말육",1,1,1,2,2,1,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말칠",1,0,0,1,3,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말팔",0,1,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말구",1,0,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십",2,1,0,1,1,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십일",1,0,0,1,1,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십이",2,0,0,2,2,1,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십삼",0,0,0,0,1,0,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십사",1,0,1,0,3,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십오",0,1,0,0,3,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십육",1,1,1,0,3,0,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십칠",2,0,1,1,3,0,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십팔",3,0,0,3,1,0,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십구",3,1,1,3,0,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십",1,2,0,1,3,2,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십일",3,1,1,1,0,1,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십이",3,1,1,1,1,2,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십삼",3,1,1,1,0,1,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십사",4,1,1,1,1,0,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십오",4,1,1,0,2,1,2,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십육",4,1,1,1,1,2,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십칠",4,1,1,0,2,1,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십팔",4,1,1,1,0,2,0);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십구",4,1,1,1,2,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이삼십",4,1,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,temperature,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이삼십일",1,1,1,0,0,2,1);
INSERT INTO Menu(menuName,weather,temperature,imgPath,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("찜닭",1,1,"/pickmeal/resources/img/menu/찜닭.jpg",1,1,3,2,1);
INSERT INTO Menu(menuName,weather,temperature,imgPath,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("찜닭",1,1,"/pickmeal/resources/img/menu/찜닭.jpg",1,1,2,2,1);
INSERT INTO Menu(menuName,weather,temperature,imgPath,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("오마카세",1,1,"/pickmeal/resources/img/menu/오마카세.jpg",2,2,4,3,2);

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