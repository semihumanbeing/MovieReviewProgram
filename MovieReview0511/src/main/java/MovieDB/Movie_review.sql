
create table review 
(
	reviewIdx int,
	movieIdx int,
	id varchar2(100),
	reviewDate varchar2(100),
	reviewText varchar2(100) not null

)

--�⺻Ű & �ܷ�Ű �߰�
alter table review
	add constraint pk_review_reviewIdx primary key (reviewIdx);
alter table review
	add constraint fk_review_movieIdx foreign key (movieIdx) references movie (movieIdx) ;
alter table review
	add constraint fk_review_id foreign key (id) references users (id);

--�⺻Ű & �ܷ�Ű ����	
alter table review
	drop constraint pk_review_reviewIdx
alter table review
	drop constraint fk_review_movieIdx
alter table review
	drop constraint fk_review_id



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


