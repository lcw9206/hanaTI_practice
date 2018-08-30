import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CollectionQuiz {

	// hashcode를 Object의 것을 쓰지 않도록 오버라이딩해야한다.
	public static void main(String[] args) {
		Set<Account> set = new HashSet<Account>();
		// 중복이 없어야하지만 밑을 실행하면 3개가 다 들어간다.
		set.add(new Account("1111", "김기정", 1111, 10000));
		set.add(new Account("2222", "박기정", 1111, 10000));
		set.add(new Account("1111", "김기정", 1111, 10000));
		System.out.println(set);
		
		// 환경변수 출력
		Map<String, String> map = System.getenv();
		Set<String> ketSet = map.keySet();
		for (String name : ketSet) {
			String value = System.getenv(name);
			System.out.println(name + "=" + value);
		}
	}
}