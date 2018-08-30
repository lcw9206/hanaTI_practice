import java.util.Comparator;

public class MoneyCompare implements Comparator<Account> {

	// 기준을 제시해주는 메서드
	// 앞의 값이 크면 1, 같으면 0, 뒤의 값이 크면 -1 반환 
	@Override
	public int compare(Account o1, Account o2) {
		return (int)(o1.getRestMoney() - o1.getRestMoney());
		
	}

}
