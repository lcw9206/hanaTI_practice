import java.util.Comparator;

public class NumberCompare implements Comparator<Account> {

	// 기준을 제시해주는 메서드
	// 앞의 값이 크면 1, 같으면 0, 뒤의 값이 크면 -1 반환 
	@Override
	public int compare(Account o1, Account o2) {
		// 문자열 비교를 위해 compareTo를 이용한다.
		// 내림차수 
//		return o1.getAccountNum().compareTo(o2.getAccountNum());
		// 오름차수
		return o2.getAccountNum().compareTo(o1.getAccountNum());
		
	}

}
