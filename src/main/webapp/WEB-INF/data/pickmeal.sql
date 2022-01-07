CREATE TABLE Member (
	id			BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	memberName	VARCHAR(100)
)

SELECT * FROM Member;

INSERT INTO Member(memberName) VALUES ("아아아");

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

CREATE TABLE RestaurantPreference (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	restaurantId	BIGINT,
	gender CHAR(1),
	age CHAR(3)
)

SELECT * FROM RestaurantPreference;
SELECT * FROM Menu;
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말일",1,1,0,2,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이",2,1,1,3,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말삼",0,0,1,3,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말사",1,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말오",2,1,1,0,2,1);
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
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십팔",1,0,0,3,0,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말십구",1,1,1,3,0,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십",2,0,1,3,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십일",0,1,0,1,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십이",0,1,1,2,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십삼",0,1,0,1,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십사",0,1,1,0,1,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십오",1,1,0,2,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십육",2,1,1,1,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십칠",1,1,0,1,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십팔",2,1,1,0,2,0);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이십구",0,1,1,2,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이삼십",1,1,0,1,2,1);
INSERT INTO Menu(menuName,weather,soupy,hot_ice,carbohydrate,mainFood,spicy) VALUES ("김말이삼십일",0,1,0,0,2,1);



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