
public class WrapperClassExample {

	public static void main(String[] args) {

		char c = 'A';
		// 객체화
		Character obj = new Character(c);
		// 복구
		char c2 = obj.charValue();

		char[] data = { 'A', 'a', '4', ' ', '#' };
		for (int i = 0; i < data.length; i++) {
			if (Character.isUpperCase(data[i])) {
				System.out.println(data[i] + "은 대문자이다.");
			} else if (Character.isLowerCase(data[i])) {
				System.out.println(data[i] + "은 소문자이다.");
			} else if (Character.isDigit(data[i])) {
				System.out.println(data[i] + "은 숫자이다.");
			} else if (Character.isSpaceChar(data[i])) {
				System.out.println(data[i] + "은 공백문자이다.");
			}
		}

		// 객체화 
		Integer integer1 = new Integer(10);
		Integer integer2 = new Integer("10");
		// 복구
		int i1 = integer1.intValue();
		int i2 = integer2.intValue();
		System.out.println("두수의 합: " + (i1 + i2));

		// parseInt : int형으로 형변환
		String str = "3578";
		int i3 = Integer.parseInt(str);
		System.out.println(i3);
		System.out.println("10진수를 2진수로:" + Integer.toBinaryString(i3));
		System.out.println("10진수를 8진수로:" + Integer.toOctalString(i3));
		System.out.println("10진수를 16진수로:" + Integer.toHexString(i3));

		System.out.println(Integer.SIZE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		
	}

}
