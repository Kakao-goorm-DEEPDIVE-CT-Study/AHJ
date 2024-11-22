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
