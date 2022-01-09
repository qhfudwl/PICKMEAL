CREATE TABLE Member (
	id			BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	memberName	VARCHAR(100)
)

SELECT * FROM Member;

INSERT INTO Member(memberName) VALUES ("아아아");


CREATE TABLE RestaurantPreference (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	restaurantId	BIGINT,
	gender CHAR(1),
	age CHAR(3)
)

CREATE TABLE Review (
	id				BIGINT			PRIMARY KEY	AUTO_INCREMENT,
	restaurantId	BIGINT,
	bathroom		INT,
	kind			INT,
	specialDay		INT,
	clean			INT,
	parking			INT,
	goodgroup		INT,
	alone			INT,
	big				INT,
	interior		INT
)

ALTER TABLE Review ADD userCount INT NOT NULL DEFAULT '0';

INSERT INTO Review(restaurantId, bathroom, kind, specialDay, clean, parking, goodgroup, alone, big, interior, usercount)
VALUES (1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 );


SELECT * FROM RestaurantPreference;

SELECT * FROM Review;

