SELECT * FROM RestaurantPreference;
DELETE FROM RestaurantPreference;

INSERT INTO RestaurantPreference(restaurantId,gender,age) 
VALUES 
(1,'F',20),(1,'F',20),(1,'F',22),(1,'F',19),(1,'M',11),
(2,'M',22),(2,'M',23),(2,'M',14),(2,'M',15),(2,'M',11),

(1,'F',20),(1,'F',20),(1,'F',20),(1,'F',20),(1,'F',20),
(1,'F',20),(1,'F',20),(1,'F',20),(1,'M',20),(1,'M',20),

(1,'M',30),(1,'M',30),(1,'M',30),(1,'F',30),(1,'F',30),
(1,'F',30),(1,'F',30),(1,'F',30),(1,'M',30),(1,'F',30),

(1,'F',40),(1,'F',40),(1,'F',40),(1,'F',40),(1,'F',40),
(1,'F',41),(1,'F',42),(1,'F',40),(1,'F',40),(1,'F',40);

SELECT restaurantId, COUNT(CASE WHEN gender='F' THEN 1 ELSE NULL END) AS femaleCount
				 , COUNT(CASE WHEN gender='M' THEN 1 ELSE NULL END) AS maleCount
				 , COUNT(CASE WHEN age>=10 AND age <20 THEN 1 ELSE NULL END) AS 10s
				 , COUNT(CASE WHEN age>=20 AND age <30 THEN 1 ELSE NULL END) AS 20s
				 , COUNT(CASE WHEN age>=30 AND age <40 THEN 1 ELSE NULL END) AS 30s
				 , COUNT(CASE WHEN age>=40 AND age <50 THEN 1 ELSE NULL END) AS 40s
				 , COUNT(CASE WHEN age>=50 AND age <60 THEN 1 ELSE NULL END) AS 50s
				 , COUNT(CASE WHEN age>=60 AND age <70 THEN 1 ELSE NULL END) AS 60s
				 FROM RestaurantPreference
				 GROUP BY restaurantId HAVING restaurantId=1;