package LamvdaEx;

public class MyFnInterfaceEx1{

	public static void main(String[] args) {
		MyFnInterface1 fi;
		//람다식은 익명 객체를 반환하기에 fi에 익명 객체를 넣은 후 fi.method();처럼수행
		
		fi = () -> {
			String str = "method call01";
			System.out.println(str);
		};
		fi.method();
		
		
		fi = () -> {
			System.out.println("method call02");
		};
		fi.method();
		
//		var f0 = () -> {
//			System.out.println("method call02");
//		};
//		f0.method();
	}

}
