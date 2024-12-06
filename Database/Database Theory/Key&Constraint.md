## 1. **Keys의 개념**

- 테이블 내에서 각 튜플을 고유하게 식별할 수 있는 속성 또는 속성들의 조합
- **역할**:
    - 데이터를 고유하게 식별하여 중복 방지
    - 테이블 간의 관계를 정의하는 데 사용

## 2. **Keys의 종류**

1. **Super Key**:
    - 테이블 내에서 튜플을 유일하게 식별할 수 있는 속성들의 집합
    - **예시**:
        
        ```
        STUDENT 테이블: {id}, {email}, {id, name}, {id, major}
        ```
        
2. **Candidate Key**:
    - Super Key 중에서 최소한의 속성들로 구성된 키
    - **예시**:
        
        ```
        STUDENT 테이블: {id}, {email} (중복 없이 유일한 값)
        ```
        
3. **Primary Key**:
    - Candidate Key 중 하나를 선택하여 테이블의 기본 키로 설정
    - **특징**:
        - Null 값 불가
        - 중복 불가
    - **예시**:
        
        ```sql
        CREATE TABLE STUDENT (
            id INT PRIMARY KEY,
            name VARCHAR(50) NOT NULL,
            major VARCHAR(50),
            year INT CHECK (year BETWEEN 1 AND 4)
        );
        
        or
        
        CREATE TABLE STUDENT (
            id INT,
            name VARCHAR(50) NOT NULL,
            major VARCHAR(50),
            PRIMARY KEY(id, name),
            year INT CHECK (year BETWEEN 1 AND 4)
        );
        ```
        
4. **Alternate Key**:
    - Candidate Key 중 Primary Key로 선택되지 않은 키
    - **예시**:
        
        ```
        STUDENT 테이블: email
        ```
        
5. **Foreign Key**:
    - 다른 테이블의 Primary Key를 참조하는 속성
    - 한 테이블의 Column이 다른 테이블의 Primary Key나 Unique Key를 참조하도록 설정하는 제약 조건
    - 테이블간 관계 설정 가능
    - 데이터 무결성 유지 가능
    - 참조 무결성 조건을 만족해야 함
    - 예제 :
        
        ```sql
        CREATE TABLE ChildTable (
            column_name1 DataType,
            column_name2 DataType,
            FOREIGN KEY (column_name1) REFERENCES ParentTable(parent_column)
                ON DELETE option
                ON UPDATE option
        );
        ```
        
    - **주요동작 :**
        - **CASCADE**
            - 참조되는 값이 변경되거나 삭제될 때 참조하고 있는 테이블도 동일한 작업을 수행.
            - ex) 부모 테이블에서 행이 삭제되면 자식 테이블의 해당 행도 자동으로 삭제.
            - **예제**:
                
                ```sql
                CREATE TABLE Department (
                    id INT PRIMARY KEY,
                    name VARCHAR(50)
                );
                
                CREATE TABLE Employee (
                    id INT PRIMARY KEY,
                    name VARCHAR(50),
                    dept_id INT,
                    FOREIGN KEY (dept_id) REFERENCES Department(id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
                ```
                
                - Department 테이블의 행이 삭제되거나 id가 변경되면 Employee 테이블의 관련 데이터도 삭제되거나 갱신.
        - **SET NULL**
            - 참조되는 값이 변경되거나 삭제될 때 참조하고 있는 테이블의 외래 키 값을 NULL로 설정
            - 외래 키 열이 NOT NULL 제약 조건을 가지면 사용 불가
            - **예제**:
                
                ```sql
                CREATE TABLE Department (
                    id INT PRIMARY KEY,
                    name VARCHAR(50)
                );
                
                CREATE TABLE Employee (
                    id INT PRIMARY KEY,
                    name VARCHAR(50),
                    dept_id INT,
                    FOREIGN KEY (dept_id) REFERENCES Department(id)
                        ON DELETE SET NULL
                        ON UPDATE SET NULL
                );
                ```
                
                - Department 테이블의 행이 삭제되거나 id가 변경되면 Employee 테이블의 `dept_id`가 NULL로 설정.단, 외래 키 열이 `NOT NULL` 제약 조건을 가지면 SET NULL 사용 불가
        - **RESTRICT**
            - 참조되는 값이 사용 중이면 삭제나 갱신을 허용하지 않음
            - **예제**:
                
                ```sql
                CREATE TABLE Department (
                    id INT PRIMARY KEY,
                    name VARCHAR(50)
                );
                
                CREATE TABLE Employee (
                    id INT PRIMARY KEY,
                    name VARCHAR(50),
                    dept_id INT,
                    FOREIGN KEY (dept_id) REFERENCES Department(id)
                        ON DELETE RESTRICT
                        ON UPDATE RESTRICT
                );
                ```
                
                - **설명**: Department 테이블의 행이 Employee 테이블에서 참조 중이면 삭제나 갱신 불가.
        - **NO ACTION**
            - RESTRICT와 유사하지만 지연 검사가 가능한 경우에 사용
            - 참조 테이블의 변경이 완료된 후에도 외래 키 제약 조건을 만족하지 않으면 오류 발생
            - **예제**:
                
                ```sql
                CREATE TABLE Department (
                    id INT PRIMARY KEY,
                    name VARCHAR(50)
                );
                
                CREATE TABLE Employee (
                    id INT PRIMARY KEY,
                    name VARCHAR(50),
                    dept_id INT,
                    FOREIGN KEY (dept_id) REFERENCES Department(id)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION
                );
                ```
                
                - **설명**: Department 테이블에서 변경된 데이터가 다른 트랜잭션에 의해 처리되지 않는 경우 오류 발생
        - **SET DEFAULT**
            - 참조되는 값이 변경되거나 삭제될 때 외래 키 열을 기본값으로 설정.
            - **예제**:
                
                ```sql
                CREATE TABLE Department (
                    id INT PRIMARY KEY,
                    name VARCHAR(50)
                );
                
                CREATE TABLE Employee (
                    id INT PRIMARY KEY,
                    name VARCHAR(50),
                    dept_id INT DEFAULT 1,
                    FOREIGN KEY (dept_id) REFERENCES Department(id)
                        ON DELETE SET DEFAULT
                        ON UPDATE SET DEFAULT
                );
                ```
                
                - **설명**: Department 테이블의 행이 삭제되거나 id가 변경되면 Employee 테이블의 dept_id가 기본값(1)로 설정
