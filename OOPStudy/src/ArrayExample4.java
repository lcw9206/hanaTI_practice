import java.util.Scanner;

/**
 * 레퍼런스 타입 배열 선언, 생성, 초기화
 * 
 * @author 이철우
 *
 */
public class ArrayExample4 {

	public static void main(String[] args) {
//		Account account = new Account("1111-2222-3333", "Lee", 1234, 100000);
		// 많은 계좌 관리를 위한 배열 선언 및 생성
		Account[] accounts = new Account[100];

		int index = 0;
		// 은행 계좌 개설
		accounts[index] = new Account("1111-2222-3333", "Lee", 1234, 100000);
		index++;
		accounts[index] = new Account("5555-2222-3333", "Park", 1234, 200000);
		index++;
		accounts[index] = new Account("3333-2222-3333", "Kim", 1234, 500000);
		index++;

		// 전 계좌 목록 출력
		// accounts에는 객체가 참조되어 있으므로 객체를 출력한다.
		for (int i = 0; i < index; i++) {
			// 아래와 같이 객체를 출력하면 주소값이 아닌 해쉬값이 나온다. 이를 객체의 별칭이라고 생각하자. 주소값이 아니다!!!!!!!!!!!!!!
			// System.out.println(account);
			// System.out.println(account.getAccountNum() + account.getRestMoney());
			// 보통 account 객체의 값을 가져오려면 위와 같이 Account 클래스의 get함수를 요소마다 호출해 이용할 것이다.
			// 위와 같은 생각은 외부에서 내부의 데이터에 접근하고 있으므로 잘못된 것이며 OOP라 할 수 없다.
			// 그러므로 클래스 내부에 자신의 데이터 전체를 출력하는 기능을 넣어야 한다. 그리고 이를 캡슐화라 한다.
			System.out.println(accounts[i].toString());
		}

		// 계좌번호로 계좌 조회 기능
		String num = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색 계좌번호 : ");
		num = scanner.nextLine();

		// String num과 String AccountNum을 비교하는 것이므로 객체를 비교하는 것이다.
		// 따라서 equals를 사용해줘야 한다. ==은 reference의 주소를 비교하게 된다.
		for (int i = 0; i < index; i++) {
			if (num.equals(accounts[i].getAccountNum())) {
				System.out.println(accounts[i].toString());
				break;
			}

			if (i == index - 1) {
				System.out.println("찾는 계좌가 없습니다.");
			}
		}

	}
}
