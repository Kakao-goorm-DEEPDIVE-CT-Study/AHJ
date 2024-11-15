### 객체지향

- 프로그램을 여러 객체(Object)로 나누어 구성하고 객체간 상호작용을 활용하는 방식

### 클래스

- 객체를 생성하기 위한 설계도, 틀
    - 속성 : 클래스가 가진 변수(필드)
    - 메서드 : 클래스가 수행할 동작
- 객체
    - 클래스를 사용하여 생성된 실제 인스턴스
    - 클래스에서 정의한 속성, 메서드 사용
- 생성자
    - 객체가 생성될때 호출되는 메서드
    - 초기화 작업을 수행
    - 클래스 이름과 동일한 이름을 가지며 반환 타입이 없음
    - 기본 생성자
        - 매개변수가 없는 생성자로 프로그래머가 생성자를 정의하지 않으면 컴파일러가 자동으로 추가
- 예제
    
    ```java
    class Book {
     private String title;
     private String author;
     private int price;
     
     public Book(String title, String author, int price){
    	 this.title = title;
    	 this.author = author;
    	 this.price = price;
     }
    public class Main{
    	public static void main(String[] args){
    		Book book = new Book("책이름","작가",10000);
    	}
    }
    ```