6. Unique key
    - 특징 : 중복된 값을 가질 수 없음
    
    ```sql
    CREATE TABLE STUDENT (
        id INT UNIQUE KEY,
        name VARCHAR(50) NOT NULL,
        major VARCHAR(50),
        year INT CHECK (year BETWEEN 1 AND 4)
    );
    
    or
    
    CREATE TABLE STUDENT (
        id INT,
        name VARCHAR(50) NOT NULL,
        major VARCHAR(50),
        UNIQUE KEY(id, name),
        year INT CHECK (year BETWEEN 1 AND 4)
    );
    ```
    

## 3. **제약 조건 (Constraints)**

1. **암묵적 제약 (Implicit Constraints)**:
    - 관계형 데이터 모델 자체에서 정의된 기본 제약 조건
    - 릴레이션(테이블)에는 중복된 튜플이 없음
    - 릴레이션의 각 속성 이름은 고유해야 함
2. **스키마 기반 제약 (Schema-Based Constraints)**:
    - 스키마 정의 시 명시적으로 선언하는 제약 조건
    - **Domain Constraints:**
        - **check**
            - 속성 값이 지정된 도메인에 속해야 함
            - **예시**:
                
                ```sql
                year INT CHECK (year BETWEEN 1 AND 4);
                
                age INT CHECK(age > 0);
                ```
                
        - default
            - 속성 값의 기본값을 설정
                
                ```sql
                status VARCHAR(10) DEFAULT 'active'
                ```
                
    - **Key Constraints**:
        - Primary Key는 중복 불가
    - **Not Null Constraints**:
        - 특정 속성 값은 Null 불가
        - **예시**:
            
            ```sql
            name VARCHAR(50) NOT NULL;
            ```
            
    - **Entity Integrity Constraint**:
        - Primary Key 값은 Null 불가
    - **Referential Integrity Constraint**:
        - Foreign Key는 참조하는 Primary Key와 일치해야 함
        - **예시**:
            
            ```sql
            FOREIGN KEY (student_id) REFERENCES STUDENT(id);
            ```
            
3. **애플리케이션 기반 제약 (Application-Based Constraints)**:
    - 데이터베이스에서 표현할 수 없는 복잡한 비즈니스 로직을 프로그램으로 구현
    - **예시**:
        - 수강 신청 시 학점이 18학점을 초과하지 못하도록 제한

### **예제**

1. **Key 설정**:
    
    ```sql
    CREATE TABLE STUDENT (
        id INT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        major VARCHAR(50),
        year INT CHECK (year BETWEEN 1 AND 4)
    );
    ```
    
2. **Foreign Key 설정**:
    
    ```sql
    CREATE TABLE ENROLL (
        student_id INT,
        course_id INT,
        grade CHAR(1),
        PRIMARY KEY (student_id, course_id),
        FOREIGN KEY (student_id) REFERENCES STUDENT(id),
        FOREIGN KEY (course_id) REFERENCES COURSE(id)
    );
    ```
    
3. **제약 조건 위반 방지**:
    - 학년 값이 1~4를 초과할 경우 에러 발생:
        
        ```sql
        INSERT INTO STUDENT (id, name, major, year)
        VALUES (2, 'Bob', 'Math', 5);
        ```