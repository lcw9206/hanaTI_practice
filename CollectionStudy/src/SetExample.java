import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * 데이터의 중복 저장 없이 데이터를 관리하기 위한 규약(인터페이스)이다. 순서와 관련 없이 데이터를 관리한다. HashSet이 Set
 * 인터페이스를 구현한 대표적인 클래스이다.
 * 
 * @author 이철우
 *
 */
public class SetExample {

	public static void main(String[] args) {
		Set set = null;
		// 용량
		set = new HashSet(10);

		// 모든 값을 넣을 수 있다.
		set.add("황의조");
		set.add("손흥민");
		set.add("바나나");
		set.add(100);
		// autoboxing이 일어남
		set.add(new Integer(100));
		set.add(Calendar.getInstance());
		set.add("황의조");

		System.out.println("담겨진 개수 : " + set.size());
		System.out.println("비어있는지 여부 : " + set.isEmpty());
		
		Set boddari = new HashSet();
		boddari.add("AAAA");
		boddari.add("BBBB");
		boddari.add("CCCC");
		
		set.addAll(boddari);
		System.out.println("담겨진 개수 : " + set.size());
		
		boolean result = set.remove("바나나");
		System.out.println("삭제결과: " + result);
		
		System.out.println(set.contains("황의조"));
		
		Object[] list = set.toArray();
		for (Object object : list) {
			// object 중 다운 캐스팅이 필요한 경우도 있음. ex) String의 length();
			if(object instanceof String) { 
			System.out.println(((String) object).length());
			}
			System.out.println(object);
		}
		
		Iterator iter = set.iterator();
		while(iter.hasNext()) {
			Object object = iter.next();
			System.out.println(object);
		}
	}
}
