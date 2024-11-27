## Stream (스트림)

- Java 8부터 도입된 기능
- 람다 표현식을 활용해 배열과 컬렉션을 함수형 스타일로 간단히 처리 가능
- 기존 for문이나 iterator 대비 코드 간결화

### **특징**

1. **데이터 소스 변경 X :** 원본 데이터가 수정되지 않는 읽기 전용
2. **일회용 :** 한 번 사용하면 닫혀서 재사용 불가
3. **지연 실행 :** 최종 연산 호출 전까지 중간 연산 수행하지 않음
    - filter, map같은 중간 연산은 정의만 함
4. **내부 반복 처리 :** 
    - 내부 반복으로 forEach() 활용, 데이터 소스 요소에 람다식 적용
        - 내부 반복 : 반복 처리를 라이브러리가 담당
            - 개발자는 로직만 제공
5. **병렬 처리 가능 :** 멀티스레드 활용으로 병렬 작업 용이
6. **기본형 스트림 제공 :**
    - IntStream, DoubleStream 등
    - .sum(), .average() 등 기본 연산 지원

### **중간 연산 (Intermediate Operations)**

- 데이터를 가공하거나 변환
1. **.filter()**
    - 요소 필터링
    - ex) .filter(x -> x > 10)
2. **.map()**
    - 요소 변환
        - 모든 요소에 반영
    - ex) .map(x -> x * 2)
3. **.sorted()**
    - 정렬
    - ex) .sorted()
4. **.distinct()**
    - 중복제거
5. **.limit()**
    - 크기 제한
    - ex) .limit(5)
6. **.peek()**
    - 중간 작업 결과 확인
    - .peek(System.out::println)

### **최종 연산 (Terminal Operations)**

- 결과를 생성하는 연산
1. **계산**
    - 스트림 요소에 대해 합계, 개수, 최소값, 최대값 등의 계산 수행
    - **주요 메서드**:
        - **.count():** 스트림 요소 개수 반환
        - **.sum():** 스트림 요소의 합계 반환
        - **.min() , .max():** 최소값 또는 최대값 반환
    - **예제**
        
        ```java
        IntStream numbers = IntStream.of(3, 5, 7, 9);
        
        long count = numbers.count(); 
        System.out.println("Count: " + count); -> Count: 4
        ```
        
2. **축약**
    - 스트림 요소를 하나의 값으로 축약.
    - **주요 메서드**:
        - **.reduce(BinaryOperator<T>):** 두 개의 요소를 하나로 결합하는 함수 반복
        - **.reduce(identity, BinaryOperator<T>):** 초기값(identity)과 결합
    - **예제**
        
        ```java
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4);
        
        int sum = numbers.reduce(0, (a, b) -> a + b);
        System.out.println("Sum: " + sum); 
        
        -> Sum: 10
        
        Stream<Integer> numbers = Stream.of(1, 2, 3, 4);
        
        int sum = numbers.reduce(10, (a, b) -> a + b);
        System.out.println(sum); 
        
        -> Sum: 20
        ```
        
3. **수집**
    - 스트림 결과를 리스트, 맵 등 컬렉션으로 변환.
    - **주요 메서드**:
        - **.collect(Collectors.toList()):** 결과를 리스트로 변환
        - **.collect(Collectors.toSet()):** 결과를 집합으로 변환
        - **.collect(Collectors.toMap()):** 결과를 맵으로 변환
    - **예제**
        
        ```java
        Stream<String> names = Stream.of("Alice", "Bob", "Charlie");
        
        List<String> list = names.collect(Collectors.toList());
        System.out.println(list); -> [Alice, Bob, Charlie]
        ```
        
4. **검사**
    - 스트림 요소 중 조건을 만족하는 값이 있는지 검사
    - **주요 메서드**:
        - **.anyMatch(Predicate):** 하나라도 조건을 만족하면 true
        - **.allMatch(Predicate):** 모든 요소가 조건을 만족하면 true
        - **.noneMatch(Predicate):** 아무 요소도 조건을 만족하지 않으면 true
    - **예제**
        
        ```java
        Stream<Integer> numbers = Stream.of(5, 10, 15);
        
        boolean hasLarge = numbers.anyMatch(x -> x > 10);
        System.out.println("Any match: " + hasLarge); 
        -> Any match: true
        ```
        
5. **반복**
    - 스트림 요소를 순차적으로 처리하며, 각 요소에 대해 작업 수행.
    - **주요 메서드**:
        - **.forEach(Consumer):** 요소를 순서대로 처리
        - **.forEachOrdered(Consumer):** 병렬 스트림에서도 순서 보장하며 처리
    - **예제**
        
        ```java
        Stream<String> names = Stream.of("Alice", "Bob", "Charlie");
        
        names.forEach(System.out::println);
        
        -> Alice
           Bob
           Charlie
        ```
        
6. **조회**
    - 조건에 맞는 첫 번째 요소 또는 아무 요소를 반환.
    - **주요 메서드**:
        - **.findFirst():** 첫 번째 요소 반환
        - **.findAny():** 병렬 스트림에서 조건에 맞는 요소 중 하나를 반환(순서는 보장되지 않음)
    - **예제**
        
        ```java
        Stream<Integer> numbers = Stream.of(3, 7, 10);
        
        Optional<Integer> first = numbers.filter(x -> x > 5).findFirst();
        first.ifPresent(System.out::println); 
        -> 출력: 7
        ```
        

### 활용 예제

```java
package stream;

import java.util.Arrays;
import java.util.List;

public class StreamEx {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "String", "API");

        list.stream().forEach(System.out::println);

        list.stream()
                .filter(s -> s.startsWith("J"))
                .forEach(System.out::println);

        list.stream()
//                .map(s -> s.toUpperCase())
                .map(String::toUpperCase)//map은 모든 요소에 적용하는 메서드이기에 가능
                .forEach(System.out::println);

        int totalLength = list.stream()
                .mapToInt(String::length)
                .sum();
        System.out.println("list의 전체 길이 : " + totalLength);
    }
}

```