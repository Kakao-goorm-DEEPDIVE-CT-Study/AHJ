## 1. 데이터베이스

### 데이터베이스 생성

- 새로운 데이터베이스를 생성할 때 사용하는 명령어
- 데이터베이스 이름은 알파벳, 숫자, 밑줄(_)로 구성 가능
- 동일한 이름의 데이터베이스가 이미 존재하면 에러 발생
- 데이터베이스 이름은 실무에서 프로젝트명, 도메인명 등으로 직관적으로 설정하는 것이 유지보수에 용이
- 예제:
    
    ```sql
    CREATE DATABASE 회사DB;
    ```
    
    - 데이터베이스 생성 시 에러 방지 : 동일 이름의 데이터베이스가 이미 존재할 경우를 대비
        
        ```sql
        CREATE DATABASE IF NOT EXISTS 회사DB;
        ```
        

### 데이터베이스 선택

- 생성한 데이터베이스를 사용하려면 선택 명령어 사용 필요
- 선택된 데이터베이스는 이후 명령에서 기본 컨텍스트로 설정
- 데이터베이스를 선택하지 않고 작업하면 "No database selected" 오류 발생 가능.
- 예제:
    
    ```sql
    USE 회사DB;
    ```
    

### 현재 데이터베이스 확인

- 현재 작업 중인 데이터베이스 이름을 확인
- 결과로 현재 사용 중인 데이터베이스 이름 반환
- 데이터베이스를 명시적으로 선택했는지 확인할 때 유용.
- 예제:
    
    ```sql
    SELECT DATABASE();
    ```
    

### 데이터베이스 삭제

- 더 이상 사용하지 않는 데이터베이스를 삭제
- 데이터베이스 삭제는 내부의 모든 데이터와 테이블을 제거하므로 신중히 사용
- 삭제 작업 전에 데이터베이스의 스냅샷을 생성하거나 백업 파일을 저장
- 예제:
    
    ```sql
    DROP DATABASE 회사DB;
    ```
    
    - 데이터베이스 삭제 시 에러 방지 : 존재하지 않는 데이터베이스를 삭제하려 할 때 발생하는 오류를 방지
        
        ```sql
        DROP DATABASE IF EXISTS 회사DB;
        ```
        

## 2. 테이블

- 테이블은 데이터베이스에서 데이터를 저장하는 기본 단위

```sql
CREATE TABLE Department (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    leader_id INT
);

CREATE TABLE Employee (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT CHECK(age > 0),
    department_id INT,
    salary DECIMAL(10, 2),
    FOREIGN KEY (department_id) REFERENCES Department(id)
);
```

### 테이블 설계

1. **속성 이름의 일관성**
    - 속성 이름은 직관적이고 가독성이 좋아야 함.
    - ex) employee_id, department_id처럼 명확히 표현.
2. **데이터 타입 선택**
    - 저장 용량과 데이터 유효성을 고려하여 데이터 타입을 선택.
    - ex) 전화번호처럼 길이가 일정한 값은 CHAR을 사용, 이름처럼 가변 길이 데이터는 VARCHAR 사용.
3. **제약 조건 활용**
    - 제약 조건을 통해 데이터 무결성을 유지.
    - ex) PRIMARY KEY, FOREIGN KEY, NOT NULL, CHECK 등을 적절히 사용.

### 테이블 생성

- **CREATE TABLE :**
    - 테이블의 구조를 정의
    - 각 열(Column)의 이름, 데이터 타입, 제약 조건 등을 설정 가능
- 예제:
    
    ```sql
    CREATE TABLE Employee (
        id INT PRIMARY KEY,
        name VARCHAR(50) NOT NULL,
        age INT CHECK(age > 0),
        department_id INT,
        salary DECIMAL(10, 2)
    );
    ```
    

### 테이블 수정

- **ALTER TABLE :**
    - 테이블 생성 후 속성을 추가, 삭제, 수정할 수 있음
    - ALTER TABLE 명령어를 사용하여 테이블 구조를 유연하게 변경 가능.
- 예제:
    
    ```sql
    ALTER TABLE Employee ADD COLUMN phone VARCHAR(20); -- 열 추가
    ALTER TABLE Employee DROP COLUMN phone;           -- 열 삭제
    ALTER TABLE Employee MODIFY COLUMN salary DECIMAL(12, 2); -- 열 수정
    ```
    

### 테이블 삭제

- 더 이상 필요 없는 테이블은 삭제
- 예제:
    
    ```sql
    DROP TABLE Employee;
    ```
    

## 3. 주요 데이터 타입

### **1. 정수형**

- **TINYINT**: 1바이트, -128 ~ 127
- **SMALLINT**: 2바이트, -32,768 ~ 32,767
- **MEDIUMINT**: 3바이트, -8,388,608 ~ 8,388,607
- **INT,** **INTEGER**: 4바이트, -2,147,483,648 ~ 2,147,483,647
- **BIGINT**: 8바이트, 매우 큰 정수 처리 가능

### **2. 부동소수점 및 고정소수점**

- **FLOAT**: 4바이트, 소수점 포함 값 저장
- **DOUBLE**: 8바이트, 더 높은 정밀도의 소수점 값 저장
- **DECIMAL**: 고정된 자리수의 소수점 값 저장
    - 부동소수점(FLOAT, DOUBLE)은 계산 속도가 빠르지만, 정밀도가 낮아 금융 데이터 처리에는 부적합
    - 금융 데이터는 항상 DECIMAL을 사용하여 정밀한 값을 저장
    - 예제:
        
        ```sql
        salary DECIMAL(10, 2); -- 최대 10자리, 소수점 이하 2자리
        ```
        

### **3. 문자열**

- **CHAR(n)**: 고정 길이 문자열, 데이터 길이가 일정할 때 사용
- **VARCHAR(n)**: 가변 길이 문자열, 길이가 가변적일 때 사용
- **TEXT**: 대용량 문자열, 긴 텍스트 데이터를 저장할 때 사용

### **4. 날짜 및 시간**

- **DATE**: 날짜만 저장 (YYYY-MM-DD 형식)
- **DATETIME**:
    - 날짜와 시간을 저장 (YYYY-MM-DD HH:MM:SS 형식)
    - 특정 시점을 기록하는 데 적합
- **TIMESTAMP**:
    - 타임존 포함 시간 데이터
    - 서버의 시간대를 기준으로 저장하며, 자동으로 업데이트 가능
        - 데이터 변경 시 자동 업데이트가 필요한 경우 유용

### **5. 기타 데이터 타입**

- **BOOLEAN**: 논리 값 저장 (0 또는 1로 표현)
- **JSON**:
    - 구조화된 JSON 형식 데이터 저장
    - 복잡한 데이터 구조를 쉽게 저장하고 검색 가능
    - 예제:
        
        ```sql
        CREATE TABLE User (
            id INT PRIMARY KEY,
            info JSON
        );
        
        INSERT INTO User (id, info) VALUES (1, '{"name": "Alice", "age": 30}');
        ```
        
- **BLOB :** 바이너리 대용량 객체 저장 (이미지, 동영상 등)
    - TINYBLOB, BLOB, MEDIUMBLOB, LONGBLOB