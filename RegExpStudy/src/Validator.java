/**
 * 정규표현식을 활용한 유효성 체크 공통메소드
 * @author 김기정 
 *
 */
public class Validator {
	/**
	 * 입력필드 입력여부 검증
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value){
		if (value == null || value.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 아이디 유효성 검증
	 * 영문과숫자조합으로 8~10자 아이디
	 * ex) bangry313
	 * @param id
	 * @return
	 */
	public static boolean isValidId(String id){
		String regex = "^[0-9a-zA-Z]{8,10}$";
		return id.matches(regex);
	}

	/**
	 * 이메일 유효성 검증
	 * ex) bangry313@gmail.com
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
//		String regex = "^[0-9a-zA-Z]+\\@.[a-z].\\.[a-z]$";
		String regex = "^[a-zA-Z0-9]+\\@.\\.[a-z]$";
		
		return email.matches(regex);
	}
	
	/**
	 * 전화번호 유효성 검증
	 * 2~3자리숫자-3~4자리숫자-4자리숫자
	 * ex) 02-1234-5678
	 * @param number
	 * @return
	 */
	public static boolean isValidPhoneNumber(String number) {
		String regex = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
		return number.matches(regex);
	}
	
	/**
	 * 휴대폰번호 유효성 검증
	 * 010|011|016|017|018|019-3~4자리숫자-4자리숫자
	 * ex) 10-9179-8707
	 * @param number
	 * @return
	 */
	public static boolean isValidMobileNumber(String number) {
		String regex = "^01[0-9]-\\d{3,4}-\\d{4}$";
		return number.matches(regex);
	}
	
	/**
	 * 주민등록번호 유효성 검증
	 * 6자리숫자-1~8로 시작하는 7자리숫자
	 * ex) 680313-1234567
	 * @param ssn
	 * @return
	 */
	public static boolean isValidSSN(String ssn) {
		String regex = "^\\d{6}-[1-4]\\d{6}$";
		return ssn.matches(regex);
	}
	
	/**
	 * IP주소 유효성 검증
	 * 0~255.0~255.0~255.0~255
	 * ex) 192.168.0.28
	 * @param ip
	 * @return
	 */
	public static boolean isValidIP(String ip) {
		String regex = "^[1-2]?\\d{1,3}\\.[1-2]?\\d{1,3}\\.[1-2]?\\d{1,3}\\.[1-2]?\\d{1,3}$";
		return ip.matches(regex);
	}
	
	/**
	 * 파일명 유효성 검증
	 * ex) sample.gif
	 * @param ip
	 * @return
	 */
	public static boolean isValidFile(String fileName) {
		return false;
	}

	
	public static void main(String[] args) {
		System.out.println(Validator.isEmpty("  "));
		System.out.println(Validator.isValidId("bangry313"));
		System.out.println(Validator.isValidEmail("kj3133k@aa.com"));
		System.out.println(Validator.isValidPhoneNumber("02-1234-1234"));
		System.out.println(Validator.isValidMobileNumber("019-1234-1234"));
		System.out.println(Validator.isValidSSN("680313-1234568"));
		System.out.println(Validator.isValidIP("192.168.34.56"));
		System.out.println(Validator.isValidFile("abc.png"));
	}
}
