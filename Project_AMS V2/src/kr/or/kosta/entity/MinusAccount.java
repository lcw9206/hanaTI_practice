package kr.or.kosta.entity;

import java.util.Formatter;

/**
 * Account를 확장한 마이너스 계좌 클래스
 * 
 * @author 이철우
 */
public class MinusAccount extends Account {
	private long borrowMoney;

	public MinusAccount() {
		this(null, null, 0, 0, 0);
	}

	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}

	// Getter와 Setter 메서드
	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	@Override
	public long getRestMoney() {
		return super.getRestMoney() - getBorrowMoney();

	}

	/**
	 * 마이너스 계좌용 출력 메서드 
	 * 줄맞춤을 위해 Formatter 클래스 사용합니다.
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		String aNum = String.format("%19s", getAccountNum());
		String aOwner = String.format("\t %-14s", getAccountOwner());
		Formatter restFormatter = new Formatter();
		Formatter borrowFormatter = new Formatter();
		return "마이너스계좌\t" + aNum + aOwner + restFormatter.format("%,9d", getRestMoney())
				+ borrowFormatter.format("\t%,8d\n", getBorrowMoney());
	}

}
