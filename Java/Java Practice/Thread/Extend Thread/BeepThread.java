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
