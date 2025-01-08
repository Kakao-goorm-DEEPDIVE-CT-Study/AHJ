## JOIN

**Implicit Join**

- JOIN키워드를 명시하지 않고 from절에 table 나열, where절에 join condition을 명시
  - 옛 방식으로 현재는 사용하지 않음
- 단점
  - where절에 selection condition과 join condition이 같이 있기에 가독성이 떨어짐
- 예제
  ```sql
  SELECT E.id, E.name, D.name AS department_name
  FROM EMPLOYEE E, DEPARTMENT D
  WHERE E.dept_id = D.id AND E.salary > 50000000;
  ```

**Explicit Join**

- from절에 JOIN 키워드와 함께 join되는 테이블을 명시
- ON 키워드 뒤에 join condition을 명시
- 장점
  - 가독성이 좋고 복잡한 쿼리 작성시 실수 가능성이 적음
- 예제
  ```sql
  SELECT E.id, E.name, D.name AS department_name
  FROM EMPLOYEE E
  INNER JOIN DEPARTMENT D ON E.dept_id = D.id
  WHERE E.salary > 50000000;
  ```

**Inner Join**

- 두 table에서 join condition을 만족하는 튜플들을 반환
  - null값을 가지는 튜플은 튜플에 포함되지 못함
- join condition은 다양한 비교 연산자가 가능
- 예제
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
  ```sql
  SELECT *
  FROM EMPLOYEE E
  INNER JOIN DEPARTMENT D ON E.dept_id = D.id;
  ```
  - 결과
    | id  | name  | dept_id | salary |
    | --- | ----- | ------- | ------ |
    | 1   | Alice | 101     | 60000  |
    | 2   | Bob   | 102     | 55000  |
    | id  | name | leader_id |
    | --- | ---- | --------- |
    | 101 | HR   | 1         |
    | 102 | IT   | 2         |

**Outer Join**

- join 조건을 만족하지 못한 튜플도 null로 반환
- left : from절에 나오는 테이블
- right : join 키워드 뒤에 나오는 테이블
- Left Outer Join
  - 왼쪽 테이블의 모든 튜플을 허용
  - 오른쪽 테이블의 값이 없다면 null
  - 예제
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
    ```sql
    SELECT *
    FROM EMPLOYEE E
    LEFT JOIN DEPARTMENT D ON E.dept_id = D.id;
    ```
  - 결과
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
    | id   | name | leader_id |
    | ---- | ---- | --------- |
    | 101  | HR   | 1         |
    | 102  | IT   | 2         |
    | NULL | NULL | NULL      |
- Right Outer Join
  - 오른쪽 테이블의 모든 튜플을 허용
  - 왼쪽 테이블의 값이 없다면 null
  - 예제
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
  ```sql
  SELECT *
  FROM EMPLOYEE E
  RIGHT JOIN DEPARTMENT D ON E.dept_id = D.id;
  ```
  - 결과
    | id   | name  | dept_id | salary |
    | ---- | ----- | ------- | ------ |
    | 1    | Alice | 101     | 60000  |
    | 2    | Bob   | 102     | 55000  |
    | NULL | NULL  | NULL    | NULL   |
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
- Full Outer Join
  - 양쪽 테이블의 모든 튜플을 허용
  - 모든 null 허용
  - MySQL은 지원하지 않음

**Equi Join**

- join condition에서 = 연산자를 사용하는 Join
- 동일한 값을 가진 컬럼을 기준으로 조인
- 예제
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
  ```sql
  SELECT *
  FROM EMPLOYEE E
  INNER JOIN DEPARTMENT D ON E.dept_id = D.id;
  ```
  - 결과
    | id  | name  | dept_id | salary |
    | --- | ----- | ------- | ------ |
    | 1   | Alice | 101     | 60000  |
    | 2   | Bob   | 102     | 55000  |
    | id  | name | leader_id |
    | --- | ---- | --------- |
    | 101 | HR   | 1         |
    | 102 | IT   | 2         |

**Using**

- equi join시 칼럼 이름이 동일하다면 using 키워드 사용 가능
  - 이때 같은 이름의 속성은 한번만 표시됨
- 예제
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
  ```sql
  SELECT *
  FROM EMPLOYEE E
  INNER JOIN DEPARTMENT D USING (dept_id);
  ```
  - 결과
    | id  | name  | salary | dept_id |
    | --- | ----- | ------ | ------- |
    | 1   | Alice | 60000  | 101     |
    | 2   | Bob   | 55000  | 102     |
    | name | leader_id |
    | ---- | --------- |
    | HR   | 1         |
    | IT   | 2         |

**Natural Join**

- 두 테이블에서 같은 이름을 가지는 모든 속성에 대해서 equi join을 수행
- join condition을 따로 명시하지 않음
- 예제
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
  ```sql
  SELECT *
  FROM EMPLOYEE E
  NATURAL JOIN DEPARTMENT D;
  ```
  - 결과
    | id  | name  | dept_id | salary | leader_id |
    | --- | ----- | ------- | ------ | --------- |
    | 1   | Alice | 101     | 60000  | 1         |
    | 2   | Bob   | 102     | 55000  | 2         |

**Cross Join**

- 두 테이블의 튜플 조합하여 만들 수 있는 모든 조합을 결과로 반환
- join condition없는 경우 모든 조합을 생성
- Mysql에서는
  - cross join에 on, using을 사용하면 inner join으로 동작
  - inner join에 on, using 없이 사용하면 cross join으로 동작
  - **EMPLOYEE 테이블**
    | id  | name    | dept_id | salary |
    | --- | ------- | ------- | ------ |
    | 1   | Alice   | 101     | 60000  |
    | 2   | Bob     | 102     | 55000  |
    | 3   | Charlie | NULL    | 50000  |
  - **DEPARTMENT 테이블**
    | id  | name      | leader_id |
    | --- | --------- | --------- |
    | 101 | HR        | 1         |
    | 102 | IT        | 2         |
    | 103 | Marketing | NULL      |
  ```sql
  SELECT *
  FROM EMPLOYEE E
  CROSS JOIN DEPARTMENT D;
  ```
  - 결과
    | id  | name    | dept_id | salary | id  |
    | --- | ------- | ------- | ------ | --- |
    | 1   | Alice   | 101     | 60000  | 101 |
    | 1   | Alice   | 101     | 60000  | 102 |
    | 1   | Alice   | 101     | 60000  | 103 |
    | 2   | Bob     | 102     | 55000  | 101 |
    | 2   | Bob     | 102     | 55000  | 102 |
    | 2   | Bob     | 102     | 55000  | 103 |
    | 3   | Charlie | NULL    | 50000  | 101 |
    | 3   | Charlie | NULL    | 50000  | 102 |
    | 3   | Charlie | NULL    | 50000  | 103 |
    | name      | leader_id |
    | --------- | --------- |
    | HR        | 1         |
    | IT        | 2         |
    | Marketing | NULL      |
    | HR        | 1         |
    | IT        | 2         |
    | Marketing | NULL      |
    | HR        | 1         |
    | IT        | 2         |
    | Marketing | NULL      |
