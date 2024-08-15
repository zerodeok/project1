
create table plan(
                     plan_id int auto_increment,
                     password varchar(10),
                     reg_dttm date,
                     udt_dttm date,
                     cntn varchar(2000),
                     constraint plan_pk primary key (plan_id)
);
