## 1. **Database란?**

- 전자적으로 저장되고 사용되는 관련 있는 데이터들의 조직화된 집합
    - **관련 있는 데이터**: 같은 서비스나 시스템에서 함께 사용되는 데이터
        - ex) 학생 관리 시스템에서의 학생 정보(이름, 학번, 전공 등)
    - **조직화된 집합**: 데이터를 효율적으로 저장하고 관리하여 쉽게 검색, 수정, 삭제가 가능하도록 설계
        - **목적**: 저장된 데이터를 빠르게 찾고 활용하는 것

## 2. **DBMS(Database Management System)**

- Database를 정의하고, 생성 및 관리하는 기능을 제공하는 소프트웨어
    - 사용자와 Database 간의 인터페이스 역할 수행
    - 데이터를 효율적으로 접근하고 관리할 수 있도록 지원
    - **주요 기능**:
        1. 데이터 정의: 테이블, 관계, 제약 조건 등을 생성
        2. 데이터 조작: 삽입, 수정, 삭제, 검색
        3. 데이터 보안: 접근 권한 설정, 인증 및 암호화
        4. 데이터 무결성: 제약 조건을 통해 데이터 정확성과 일관성 유지
- **대표적인 DBMS**:
    - 오픈 소스: PostgreSQL, MySQL
    - 상용: Oracle, SQL Server

## 3. **메타데이터(Metadata)**

- Database를 정의하거나 기술하는 데이터
    - 데이터 구조와 속성을 설명하며, Database 운영에 필수적인 정보
    - **주요 요소**:
        - 데이터 유형 (int, varchar 등)
        - 테이블 구조
        - 제약 조건 (primary key, foreign key 등)
        - 보안 설정 (권한, 암호화 등)
- **저장 방식**:
    - meta data는 DBMS 내부에서 Catalog라는 형태로 저장되고 관리됨
    - **Catalog**:
        - 데이터베이스 객체(테이블, 뷰 등)에 대한 정의를 포함
        - DBMS가 쿼리를 처리할 때 참조

## 4. **처리 방식**

- DBMS를 통한 데이터 처리 흐름:
    1. 사용자가 **Application Program**을 통해 쿼리를 생성
    2. DBMS가 쿼리를 분석하고 요청을 처리
    3. 메타데이터를 참조하여 필요한 데이터에 접근
    4. 데이터베이스에서 결과를 반환

### 예제: 학생 관리 시스템

1. **서비스의 데이터베이스 구조**:
    - **관련 데이터**:
        - 학생 정보: 이름, 학번, 전공, 학년
    - **조직화된 집합**:
        - 테이블 STUDENT:
            
            ```
            STUDENT(id, name, major, year)
            ```
            
2. **SQL을 사용한 DBMS 적용**:
    
    ```sql
    CREATE TABLE STUDENT (
        id INT PRIMARY KEY,
        name VARCHAR(50),
        major VARCHAR(50),
        year INT
    );
    
    INSERT INTO STUDENT (id, name, major, year) VALUES (1, 'Alice', 'Computer Science', 3);
    ```
    
3. **DBMS 선택 기준**:
    - 데이터 양과 사용 목적에 따라 선택
        - 소규모 애플리케이션 → MySQL
        - 대규모 시스템 → Oracle, PostgreSQL
4. **데이터베이스 설계**:
    - **초기 설계 단계**:
        1. 관련 데이터 파악(학생, 강의, 성적 등)
        2. 테이블 정의 및 관계 설정
    - 예제:
        - 강의 정보를 추가:
            
            ```sql
            CREATE TABLE COURSE (
                id INT PRIMARY KEY,
                title VARCHAR(100),
                credits INT
            );
            ```
            
5. **메타데이터 활용**:
    - DBMS는 자동으로 생성된 메타데이터를 활용해 데이터 일관성 및 성능 관리
        - ex) 테이블에 새로운 Attribute 추가 시, 메타데이터가 업데이트되어 쿼리에 반영

## 5. **데이터 모델(Data Model)**

- 데이터베이스 구조를 표현하고 조직화하기 위한 개념적 틀
    - 데이터 유형, 관계, 제약 조건 등을 정의하여 데이터 구조를 명확히 함
- **역할**:
    - 복잡한 데이터베이스 구조를 추상화하여 이해를 돕는 수단 제공
    - 데이터베이스 설계 및 관리를 위한 지침 제공
