package LamvdaEx;

public class MyFnInterfaceEx2{

	public static void main(String[] args) {
		MyFnInterface2 fi;
		//람다식은 익명 객체를 반환하기에 fi에 익명 객체를 넣은 후 fi.method();처럼수행
		
		fi = (x, y) -> {
			return x + y;
		};
		System.out.println(fi.method(2, 5));
		
		
		fi = (x, y) -> x + y;
		System.out.println(fi.method(10, 20));
		
		
		fi = (x,y) -> sum(x,y);
		System.out.println(fi.method(11, 12));

		
	}
	public static int sum(int x, int y) {
		return x + y;
	}
}
