### String

- 문자열을 다루기 위해 제공되는 클래스
- 불변 객체임으로 한번 생성되면 수정할 수 없다
    - String str = “hello”;
        - hello라는 객체를 생성하여 해당 주소를 str에 저장
    - str = “hi”
        - hi라는 객체를 생성하여 해당 주소를 str에 저장
            - 기존 hello객체는 변경되지 않고 그대로 남아 있지만 더이상 참조되지 않기 때문에 자바의 Garbage Collector에 의해 메모리에서 제거됨

### String Pool

- 메모리를 효율적으로 관리하기 위해 JVM이 제공하는 메모리 공간
- 동일한 문자열 리터럴을 사용한 경우 String Pool에 저장된 문자열을 재사용함
    
    ```java
    String str1 = "hello";
    String str2 = "hello";
    System.out.println(str1 == str2) -> true
    ```
    
- new 키워드를 사용하여 문자열을 생성하면 새로운 객체가 생성됨
    - String pool을 사용하지 않음
    
    ```java
    String str1 = "hello";
    String str2 = new String("hello");
    System.out.println(str1 == str2) -> false
    ```
    

### 문자열 변경

- String은 불변이므로 문자열 변경시 새로운 객체가 생성됨
    - 긴 문자열 변경시 많은 메모리를 사용함
    - 잦은 문자열 변경시 반복적으로 메모리를 할당하고 해제해야 하므로 성능 저하 발생
        - Garbage Collector 부담이 커짐

### **StringBuilder**

- 가변 객체이므로 메모리 할당을 줄이고 성능 향상에 도움이 됨
    - 문자열 추가, 수정, 삭제 등의 작업이 빈번할때 유용

```java
StringBuilder sb = new StringzBuilder("hello");
sb.append(" world");
System.out.println(sb.toString());
```

- 메서드
    - **insert()**
        - 특정 위치에 문자열, 문자 삽입
            
            ```java
            StringBuilder sb = new StringzBuilder("hello");
            sb.insert(6, "world");
            System.out.println(sb); -> hello world
            ```
            
    - **delete()**
        - 문자열의 특정 구간 삭제
            
            ```java
            StringBuilder sb = new StringzBuilder("hello beautiful world");
            sb.delete(6,16);
            System.out.println(sb); -> hello world
            ```
            
    - **deleteCharAt()**
        - 특정 인덱스 문자 삭제
            
            ```java
            StringBuilder sb = new StringzBuilder("hellon world");
            sb.deleteCharAt(6);
            System.out.println(sb);
            ```
            
    - **setLength()**
        - 문자열의 길이를 설정
            - 줄이면 해당 부분 삭제
            - 늘리면 null로 채워짐
                
                ```java
                StringBuilder sb = new StringBuilder("Hello world!");
                sb.setLength(5); 
                System.out.println(sb); -> Hello
                
                sb.setLength(10); 
                System.out.println(sb); -> Hello\u0000\u0000\u0000\u0000\u0000
                ```
                
    - **reverse()**
        - 문자열을 역순으로 변환
            
            ```java
            StringBuilder sb = new StringBuilder("!dlrow olleH!");
            sb.reverse(); 
            System.out.println(sb); -> Hello World!
            ```
            
    - **replace()**
        - 특정 구간의 문자열을 다른 문자열과 교환
            
            ```java
            StringBuilder sb = new StringBuilder("Hello world!");
            sb.replace(6, 11, "Java");
            System.out.println(sb); -> Hello Java!
            ```
            
    - **setCharAt()**
        - 특정 위치의 문자를 설정
            
            ```java
            StringBuilder sb = new StringBuilder("Hello world!");
            sb.setCharAt(6, 'W');
            System.out.println(sb); -> Hello World!
            ```
            
    - **length()**
        - 문자열 길이 반환
            
            ```java
            StringBuilder sb = new StringBuilder("123456");
            System.out.println(sb.length); -> 6
            ```
            
    - **toString()**
        - StringBuilder를 문자열로 반환
            
            ```java
            StringBuilder sb = new StringBuilder("Hello world!");
            System.out.println(sb.toString); -> Hello world!
            ```
            

