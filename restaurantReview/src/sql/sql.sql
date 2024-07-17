CREATE TABLE member(
	id VARCHAR2(30) PRIMARY KEY,
	password VARCHAR(30) NOT NULL,
	grade NUMBER(1)NOT NULL
);
CREATE TABLE restaurant(
    id NUMBER(10) PRIMARY KEY,
    name VARCHAR2(60) NOT NULL UNIQUE,
    lowest_price NUMBER(7)NOT NULL,
    highest_price NUMBER(7)NOT NULL,
    duration_of_time NUMBER(2)NOT NULL,
    lastest_date DATE DEFAULT SYSDATE
);
CREATE SEQUENCE jsp.restaurant_id_seq -- 시퀸스 등록
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1;
CREATE TABLE review(
    id NUMBER(10) PRIMARY KEY,
    member_id VARCHAR2(30) REFERENCES jsp.member(id) NOT NULL,
    restaurant_id NUMBER(10) REFERENCES jsp.restaurant(id) NOT NULL,
    menu VARCHAR2(60) NOT NULL,
    point NUMBER(1)NOT NULL,
    review_content VARCHAR2(500),
    place_full VARCHAR2(9),
    lastest_date DATE DEFAULT SYSDATE
);
CREATE SEQUENCE jsp.review_id_seq 
    INCREMENT BY 1
    START WITH 1
    MINVALUE 1;

  -- 샘플 데이터
insert into member (id,password,grade)
values( '1@1','123',0); -- 관리자 계정
insert into member (id,password,grade)
values( 'test1','1234',1);
insert into member (id,password,grade)
values( 'test2','1234',1);
insert into member (id,password,grade)
values( 'test3','1234',1);
insert into member (id,password,grade)
values( 'test4','1234',1);
insert into member (id,password,grade)
values( 'test5','1234',1);

INSERT INTO restaurant (id ,name, lowest_price, highest_price, duration_of_time)
values( restaurant_id_seq.NEXTVAL ,'도리집' , 4000, 7000, 5 );
INSERT INTO restaurant (id, name, lowest_price, highest_price, duration_of_time)
values( restaurant_id_seq.NEXTVAL ,'고투웤' , 8900, 12000, 5 );
INSERT INTO restaurant (id,name, lowest_price, highest_price, duration_of_time)
values(restaurant_id_seq.NEXTVAL , '미진분식' , 5000 , 6000, 5 );

-- 가게 1 도리집 샘플 데이터
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test1' , 1 ,'빨간 도리밥' , 5 , 
'가격도 싸고 맛도 좋다 시내에서 5000원이 넘지않은 식사를 할 수 있는 
좋은 곳 입니다.', '상' );
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test2' , 1 ,'까만 도리밥' , 4 , 
'가격이 싸며 그럭저럭 만족스러웠다. 대신 자리가 없을때가 많아요ㅠㅠㅠ', '상' );
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test3' , 1 ,'도리텐동' , 5 , 
'뭐 텐동에 새우면 충분하지', '상' );
-- 가게2 고투웤 샘플데이터
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test1' , 2 ,'레귤러 세트' , 3 , 
'미드에서 본 음식 기대만큼은 아니더라구요.', '하' );
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test3' , 2 ,'스몰 세트' , 5 , 
'새로운 음식 새로운 맛 널널한 자리 먹고가기 좋았어요', '하' );
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test5' , 2 ,'스몰 세트' , 4 , 
'완전 바로나온 그 바삭한 느낌이 아니였어요', '하' );
-- 가게 3 미진분식 샘플데이터
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test4' , 3 ,'쫄면' , 5 , 
'새로운 새콤함이 느껴져요', '상' );
INSERT INTO review ( id, member_id, restaurant_id,menu,point, review_content, place_full )
values(review_id_seq.NEXTVAL, 'test5' , 3 ,'우동' , 4 , 
'맛은 좋지만 줄서고 이용하기 어렵네요.', '상' );
