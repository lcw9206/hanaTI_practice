import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsExample {

	public static void main(String[] args) {
		List<Account> list = new ArrayList<>();
		
		list.add(new Account("4111", "김기정", 1111, 10000));
		list.add(new Account("2222", "박기정", 1111, 20000));
		list.add(new Account("3111", "송기정", 1111, 30000));
		
		System.out.println(list);
		// Comparator를 이용해 기준 정렬 설정
		Collections.sort(list, new MoneyCompare());
		System.out.println(list);
	}

}
