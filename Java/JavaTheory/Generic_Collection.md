### 제네릭스

- 타입 파라미터를 이용하여 클래스, 메서드를 선언하는 기법
    - 타입 파라미터
        - E : Element
        - T : Type
        - V : Value
        - K : Key
- 클래스, 인터페이스 이름 뒤에 다이아몬드 연산자를 붙임
    - 기본 데이터 타입은 올 수 없음
    - Wrapper 클래스 사용
- 모든 종류의 타입을 다룰 수 있음
- 예제
    
    ```java
    public class ClassName<T> {
    }
    
    public interface InterfaceName<T> {
    }
    ```
    

### 장점

- 컴파일 시 강한 타입 체크 가능
    - 클래스, 인터페이스 설계 시 구체적인 타입을 명시 X
        - 실제 클래스 구현시 구체적인 타입 지정
    - 예제
        
        ```java
        List<String> list = new ArrayList<String>();
        ```
        
- 강제 타입 변환 제거 가능
    - 프로그램 성능 향상
    - 예제
        
        ```java
        List list = new ArrayList();
        list.add(“hello”);
        String str = (String)list.get(0);
        
        제너릭 사용
        List<String> list = new ArrayList<String>();
        list.add(“hello”);
        String str = list.get(0);
        
        ```
        

### 멀티 타입 파라미터

- 2개 이상의 타입 파라미터 사용
    - 각 타입 파라미터는 쉼표로 구분
- 예제
    - TV
        
        ```java
        package multiGeneric;
        
        public class TV {
        	@Override
        	public String toString() {
        		return "TV";
        	}
        }
        
        ```
        
    - Car
        
        ```java
        package multiGeneric;
        
        public class Car {
        
        	@Override
        	public String toString() {
        		return "Car";
        	}
        	
        }
        
        ```
        
    - Product
        
        ```java
        package multiGeneric;
        
        public class Product<T, M> {
        	private T kind;
        	private M model;
        	
        	public T getKind() {
        		return kind;
        	}
        	public void setKind(T kind) {
        		this.kind = kind;
        	}
        	public M getModel() {
        		return model;
        	}
        	public void setModel(M model) {
        		this.model = model;
        	}
        	
        	
        }
        
        ```
        
    - Main
        
        ```java
        package multiGeneric;
        
        public class productMain {
        	public static void main(String[] args) {
        		Product<TV, String> prd1 = new Product<TV, String>();
        		prd1.setKind(new TV());
        		prd1.setModel("스마트TV");
        		
        		TV tv = prd1.getKind();
        		String tvModel = prd1.getModel();
        		System.out.println(tv + " : " + tvModel);
        		
        		Product<Car, String> prd2 = new Product<Car, String>();
        		prd2.setKind(new Car());
        		prd2.setModel("스포츠카");
        		
        		Car car = prd2.getKind();
        		String carModel = prd2.getModel();
        		System.out.println(car + " : " + carModel);
        	}
        }
        
        ```
        

### Collection Framework

- 컬렉션은 데이터를 모아 관리하는 자료구조
    - 자바의 컬렉션 프레임워크는 이러한 자료구조를 표준화한 라이브러리
        - java.util
- 배열과 달리 동적으로 크기를 조정할 수 있으며, 데이터 추가, 삭제, 검색 등의 작업을 더 효율적으로 수행 가능
- 인터페이스를 통해 정형화된 방법으로 클래스 이용

### **컬렉션의 주요 인터페이스**

### **List**

- 데이터의 순서가 중요하거나 중복된 데이터를 허용할때 사용
    - 데이터의 순서 유지
    - 중복을 허용
- 장점 : 인덱스를 사용해 접근할 수 있습니다.
- 단점 : 큰 데이터 집합에서 검색, 정렬시 성능 저하 발생
- ex) ArrayList, LinkedList
- 예제
    - Product
        
        ```java
        package ArrayListEx;
        
        public class Product {
        	private int prdNum;
        	private String prdName;
        	private int prdPrice;	
        	
        	public Product(int prdNum, String prdName, int prdPrice) {
        		this.prdNum = prdNum;
        		this.prdName = prdName;
        		this.prdPrice = prdPrice;
        	}
        	
        	public int getPrdNum() {
        		return prdNum;
        	}
        	public void setPrdNum(int prdNum) {
        		this.prdNum = prdNum;
        	}
        	public String getPrdName() {
        		return prdName;
        	}
        	public void setPrdName(String prdName) {
        		this.prdName = prdName;
        	}
        	public int getPrdPrice() {
        		return prdPrice;
        	}
        	public void setPrdPrice(int prdPrice) {
        		this.prdPrice = prdPrice;
        	}
        
        	@Override
        	public String toString() {
        		return "Product [prdNum=" + prdNum + ", prdName=" + prdName + ", prdPrice=" + prdPrice + "]";
        	}
        	
        	
        }
        
        ```
        
    - ProductMain
        
        ```java
        package ArrayListEx;
        
        import java.util.ArrayList;
        import java.util.Iterator;
        
        public class ProductMain {
        	public static void main(String[] args) {
        		ArrayList<Product> prdList = new ArrayList<Product>();
        		
        		Product p1 = new Product(10001,"모니터",200000);
        		Product p2 = new Product(10002,"키보드",30000);
        		Product p3 = new Product(10003,"마우스",10000);
        	
        		prdList.add(p1);
        		prdList.add(p2);
        		prdList.add(p3);
        		
        		for(int i = 0; i < prdList.size(); i++) {
        			System.out.println(prdList.get(i));
        		}
        		
        		for(Product prd : prdList) {
        			System.out.println(prd);
        		}
        		
        		Iterator<Product> it = prdList.iterator();
        		while(it.hasNext()) {
        			System.out.println(it.next());
        		}
        		
        	}
        }
        
        ```
        

