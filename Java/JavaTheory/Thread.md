### **쓰레드**

- 쓰레드는 프로그램 내에서 독립적으로 실행되는 흐름을 의미
    - 프로세스 == 하나의 작업 단위
    - 쓰레드 == 프로세스 내에서 실행되는 더 작은 실행 단위
- 하나의 자바 프로그램은 기본적으로 메인 쓰레드 실행
    - 추가적으로 여러 쓰레드를 만들어 동시에 여러 작업을 수행할 수 있습니다.


### 멀티 프로세스

- 여러 프로그램들을 실행하고 처리하는 것
    - ex) 인터넷, 노션, IDE등 동시에 여러 프로그램 실행

### **싱글 쓰레드**

- 한 번에 한 작업만 수행
    - ex) 사용자가 파일을 다운로드하는 동안 다른 작업을 할 수 없고 다운로드가 끝날 때까지 기다려야 함

### **멀티 쓰레드**

- 여러 개의 쓰레드를 생성하여 동시에 여러 작업을 수행
    - ex) 백그라운드에서 파일을 다운로드하면서 동시에 사용자가 웹 페이지를 탐색 가능

### **멀티 쓰레딩의 필요성**

- 동시성(Concurrency): 여러 작업을 동시에 처리하는 것처럼 보이게 만듦
- 병렬 처리(Parallelism): 실제로 여러 작업을 동시에 실행하는 것
- 응답성 향상: 사용자가 입력한 명령에 빠르게 반응하도록 만들어줌
- 리소스 활용 최적화: CPU의 멀티코어를 활용하여 성능을 극대화함

### 생성방법

- 기존 코드(메인 스레드)
    
    ```java
    package ThreadEx;
    
    import java.awt.Toolkit;
    
    //단일 스레드 : 메인 스레드만 존재
    public class BeepPrintEx {
    	public static void main(String[] args) {
    		Toolkit toolkit = Toolkit.getDefaultToolkit();
    		for(int i = 0; i < 5; i++) {
    			toolkit.beep();
    			try {
    				Thread.sleep(500);
    			} catch (Exception e) {
    			}
    		}
    		
    		for(int i = 0; i < 5; i++) {
    			System.out.print("땡 ");
    			try {
    				Thread.sleep(500);
    			} catch (Exception e) {
    			}
    		}
    	}
    }
    ```
    
- Thread 객체로 직접 생성
    - BeepPrintMain
        
        ```java
        package ThreadEx2;
        
        //단일 스레드 : 메인 스레드만 존재
        public class BeepPrintMain {
        	public static void main(String[] args) {
        		//Runnable 객체 구현
        		Runnable beepTask = new BeepTask();
        		
        		//작업 스레드 생성
        		Thread thread = new Thread(beepTask);
        		
        		//스레드 호출
        		thread.start();
        		
        		for(int i = 0; i < 5; i++) {
        			System.out.print("땡 ");
        			try {
        				Thread.sleep(500);
        			} catch (Exception e) {
        			}
        		}
        		
        	}
        }
        
        ```
        
    - BeepTask
        - Runnable 인터페이스를 상속받아 사용
            - run() 메서드 하나만 정의되어 있는 함수형 인터페이스
            - 실제 쓰레드는 아님
        
        ```java
        package ThreadEx2;
        
        import java.awt.Toolkit;
        
        public class BeepTask implements Runnable {
        
        	@Override
        	public void run() {
        		Toolkit toolkit = Toolkit.getDefaultToolkit();
        		for(int i = 0; i < 5; i++) {
        			toolkit.beep();
        			try {
        				Thread.sleep(500);
        			} catch (Exception e) {
        			}
        		}
        	}
        	
        }
        
        ```
        
- 익명 구현 객체 사용
    - Runnable 생성자 호출
    
    ```java
    package ThreadEx3;
    
    import java.awt.Toolkit;
    
    //단일 스레드 : 메인 스레드만 존재
    public class BeepPrintMain {
    	public static void main(String[] args) {
    		
    		//작업 스레드 생성
    		//익명 구현 객체 사용
    		Thread thread = new Thread(new Runnable() {
    
    			@Override
    			public void run() {
    				Toolkit toolkit = Toolkit.getDefaultToolkit();
    				for(int i = 0; i < 5; i++) {
    					toolkit.beep();
    					try {
    						Thread.sleep(500);
    					} catch (Exception e) {
    					}
    				}
    			}
    			
    		});
    		
    		//스레드 호출
    		thread.start();
    		
    		for(int i = 0; i < 5; i++) {
    			System.out.print("땡 ");
    			try {
    				Thread.sleep(500);
    			} catch (Exception e) {
    			}
    		}
    		
    	}
    }
    
    ```
    
    - 람다 사용
        
        ```java
        package ThreadEx4;
        
        import java.awt.Toolkit;
        
        //단일 스레드 : 메인 스레드만 존재
        public class BeepPrintMain {
        	public static void main(String[] args) {
        		
        		//작업 스레드 생성
        		//익명 구현 객체 & 람다 사용
        		Thread thread = new Thread(() -> {
        				Toolkit toolkit = Toolkit.getDefaultToolkit();
        				for(int i = 0; i < 5; i++) {
        					toolkit.beep();
        					try {
        						Thread.sleep(500);
        					} catch (Exception e) {
        					}
        				}
        		});
        		
        		//스레드 호출
        		thread.start();
        		
        		for(int i = 0; i < 5; i++) {
        			System.out.print("땡 ");
        			try {
        				Thread.sleep(500);
        			} catch (Exception e) {
        			}
        		}
        		
        	}
        }
        
        ```
        