### String 메서드

- **length()**
    - 문자열의 길이를 구하는 함수
        
        ```java
        String str = "12345"
        System.out.println(str.length()); -> 5
        ```
        
- **charAt()**
    - 문자열에서 특정 인덱스의 문자를 구하는 함수
        - 인덱스 : 배열과 같이 0부터 시작함
        
        ```java
        String str = "012345"
        System.out.println(str.charAt(3)); -> 3
        ```
        
- **substring()**
    - 문자열의 부분을 추출하는 함수
        
        ```java
        String str = "-> target <-";
        System.out.println(str.substring(3,8)); -> target
        ```
        
- **contains()**
    - 특정 문자열이 포함되어 있는지를 확인하는 함수
        
        ```java
        String str = "Java Programming";
        System.out.println(str.contains("Java")); -> true
        ```
        
- **replace()**
    - 문자열을 교체하는 함수
        
        ```java
        String str = "Before Change";
        System.out.println(str.replace("Before", "After")); -> After Change
        ```
        
- **split()**
    - 문자열을 분리하는 함수
        
        ```java
        String str = "apple,banana,orange";
        String[] fruits = str.split(",");
        for(String fruit : fruits){
        	System.out.println(fruit);
        }
        -> 
        apple
        banana
        orange
        ```
        
- **trim()**
    - 공백을 제거하는 함수
        
        ```java
        String str = "   blank   ";
        System.our.println(str.trim()); -> "blank"
        ```
        
- **equals()**
    - 문자열의 내용을 비교하는 함수
    - == 연산자와의 차이점
        - equals()함수는 문자열의 내용을 비교하지만 ==연산자는 객체 참조를 비교한다
            - 즉 저장된 주소를 비교
    
    ```java
    String str1 = "match";
    String str2 = "miss match";
    String str3 = new String("match");
    System.out.println(str1.equals(str2); -> false
    System.out.println(str1.equals(str3); -> true
    System.out.println(str1 == str2); -> false
    System.out.println(str1 == str3); -> false
    ```
    
- **equalsIgnoreCase()**
    - 대소문자를 무시하고 문자열을 비교하는 함수
    
    ```java
    String str1 = "HELLO";
    String str2 = "hello";
    System.out.println(str1.equalsIgnoreCase(str2)); -> true
    ```
    
- **compareTo()**
    - 사전순으로 비교하여 정수 값을 반환
    - 0 : 문자열이 동일
    - 양수 : a > b
    - 음수 : a < b
    
    ```java
    String A = "a";
    String B = "b";
    String C = "c";
    System.out.println(B.compareTo(A)); -> 1
    System.out.println(B.compareTo(B)); -> 0
    System.out.println(B.compareTo(C)); -> -1
    ```
    
- **concat()**
    - 문자열을 연결하는 기능
    - 문자열을 변경하지 않고 새로운 객체를 생성
    
    ```java
    String str1 = "Hello";
    String str2 = " World";
    String result = str1.concat(str2);
    System.out.println(result); -> Hello World
    ```
    
- **toUpperCase()**
    - 모든 문자열을 대문자로 변환
    - 문자열을 변경하지 않고 새로운 객체를 생성
    
    ```java
    String str = "hello world";
    String result = str.toUpperCase();
    System.out.println(str); -> hello world
    System.out.println(result); -> HELLO WORLD
    ```
    
- **toLowerCase()**
    - 모든 문자열을 소문자로 변환
    - 문자열을 변경하지 않고 새로운 객체를 생성
    
    ```java
    String str = "HELLO WORLD";
    String result = str.toLowerCase();
    System.out.println(str); -> HELLO WORLD
    System.out.println(result); -> hello world
    ```
    
- indexOf()
    - 문자, 문자열의 첫 번째 인덱스 반환
        
        ```java
        String str = "Find me";
        String target = "me";
        int index = str.indexOf(target);
        System.out.println(index) -> 5
        ```
        
- toCharArray()
    - 문자열을 문자 배열로 반환
        
        ```java
        String str = "hello";
        char[] charArray = str.tocharArray();
        for(char c : charArray){
        	System.out.println(c + " "); ->h e l l o
        }
        ```