
/**
 * 레퍼런스 타입 배열 선언, 생성, 초기화
 * 
 * @author 이철우
 *
 */
public class ArrayExample3 {

	public static void main(String[] args) {
		// 선언
		String[] teams;
		// 생성, null로 초기화
		teams = new String[10];
		// 초기화, 명시적 선언이 가능해 클래스임에도 바로 사용 가능
		teams[0] = "두산 베어스";
		teams[1] = "SK 와이번즈";
		teams[2] = "한화 이글즈";
		teams[9] = "NC 다이노즈";
		// 위를 보면 단순히 문자열을 배열에 할당한 것이라 생각할 수 있지만, String이 객체이므로 막강한 기능이 있다.
		// 예를들어 String객체의 length를 이용해 각 배열의 길이를 구할 수 있다.

		for (int i = 0; i < teams.length; i++) {
			int count = 0;
			if (teams[i] != null) {
				count = teams[i].length();
			}
			System.out.println((i + 1) + "위 :" + teams[i] + "(" + count + ")");
		}

	}

}
