
create table review 
(
	postno int,
	postdate varchar2(100),
	mono int,
	id varchar2(100)

)

alter table review
	add constraint pk_review_postno primary key (postno);
alter table review
	add constraint fk_review_mono foreign key (mono) references movie (mono) ;
alter table review
	add constraint fk_review_id foreign key (id) references users (id);

create table users
(
	id varchar2(100),
	pwd varchar2(100) not null
)

alter table users
	add constraint pk_users_id primary key (id);


create table movie
(
	mono int,
	motitle varchar2(100)
)

alter table movie
	add constraint pk_movie_mono primary key (mono);
	
insert into movie values (1,'살인의 추억');
insert into movie values (2,'1987');
insert into movie values (3,'범죄도시');
insert into movie values (4,'범죄와의전쟁');
insert into movie values (5,'');
insert into movie values (6,'');
	

select * from review
select * from users
select * from movie

