create table employee (
	id 			int primary key,
	birth_date 	date,
	first_name	varchar(50),
	last_name	varchar(50),
	gender		varchar(1)
);

create or replace PROCEDURE FILTER_EMPLOYEES(name IN VARCHAR2, ref_cursor OUT SYS_REFCURSOR) AS
BEGIN
  open ref_cursor for
    select id, last_name, first_name, birth_date from employee where last_name like '%' || name || '%';
END FILTER_EMPLOYEES;
/

insert into employee values(1, '19-JAN-1990', 'Ilon', 'Paiva', 'M');
insert into employee values (2, '12-MAR-92', 'Scub', 'Slub', 'F')
