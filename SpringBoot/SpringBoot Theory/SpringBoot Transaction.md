## **1. 트랜잭션이란?**

- 데이터 베이스에서 작업 단위를 묶어서 처리하기 위한 개념
- 하나의 작업이 모두 성공하거나 실패하도록 보장함
    - 중간에 문제가 발생해도 데이터가 일관된 상태를 유지
- 데이터 무결성과 안정성을 위해 ACID 속성을 따름
- 경계를 설정해 데이터베이스 작업의 시작 ~ 끝을 구분
    - 설정된 경계 내에서 모두 commit, rollback처리

## **2. ACID 속성**

1. **원자성 (Atomicity)**:
    - 모든 작업은 전부 성공하거나 전부 실패
    - 성공 시 commit 처리
    - 실패 시 rollback 처리
    - ex) 송금 작업 중 돈이 빠져나갔는데 수신자가 받지 못하면, 작업을 취소하고 돈을 원래 상태로 돌려야 함
2. **일관성 (Consistency)**:
    - 작업 전후 데이터 상태가 항상 일관된 규칙을 만족
    - ex) 계좌 A와 계좌 B의 총 잔액이 작업 전후 동일해야 함
3. **고립성 (Isolation)**:
    - 한 Transaction이 완료되기 전에는 다른 Transaction이 간섭할 수 없음
    - ex) 쇼핑몰에서 재고 수량이 1인 상품에 대해 동시에 구매 요청이 들어오면 한 번에 하나만 처리되도록 함
4. **지속성 (Durability)**:
    - 작업 결과가 영구히 저장됨
    - ex) 송금 완료 후 시스템 오류가 발생해도 결과는 사라지지 않음

## **3. Spring의 트랜잭션 관리 방식**

### **선언적 트랜잭션 관리**

- @Transactional 어노테이션 사용
- 스프링 컨테이너가 Transaction 시작, 커밋, 롤백을 자동으로 처리

**장점**:

- 코드가 간결하고 가독성 높음
- Annotation사용으로 Transaction 로직이 코드와 분리되어 유지보수 용이

**사용법**:

- **클래스 단위 적용**:
    - 클래스의 모든 메서드가 Transaction으로 처리됨
        
        ```java
        @Transactional
        public class MyService { ... }
        ```
        
- **메서드 단위 적용**:
    - 특정 메서드에만 Transaction 적용
        
        ```java
        @Transactional
        public void processOrder() { ... }
        ```
        

### **프로그래밍적** Transaction **관리**

- TransactionTemplate 또는 PlatformTransactionManager를 사용하여 수동으로 Transaction 제어

**장점**:

- Transaction 동작을 더 세밀하게 제어 가능.
    - 예외 상황이나 특정 조건에 따라 Transaction 동작을 조정해야 할 때 유용

**단점**:

- 코드 복잡도 증가
- 선언적 방식보다 구현이 번거로움

## **4.** Transaction **전파**

- 현재 실행 중인 Transaction이 있는 상황에서 다른 메서드, 서비스가 Transaction을 요구하는 작업을 호출할때 Transaction을 어떻게 처리할지 정의하는 방법

### **Propagation의 주요 옵션**

1. **REQUIRED** (기본값)
    - 현재 Transaction이 존재하면 이를 사용, 없으면 새로운 Transaction을 생성
    - 대부분의 일반적인 서비스 로직에 적합
    - ex) A 메서드에서 B 메서드를 호출 → A 트랜잭션이 있으면 공유, 없으면 새 Transaction 생성
    
    ```java
    @Transactional(propagation = Propagation.REQUIRED)
    public void methodA() {
        // 기존 트랜잭션이 있다면 공유
    }
    ```
    
2. **REQUIRES_NEW**
    - 항상 새로운 Transaction을 생성, 기존 Transaction은 일시 중단
    - 로그 기록, 보조 작업처럼 주 Transaction과 분리되어야 하는 작업에 적합
    - ex) A 메서드가 B 메서드를 호출 → B 메서드는 항상 독립적인 Transaction으로 실행
    
    ```java
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void methodB() {
        // 항상 새로운 트랜잭션으로 실행
    }
    ```
    
