## JDBC

- Java에서 데이터베이스와 상호 작용하기 위해 사용되는 API
- 데이터베이스 연결, 쿼리, 결과등 작업 수행
- 다양한 데이터베이스에 대한 공통된 인터페이스 제공

**✅ 구성요소**

- JDBC Driver
    - Java 애플리케이션과 데이터베이스간 통신 담당
- Connection
    - 데이터베이스와의 연결 관리
    - SQL 명령 실행에 필요한 객체를 생성
- Statement
    - SQL 쿼리를 실행하는 객체
    - Statement
        - 단순 쿼리 실행
        - SQL문을 수행하는 과정에서 매번 컴파일을 하기 때문에 성능 저하
        - 실행되는 SQL문을 확인 가능
    - PreparedStatement
        - 쿼리에서 매개변수를 바인딩해 실행
        - 컴파일이 미리 되어 있기에 Statement에 비해 성능이 좋음
        - 특수문자를 파싱하기에 SQL injection공격 방어 가능
        - ? 부분에 변화를 주는 형태로 쿼리문을 수행하기에 실행되는 SQL을 파악하기 어려움
    - CallableStatement
        - 저장된 프로시저 호출
- ResultSet
    - SQL 쿼리의 결과를 저장하는 객체
    - 행 단위로 데이터를 읽음
- SQLException
    - 데이터베이스 작업 중 발생하는 예외처리

**✅ JDBC 동작 과정**

- 드라이버 로드
    - JDBC 드라이버 로드
    - Class.forName(”com.mysql.cj.jdbc.Driver”);
- 데이터베이스 연결
    - 데이터베이스 URL, 사용자 이름, 비밀번호를 통해 연결 생성
    - Connection conn = DriverManager.getConnection(url, user, password);
- SQL 명령 실행
    - Statment, PreparedStatement 객체를 생성하여 쿼리 실행 준비
    - Statement stmt = conn.createStatement();
    - PreparedStatement pstmt = conn.prepareStatement(sql);
- 결과 처리
    - SQL명령을 실행하여 데이터를 읽어옴
    - ResultSet rs = stmt.executeQuery(sql);
- 리소스 정리
    - 데이터베이스 연결, 자원 해제
    - conn.close(), stmt.close(), rs.close()
- 예제
    
    ```java
    import java.sql.*;
    
    public class JdbcExample {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/testdb";
            String user = "root";
            String password = "password";
    
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
    
            try {
                // 1. 드라이버 로드
                Class.forName("com.mysql.cj.jdbc.Driver");
    
                // 2. 데이터베이스 연결
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Database connected!");
    
                // 3. SQL 명령 실행
                String sql = "SELECT * FROM users";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
    
                //pstmt = conn.prepareStatement(sql);
    						//pstmt.setInt(1, 123); 
                rs = pstmt.executeQuery(sql);
    
                // 4. 결과 처리
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
                }
    
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    // 5. 리소스 정리
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    ```