## **1. Maven이란?**

- **Apache Maven**:
    - 자바 프로젝트의 빌드, 의존성 관리, 프로젝트 관리를 위한 빌드툴
    - 프로젝트의 빌드(Build), 의존성 관리, 문서화, 배포 등을 자동화
- **빌드 툴(Build Tool)**:
    - 소스 코드를 배포 가능한 산출물(Artifact)로 컴파일하고 패키징
- **프로젝트 관리 툴**:
    - 프로젝트의 구조, 라이브러리 의존성, 빌드 프로세스 등을 선언적으로 관리

**Maven이 없다면?**

- 라이브러리를 **직접 다운로드**하여 프로젝트 경로에 복사해야 함
- Java의 경우 Classpath에 라이브러리를 수동으로 지정해야 함
    - 라이브러리를 공유해야 하며, 버전 관리가 어려움

---

## **2. Maven 활용 패턴**

- Maven은 프로젝트의 빌드(Build)부터 릴리스(Release)까지 전 과정을 자동화가능
- **Build**: 소스 코드를 컴파일하고 빌드
- **Package**: 배포 가능한 jar, war 파일 등을 생성
- **Test**: 자동화된 테스트를 실행
- **Report**: 프로젝트의 문서화 및 리포트를 생성
- **Release**: 최종 산출물을 배포

---

## **3. 의존성 관리**

### **라이브러리 다운로드 자동화**

- 의존성 있는 라이브러리를 수동으로 하나씩 다운받을 필요가 없음
    - 의존성 선언만으로 필요한 라이브러리를 자동으로 다운로드
        - 중앙 저장소(Maven Central Repository)에서 라이브러리를 가져옴

### **Maven은 선언적**

- 명령식(Imperative)이 아닌 선언식(Declarative) 방식을 채택
    - pom.xml 파일에 라이브러리와 버전만 명시하면 Maven이 알아서 관리
        - 재다운로드, 최신 버전 설치 등을 Maven이 자동으로 처리

---

## **4. POM(Project Object Model)**

- **POM이란?**
    - 프로젝트의 기본 단위
        - 각 프로젝트는 하나의 pom.xml 파일을 가짐
    - pom.xml 파일에 프로젝트 정보와 의존성을 정의
    - Maven은 pom.xml을 읽어 프로젝트를 빌드하고 관리하는 방법을 이해

### **Maven의 자원 식별 형식**

- **그룹 식별자(Group ID)**: 프로젝트가 속한 그룹 또는 회사의 도메인 이름
    - ex) com.example
- **산출물 식별자(Artifact ID)**: 프로젝트의 이름
    - ex) myapp
- **버전(Version)**: 프로젝트의 버전
    - ex) 1.0.0
- 예제
    
    ```xml
    <project>
        <modelVersion>4.0.0</modelVersion>
        <groupId>com.example</groupId>
        <artifactId>myapp</artifactId>
        <version>1.0.0</version>
    </project>
    
    ```
    

---

## **5. Plugin**

### **플러그인이란?**

- Maven이 프로젝트를 빌드, 리포팅, 테스트하는 데 사용되는 모듈
- Maven 자체는 플러그인 실행 프레임워크이며, 실제 작업은 플러그인이 수행
- 플러그인은 다른 산출물과 마찬가지로 저장소에서 관리

### **목적(Goal)**

- 플러그인이 수행하는 특정 작업의 최소 단위
- 하나의 플러그인은 여러 개의 Goal을 가질 수 있음
    - == 여러 작업을 수행할 수 있음
- **형식**: <plugin>:<goal>
    - ex) maven-compiler-plugin:compile
- 플러그인은 여러 goal을 묶어 라이프 사이클 단계를 만들고 실행
    - ex) mvn install

### **플러그인 선언 예시 (pom.xml)**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>

```

---

## **6. Lifecycle (라이프사이클)**

### **Maven의 빌드 라이프사이클**

- 빌드 라이프사이클은 프로젝트를 빌드하기 위한 일련의 단계를 의미
    - Maven은 프레임워크이기 때문에 작업 방식과 빌드 순서가 정해져 있음
    - 각 Phase는 이전 단계가 성공적으로 완료되어야 실행

### Phase

- 실질적인 작업을 수행하는 것은 각 Phase에 연결된 플러그인 Goal
    - jar, war등 패키지 타입에 따라 수행되는 Goal이 다름

### **주요 라이프사이클**

- **clean 라이프사이클**: 이전 빌드의 산출물을 삭제
- **default 라이프사이클**: 실제 프로젝트의 빌드를 수행
    - **compile**: 소스 코드를 컴파일
    - **test**: 테스트 코드를 실행
    - **package**: 컴파일된 코드를 패키징
        - ex) JAR, WAR
    - **verify**: 패키지가 유효한지 검증
    - **install**: 패키지를 로컬 저장소에 설치
    - **deploy**: 패키지를 원격 저장소에 배포
- **site 라이프사이클**: 프로젝트의 문서화와 사이트 생성을 수행

### **3) Phase 실행 방법**

- 특정 Phase를 실행하려면 mvn [phase명] 명령어를 사용
- ex) 컴파일: mvn compile, 패키징: mvn package
    - Phase 간 의존 관계가 있으므로 mvn package를 실행하면 compile과 test 단계도 자동으로 실행

---

## **7. Profile**

### **프로파일이란?**

- 서로 다른 대상 환경을 위한 빌드 설정 집합입니다.
    - 다른 운영 체제, 다른 배포 환경
- 각 환경에 이름을 붙여 빌드시 해당 환경 전용 빌드 설정

### **프로파일 활성화 및 사용**

- 프로파일을 활성화하려면 P 옵션을 사용
- ex) mvn package -P prod
    - prod란 환경에 맞춤형 빌드
    - 위 명령은 prod 프로파일을 활성화하여 패키징

### **pom.xml에서 프로파일 설정**

```xml

<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <properties>
            <env>dev</env>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <env>prod</env>
        </properties>
    </profile>
</profiles>

```

- **<id>**: 프로파일의 이름을 지정합니다.
- **<activeByDefault>**: 기본 프로파일로 설정합니다.
- **<properties>**: 프로파일별로 다른 프로퍼티 변수를 설정합니다.
- **<env> :** 프로파일마다 다른 경로의 설정파일 사용할때 사용
- 개발용 프로파일(dev)에서는 config/dev 디렉토리의 설정 파일을 사용
    - id=dev : 디폴트 프로파일
- 운영용 프로파일(prod)에서는 config/prod 디렉토리의 설정 파일을 사용
    - id=prod : prod 환경

### **4) 프로파일별 설정 적용**

- 빌드 과정에서 ${env} 변수를 사용하여 프로파일별 설정을 적용

```xml
<build>
    <resources>
        <resource>
            <directory>config/${env}</directory>
        </resource>
    </resources>
</build>

```