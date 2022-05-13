
create table review 
(
	reviewIdx int,
	movieIdx int,
	id varchar2(100),
	reviewDate varchar2(100),
	reviewText varchar2(100) not null

)

--기본키 & 외래키 추가
alter table review
	add constraint pk_review_reviewIdx primary key (reviewIdx);
alter table review
	add constraint fk_review_movieIdx foreign key (movieIdx) references movie (movieIdx) ;
alter table review
	add constraint fk_review_id foreign key (id) references users (id);

--기본키 & 외래키 지우기	
alter table review
	drop constraint pk_review_reviewIdx
alter table review
	drop constraint fk_review_movieIdx
alter table review
	drop constraint fk_review_id

--테이블 지우기
drop table review

----------------------------------------------------------------------------------------

create table users
(
	id varchar2(100),
	pwd varchar2(100) not null
)

alter table users
	add constraint pk_users_id primary key (id);
	
drop table users
	
----------------------------------------------------------------------------------------


create table movie
(
	movieIdx int,
	movieTitle varchar2(100)
)

alter table movie
	add constraint pk_movie_movieIdx primary key (movieIdx);
	
insert into movie values (1,'살인의 추억');
insert into movie values (2,'1987');
insert into movie values (3,'범죄도시');
insert into movie values (4,'범죄와의전쟁');
insert into movie values (5,'아메리칸 싸이코');
insert into movie values (6,'폭력의 역사');
	

select * from review
select * from users
select * from movie

--테이블 지우기
drop table movie


----------------------------------------------------------------------------------------

--뷰 만들기
create or replace view selectAll
as
select  r.reviewIdx, r.id reviewId , r.movieIdx remoIdx, u.id userId , m.movieIdx moIdx , m.movieTitle 
from review r
	left outer join users u
	on r.id = u.id
		left outer join movie m
		on r.movieIdx = m.movieIdx
	order by r.reviewIdx

--left outer join 조회 -> users, movie, review
select * from selectAll

--뷰 삭제
drop view selectAll

----------------------------------------------------------------------------------------

--시퀀스 생성
create sequence seq_reviewIdx

--시퀀스 적용
insert into review(reviewIdx) values(seq_reviewIdx.nextVal)
-- ex)
insert into review values(seq_reviewIdx.nextVal,1,'최규범',sysdate,'심금을 울리는 영화..')
