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