### **Set**

- 중복된 데이터를 허용하지 않는 집합이 필요할 때 사용
    - 중복을 허용하지 않음
    - 요소의 순서를 보장하지 않으며, 유일한 값만 저장
- 장점 : 데이터 중복 방지, HashSet의 경우 데이터 추가,삭제,검색이 빠름
- 단점 : TreeSet과 같이 정렬을 지원하는 경우 데이터 추가,삭제에 비용이 듬
- ex) HashSet, TreeSet
- 예제
    
    ```java
    import java.util.*;
    
    public class HashSetExample {
        public static void main(String[] args) {
            Set<String> set = new HashSet<>();
            set.add("Apple");
            set.add("Banana");
            set.add("Apple"); // 중복된 값 추가 시 무시됨
            System.out.println("HashSet: " + set);
        }
    }
    
    ```
    

### **Map**

- 데이터를 키,값 으로 관리할 때, 빠른 검색이 필요할때 사용
    - 키-값 쌍(Key-Value Pair)으로 데이터를 저장
    - 키는 중복이 허용되지 않지만, 값은 중복될 수 있음
- 장점 : 키를 통해 빠르게 데이터 접근 가능
- 단점 : 키의 중복 허용 X, 중복된 데이터를 저장하기 위해서는 추가 로직 필요
- ex) HashMap, TreeMap
- 예제
    
    ```java
    package MapEx;
    
    import java.util.*;
    
    public class HashMapEx {
    
    	public static void main(String[] args) {
    		Map<String, String> map = new HashMap<String, String>();
    		
    		map.put("test001", "a1111");
    		map.put("test002", "a2222");
    		map.put("test003", "a3333");
    		
    		for(String k : map.keySet()) {
    			System.out.println("id : " + k + ", password : " + map.get(k));
    		}
    	}
    
    }
    
    ```
    

### **Queue**

- 작업을 순차적으로 처리하거나 특정 조건에 따라 데이터를 처리해야 할 때 사용
- FIFO(First In First Out) 구조로 데이터를 처리
- Priority Queue는 우선순위에 따라 데이터를 처리
- 장점 : 데이터를 순차적으로 처리할 때 유용
- 단점: 무작위 접근 불가
- 예제
    
    ```java
    import java.util.LinkedList;
    import java.util.Queue;
    
    public class QueueExample {
        public static void main(String[] args) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(10);
            queue.offer(20);
            queue.offer(30);
    
            System.out.println("Removed element: " + queue.poll());
            System.out.println("Removed element: " + queue.poll());
            System.out.println("Removed element: " + queue.poll());
    
        }
    }
    -> Removed element: 10
    	 Removed element: 20
    	 Removed element: 30
    ```
    

### **Deque**

- 양방향 큐(Double-Ended Queue)구조.
- 데이터를 양쪽 끝에서 추가하거나 제거할 수 있음
    - FIFO 와 LIFO 둘 다의 특성을 지원
- 장점
    - 양쪽 끝에서 데이터 추가 및 제거가 가능
    - 스택과 큐의 기능을 모두 구현 가능
- 단점
    - Array기반 덱은 내부적으로 배열을 사용하기 때문에 매우 큰 데이터셋을 다룰 때 메모리 재할당으로 인한 성능 저하가 발생 할 수 있음
    - LinkedList기반의 덱은 노드 기반 데이터 구조의 오버헤드가 있을 수 있음
- 예제
    
    ```java
    import java.util.ArrayDeque;
    import java.util.Deque;
    
    public class DequeExample {
        public static void main(String[] args) {
            Deque<String> deque = new ArrayDeque<>();
            //last에 넣음
            deque.addLast("First");
            deque.addLast("Second");
            deque.addLast("Third");
    
            System.out.println("Removed element from front: " + deque.removeFirst());
            System.out.println("Removed element from front: " + deque.removeFirst());
            System.out.println("Removed element from front: " + deque.removeFirst());
    				//first에 넣음
            deque.addFirst("One");
            deque.addFirst("Two");
            deque.addFirst("Three");
    
            System.out.println("Removed element from back: " + deque.removeLast());
            System.out.println("Removed element from back: " + deque.removeLast());
            System.out.println("Removed element from back: " + deque.removeLast());
        }
    }
    -> Removed element from front: First
    	 Removed element from front: Second
    	 Removed element from front: Third
    	 
    	 Removed element from back: One
    	 Removed element from back: Two
    	 Removed element from back: Three
    
    ```
    

### **Stack**

- LIFO(First In First Out) 구조의 데이터 집합
- 장점 : 후입선출 방식 관리, backtracking에 유용
    - 가장 최근 순으로 추출할 수 있기에 back tracking이 가능
- 단점 : 무작위 접근 불가
    - 가장 최근에 들어간 데이터만 추출 가능
- 예제
    
    ```java
    import java.util.Stack;
    
    public class StackExample {
        public static void main(String[] args) {
            Stack<String> stack = new Stack<>();
            stack.push("First");
            stack.push("Second");
            stack.push("Third");
    
    		    System.out.println("Popped element: " + stack.pop());
            System.out.println("Popped element: " + stack.pop());
            System.out.println("Popped element: " + stack.pop());
    
        }
    }
    -> Popped element: Third
    	 Popped element: Second
    	 Popped element: First
    ```