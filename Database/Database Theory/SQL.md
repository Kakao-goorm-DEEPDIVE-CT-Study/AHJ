## 1. **SQL이란?**

- 관계형 데이터베이스를 정의하고 조작하며 제어하기 위한 표준화된 언어
- 관계형 데이터베이스는 데이터를 테이블 구조로 저장, 테이블 간 관계를 통해 데이터를 연결
    - SQL은 이러한 관계 관리, 데이터 검색, 조작하는 데 필수적인 언어
- **역할**:
    - 데이터베이스 구조 정의
    - 데이터 조작 (삽입, 조회, 수정, 삭제)
    - 보안 및 접근 권한 제어

## 2. **SQL의 주요 구성**

1. **DDL (Data Definition Language)**:
    - 데이터베이스 구조를 생성, 수정, 삭제하는 명령어
    - **주요 명령어**: CREATE, ALTER, DROP
    - **예시**:
        
        ```sql
        CREATE TABLE STUDENT (
            id INT PRIMARY KEY,
            name VARCHAR(50),
            major VARCHAR(50),
            year INT
        );
        
        ALTER TABLE Employee ADD COLUMN salary DECIMAL(10, 2);
        
        DROP TABLE Employee
        ```
        
2. **DML (Data Manipulation Language)**:
    - 데이터를 삽입, 삭제, 수정하는 명령어
    - **주요 명령어**: INSERT, UPDATE, DELETE, SELECT
    - **예시**:
        
        ```sql
        INSERT INTO Employee (id, name, age, department_id)VALUES (1, 'Alice', 30, 101);
        
        UPDATE Employee SET age = 31 WHERE id = 1;
        
        DELETE FROM Employee WHERE id = 1;
        ```
        
3. **VDL (View Definition Language)**:
    - External Schema(뷰)를 정의
    - SQL의 DDL이 수행
    - **예시**:
        
        ```sql
        CREATE VIEW CS_STUDENTS AS
        SELECT id, name
        FROM STUDENT
        WHERE major = 'Computer Science';
        ```
        
4. **SDL (Storage Definition Language)**:
    - Internal Schema(저장 구조)를 정의
    - 거의 사용되지 않음
- **통합 SQL**
    - 현대 SQL은 DDL, DML, VDL의 기능을 통합 제공
    - 사용자는 하나의 SQL 언어로 데이터 정의, 조작, 관리 가능

## 3. SQL의 응용

- **특정 부서의 평균 연봉 구하기**
    
    ```sql
    SELECT department_id, AVG(salary)
    FROM Employee
    GROUP BY department_id;
    ```
    
- **가장 높은 연봉을 받는 직원 찾기**
    
    ```sql
    SELECT name, salary
    FROM Employee
    WHERE salary = (SELECT MAX(salary) FROM Employee);
    ```
    
- **특정 부서에서 연봉이 가장 높은 직원 찾기**
    
    ```sql
    SELECT name, salary
    FROM Employee
    WHERE salary = (
        SELECT MAX(salary)
        FROM Employee
        WHERE department_id = 101
    );
    
    ```
    

## **4. 데이터 삽입 (INSERT INTO)**

- **INSERT INTO** : 명령어를 사용하여 테이블에 데이터를 추가
- Primary Key 값이 중복되면 에러 발생
    - ex) 동일한 id 값을 두 번 삽입하려고 하면 실패
- Foreign Key 삽입시 참조 테이블에 해당 값이 존재해야 삽입 가능
    - ex) Employee 테이블의 department_id가 Department 테이블의 id와 일치해야 함
- 테이블의 모든 열(Column)에 값을 삽입할 경우
- 값은 테이블의 열 순서와 일치해야 함
    
    ```sql
    INSERT INTO 테이블명 VALUES (값1, 값2, 값3, ...);
    ```
    
- 예제 :
    
    ```sql
    INSERT INTO Employee VALUES (1, 'Alice', 'HR', 30);
    ```
    
- **선택적 열 삽입**
    - 특정 열만 선택하여 데이터를 삽입할 경우
    - 값의 순서는 지정된 열의 순서와 같아야 함
        
        ```sql
        INSERT INTO 테이블명 (열1, 열2, ...) VALUES (값1, 값2, ...);
        ```
        
    - 예제:
        
        ```sql
        INSERT INTO Employee (id, name) VALUES (2, 'Bob');
        ```
        
