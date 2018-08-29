import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * Vector를 이용한 은행계좌 관리
 * @author 이철우
 *
 */
public class AccountManagerVector {
	// Vector는 
	private Vector<Account> accounts;
	
	AccountManagerVector(){
		this(100);
	}
	
	AccountManagerVector(int capacity) {
		// Vector(생성량, 증가량)
		accounts = new Vector<Account>(capacity, 5);
	}
	
	
	
	/**
	 * 계좌 생성 메서드
	 * call by reference
	 * @param account
	 */
	public void add(Account account) {
		accounts.addElement(account);
	}
	
	/**
	 * 실제 들어있는 전체계좌 반환
	 * @return Account[]
	 */
	public List list() {
		// List는 남는 공간을 알아서 줄여준다.
		return accounts;
	}
	
	/**
	 * 계좌 검색
	 * @param accountNum
	 * @return Account
	 */
	public Account get(String accountNum) {
		Enumeration enu = accounts.elements();
		while(enu.hasMoreElements()) {
			Account account = (Account)enu.nextElement();
			boolean eq = account.getAccountNum().equals(accountNum);
			if(eq) {
				return account;
			}
		}
		return null;
	}
	
	/**
	 * 이름 검색
	 * @param accountOwner
	 * @return Account[]
	 */
	public List search(String accountOwner) {
		List ac = new ArrayList();
		Enumeration<Account> enu = accounts.elements();
		while(enu.hasMoreElements()) {
			Account account = enu.nextElement();
			if(account.getAccountOwner().equals(accountOwner)) {
				ac.add(account);
			}
		}
		 
		return ac;
	}
	
	/**
	 * 계좌 삭제 
	 * @param accountNum
	 * @return boolean
	 */
	public boolean remove(String accountNum) {
		Enumeration enu = accounts.elements();
		while(enu.hasMoreElements()) {
			Account account = (Account)enu.nextElement();
			if(account.getAccountNum().equals(accountNum)) {
				return accounts.removeElement(account);
			}
		}
		return false;
	}
}