- 클래스 인터페이스를 상속받아 사용
    - BeepThreadMain
        
        ```java
        package ThreadEx5;
        
        //단일 스레드 : 메인 스레드만 존재
        public class BeepThreadMain {
        	public static void main(String[] args) {
        		Thread thread = new BeepThread();
        		thread.start();
        		
        		for(int i = 0; i < 5; i++) {
        			System.out.print("땡 ");
        			try {
        				Thread.sleep(500);
        			} catch (Exception e) {
        			}
        		}
        		
        	}
        }
        
        ```
        
    - BeepThread
        
        ```java
        package ThreadEx5;
        
        import java.awt.Toolkit;
        
        //쓰레드 하위 클래스로부터 생성
        //쓰레드 클래스 상속 받아 사
        public class BeepThread extends Thread{
        	@Override
        	public void run() {
        		Toolkit toolkit = Toolkit.getDefaultToolkit();
        		for(int i = 0; i < 5; i++) {
        			toolkit.beep();
        			try {
        				Thread.sleep(500);
        			} catch (Exception e) {
        			}
        		}
        	}
        }
        
        ```
        
- 멀티스레드
    - MultiThreadMain
        
        ```java
        package ThreadEx6;
        
        public class MultiThreadMain {
        
        	public static void main(String[] args) {
        		Thread t1 = new MultiThread(1);
        		Thread t2 = new MultiThread(2);
        		Thread t3 = new MultiThread(3);
        
        		t1.start();
        		t2.start();
        		t3.start();
        	}
        
        }
        
        ```
        
    - MultiThread
        
        ```java
        package ThreadEx6;
        
        public class MultiThread extends Thread{
        	
        	private int id;
        	
        	public MultiThread(int id) {
        		this.id = id;
        	}
        
        	@Override
        	public void run() {
        		for(int i = 0; i < 5; i++) {
        			System.out.println("thread(" + id + "), i : " + i); 
        		}
        	}
        }
        
        ```
        

### 실행방법

- thread.start()를 통해 Runnable의 run()을 실행시킴

### 동기화

- 동기화란 여러 쓰레드가 동시에 공유 자원에 접근할 때 발생할 수 있는 문제를 방지하기 위한 기법
- 멀티쓰레딩에서는 여러 쓰레드가 동시에 같은 데이터를 수정하거나 읽을 수 있기 때문에 데이터의 일관성이 깨질 수 있음
    - Race Condition
        - 두 개 이상의 쓰레드가 동시에 같은 자원에 접근하여 원하지 않는 결과를 초래하는 상황
    - ex) 은행 계좌 잔액

### synchronized

- 특정 코드 영역이 한 번에 하나의 쓰레드만 접근할 수 있도록 보호
- 인스턴스 메서드 동기화와 정적 메서드 동기화로 나눌 수 있음
    - 인스턴스 메서드 동기화: 객체 단위로 락(lock)을 걸어 해당 객체에 대해 하나의 쓰레드만 접근 가능.
    - 정적 메서드 동기화: 클래스 전체에 락을 걸어 해당 클래스의 모든 인스턴스가 공유하는 자원에 접근 제한.
- 예제
    
    ```java
    class BankAccount {
        private int balance = 1000;
    
        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + "가 출금: " + amount);
                balance -= amount;
                System.out.println("잔액: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " 출금 실패, 잔액 부족");
            }
        }
    }
    
    public class SynchronizedExample {
        public static void main(String[] args) {
            BankAccount account = new BankAccount();
    
            Thread t1 = new Thread(() -> account.withdraw(600), "Thread-1");
            Thread t2 = new Thread(() -> account.withdraw(600), "Thread-2");
    
            t1.start();
            t2.start();
        }
    }
    -> 
    Thread-1가 출금: 600
    잔액: 400
    Thread-2 출금 실패, 잔액 부족
    ```