- **여러 튜플 삽입**
    - 한 번에 여러 개의 튜플 삽입 가능
        
        ```sql
        INSERT INTO 테이블명 VALUES
            (값1, 값2, 값3, ...),
            (값4, 값5, 값6, ...),
            ...;
        ```
        
    - 예제 :
        
        ```sql
        INSERT INTO Employee VALUES
            (3, 'Charlie', 'Finance', 28),
            (4, 'Diana', 'IT', 35);
        ```
        
- **에러 메시지 확인**
    - 테이블 생성 시 적용된 제약 조건 확인
        
        ```sql
        SHOW CREATE TABLE 테이블명;
        ```
        
    - 예제:
        
        ```sql
        CONSTRAINT `employee_chk_2` CHECK (`salary` >= 500000)
        ```
        

## **5. 데이터 수정 (UPDATE)**

- 특정 조건을 만족하는 데이터의 값을 수정
- 조건절(WHERE)을 생략하면 모든 행의 값이 수정되므로 주의.
- 제약 조건(CHECK, NOT NULL 등)을 위반하는 값으로 수정하면 에러 발생.
    
    ```sql
    UPDATE 테이블명 SET 열1 = 수정할값1 WHERE 조건;
    ```
    
    - 예제:
        
        ```sql
        UPDATE Employee SET name = 'Eve' WHERE id = 2;
        ```
        
- **여러 속성 수정**
    - 여러 열의 값을 동시에 수정:
        
        ```sql
        UPDATE 테이블명 SET 열1 = 수정할값1, 열2 = 수정할값2 WHERE 조건;
        ```
        
    - 예제:
        
        ```sql
        UPDATE Employee SET name = 'Eve', department = 'Marketing' WHERE id = 2;
        ```
        

## **6. 데이터 삭제 (DELETE FROM)**

- 특정 조건을 만족하는 행 삭제:
- 조건절(WHERE)을 생략하면 모든 데이터가 삭제됨.
    
    ```sql
    DELETE FROM 테이블명 WHERE 조건;
    ```
    
    - 예제:
        
        ```sql
        DELETE FROM Employee WHERE id = 3;
        ```
        
- 참조 테이블(Foreign Key)을 사용하는 경우, 삭제하려는 데이터가 참조 중이면 에러 발생.
- 이를 방지하려면 ON DELETE 옵션(CASCADE, SET NULL 등)을 활용.

### **특수 연산자 : 데이터 제외**

- 특정 값을 제외하고 데이터를 삭제하거나 수정
    - <>: '같지 않다'를 의미하는 비교 연산자
    
    ```sql
    DELETE FROM Employee
    WHERE department <> 'HR'; -- HR 부서를 제외한 모든 행 삭제
    ```
    

## 예제

```sql
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE,
    manager_id INT
);
```

- **문제 1**
- 급여가 500,000 이상이고 department가 'IT' 또는 'HR'인 직원의 이름과 부서를 조회하시오.
    
    ```sql
    SELECT emp_name, department
    FROM employees
    WHERE salary >= 500000 AND (department = 'IT' OR department = 'HR');
    ```
    
- **문제 2**
- hire_date가 NULL인 직원을 삭제하시오.
    
    ```sql
    DELETE FROM employees
    WHERE hire_date IS NULL;
    ```
    
- **문제 3**
- 급여가 400,000 이하이고 department가 NULL인 직원의 급여를 **500,000으로 수정**하시오.
    
    ```sql
    UPDATE employees
    SET salary = 500000
    WHERE salary <= 400000 AND department IS NULL;
    ```
    
- **문제 4**
- department가 'Finance'이면서 manager_id가 NULL이 아닌 직원의 정보를 조회하시오.
    
    ```sql
    SELECT *
    FROM employees
    WHERE department = 'Finance' AND manager_id IS NOT NULL;
    ```
    
- **문제 5**
- 입사일이 2021년 이후이면서 급여가 600,000 이상인 직원의 부서를 'High Earners'로 변경하시오.
    
    ```sql
    UPDATE employees
    SET department = 'High Earners'
    WHERE hire_date > '2021-01-01' AND salary >= 600000;
    ```
    
- **문제 6**
- emp_id가 101에서 110 사이인 직원의 급여를 700,000으로 변경하시오.
    
    ```sql
    UPDATE employees
    SET salary = 700000
    WHERE emp_id BETWEEN 101 AND 110;
    ```
    
