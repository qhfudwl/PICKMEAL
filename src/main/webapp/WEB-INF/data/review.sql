SELECT * FROM Review;
DELETE FROM Review;


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

ALTER TABLE Review ADD userCount INT NOT NULL DEFAULT '1';


INSERT INTO Review(restaurantId, bathroom, kind, specialDay, clean, parking, goodgroup, alone, big, interior, usercount)
VALUES 
(119, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 );

delete from Review where id = 6;
SELECT * FROM Review
UPDATE Review SET bathroom = 1, kind = 1, specialDay = 1, clean = 1, parking = 1, goodgroup = 1, alone = 1, big = 1,interior = 1 WHERE restaurantId = 119

SELECT EXISTS (SELECT id FROM Review WHERE restaurantId = 1);
