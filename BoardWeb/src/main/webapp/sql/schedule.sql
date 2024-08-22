create table tbl_schedule(
    title varchar2(300) not null,
    start_date VARCHAR2(20) not null,
    end_date VARCHAR2(20) not null
);

insert into tbl_schedule(title, start_date, end_date)
values('여름휴가','2024-08-18','2024-08-25');

insert into tbl_schedule(title, start_date, end_date)
values('발표회의','2024-08-26T16:00:00','2024-08-25T18:00:00');

insert into tbl_schedule(title, start_date)
values('여행','2024-08-30');

select *
		from tbl_schedule;