- **문제 7**
- manager_id가 201인 직원 중에서 salary가 450,000 이하인 직원을 삭제하시오.
    
    ```sql
    DELETE FROM employees
    WHERE manager_id = 201 AND salary <= 450000;
    ```
    
- **문제 8**
- 급여가 가장 낮은 직원의 이름과 급여를 조회하시오.
    
    ```sql
    SELECT emp_name, salary
    FROM employees
    WHERE salary = (SELECT MIN(salary) FROM employees);
    ```
    
- **문제 9**
- department가 'Marketing'인 직원의 manager_id를 '300'으로 설정하시오.
    
    ```sql
    UPDATE employees
    SET manager_id = 300
    WHERE department = 'Marketing';
    ```
    
- **문제 10**
- department가 NULL이거나 salary가 700,000 이상인 직원을 모두 삭제하시오.
    
    ```sql
    DELETE FROM employees
    WHERE department IS NULL OR salary >= 700000;
    ```
    
- **문제 11**
- manager_id가 105이면서 salary가 600,000 이하인 직원의 급여를 650,000으로 수정하시오.
    
    ```sql
    UPDATE employees
    SET salary = 650000
    WHERE manager_id = 105 AND salary <= 600000;
    ```
    
- **문제 12**
- 입사일이 2022년 이전이고 급여가 500,000 이상인 직원의 이름과 급여를 조회하시오.
    
    ```sql
    SELECT emp_name, salary
    FROM employees
    WHERE hire_date < '2022-01-01' AND salary >= 500000;
    ```
    
- **문제 13**
- 모든 직원의 급여를 20% 증가시키되, department가 'HR'인 직원은 제외하시오.
    
    ```sql
    UPDATE employees
    SET salary = salary * 1.2
    WHERE department != 'HR';
    ```
    
- **문제 14**
- department가 NULL이고 manager_id가 102인 직원의 부서를 'Support'로 설정하시오.
    
    ```sql
    UPDATE employees
    SET department = 'Support'
    WHERE department IS NULL AND manager_id = 102;
    ```
    
- **문제 15**
- 급여가 300,000 이상인 직원의 이름과 부서를 조회하되, department가 'Sales'인 경우는 제외하시오.
    
    ```sql
    SELECT emp_name, department
    FROM employees
    WHERE salary >= 300000 AND department != 'Sales';
    ```## 1. **SQL이란?**

- 관계형 데이터베이스를 정의하고 조작하며 제어하기 위한 표준화된 언어
- 관계형 데이터베이스는 데이터를 테이블 구조로 저장, 테이블 간 관계를 통해 데이터를 연결
    - SQL은 이러한 관계 관리, 데이터 검색, 조작하는 데 필수적인 언어
- **역할**:
    - 데이터베이스 구조 정의
    - 데이터 조작 (삽입, 조회, 수정, 삭제)
    - 보안 및 접근 권한 제어

## 2. **SQL의 주요 구성**

1. **DDL (Data Definition Language)**:
    - 데이터베이스 구조를 생성, 수정, 삭제하는 명령어
    - **주요 명령어**: CREATE, ALTER, DROP
    - **예시**:
        
        ```sql
        CREATE TABLE STUDENT (
            id INT PRIMARY KEY,
            name VARCHAR(50),
            major VARCHAR(50),
            year INT
        );
        
        ALTER TABLE Employee ADD COLUMN salary DECIMAL(10, 2);
        
        DROP TABLE Employee
        ```
        
2. **DML (Data Manipulation Language)**:
    - 데이터를 삽입, 삭제, 수정하는 명령어
    - **주요 명령어**: INSERT, UPDATE, DELETE, SELECT
    - **예시**:
        
        ```sql
        INSERT INTO Employee (id, name, age, department_id)VALUES (1, 'Alice', 30, 101);
        
        UPDATE Employee SET age = 31 WHERE id = 1;
        
        DELETE FROM Employee WHERE id = 1;
        ```
        
3. **VDL (View Definition Language)**:
    - External Schema(뷰)를 정의
    - SQL의 DDL이 수행
    - **예시**:
        
        ```sql
        CREATE VIEW CS_STUDENTS AS
        SELECT id, name
        FROM STUDENT
        WHERE major = 'Computer Science';
        ```
        
4. **SDL (Storage Definition Language)**:
    - Internal Schema(저장 구조)를 정의
    - 거의 사용되지 않음
- **통합 SQL**
    - 현대 SQL은 DDL, DML, VDL의 기능을 통합 제공
    - 사용자는 하나의 SQL 언어로 데이터 정의, 조작, 관리 가능

