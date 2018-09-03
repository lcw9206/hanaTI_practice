package kr.or.kosta.entity;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Map을 이용한 은행계좌 관리 클래스
 * 
 * @author 이철우
 */
public class AccountManagerMap {
	private Hashtable<String, Account> accounts;

	// Default 생성자
	public AccountManagerMap() {
		this(10);
	}

	public AccountManagerMap(int capacity) {
		accounts = new Hashtable<>(capacity, 5);
	}

	/**
	 * 계좌 생성 메서드 
	 * containsKey 메서드를 이용해 중복 계좌 검색 후, 계좌 생성 Swing의 요소를 이용해 문구를 출력합니다.
	 * 
	 * @param account
	 */
	public void add(Account account) {
		if (!accounts.containsKey(account.getAccountNum())) {
			accounts.put(account.getAccountNum(), account);
		} else {
			try {
				throw new AccountException("이미 존재하는 계좌번호입니다.", -600);
			} catch (AccountException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "이미 존재하는 계좌번호입니다.", "알림", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

	/**
	 * 계좌 리스트 반환 메서드
	 * Account 객체를 List로 받아 Enumeration을 이용해 계좌 목록들을 list에 담아 반환합니다.
	 * 
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
	 * 계좌 번호를 이용한 계좌 검색 메서드 
	 * containsKey 메서드를 이용해 계좌 유무를 파악합니다.
	 * 
	 * @param accountNum
	 * @return Account
	 */
	public Account get(String accountNum) {
		if (accounts.containsKey(accountNum)) {
			return accounts.get(accountNum);
		}
		return null;
	}

	/**
	 * 예금자명을 이용한 계좌 검색 메서드
	 * 계좌 리스트 반환 메서드와 동일한 로직에 while문 안에서 예금자명을 기준으로 list를 작성합니다.
	 * 
	 * @param accountOwner
	 * @return Account[]
	 */
	public List<Account> search(String accountOwner) {
		List<Account> list = new ArrayList<Account>();
		Enumeration<Account> e = accounts.elements();
		while (e.hasMoreElements()) {
			Account acc = e.nextElement();
			if (acc.getAccountOwner().equals(accountOwner)) {
				list.add(acc);
			}
		}
		return list;
	}

	/**
	 * 계좌 삭제 메서드 
	 * containsKey 메서드를 이용해 계좌 유무를 파악합니다.
	 * 
	 * @param accountNum
	 * @return boolean
	 */
	public boolean remove(String accountNum) {
		if (accounts.containsKey(accountNum)) {
			// 형변환을 위한 != null;
			return accounts.remove(accountNum) != null;
		}
		return false;
	}
}
