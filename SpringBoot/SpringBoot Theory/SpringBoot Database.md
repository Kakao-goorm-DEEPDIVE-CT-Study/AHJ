## **1. H2 데이터베이스**

- Java 기반 경량 인메모리 DB
- **특징 :** SQL 콘솔을 통해 데이터 조회/수정 가능
- **사용사례 :** 사용테스트 및 개발 환경에서 주로 사용

## **2. 순수 JDBC**

- 자바가 기본으로 제공하는 데이터베이스 접근 방식
    - Java 기본 제공 API 사용
    - java.sql 패키지
- **특징 :**
    - SQL 직접 작성, 커넥션 관리, 예외 처리 등 개발자가 직접 관리
    - 코드가 길고 복잡해질 수 있음
    - 단순 작업에 적합

## **3. Spring JDBC Template**

- Spring이 지원하는 JDBC
- **특징 :**
    - 커넥션 관리, 쿼리 실행, 예외 처리 등 자동화, 간소화
    - SQL은 여전히 직접 작성
    - 코드 간결, 생산성 향상

## **4. MyBatis**
- Java 기반의 데이터베이스 접근 프레임워크
- SQL 중심 개발 + 객체 매핑을 지원하는 방식
    - SQL을 명시적으로 작성하지만 데이터 매핑 작업을 자동화함
    - JDBC와 비슷한 SQL 중심 접근 방식 + 동적 SQL 생성, 자동 객체 매핑
        - 복잡도를 낮춤

**✅ 주요특징**

- SQL 매핑 지원
    - SQL을 XML파일 또는 어노테이션으로 관리
    - SQL 작성의 자유도를 제공하면서 객체,테이블간 매핑을 간단하게 처리
- 객체 매핑
    - SQL 결과를 Java 객체로 매핑
    - Java 객체를 SQL 파라미터로 사용
- 다양한 DB 지원
    - JDBC 기반으로 동작하기 때문에 다양한 데이터베이스와 호환가능
- 동적 쿼리 가능
    - **\<if\>** : if문과 동일
        
        ```xml
        <if test="title != null">
            title = #{title}
        </if>
        ```
        
    - **\<choose\>, \<when\>, \<otherwise\>** : else if, switch 문과 동일
        
        ```xml
        <choose>
            <when test="title != null">
                title = #{title}
            </when>
            <when test="regUser != null">
                reg_user = #{regUser}
            </when>
            <otherwise>
                reg_date >= CURRENT_DATE
            </otherwise>
        </choose>
        ```
        
    - **\<where\>** : SQL의 where절
        
        ```xml
        <where>
            <if test="title != null">
                title = #{title}
            </if>
            <if test="regUser != null">
                AND reg_user = #{regUser}
            </if>
        </where>
        ```
        
    - **\<foreach\>**
        
        ```xml
        <foreach collection="boards" item="board" separator=",">
            (#{board.title}, #{board.contents})
        </foreach>
        
        ```
        

**✅ 매핑 구성요소**

- **parameterType**
    - SQL 문장에 전달될 파라미터의 데이터 타입을 지정
    - 주로 \<select\>, \<insert\>, \<update\>, \<delete\> 태그에서 사용
    - 예제
        
        ```xml
        <select id="getPostById" parameterType="long" resultType="Board">
            SELECT * FROM board WHERE seq = #{seq}
        </select>
        ```
        
- **resultType**
    - SQL 실행 결과의 반환 데이터 타입을 지정
    - 주로 \<select\>태그에서 사용
    - 예제
        - SQL 결과가 Board 객체에 매핑됨
        
        ```xml
        <select id="getAllPosts" resultType="Board">
            SELECT * FROM board
        </select>
        ```
        
- resultMap
    - 커스텀 매핑 규칙을 정의
        - 복잡한 매핑방식 처리 가능
    - 주로 \<select\>에서 사용
    - 예제
        
        ```xml
        <resultMap id="BoardResultMap" type="Board">
            <id property="seq" column="seq" />
            <result property="title" column="title" />
            <result property="contents" column="contents" />
            <result property="regUser" column="reg_user" />
            <result property="regDate" column="reg_date" />
        </resultMap>
        
        <select id="getAllPosts" resultMap="BoardResultMap">
            SELECT * FROM board
        </select>
        ```
        

