/**
 * 배열을 이용한 은행계좌 관리
 * @author 이철우
 *
 */
public class AccountManager {
	private Account[] accounts;
	private int count;
	
	AccountManager(){
		this(100);
	}
	
	AccountManager(int num) {
		accounts = new Account[num];
	}
	
	/**
	 * 계좌 생성 메서드
	 * call by reference
	 * @param account
	 */
	public void add(Account account) {
		accounts[count] = account;
		count++;
	}
	
	/**
	 * 실제 들어있는 전체계좌 반환
	 * @return Account[]
	 */
	public Account[] list() {
		Account[] accountsList = new Account[count];
		for (int i = 0; i < count; i++) {
			accountsList[i] = accounts[i];
		} 
		
		return accountsList;
	}
	
	/**
	 * 계좌 검색
	 * @param accountNum
	 * @return Account
	 */
	public Account get(String accountNum) {
		int check = 0;
		for (int i = 0; i < count; i++) {
			if(accountNum.equals(accounts[i].getAccountNum())) {
				check++;
				return accounts[i];
			}
		}
		
		if(check == 0) {
			System.out.println("해당하는 계좌가 없습니다.");
		}
		return null;
	}
	
	/**
	 * 이름 검색
	 * @param accountOwner
	 * @return Account[]
	 */
	public Account[] search(String accountOwner) {
		int namecount = 0;
		for (int i = 0; i < count; i++) {
			if(accountOwner.equals(accounts[i].getAccountOwner())) {
				namecount ++;
			}
		}
		
		Account[] realAccounts = new Account[namecount];
		
		for (int i = 0; i < namecount; i++) {
			if(accountOwner.equals(accounts[i].getAccountOwner())) {
				realAccounts[i] = accounts[i];
			}
		} 
		return realAccounts;
	}
	
	/**
	 * 계좌 삭제 
	 * @param accountNum
	 * @return boolean
	 */
	public boolean remove(String accountNum) {
		for (int i = 0; i < count; i++) {
			if(accountNum.equals(accounts[i].getAccountNum())) {
				for (int j = i; j < count; j++) {
					accounts[j] = accounts[j+1];
				}
				count--;
				return true;
			}
		}
		return false;
	}
}
