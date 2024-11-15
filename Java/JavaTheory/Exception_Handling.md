### 예외처리

- 예외(exception)
    - 프로그램 실행중 예기치 않은 상황이 발생한 것
        - try - catch문으로 예외 처리 가능
        - finally
            - 예외 발생 여부와 상관없이 항상 실행
    - 예제
        
        ```java
        public class ExceptionHandlingExample {
            public static void main(String[] args) {
                try {
                    int result = 10 / 0; // 예외 발생 (ArithmeticException)
                } catch (ArithmeticException e) {
                    System.out.println("0으로 나눌 수 없습니다.");
                } finally {
                    System.out.println("예외 처리 완료");
                }
            }
        }
        ```
        
- Checked Exception
    - 컴파일 시점에서 반드시 예외 처리 필요
    - 예외처리를 하지 않은 경우 컴파일 오류가 발생
    - ex) IOException, SQLException
- Unchecked Exception
    - 런타임 시점에 발생하는 예외
    - 예외처리를 하지 않아도 컴파일 오류가 발생하지 않음
        - 하지만 실행중인 프로그램이 종료될 수 있음
    - ex) NullPointerException, ArrayIndexOutOfBoundsException

### throw, throws

- throw
    - 예외를 강제로 발생시키는 것
    - 프로그래머가 의도적으로 예외를 발생시킬 수 있음
    - 예제
        
        ```java
        public class ThrowExample {
            public static void main(String[] args) {
                try {
                    checkAge(15); // 18세 미만이므로 예외 발생
                } catch (IllegalArgumentException e) {
                    System.out.println("예외 발생: " + e.getMessage());
                }
            }
        
            public static void checkAge(int age) {
                if (age < 18) {
                    throw new IllegalArgumentException("나이는 18세 이상이어야 합니다.");//IllegalArgumentException 발생시킴
                }
                System.out.println("입력된 나이: " + age);
            }
        }
        -> 나이는 18세 이상이어야 합니다.
        ```
        

### throws

- 해당 메서드 선언부에 사용하여 메서드가 예외를 던질 수 있음을 선언
- 메서드 내부에서 발생한 예외를 메서드를 호출한 곳으로 전파
- 예제
    
    ```java
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException;
    
    public class ThrowsExample {
        public static void main(String[] args) {
            try {
                readFile("nonexistent-file.txt");
            } catch (IOException e) {
                System.out.println("파일 읽기 실패: " + e.getMessage());
            }
        }
    
        public static void readFile(String filePath) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            System.out.println(reader.readLine());
            reader.close();
        }
    }
    -> 파일 읽기 실패: nonexistent-file.txt (No such file or directory)
    ```
    

### 에러

- 시스템 레벨에서 발생하는 문제
    - JVM, HW의 한계로 발생
    - 코드에서 예측, 처리 불가능
- ex) OutofMemoryError, StackOverflowError, VirtualMachineError