import java.util.StringTokenizer;

public class StringTokenizerExample {

	public static void main(String[] args) {
		// -를 구분자라 하며, 이를 이용해 문자열을 나눈 것을 토큰이라한다.
		String date = "2018-8-23";
		StringTokenizer str = new StringTokenizer(date, "-");
		System.out.println(str.countTokens());
		
		while(str.hasMoreTokens()) {
			String token = str.nextToken();
			System.out.println(token);
		}
	}

}
