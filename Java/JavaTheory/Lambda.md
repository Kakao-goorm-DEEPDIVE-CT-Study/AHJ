### 람다 표현식

- 간결한 함수 표현 방식
- 코드가 매우 간결해짐
- 컬렉션 요소를 필터링, 매핑하여 원하는 결과를 얻을 수 있음
- 익명 함수를 생성하기 위한 방식
    - 기존의 익명 클래스를 대체하여 더 간결한 코드를 작성할 수 있음
- 함수형 인터페이스와 함께 사용됩니다.

### 람다 표현식의 주요 특징

- 간결한 코드: 익명 클래스를 사용할 필요 없이, 짧고 간결하게 작성 가능.
- 함수형 프로그래밍 지원: 자바에서도 함수형 스타일로 코드를 작성할 수 있게 도와줌.
- 지연 실행(Lazy Evaluation): 필요한 시점에 실행되므로 성능 최적화에 유리함.

### **기본 문법**

- (타입 매개변수) -> { 실행 코드 }
    - 매개변수를 이용해 {실행코드}를 실행
    - 매개변수 타입을 일반적으로 명시하지 않음
        - 매개 변수 타입은 런타임 시에 대입되는 값에 따라 자동인식
            
            ```java
            (x,y) -> {return x + y;}
            ```
            
    - 매개변수가 하나라면 괄호 생략 가능
        - 매개변수가 없다면 빈 괄호 필수
            
            ```java
            () -> System.out.println("hello");
            ```
            
    - 실행문이 하나라면 중괄호 생략 가능
        - 실행 코드가 return문 뿐이라면 return 생략 가능
            
            ```java
            (x,y) -> {return x + y;}
            =>
            (x,y) -> x + y;
            ```
            

### **예제**

```java
(int a, int b) -> a + b
```

### **함수형 인터페이스**

- 하나의 추상 메서드만 가지는 인터페이스
    - @FunctionalInterface 사용
        - 메서드가 2개 이상 생성된 경우 오류 발생
- 람다식 == 매개 변수를 가진 코드 블록
    - 메서드 선언과 비슷
    - 자바는 메서드 단독 선언 불가능 → 클래스에서 선언해야 함
    
    ⇒ 람다식은 메서드를 선언하는것이 아닌 메서드를 가진 객체를 생성하는 것
    
- 람다식은 인터페이스의 익명 구현 객체를 생성

### **함수형 인터페이스 예제**

- 매개 변수와 리턴값이 없는 람다식
    - MyFnInterface1
        
        ```java
        package LamvdaEx;
        
        @FunctionalInterface
        public interface MyFnInterface1{
        	public void method()
        }
        
        ```
        
    - MyFnInterfaceEx1
        
        ```java
        package LamvdaEx;
        
        public class MyFnInterfaceEx1{
        
        	public static void main(String[] args) {
        		MyFnInterface1 fi;
        		//람다식은 익명 객체를 반환하기에 fi에 익명 객체를 넣은 후 fi.method();처럼수행
        		
        		fi = () -> {
        			String str = "method call01";
        			System.out.println(str);
        		};
        		fi.method(); -> method call01
        		
        		
        		fi = () -> {
        			System.out.println("method call02");
        		};
        		fi.method(); -> method call02
        		
        	}
        
        }
        
        ```
        
- 매개 변수가 있는 람다식
    - MyFnInterface2
        
        ```java
        package LamvdaEx;
        
        @FunctionalInterface
        public interface MyFnInterface2{
        	public void method(int x, int y);
        }
        
        ```
        
    - MyFnInterfaceEx2
        
        ```java
        package LamvdaEx;
        
        public class MyFnInterfaceEx2{
        
        	public static void main(String[] args) {
        		MyFnInterface2 fi;
        		//람다식은 익명 객체를 반환하기에 fi에 익명 객체를 넣은 후 fi.method();처럼수행
        		
        		fi = (x, y) -> {
        			System.out.println(x + y);
        		};
        		fi.method(2,5) -> 7		
        }
        
        ```
        
- 리턴값이 있는 람다식
    - MyFnInterface3
        
        ```java
        package LamvdaEx;
        
        @FunctionalInterface
        public interface MyFnInterface3{
        	public int method();
        }
        
        ```
        
    - MyFnInterfaceEx3
        
        ```java
        package LamvdaEx;
        
        public class MyFnInterfaceEx3{
        
        	public static void main(String[] args) {
        		MyFnInterface2 fi;
        		//람다식은 익명 객체를 반환하기에 fi에 익명 객체를 넣은 후 fi.method();처럼수행
        		
        		fi = () -> {
        			return 2 + 2;
        		};
        		System.out.println(fi.method());		
        		
        	}
        }
        
        ```
        
- 리턴값 & 매개변수
    - MyFnInterface4
        
        ```java
        package LamvdaEx;
        
        @FunctionalInterface
        public interface MyFnInterface4{
        	public int method(int x, int y);
        }
        
        ```
        
    - MyFnInterfaceEx4
        
        ```java
        package LamvdaEx;
        
        public class MyFnInterfaceEx4{
        
        	public static void main(String[] args) {
        		MyFnInterface2 fi;
        		//람다식은 익명 객체를 반환하기에 fi에 익명 객체를 넣은 후 fi.method();처럼수행
        		
        		fi = (x, y) -> {
        			return x + y;
        		};
        		System.out.println(fi.method(2, 5));
        		
        		
        		fi = (x, y) -> x + y;
        		System.out.println(fi.method(10, 20));
        		
        		
        		fi = (x,y) -> sum(x,y);
        		System.out.println(fi.method(11, 12));
        
        		
        	}
        	public static int sum(int x, int y) {
        		return x + y;
        	}
        }
        
        ```