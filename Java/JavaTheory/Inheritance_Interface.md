### 상속

- 부모 클래스의 기능을 재사용하고 자식클래스에서 기능을 확장하기 위함
    - 공통된 기능을 자식 클래스가 재사용할 수 있게 하는 역할
- extends 키워드를 사용하여 상속
- 단일 상속만 가능
    - 1개의 class만 상속 가능
- 예제
    
    ```java
    class Person {
        String name;
        int age;
    
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    
        public void introduce() {
            System.out.println("안녕하세요, 제 이름은 " + name + "이고, 나이는 " + age + "살입니다.");
        }
    }
    
    class Student extends Person {
        String studentId;
        String major;
    
        public Student(String name, int age, String studentId, String major) {
            super(name, age); // 부모 클래스의 생성자 호출
            this.studentId = studentId;
            this.major = major;
        }
    
        @Override
        public void introduce() {
            super.introduce();//부모 클래스에 있는 메서드 호출
            System.out.println("학번: " + studentId + ", 전공: " + major);
        }
    }
    
    public class InheritanceExample {
        public static void main(String[] args) {
            Student student = new Student("홍길동", 20, "20240001", "컴퓨터공학");
            student.introduce();
        }
    }
    -> 안녕하세요, 제 이름은 홍길동이고, 나이는 20살입니다.
    	 학번: 20240001, 전공: 컴퓨터공학
    ```
    
- this 키워드
    - 현재 객체를 가리킴
        - 변수, 메서드 접근 가능
        - 객체에 접근하는 것이기에 부모 클래스 변수, 메서드도 접근 가능
- super 키워드
    - 부모 클래스를 가리킴
        - 부모 클래스의 변수, 메서드 접근 가능
    - 상속 받은 경우 super();를 통해 부모 클래스의 생성자를 호출해야 함

### 추상클래스

- abstract로 선언된 클래스
- body가 구현되지 않은 미완성 메서드를 포함
    - 전체적인 틀만 갖춘 상황
- 객체를 생성할 수 없음
    - new 키워드로 생성 불가
- 상속을 통해 자식 클래스에서 구현
    - 오버라이딩 사용
    - 용도에 맞게 구현
    - 구현이 강제되기에 반드시 필요한 기능을 구현하게 할 수 있음
- 용도
    - 클래스 설계 규격을 맞춰 클래스들의 필드, 메서드 이름을 통일할 목적
        - 각 클래스 특성에 맞게 규격 외 부분 추가 가능
    - 클래스 작성시 시간 절약

### 추상메서드

- 메서드 선언만 가능하고 기능 정의는 불가능
    - 구체적인 구현은 자식클래스에서 수행
- 추상메서드를 포함한 클래스는 반드시 추상 클래스로 선언되어야 함

### 인터페이스

- implements 키워드를 통해 상속받을 수 있음
- 객체 기능을 공개한 표준화 문서와 같은 것
    - 함수 선언만 가능하고 기능 정의는 불가능
    - 모든 메서드가 추상 메서드인 추상 클래스와 동일
        - == 모든 메서드를 구현해야 함
- 객체 생성 불가
- 인터페이스 끼리 상속 가능

### 인터페이스 구성

- 상수 필드
    - public static final
        - 생략해도 컴파일 과정에서 컴파일러가 붙여줌
        - 실행 전 값이 정의되어 있지 않으면 에러 발생
    - 변수와 같은 런타임시에 데이터를 저장할 수는 없지만 상수는 가능
- 추상 메서드
    - 선언만 되어 있고 구현되지 않은 메서드
    - public abstract
        - 생략해도 컴파일 과정에서 컴파일러가 붙여줌
- 디폴트 메서드
    - 실행 블록을 갖고 있는 메서드
    - default 키워드를 반드시 붙여야 함
- static 메서드
    - 실행 블록을 갖고 있는 메서드
    - 객체 없이 호출 가능한 메서드

### 인터페이스 장점

- 다중상속 가능
    - 여러개의 인터페이스를 상속받을 수 있음
- 다형성
    - 여러 클래스가 동일한 메서드를 갖게 되어 프로그래머가 일관된 방식으로 사용할 수 있음
        - 오버라이딩을 사용하여 지원
            - 함수명, 매개변수 타입,개수,순서, 반환타입이 동일해야함
            - 접근제어자는 부모 클래스의 메서드보다 좁은 범위로 변경할 수 없음
                - public → protected → default → private
            - @Override
                - 오버라이딩을 제대로 했는지 체크해줌
                    - 올바른 오버라이딩이 안된경우 에러 발생
        - ex) interface : Pay → Class : NaverPay,KakaoPay
            - 모두 pay(); 메서드를 사용
- 유연성, 확장성
    - 새로운 기능 추가시 기존 코드 수정하지 않고 새로운 클래스를 추가하여 해결 가능
        - ex) interface : Pay → Class : NaverPay,KakaoPay
            - SamsungPay추가시 class만 생성해서 구현하면 됨
