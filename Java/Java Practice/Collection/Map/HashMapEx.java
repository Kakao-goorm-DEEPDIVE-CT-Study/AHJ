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