## 3. SQL의 응용

- **특정 부서의 평균 연봉 구하기**
    
    ```sql
    SELECT department_id, AVG(salary)
    FROM Employee
    GROUP BY department_id;
    ```
    
- **가장 높은 연봉을 받는 직원 찾기**
    
    ```sql
    SELECT name, salary
    FROM Employee
    WHERE salary = (SELECT MAX(salary) FROM Employee);
    ```
    
- **특정 부서에서 연봉이 가장 높은 직원 찾기**
    
    ```sql
    SELECT name, salary
    FROM Employee
    WHERE salary = (
        SELECT MAX(salary)
        FROM Employee
        WHERE department_id = 101
    );
    
    ```
    

## **4. 데이터 삽입 (INSERT INTO)**

- **INSERT INTO** : 명령어를 사용하여 테이블에 데이터를 추가
- Primary Key 값이 중복되면 에러 발생
    - ex) 동일한 id 값을 두 번 삽입하려고 하면 실패
- Foreign Key 삽입시 참조 테이블에 해당 값이 존재해야 삽입 가능
    - ex) Employee 테이블의 department_id가 Department 테이블의 id와 일치해야 함
- 테이블의 모든 열(Column)에 값을 삽입할 경우
- 값은 테이블의 열 순서와 일치해야 함
    
    ```sql
    INSERT INTO 테이블명 VALUES (값1, 값2, 값3, ...);
    ```
    
- 예제 :
    
    ```sql
    INSERT INTO Employee VALUES (1, 'Alice', 'HR', 30);
    ```
    
- **선택적 열 삽입**
    - 특정 열만 선택하여 데이터를 삽입할 경우
    - 값의 순서는 지정된 열의 순서와 같아야 함
        
        ```sql
        INSERT INTO 테이블명 (열1, 열2, ...) VALUES (값1, 값2, ...);
        ```
        
    - 예제:
        
        ```sql
        INSERT INTO Employee (id, name) VALUES (2, 'Bob');
        ```
        
- **여러 튜플 삽입**
    - 한 번에 여러 개의 튜플 삽입 가능
        
        ```sql
        INSERT INTO 테이블명 VALUES
            (값1, 값2, 값3, ...),
            (값4, 값5, 값6, ...),
            ...;
        ```
        
    - 예제 :
        
        ```sql
        INSERT INTO Employee VALUES
            (3, 'Charlie', 'Finance', 28),
            (4, 'Diana', 'IT', 35);
        ```
        
- **에러 메시지 확인**
    - 테이블 생성 시 적용된 제약 조건 확인
        
        ```sql
        SHOW CREATE TABLE 테이블명;
        ```
        
    - 예제:
        
        ```sql
        CONSTRAINT `employee_chk_2` CHECK (`salary` >= 500000)
        ```
        

## **5. 데이터 수정 (UPDATE)**

- 특정 조건을 만족하는 데이터의 값을 수정
- 조건절(WHERE)을 생략하면 모든 행의 값이 수정되므로 주의.
- 제약 조건(CHECK, NOT NULL 등)을 위반하는 값으로 수정하면 에러 발생.
    
    ```sql
    UPDATE 테이블명 SET 열1 = 수정할값1 WHERE 조건;
    ```
    
    - 예제:
        
        ```sql
        UPDATE Employee SET name = 'Eve' WHERE id = 2;
        ```
        
- **여러 속성 수정**
    - 여러 열의 값을 동시에 수정:
        
        ```sql
        UPDATE 테이블명 SET 열1 = 수정할값1, 열2 = 수정할값2 WHERE 조건;
        ```
        
    - 예제:
        
        ```sql
        UPDATE Employee SET name = 'Eve', department = 'Marketing' WHERE id = 2;
        ```
        

## **6. 데이터 삭제 (DELETE FROM)**

- 특정 조건을 만족하는 행 삭제:
- 조건절(WHERE)을 생략하면 모든 데이터가 삭제됨.
    
    ```sql
    DELETE FROM 테이블명 WHERE 조건;
    ```
    
    - 예제:
        
        ```sql
        DELETE FROM Employee WHERE id = 3;
        ```
        
- 참조 테이블(Foreign Key)을 사용하는 경우, 삭제하려는 데이터가 참조 중이면 에러 발생.
- 이를 방지하려면 ON DELETE 옵션(CASCADE, SET NULL 등)을 활용.