3. **SUPPORTS**
    - Transaction이 있으면 참여, 없으면 Transaction 없이 실행.
    - Transaction이 선택적인 작업에 사용
    - ex) A 메서드가 B 메서드를 호출 → A가 Transaction을 가지고 있다면 B가 참여, 없으면 B는 Transaction 없이 실행
    
    ```java
    @Transactional(propagation = Propagation.SUPPORTS)
    public void methodSupports() {
        // 트랜잭션이 있으면 참여, 없으면 트랜잭션 없이 실행
    }
    ```
    
4. **NOT_SUPPORTED**
    - Transaction을 무시하고 Non - Transaction 모드로 실행
    - 성능 최적화를 위해 Transaction이 필요 없는 작업
    
    ```java
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void methodNotSupported() {
        // 항상 트랜잭션 없이 실행
    }
    ```
    
5. **NEVER**
    - Transaction이 있으면 예외 발생
    - Transaction이 절대 허용되지 않는 작업
    
    ```java
    @Transactional(propagation = Propagation.NEVER)
    public void methodNever() {
        // 트랜잭션이 존재하면 예외 발생
    }
    ```
    
6. **MANDATORY**
    - 반드시 기존 Transaction에 참여해야 함
    - Transaction이 없으면 예외 발생
    - 항상 상위 트랜잭션의 존재를 기대하는 작업에 적합
    
    ```java
    @Transactional(propagation = Propagation.MANDATORY)
    public void methodMandatory() {
        // 반드시 상위 트랜잭션이 있어야 실행
    }
    ```
    

## **4. 트랜잭션 격리 수준 (Isolation)**

- 여러 트랜잭션이 동시에 실행될 때 데이터의 안정성을 보장

### **격리 수준 설명**

1. **READ_UNCOMMITTED**:
    - 다른 트랜잭션의 작업 중인 데이터를 읽을 수 있음
    - 가장 낮은 안정성
    - **문제** : dirty read 발생 가능
        - 다른 트랜잭션에서 아직 커밋되지 않은 데이터를 읽는 것
    
    ```java
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void methodReadUncommitted() {
        // 커밋되지 않은 데이터도 읽을 수 있음
    }
    ```
    
2. **READ_COMMITTED**:
    - 커밋된 데이터만 읽음
    - dirty read 방지
    - **문제 :** non-repeatable read 발생 가능
        - 같은 트랜잭션 내에서 동일한 데이터를 두번 읽었을때 값이 달라지는 상황
            1. A 데이터 조회 : 1
            2. B 데이터 수정 : 1 → 2
            3. A 데이터 조회 : 2
    
    ```java
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void methodReadCommitted() {
        // 커밋된 데이터만 읽음
    }
    ```
    
3. **REPEATABLE_READ**:
    - 동일 트랜잭션 내에서는 항상 동일 데이터를 읽음
    - non-repeatable read 방지
    - **문제 :** phantom read 발생 가능
        - 동일 조건으로 데이터를 조회했을 때 결과 데이터 집합이 달라지는 현상
            - insert
                - A 데이터 조회 : 1
                - B 데이터 삽입 : 1 2
                - A 데이터 조회 : 1 2
            - delete
                - A 데이터 조회 : 1 2
                - B 데이터 삭제 : 1 2 → 1
                - A 데이터 조회 : 1
    
    ```java
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void methodRepeatableRead() {
        // 트랜잭션 내에서 동일 데이터의 일관성을 유지
    }
    ```
    
4. **SERIALIZABLE**:
    - 모든 트랜잭션을 순차적으로 처리.
    - 데이터 충돌 완전히 방지, 하지만 성능 저하.
    
    ```java
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void methodSerializable() {
        // Phantom Read 방지
    }
    ```
    

## **5. 롤백 규칙**

- 기본적으로 언체크 예외(RuntimeException), 에러 발생 시 롤백 처리
- Exception은 롤백되지 않지만 필요 시 설정 가능.

```java
@Transactional(rollbackFor = Exception.class)
```

## **6. 추가 설정**

- **시간 제한**:
    - 트랜잭션이 지정된 시간 안에 완료되지 않으면 강제 롤백.
    
    ```java
    @Transactional(timeout = 30)
    ```
    
- **읽기 전용**:
    - 데이터 읽기 전용 작업에 사용하여, 불필요한 수정 작업 방지.
    
    ```java
    @Transactional(readOnly = true)
    ```