**✅ xml 비교연산자**

- < : &lt;
- <= : &lt;=
- > : &gt;
- >= : &gt;=

**✅동작 과정**

- 설정 파일 로드
    - application.properties에서 데이터베이스 연결 및 매퍼 설정 로드
        
        ```yaml
        spring.application.name=mybatis
        server.port=8088
        
        mybatis.mapper-locations=classpath:/mappers/*.xml
        mybatis.configuration.map-underscore-to-camel-case=true
        #h2
        spring.sql.init.schema-locations=classpath:/db/h2/schema.sql
        spring.sql.init.data-locations=classpath:/db/h2/data.sql
        # spring - database
        spring.datasource.driverClassName=org.h2.Driver
        # In-Memory mode
        spring.datasource.url=jdbc:h2:mem:topolo
        spring.datasource.username=sa
        spring.datasource.password=
        ```
        
- SQL 매핑
    - XML, 어노테이션을 통해 SQL을 정의하고 Java 객체를 매핑
    - BoardMapper.xml
        
        ```xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="io.goorm.mybatis.mapper.BoardMapper">
            <!--  SELECT  -->
            <select id="selectAll" resultType="io.goorm.mybatis.board.model.Board"> SELECT * FROM board ORDER BY seq desc </select>
            <select id="selectById" parameterType="long" resultType="io.goorm.mybatis.board.model.Board"> SELECT * FROM board WHERE seq = #{seq} </select>
            <!--  INSERT  -->
            <insert id="insert" parameterType="io.goorm.mybatis.board.model.Board"> INSERT INTO board (title, contents, reg_user) VALUES (#{board.title}, #{board.contents}, #{board.regUser}) </insert>
            <!--  UPDATE  -->
            <update id="update" parameterType="io.goorm.mybatis.board.model.Board"> UPDATE board SET title = #{board.title}, contents = #{board.contents} WHERE seq = #{board.seq} </update>
            <!--  DELETE  -->
            <delete id="delete" parameterType="long"> DELETE FROM board WHERE seq = #{seq} </delete>
        </mapper>
        ```
        
- SQL 실행
    - Mapper 인터페이스를 통해 SQL 실행
    - BoardMapper.java
        
        ```java
        package io.goorm.mybatis.mapper;
        
        import io.goorm.mybatis.board.model.Board;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Param;
        
        import java.util.List;
        
        @Mapper
        public interface BoardMapper {
            List<Board> selectAll();
        
            Board selectById(@Param("seq") long seq);
        
            int insert(@Param("board") Board board);
        
            int update(@Param("board") Board board);
        
            int delete(@Param("seq") long seq);
        }
        
        ```
        
- 결과 반환

✅ **사용 사례**

- 완전한 ORM은 아니지만 객체 매핑 필요할 때
- 복잡한 쿼리나 조건부 동적SQL 필요 시
- JPA의 완전한 ORM기능이 불필요할때
- 레거시 데이터베이스와 통합이 필요할 때

## **5. JPA (Java Persistence API)**

- ORM 표준 인터페이스
- **특징 :**
    - 객체, RDB간 매핑 자동화
        - Entity 클래스로 객체-테이블 매핑
    - SQL 작성 대신 자바 객체를 사용해 DB 작업 수행
- **사용사례 :** 객체지향 방식으로 DB를 다루고 싶을 때 적합
    - SQL 작성 없이 CRUD 처리 가능

## **6. Spring Data JPA**

