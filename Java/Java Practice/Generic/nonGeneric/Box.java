package nonGeneric;

//제네릭 사용 X
//강제 타입 변환 발
public class Box {
	private Object object;
	
	public Object getObject() {
		return object;
	}
	
	public void setObject(Object object) {
		this.object = object;
	}
}
