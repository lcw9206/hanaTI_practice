import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Map을 이용한 은행계좌 관리
 * @author 이철우
 *
 */
public class AccountManagerMap {
	private Hashtable<String, Account> accounts;
	
	AccountManagerMap(){
		this(10);
	}
	
	AccountManagerMap(int capacity) {
		// Vector(생성량, 증가량)
		accounts = new Hashtable<>(capacity, 5);
	}
	
	
	/**
	 * 계좌 생성 메서드
	 * call by reference
	 * @param account
	 */
	public void add(Account account) throws AccountException {
		if(!accounts.containsKey(account.getAccountNum())) {
			accounts.put(account.getAccountNum(), account);
		} else {
			throw new AccountException("이미 존재하는 계좌입니다.", -5);
		}
	}
	
	/**
	 * 실제 들어있는 전체 계좌 반환
	 * @return Account[]
	 */
	public List list() {
		List<Account> list = new ArrayList<Account>();
		Enumeration<Account> e = accounts.elements();
		while (e.hasMoreElements()) {
			list.add(e.nextElement());
		}
		return list;
	}
	
	/**
	 * 계좌 검색
	 * @param accountNum
	 * @return Account
	 */
	public Account get(String accountNum) {
		if(accounts.containsKey(accountNum)) {
			return accounts.get(accountNum);
		}
		return null;
	}
	
	/**
	 * 이름 검색
	 * @param accountOwner
	 * @return Account[]
	 */
	public List<Account> search(String accountOwner) {
		List<Account> list = new ArrayList<Account>();
		Enumeration<Account> e = accounts.elements();
		while(e.hasMoreElements()) {
			Account acc = e.nextElement();
			if(acc.getAccountOwner().equals(accountOwner)) {
				list.add(acc);
			}
		}
		return list;
	}
	
	/**
	 * 계좌 삭제 
	 * @param accountNum
	 * @return boolean
	 */
	public boolean remove(String accountNum) {
		if(accounts.containsKey(accountNum)) {
			// 형변환을 위한 != null;
			return accounts.remove(accountNum) != null;
		}
		return false;
	}
}
