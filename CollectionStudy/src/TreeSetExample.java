import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {

	public static void main(String[] args) {
		// 제너릭을 이용해 String만 받는 set
		Set<String> set = new TreeSet<String>();

		// 정렬하면서 Set에 담기에 add속도가 느리며, 많은 데이터에는 적합하지 않다.
		set.add("김기정");
		set.add("이기정");
		set.add("박기정");
		set.add("최기정");
		set.add("강기정");
		set.add("김기정");
		set.add("eaaaa");
		set.add("aaaa");
		
		for (String string : set) {
			System.out.println(string);
		}
	}

}
