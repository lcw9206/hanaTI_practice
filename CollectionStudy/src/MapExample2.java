import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class MapExample2 {

	public static void main(String[] args) {
		Hashtable<String, Account> table = new Hashtable<>();
		Account account = new Account("1111","김기정",1111,1000);
		Account account2 = new Account("2222","기정",1111,1000);
		
		table.put(account.getAccountNum(), account);
		table.put(account2.getAccountNum(), account2);
		
		System.out.println(table.get("1111"));
		
		// 동기화 처리된 keys()
		Enumeration<String> e = table.keys();
		while (e.hasMoreElements()) {
			String key = e.nextElement();
			System.out.println(key);
		}
		
		// value 가져오기
		Enumeration<Account> e1 = table.elements();
		while (e1.hasMoreElements()) {
			Account value = e1.nextElement();
			System.out.println(value);
		}
	}
}
