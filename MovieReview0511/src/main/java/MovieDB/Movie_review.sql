
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

	

select * from review
select * from users
select * from movie

drop table movie


----------------------------------------------------------------------------------------
create or replace view selectAll
as
select r.id reviewId , r.movieIdx remoIdx, u.id userId , m.movieIdx moIdx , m.movieTitle 
from review r
	left outer join users u
	on r.id = u.id
		left outer join movie m
		on r.movieIdx = m.movieIdx

		
insert into users values ('aa','aa');
insert into users values ('bb','bb');



select * from selectAll

drop view selectAll



--�����߰� �Խù���ȣ












