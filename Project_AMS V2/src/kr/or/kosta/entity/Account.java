package kr.or.kosta.entity;

import java.util.Formatter;

/**
 * 일상생활의 객체를 추상화하기 위한 모델링 클래스 정의 은행계좌 객체
 * 
 * @author 이철우
 */
public class Account {

	public final static String bankName = "KOSTA 은행";
	private String accountNum;
	private String accountOwner;
	private int passwd;
	private long restMoney;

	// Default 생성자
	public Account() {
		this(null, null);
	}

	public Account(String accountNum, String accountOwner) {
		this(accountNum, accountOwner, 0, 0);
	}

	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}

	// Getter/Setter 메서드
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	public long getRestMoney() {
		return this.restMoney;
	}

	public boolean checkPasswd(int passwd) {
		return this.passwd == passwd;
	}
	
	/**
	 * 입출금 계좌용 출력 메서드 
	 * 줄맞춤을 위해 Formatter 클래스를 사용합니다.
	 */
	@Override
	public String toString() {
		String aNum = String.format("%19s", getAccountNum());
		String aOwner = String.format("\t %-14s", getAccountOwner());
		Formatter restFormatter = new Formatter();

		return "입출금계좌\t" + aNum + aOwner + restFormatter.format("%,9d\n", getRestMoney());
	}

}
