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