- **데이터 모델의 종류**
    - 추상화 수준에 따라 다음 세 가지로 분류
    1. **Conceptual Data Model (High Level)**:
        - **정의**: 비즈니스 요구사항을 추상화하여 쉽게 이해할 수 있는 방식으로 표현
        - **특징**:
            - 비전문가(일반 사용자)도 이해할 수 있는 높은 추상화 수준
            - 데이터의 엔티티(Entity), 속성(Attribute), 관계(Relationship) 중심
        - **대표 모델**:
            - Entity-Relationship(ER) 모델
    2. **Logical Data Model (Representational)**:
        - **정의**: DBMS와 독립적이면서도 실제 구현에 가까운 형태로 구조화
        - **특징**:
            - Conceptual Model보다 구체적
            - 컴퓨터에 저장되는 데이터 구조와 유사
            - 특정 DBMS에 종속되지 않음
        - **대표 모델**:
            - Relational Data Model
                
                ```
                STUDENT(id, name, major, year)
                
                | id | name  | major             | year |
                |----|-------|-------------------|------|
                | 1  | Alice | Computer Science  |  3   |
                | 2  | Bob   | Mathematics       |  2   |
                ```
                
    3. **Physical Data Model (Low Level)**:
        - **정의**: 데이터가 파일 시스템에서 물리적으로 저장되는 방식을 기술
        - **특징**:
            - 가장 낮은 추상화 수준
            - 데이터 저장소와 데이터 접근 경로를 설계
        - ex)
            - 파일 저장 포맷, 인덱스, 데이터 정렬 방식

## 6. **Database Schema**

- 데이터 모델을 기반으로 데이터베이스 구조를 표현한 설계도
    - 모델 : 개념적인 설계
        - ex) 종이에 더이터를 정리해서 적어둔 것
    - 스키마 : 데이터베이스 엔진에 구체적으로 구현한 설계
    - 데이터의 유형, 테이블 구성, 속성, 제약 조건 등을 포함
- **특징**:
    - 데이터베이스 설계 시 정의되며, 변경이 드뭄
    - 테이블 간의 관계를 명확히 규정
- 예시:
    
    ```
    STUDENT(id, name, major, year)
    ```
    

## 7. **Database State (Snapshot)**

- 특정 시점에 데이터베이스에 저장된 데이터의 집합.
    - 데이터는 지속적으로 변하지만 스키마는 고정.
- **예시**:
    
    ```
    | id | name  | major             | year |
    |----|-------|-------------------|------|
    | 1  | Alice | Computer Science  |  3   |
    | 2  | Bob   | Mathematics       |  2   |
    ```
    

### 예제

1. **Conceptual 설계**:
    - 비즈니스 요구사항을 추출하여 ER Diagram으로 추상화.
        - ex) 학사관리 시스템 설계에서 학생-강의 관계 정의.
2. **Logical 설계**:
    - 데이터베이스 독립적으로 테이블, 관계를 설계
    - 특정 데이터베이스 엔진을 고려하지 않고, 데이터 구조와 관계만 명확히 표현
    - 예제:
        
        ```scss
        STUDENT(id, name, major, year)
        COURSE(id, title, credits)
        ```
        
3. **SQL 테이블 생성**:
    - Logical 설계를 기반으로 테이블과 관계 구현
    - 예제:
        
        ```sql
        CREATE TABLE STUDENT (
            id INT PRIMARY KEY,
            name VARCHAR(50),
            major VARCHAR(50),
            year INT
        );
        
        CREATE TABLE COURSE (
            id INT PRIMARY KEY,
            title VARCHAR(100),
            credits INT
        );
        ```
        
4. **Physical 설계**:
    - 테이블 생성 이후 데이터베이스의 성능 최적화를 위해 물리적 설계를 추가
    - 데이터베이스 엔진의 특성에 따라 스토리지, 인덱스, 파티셔닝 등을 설정
    - 예제:
        
        ```sql
        CREATE INDEX idx_student_name ON STUDENT(name);
        -- 특정 열에 대해 검색 속도 향상을 위한 인덱스 추가
        ```
        

## 8. Three Schema Architecture

- 데이터베이스 시스템 설계 및 관리를 위한 아키텍처 모델
- **목적**:
    - 물리적 데이터 저장소와 사용자 간의 독립성 제공
        - 내부 구조 변경시 영향 최소화
    - 데이터베이스 시스템 변경 시 최소한의 수정으로 안정적인 운영 가능
1. **External Schema** 
    - External View, User View
    - 특정 사용자나 애플리케이션만 접근 가능한 데이터만 표현.
    - **특징**:
        - 사용자가 보는 데이터의 부분만 표현
        - 필요 없는 데이터는 숨겨 보안성 강화.
        - Logical Data Model로 표현.
    - **예시**:
        - View를 사용해 학생이 본인의 정보만 볼 수 있게 제한
            
            ```sql
            CREATE VIEW STUDENT_VIEW AS
            SELECT id, name, grade
            FROM STUDENT
            WHERE id = CURRENT_USER();
            ```
            
