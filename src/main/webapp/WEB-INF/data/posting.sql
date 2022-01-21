INSERT INTO NoticePosting(memberId, title, content, views)
VALUES (1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1),
(1,"제목","본문",1);

SELECT * FROM NoticePosting;


INSERT INTO RecommendRestaurantPosting(memberId, restaurantId, title, content, likes, views)
VALUES (1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20),
(1,1,"제목2","본문2",10,20);

SELECT * FROM RecommendRestaurantPosting;



INSERT INTO TogetherEatingPosting(memberId, restaurantId, title, content, likes, views, mealTime, recruitment, mealChk )
VALUES (1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',true,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',true,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',true,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false),
(1,1,"제목3","본문3",11,22,'2022-01-21 00:02:11',false,false);


SELECT * FROM TogetherEatingPosting;


