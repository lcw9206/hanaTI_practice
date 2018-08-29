class OperatorExample {
	public static void main(String[] args) {
		int x = 50, y = 20;
		
		// 산술연산자
		System.out.println("결과 : " + x + y);
		System.out.println("나머지 : " + (x % y));

		// 복합대입연산자
		x += y;
		System.out.println(x);

		// 형변환연산자
		double weight = 78.34343;
		System.out.println((int)weight);

		// 비트연산자
		// 비트연산자로 연산하는 것이 더 빠르다.
		x = 10;
		System.out.println(x * 2 * 2 * 2);
		System.out.println(x << 3);

		// 조건삼항연산자
		int a=2, b=3, c=1, max;
		max = (a>b) ? a : b;
		max = (b>c) ? b : c;
		System.out.println("max : " + max);
	}
}
