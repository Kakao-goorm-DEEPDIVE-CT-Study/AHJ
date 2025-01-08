-- ID가 1003인 부서에 속하는 임직원 중 리더를 제외한 부서원의 ID, 이름, 연봉을 알고 싶
select E.id, E.name, E.salary
FROM EMPLOYEE E JOIN DEPARTMENT D ON E.dept_id = D.id
WHERE E.dept_id = 1003 and E.id != D.leader_id;

-- ID가 2001인 프로젝트에 참여한 임직원들의 이름과 직군과 소속부서 이름을 알고 싶
select E.name as empl_name, E.position as empl_position, D.name as dept_name
from WORK_ON W join EMPLOYEE E on W.empl_id = E.id left join DEPARTMENT D on E.dept_id = D.id
where W.proj_id = 2001;