- 스프링에서 제공**하는** JPA 확장 라이브러리
- **특징 :**
    - Repository 인터페이스로 CRUD 기능 자동 제공
        - 필요한 메서드가 정의되어 있음
            - 구현없이 데이터베이스와 상호작용 가능
    - 빠르고 쉽게 데이터 작업 가능
    - 메서드 이름 기반으로 쿼리 자동 생성
        
        
        | 키워드 | 동작 |
        | --- | --- |
        | **And** | 두 조건을 AND로 결합 |
        | **Or** | 두 조건을 OR로 결합 |
        | **Is, Equals** | 속성이 특정 값과 같은지 확인 |
        | **Between** | 값이 두 값 사이에 있는지 확인 |
        | **LessThan** | 값이 특정 값보다 작은지 확인 |
        | **GreaterThan** | 값이 특정 값보다 큰지 확인 |
        | **Like** | 문자열 검색 (와일드카드 % 사용) |
        | **Not** | 조건의 반대 값 확인 |
        | **In** | 컬렉션 안에 값이 포함되는지 확인 |
        | **StartingWith** | 문자열이 특정 값으로 시작하는지 확인 |
        | **EndingWith** | 문자열이 특정 값으로 끝나는지 확인 |
        | **Containing** | 문자열이 특정 값을 포함하는지 확인 |
        | **OrderBy** | 결과를 특정 속성으로 정렬 |
        
        ```java
        public interface UserRepository extends JpaRepository<User, Long> {
            List<User> findByNameAndAge(String name, int age);
            List<User> findByAgeGreaterThan(int age);
            List<User> findByNameContaining(String keyword);
        }
        
        findByNameAndAge → SELECT * FROM user WHERE name = ? AND age = ?
        findByAgeGreaterThan → SELECT * FROM user WHERE age > ?
        findByNameContaining → SELECT * FROM user WHERE name LIKE %?%
        ```
        

## **7. ORM, JPA, Hibernate 개요**

1. **ORM (Object-Relational Mapping)**
    - 객체와 DB 테이블 간 데이터를 자동으로 매핑
    - SQL 작성 없이 자바 객체로 DB 작업 가능
    - **장점 :**
        - 객체지향적 DB 작업 가능
            - 코드 가독성 및 유지보수성 향상
        - SQL 작성 감소로 개발 생산성 향상
        - DB 종속성 낮춤, 코드 재사용성 높임
    - **단점**
        - 복잡한 쿼리는 ORM만으로 해결 어려움
        - 학습 곡선 존재
            - 처음 사용시 어려움
    - **대표 기술**
        - JPA, Hibernate, MyBatis 등
2. **JPA (Java Persistence API)**
    - ORM 기술을 사용하는데 필요한 표준 인터페이스
    - 주요 개념
        - **Entity**: 테이블과 매핑되는 자바 객체
        - **Persistence Context**: 엔티티 관리 환경
        - **Entity Manager**: 엔티티 관리 인터페이스
        - **JPQL**: 객체 중심의 쿼리 언어
    - **장점**
        - 객체 중심 DB 작업으로 코드와 DB 간 일관성 유지
        - DB 종속성 낮춰 이식성 높임
        - 다양한 JPA구현체 지원
    - **단점**
        - JPA만으로 복잡한 쿼리가 불가능하기에 Native SQL 필요
        - 성능 최적화 시 JPA의 추상화가 복잡하게 작용
3. **Hibernate**
    - 가장 많이 사용하는 JPA 구현체
    - JPA 표준 API 구현 + 추가 기능 제공
    - 주요 기능
        - **1차 캐시 :** Persistence Context에서 자동 제공
        - **2차 캐시**: 추가 설정 가능
        - **지연 로딩 (Lazy Loading)**: 필요한 데이터만 로드
        - **자동 생성 쿼리**: 객체 변경 사항에 따라 SQL 자동 생성, 실행
    - **장점**
        - 다양한 DB 작업 지원
        - 커뮤니티와 지원 활발
    - **단점**
        - Hibernate에 의존적 기능 사용 시 다른 구현체로 전환 어려움
        - 깊은 학습 필요

## **활용 예제**

**Spring JDBC Template**

```java
String sql = "SELECT * FROM users WHERE id = ?";
User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
```

**MyBatis**

```xml
<select id="getUserById" parameterType="int" resultType="User">
    SELECT * FROM users WHERE id = #{id}
</select>
```

**JPA**

```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
```

**Spring Data JPA**

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
```

**Hibernate**

```java
Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

// SELECT * FROM users WHERE id = ?
User user = session.get(User.class, id);

// UPDATE users SET name = ?, age = ? WHERE id = ?
user.setName("Updated Name");
user.setAge(30);
session.update(user);

// DELETE FROM users WHERE id = ?
session.delete(user);

// INSERT INTO users (name, age) VALUES (?, ?)
User newUser = new User();
newUser.setName("Alice");
newUser.setAge(25);
session.save(newUser);

tx.commit();
session.close();
```