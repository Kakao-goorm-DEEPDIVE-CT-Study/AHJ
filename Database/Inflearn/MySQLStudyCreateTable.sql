create table DEPARTMENT(
	id int primary key,
    name varchar(20) not null unique,
    leader_id int
);

create table EMPLOYEE(
	id int primary key,
    name varchar(30) not null,
    birth_date date,
    sex char(1) check(sex in ('M','F')),
    position varchar(10),
    salary int default 50000000,
    dept_id int,
    foreign key(dept_id) references department(id)
		on delete set null
        on update cascade,
	check(salary >= 50000000)
);

create table PROJECT(
	id int primary key,
    name varchar(20) not null unique,
    leader_id int,
    start_date date,
    end_date date,
    foreign key (leader_id) references employee(id)
		on delete set null
        on update cascade,
	check(start_date < end_date)
);

create table WORK_ON(
	empl_id int,
    proj_id int,
    primary key(empl_id, proj_id),
    foreign key(empl_id) references employee(id)
		on delete cascade
        on update cascade,
	foreign key(proj_id) references project(id)
		on delete cascade
        on update cascade
);