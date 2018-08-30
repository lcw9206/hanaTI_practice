import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 
 * 순서를 통해 데이터를 관리하기 위한 규약(인터페이스)이다.
 * Set과 달리 요소가 순차적으로 관리되며, 중복을 허용하는 특징을 가진다.
 * ArrayList, Vector, LinkedList가 List 인터페이스를 구현한 대표적 클래스이다
 * @author 이철우
 */
public class ListExample {

	public static void main(String[] args) {
		List list = new ArrayList(10);

		// 모든 값을 넣을 수 있다.
		list.add("황의조");
		list.add("손흥민");
		list.add("바나나");
		list.add(100);
		
		// autoboxing이 일어남
		list.add(new Integer(100));
		list.add(Calendar.getInstance());
		list.add("황의조");

		System.out.println("담겨진 개수 : " + list.size());
		System.out.println("비어있는지 여부 : " + list.isEmpty());
		
		Set boddari = new HashSet();
		boddari.add("AAAA");
		boddari.add("BBBB");
		boddari.add("CCCC");
		
		list.addAll(boddari);
		System.out.println("담겨진 개수 : " + list.size());
		
		boolean result = list.remove("바나나");
		System.out.println("삭제결과: " + result);
		
		System.out.println(list.contains("황의조"));
		
		for (Object object : list) {
			// object 중 다운 캐스팅이 필요한 경우도 있음. ex) String의 length();
			if(object instanceof String) { 
			System.out.println(((String) object).length());
			}
			System.out.println(object);
		}
		
		Iterator iter = list.iterator();
		while(iter.hasNext()) {
			Object object = iter.next();
			System.out.println(object);
		}
		
		// list에 추가된 규약 메소드들
		list.add(0,"김기정");
		System.out.println(list.get(0));
		// remove : return 값;
		System.out.println(list.remove(0));
		System.out.println(list.set(0, "황희찬"));
		
		System.out.println(list.size());
		System.out.println(list.subList(0, 3));
		
	}
}
