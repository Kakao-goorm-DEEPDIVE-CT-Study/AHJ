## **1. Maven이란?**

- Maven 프로젝트에서 사용하는 의존성과 플러그인을 저장하고 관리하는 온라인 저장소
- 해당 저장소에서 라이브러리를 다운받아 프로젝트에 포함 가능
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
- 라이브러리 저장 위치 지정
    
    ```xml
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
    	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
    										 "http://maven.apache.org/xsd/settings-1.0.0.xsd">
    										 
    <localRepositroy>E:/devEnv/repository</localRepository>
    </settings>
    ```
    

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

**✅ 라이프사이클**

- **Default(Build)**
    - 일반적인 빌드 프로세스
    - 진행과정
        - **Validate**
            - 프로젝트가 올바른지 확인
            - 의존성과 같은 필요한 모든 정보를 사용할 수 있는지 확인
        - **Compile**
            - 프로젝트 소스코드 컴파일
            - .class 파일 생성
        - **Test**
            - 테스트코드를 기반으로 유닛 테스트를 수행
            - 스킵가능
        - **Package**
            - 실제 컴파일된 소스코드, 리소스를 jar, war 등 배포를 위한 형태로 패키징
        - **Verify**
            - 통합 테스트 결과에 대한 검사 시행
            - 품질 기준 충족 여부 확인
        - **Install**
            - 패키지를 로컬 저장소에 설치
        - **Deploy**
            - package를 원격 저장소에 release
- **Clean**
    - 빌드시 생성되었던 파일들을 삭제하는 프로세스
    - 진행과정
        - **pre-clean**
            - clean 작업 전 필요한 준비 작업 수행
        - **clean**
            - 빌드시 생성된 파일들을 삭제
        - **post-clean**
            - clean 작업 후 필요한 정리 작업 수행
- **Site**
    - 프로젝트 문서, 사이트 작성, 생성
    - 진행과정
        - **pre-site**
            - 문서 생성 전 준비 작업
        - **site**
            - 프로젝트 사이트 생성
        - **post-site**
            - 문서 생성 후 정리 작업 수행
        - **site-deploy**
            - 생성된 프로젝트 사이트 원격 서버에 배포

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

---

## **8. Maven의 장점 요약**

- **자동화된 빌드 프로세스**: 빌드, 테스트, 패키징, 배포까지 자동화하여 개발 효율을 높입니다.
- **의존성 관리의 편리성**: 라이브러리의 버전 및 의존성을 선언적으로 관리하여 충돌을 방지합니다.
- **일관성 있는 프로젝트 구조**: 표준화된 프로젝트 구조를 제공하여 유지보수가 용이합니다.
- **확장성**: 플러그인과 프로파일을 통해 다양한 환경과 요구사항에 대응할 수 있습니다.

---

## **9. Maven 사용 시 유의사항**

- **`pom.xml`의 정확한 작성**: 프로젝트의 핵심 설정이므로 신중하게 작성해야 합니다.
- **의존성 충돌 관리**: 동일한 라이브러리의 다른 버전이 포함되지 않도록 의존성을 관리해야 합니다.
- **빌드 순서 이해**: 라이프사이클과 Phase의 순서를 이해하고 적절한 명령어를 사용합니다.

## 10. Maven 프로젝트 설정 파일