- 표준화
    - 프로젝트 내 통일된 메서드 구조를 강제하는 것으로 코드의 일관성 확보
        - ex) interface : Pay → Class : NaverPay,KakaoPay
            - public int pay(int amount); 매개변수, 반환값등 구조 강제

### 예제

- MemberMain
    
    ```java
    package interfaceEx;
    
    public class MemberMain {
    
    	public static void main(String[] args) {
    		MemberDTO dto = new MemberDTO(1L, "홍길동");
    		
    		MemberController c = new MemberController();
    		c.insertMember(dto);	
    	}
    }
    ```
    
- MemberDTO(Data Transfer Object)
    - 데이터를 담아서 전달하는 용도
    
    ```java
    package interfaceEx;
    
    public class MemberDTO {
    	
    	private Long memId;
    	private String memName;
    	
    	public MemberDTO(Long memId, String memName) {
    		this.memId = memId;
    		this.memName = memName;
    	}
    
    	public Long getMemId() {
    		return memId;
    	}
    
    	public void setMemId(Long memId) {
    		this.memId = memId;
    	}
    
    	public String getMemName() {
    		return memName;
    	}
    
    	public void setMemName(String memName) {
    		this.memName = memName;
    	}
    	@Override
    	public String toString() {
    		return "MemberDTO [memId=" + memId + ", memName = " + memName + "]";
    	}
    }
    
    ```
    
- MemberController
    - 요청 처리
    
    ```java
    package interfaceEx;
    
    public class MemberController {
    	MemberDAO dao = new MemberDAO();
    	
    	public void insertMember(MemberDTO dto) {
    		dao.insertMember(dto);
    	}
    	
    	
    }
    
    ```
    
- IMemberDAO(Data Access Object)
    - DB에 접속해서 데이터를 처리하는 용도
    
    ```java
    package interfaceEx;
    
    import java.util.ArrayList;
    
    public interface IMemberDAO {
    	
    	//회원 등록
    	public void insertMember(MemberDTO dto);
    	//회원 정보 삭제
    	public void deleteMember(Long memId);
    	//회원 조회 : 전체 회원 정보 조회
    	//DB에서 전체 회원 정보 반환
    	public ArrayList<MemberDTO> getAllMember(); 
    	//회원 수정
    	public void updateMember(MemberDTO dto);
    	
    }
    
    ```
    
- MemberDAO
    
    ```java
    package interfaceEx;
    
    import java.util.ArrayList;
    
    public class MemberDAO implements IMemberDAO {
    
    	@Override
    	public void insertMember(MemberDTO dto) {
    		//컨트롤러에게 전달 받은 값을 DB에 저장했다고 가정
    		System.out.println("회원 등록 성공");
    		//dto값 출력
    		System.out.println(dto);
    	}
    
    	@Override
    	public void deleteMember(Long memId) {
    		// TODO Auto-generated method stub
    
    	}
    
    	@Override
    	public ArrayList<MemberDTO> getAllMember() {
    		// TODO Auto-generated method stub
    		return null;
    	}
    
    	@Override
    	public void updateMember(MemberDTO dto) {
    		// TODO Auto-generated method stub
    
    	}
    
    }
    
    ```
    

### 추상클래스 vs 인터페이스

- 인터페이스는 모든 기능을 선언만 하여 상속받은 클래스에서 구현할 것을 강제함
- 추상클래스는 일부 기능을 구현할 수 있기에 공통의 기능을 부여하고 싶을때 유용함

### 캡슐화

- 접근 제어자로 구현 가능
- 객체의 데이터를 외부에서 접근하지 못하게 막는 것
- public
    - 모든 클래스에서 접근 가능
- protected
    - 같은 패키지, 하위 클래스 접근 가능
- default
    - 같은 패키지에서만 가능
- private
    - 선언된 클래스 내부에서만 접근 가능

### Java Bean

- 모든 필드는 private로 설정하고 Getter/Setter로 접근
    - Getter / Setter
        - 캡슐화를 지원하는 메서드
        - 내부의 데이터를 접근하는 메서드
        - Getter
            - 객체의 필드값을 반환
            - 메서드 명 : getXXX(), isXXX()
                - ex) getName();
        - Setter
            - 객체의 필드값을 수정
            - 메서드 명 : setXXX()
                - ex) setName(String Name);
- 기본 생성자가 있어야 함
    - == 매개변수가 없어도 초기화 가능한 생성자
- Serializable 인터페이스를 구현해야 함
    - 객체를 직렬화 하여 파일, 네트워크 전송에 활용
    - 객체를 저장하거나 네트워크로 전송할때 byte stream으로 변환하여 전송
- 대부분 IDE에서 Java Bean을 자동으로 인식
- 장점
    - 여러 프로젝트에서 재사용 가능
    - 캡슐화를 통해 데이터 접근 제어, 보호 가능
    - 유연성 : 객체의 상태 조작, 전송, 저장에 유용