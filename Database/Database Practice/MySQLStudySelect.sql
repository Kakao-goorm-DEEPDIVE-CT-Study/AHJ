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


-- Student 테이블을 참조해서 전공이 201번인 학생의 이름과 전화번호와 지역번호를 출력하세요. 단 지역번호는 숫자만 나와야 합니다.

select name as 이름, phone as 전화번호, SUBSTR(jumin,9,4) as 지역번호
from STUDENT
where major = 201;

-- Student 테이블에서 전공이 102 번인 학생들의 이름과 전화번호, 전화번호에서 국번 부분만 '*' 처리하여 출력하세요. 단 모든 국번은 3자리로 간주합니다.

select name as 이름, REPLACE(phone, '010', '***') as 전화번호
from STUDENT
where major = 102;

-- Student 테이블의 birthday 컬럼을 사용하여 생일이 1월인 학생의 학번, 이름, birthday 를 출력하세요.

select student_id as 학번, name as 이름, birthday as 생일
from STUDENT
where SUBSTR(birthday,6,2) = '01';

-- Student 테이블을 사용하여 제 1 전공(deptno1)이 101 번인 학과 학생들의 이름과 주민번호, 성별을 출력하되 
-- 성별은 주민번호(jumin) 컬럼을 이용하여 7번째 숫자가 1일 경우 "MAN", 2일 경우 "WOMAN" 으로 출력하세요.

select name as 이름, jumin as 주민번호, if(SUBSTR(jumin,8, 1) = 1, 'MAN', 'WOMAN') as 성별
from STUDENT
where deptno1 = 101;

-- emp 테이블을 조회하여 comm 값을 가지고 있는 사람들의 empno, ename, hiredate, 총연봉, 15% 인상 후 연봉을 출력하세요. 
-- 단, 총연봉은 (sal*12)+comm 으로 계산하고 아래 화면에서는 SAL 로 출력되었으며 15% 인상한 값은 총연봉의 15% 인상 값입니다. 
-- (HIREDATE 컬럼의 날짜 형식과 SAL 컬럼의 $ 표시와, 기호 나오게 하세요)

select empno as 사번 , ename as 이름, hiredate as 입사일, sal * 12 + comm as 총연봉, (sal * 12 + comm) * 1.15 as 인상연봉
from EMP;

-- emp 테이블을 사용하여 사원 중에서 급여(sal)와 보너스(comm)를 합친 금액이 가장 많은 경우와 가장 적은 경우, 평균 금액을 구하세요.
-- 단 보너스가 없을 경우는 보너스를 0으로 계산하고 출력 금액은 모두 소수점 첫째 자리까지만 나오게 하세요.

select max(sal + comm) as 최대_급여, min(sal + if(comm is null, 0 , comm)) as 최소_급여, avg(sal + if(comm is null, 0, comm)) as 평균_급여
from EMP;

-- Professor 테이블에서 201번 학과 교수들의 이름과 급여, bonus, 총 연봉을 아래와 같이 출력하세요. 
-- 단 총 연봉은 (pay*12+bonus)로 계산하고 bonus 가 없는 교수는 0으로 계산하세요.
select name as 이름, pay as 급여, bonus, (pay * 12 + if(bonus is null, 0, bonus))as 총연봉
from PROFESSOR
where deptno = 201;

