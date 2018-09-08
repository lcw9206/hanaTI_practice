import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex API를 이용한 정규표현식 사용
 * @author 김기정
 */
public class RegExpressionExample0 {

	public static void main(String[] args) {
		String message = "Hello Java World...";
		String regExp = "java";
		// 패턴클래스 생성 / 팩토리메서드
//		Pattern pattern = Pattern.compile(regExp);
		// 두 번째 인자는 대소문자를 구분하지 않기위해 사용
		Pattern pattern = Pattern.compile(regExp, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(message);
		System.out.println(matcher.matches()); // 패턴과 일치 여부 반환
		System.out.println(matcher.find());    // 패턴과 일치하는 문자열 존재 여부 반환
		
		System.out.println("-------------------------");
		
		String str = "as";
		pattern = Pattern.compile(".s");
		matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
		
		// Pattern 클래스의 static 메소드 활용
		System.out.println(Pattern.matches(".s", str));
		System.out.println(Pattern.matches("..s", str));
	}

}
