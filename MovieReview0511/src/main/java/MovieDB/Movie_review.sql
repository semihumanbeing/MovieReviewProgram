
create table review 
(
	reviewIdx int,
	reviewDate varchar2(100),
	movieIdx int,
	id varchar2(100)

)

alter table review
	add constraint pk_review_postno primary key (reviewIdx);
alter table review
	add constraint fk_review_movieIdx foreign key (movieIdx) references movie (movieIdx) ;
alter table review
	add constraint fk_review_id foreign key (id) references users (id);

alter table review add reviewText varchar2(100) not null;
	
alter table review
	drop constraint fk_review_id

insert into review values (1,'05-11',2,'aa','굿');
insert into review values (2,'05-11',2,'aa','굿');
insert into review values (3,'05-11',2,'aa','굿');
insert into review values (4,'05-11',2,'aa','굿');
insert into review values (5,'05-11',2,'aa','굿');

----------------------------------------------------------------------------------------

create table users
(
	id varchar2(100),
	pwd varchar2(100) not null
)

alter table users
	add constraint pk_users_id primary key (id);
	
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