2. **Conceptual Schema**
    - 데이터베이스 전체 구조를 논리적으로 표현
    - 내부 스키마와 외부 스키마를 연결
    - **특징**:
        - 모든 데이터를 통합한 하나의 뷰 제공
        - 물리적 저장 방식과 독립적
        - 엔티티, 속성, 관계, 제약 조건 등 데이터 구조의 청사진을 포함
        - Logical Data Model로 표현
    - **포함 요소**:
        - 엔티티, 데이터 유형, 관계, 제약 조건 등
    - **예시**:
        
        ```
        STUDENT(id, name, major, year)
        COURSE(id, title, credits)
        ENROLL(student_id, course_id, grade)
        ```
        
3. **Internal Schema**
    - 데이터가 물리적으로 저장되는 방식을 표현
    - **특징**:
        - 데이터의 물리적 저장 방식, 인덱스 구조를 정의
        - 특정 DBMS의 특성을 반영
        - Physical Data Model로 표현
    - **예시**:
        - 파일 시스템에 저장되는 데이터 포맷.
        - MySQL에서 테이블 데이터 저장소 설정:
            
            ```sql
            CREATE TABLE STUDENT (
                id INT PRIMARY KEY,
                name VARCHAR(50),
                major VARCHAR(50),
                year INT
            );
            ```
            
- **장점 :**
    - **논리적 독립성** : Conceptual Schema 변경 시 External Schema에 영향 없음
    - **물리적 독립성** : Internal Schema 변경 시 Conceptual Schema에 영향 없음
    - **유지보수 용이성** : 시스템 변경 시 데이터베이스 구조와 사용자가 접근하는 뷰를 분리하여 관리

## 9. **관계형 데이터 모델**

- 데이터를 2차원 테이블 형태로 표현하여 각 테이블 간의 관계를 나타내는 데이터 모델
    - **Table(Relation):** 행(Tuple)과 열(Attribute)으로 구성된 데이터 집합
    - **Tuple**:
        - 테이블의 각 행
        - 데이터를 저장하는 단위
        - 개별 레코드를 의미
    - **Attribute**:
        - 테이블의 열
        - 각 열은 데이터의 고유 이름과 타입, 특성을 나타냄
    - **Domain:**
        - 속성 값이 가질 수 있는 값의 허용 범위
- **관계형 데이터 모델의 특징**
    1. **단순한 구조**:
        - 데이터를 행과 열로 구성된 테이블로 표현하여 이해와 관리가 용이
        - 테이블 간의 관계를 통해 복잡한 데이터 구조를 단순화
    2. **데이터 독립성**:
        - 논리적 데이터 구조와 물리적 저장 구조를 분리하여 데이터 변경 시 시스템 영향 최소화
        - 응용 프로그램과 데이터베이스 간의 상호 의존성을 줄여 유지보수 용이
    3. **무결성 제약 조건**:
        - 데이터의 정확성과 일관성을 유지하기 위해 다양한 제약 조건을 적용
        - **키 제약(Key Constraints)**: 기본 키를 통해 각 튜플을 고유하게 식별
        - **참조 무결성(Referential Integrity)**: 외래 키를 통해 테이블 간의 관계를 유지하고 데이터 일관성 확보
            - 한 테이블의 값이 변하면 연결된 다른 테이블에서도 변경
    4. **표준화된 질의 언어**:
        - SQL을 사용하여 데이터 정의, 조작, 제어를 수행
        - 비절차적 언어로 사용자가 원하는 데이터를 쉽게 조회하고 관리 가능
    5. **데이터 중복 최소화**:
        - 정규화를 통해 데이터 중복을 줄이고 저장 공간 효율성을 높임
        - 테이블 간의 관계를 활용하여 중복 없이 데이터를 저장
- **관계형 데이터 모델의 구성 요소**
    1. **도메인(Domain)**:
        - 속성이 가질 수 있는 값들의 집합
        - ex) 성별 도메인 = {남, 여}
    2. **속성(Attribute)**:
        - 테이블의 열에 해당하며, 데이터의 특성을 나타냄
        - ex) 학생 테이블의 '이름', '학번'
    3. **튜플(Tuple)**:
        - 테이블의 행에 해당하며, 하나의 레코드를 구성
        - ex) 특정 학생의 정보
    4. **릴레이션(Relation)**:
        - 튜플의 집합으로 이루어진 테이블
        - ex) 학생 정보를 담은 'STUDENT' 테이블
    5. **스키마(Schema)**:
        - 데이터베이스의 구조와 제약 조건을 정의한 것
        - ex) 테이블의 이름, 속성, 데이터 타입, 제약 조건 등
- **관계형 데이터 모델의 장단점**
    - **장점**:
        - 데이터의 일관성과 무결성 보장
        - 표준화된 SQL을 통해 데이터 관리 용이
        - 데이터 독립성을 통해 시스템 유지보수 효율성 향상
    - **단점**:
        - 대용량 데이터 처리에 한계가 있을 수 있음
        - 복잡한 데이터 구조를 표현하는 데 어려움이 있을 수 있음