### **특수 연산자 : 데이터 제외**

- 특정 값을 제외하고 데이터를 삭제하거나 수정
    - <>: '같지 않다'를 의미하는 비교 연산자
    
    ```sql
    DELETE FROM Employee
    WHERE department <> 'HR'; -- HR 부서를 제외한 모든 행 삭제
    ```
    

## 예제

```sql
CREATE TABLE employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10, 2),
    hire_date DATE,
    manager_id INT
);
```

- **문제 1**
- 급여가 500,000 이상이고 department가 'IT' 또는 'HR'인 직원의 이름과 부서를 조회하시오.
    
    ```sql
    SELECT emp_name, department
    FROM employees
    WHERE salary >= 500000 AND (department = 'IT' OR department = 'HR');
    ```
    
- **문제 2**
- hire_date가 NULL인 직원을 삭제하시오.
    
    ```sql
    DELETE FROM employees
    WHERE hire_date IS NULL;
    ```
    
- **문제 3**
- 급여가 400,000 이하이고 department가 NULL인 직원의 급여를 **500,000으로 수정**하시오.
    
    ```sql
    UPDATE employees
    SET salary = 500000
    WHERE salary <= 400000 AND department IS NULL;
    ```
    
- **문제 4**
- department가 'Finance'이면서 manager_id가 NULL이 아닌 직원의 정보를 조회하시오.
    
    ```sql
    SELECT *
    FROM employees
    WHERE department = 'Finance' AND manager_id IS NOT NULL;
    ```
    
- **문제 5**
- 입사일이 2021년 이후이면서 급여가 600,000 이상인 직원의 부서를 'High Earners'로 변경하시오.
    
    ```sql
    UPDATE employees
    SET department = 'High Earners'
    WHERE hire_date > '2021-01-01' AND salary >= 600000;
    ```
    
- **문제 6**
- emp_id가 101에서 110 사이인 직원의 급여를 700,000으로 변경하시오.
    
    ```sql
    UPDATE employees
    SET salary = 700000
    WHERE emp_id BETWEEN 101 AND 110;
    ```
    
- **문제 7**
- manager_id가 201인 직원 중에서 salary가 450,000 이하인 직원을 삭제하시오.
    
    ```sql
    DELETE FROM employees
    WHERE manager_id = 201 AND salary <= 450000;
    ```
    
- **문제 8**
- 급여가 가장 낮은 직원의 이름과 급여를 조회하시오.
    
    ```sql
    SELECT emp_name, salary
    FROM employees
    WHERE salary = (SELECT MIN(salary) FROM employees);
    ```
    
- **문제 9**
- department가 'Marketing'인 직원의 manager_id를 '300'으로 설정하시오.
    
    ```sql
    UPDATE employees
    SET manager_id = 300
    WHERE department = 'Marketing';
    ```
    
- **문제 10**
- department가 NULL이거나 salary가 700,000 이상인 직원을 모두 삭제하시오.
    
    ```sql
    DELETE FROM employees
    WHERE department IS NULL OR salary >= 700000;
    ```
    
- **문제 11**
- manager_id가 105이면서 salary가 600,000 이하인 직원의 급여를 650,000으로 수정하시오.
    
    ```sql
    UPDATE employees
    SET salary = 650000
    WHERE manager_id = 105 AND salary <= 600000;
    ```
    
- **문제 12**
- 입사일이 2022년 이전이고 급여가 500,000 이상인 직원의 이름과 급여를 조회하시오.
    
    ```sql
    SELECT emp_name, salary
    FROM employees
    WHERE hire_date < '2022-01-01' AND salary >= 500000;
    ```
    
- **문제 13**
- 모든 직원의 급여를 20% 증가시키되, department가 'HR'인 직원은 제외하시오.
    
    ```sql
    UPDATE employees
    SET salary = salary * 1.2
    WHERE department != 'HR';
    ```
    
- **문제 14**
- department가 NULL이고 manager_id가 102인 직원의 부서를 'Support'로 설정하시오.
    
    ```sql
    UPDATE employees
    SET department = 'Support'
    WHERE department IS NULL AND manager_id = 102;
    ```
    
- **문제 15**
- 급여가 300,000 이상인 직원의 이름과 부서를 조회하되, department가 'Sales'인 경우는 제외하시오.
    
    ```sql
    SELECT emp_name, department
    FROM employees
    WHERE salary >= 300000 AND department != 'Sales';
    ```