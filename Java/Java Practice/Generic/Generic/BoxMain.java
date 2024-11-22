package Generic;

public class BoxMain {

	public static void main(String[] args) {
		Box<String> box = new Box<String>();
		box.setT("Hello");
		
		String str = box.getT();
		System.out.println(str);
		
		Box<Integer> box2 = new Box<Integer>();
		box2.setT(10);
		
		int num = box2.getT();
		System.out.println(num);
	}

}
