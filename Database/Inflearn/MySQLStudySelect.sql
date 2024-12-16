-- id가 9인 임직원의 이름, 직군
select name, position FROM employee WHERE id = 9;

-- project 2002를 리딩하고 있는 임직원의 id,이름,직군
select employee.id, employee.name, position
from project, employee
where project.id=2002 and project.leader_id = employee.id;

-- as 사용
select E.id AS leader_id, E.name AS leader_name, position
from project AS P, employee AS E
where P.id = 2002 and P.leader_id = E.id;

-- 디자이너들이 참여하고 있는 프로젝트들의 id와 이름
select DISTINCT P.id, P.name
FROM employee AS E, work_on AS W, project AS P
where E.position = 'DSGN' and
	  E.id = W.empl_id and W.proj_id = P.id;	
      
-- 이름이 N으로 시작하거나 N으로 끝나는 임직원들의 이름을 알고 싶다
select name
from employee
where name like 'N%' or name like '%N';

-- 이름에 NG가 들어가는 임직원들의 이름을 알고 싶다
select name
from employee
where name like '%NG%';

-- 이름이 J로 시작하는, 총 네글자의 이름을 가지는 임직원의 이름을 알고 싶다
select name
from employee
where name like 'J___';

-- id가 9인 임직원의 모든 attributes를 알고 싶다alter
select *
from employee
where id = 9;

