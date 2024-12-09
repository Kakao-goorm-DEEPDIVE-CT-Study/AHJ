-- id가 14인 임직원보다 생일이 빠른 임직원의 id,이름,생일
select id,name,birth_date 
from employee 
where birth_date < (select birth_date from employee where id = 14);

-- id가 1인 임직원과 같은 부서 같은 성별인 임직원들의 id,이름,직군
select id,name,position
from employee
where (dept_id,sex) = (select dept_id,sex from employee where id = 1);

-- id가 5인 임직원과 같은 프로젝트에 참여한 임직원들의 id
select distinct empl_id 
from work_on
where empl_id != 5 and (proj_id = 2001 or proj_id = 2002);

select distinct empl_id 
from work_on
where empl_id != 5 and proj_id IN (2001,2002);

select distinct empl_id 
from work_on
where empl_id != 5 and proj_id IN (select proj_id from work_on where empl_id = 5);

-- id가 5인 임직원과 같은 프로젝트에 참여한 임직원들의 id,이름
select id,name
from employee where id IN(
	select distinct empl_id 
	from work_on
	where empl_id != 5 and proj_id IN (
		select proj_id 
        from work_on 
        where empl_id = 5));
        
select id,name
from employee ,(
	select distinct empl_id 
	from work_on
	where empl_id != 5 and proj_id IN (
		select proj_id 
        from work_on 
        where empl_id = 5
        )
	)as DSTNCT_E
where id = DSTNCT_E.empl_id;

-- id가 7혹은 12인 임직원이 참여한 프로젝트의 id,이름
select P.id, P.name
from project P
where exists(
	select *
    from work_on W
    where W.proj_id = P.id and W.empl_id in (7,12)
    );
    -- 2000년대 생이 없는 부서의 id,이름
select D.id, D.name
from department as D
where not exists(
	select *
    from employee E
    where E.dept_id = D.id and E.birth_date >= '2000-01-01'
);

-- 리더보다 높은 연봉을 받는 부서원을 가진 리더의 id,이름,연봉
select E.id, E.name, E.salary
from department D, employee E
where D.leader_id = E.id and E.salary < ANY(
	select salary
    from employee
    where id <> D.leader_id and dept_id = e.dept_id
);

-- 리더보다 높은 연봉을 받는 부서원을 가진 리더의 id,이름,연봉,최고연봉
select E.id, E.name, E.salary,
	(
		select max(salary)
        from employee
        where dept_id = E.dept_id
	)as dept_max_salary
from department D, employee E
where D.leader_id = E.id and E.salary < ANY(
	select salary
    from employee
    where id <> D.leader_id and dept_id = e.dept_id
);

-- id가 13인 임직원과 한번도 같은 프로젝트를 참여하지 못한 임직원들의 id,이름,직군
select distinct E.id, E.name, E.position
from employee E, work_on W
where E.id = W.empl_id and W.proj_id <> ALL(
	select proj_id
    from work_on
    where empl_id = 13
);