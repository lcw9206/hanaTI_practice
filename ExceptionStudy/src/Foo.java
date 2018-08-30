import java.io.IOException;

public class Foo {
	public void someMethod() {
		String message = null;
//		Exception 발생
//		new NullPointerException 생성 후, 에러문구 출력
		System.out.println(message.length());

		int[] arr = { 1, 2, 3 };
		System.out.println(arr[3]);
	}

	// 예외처리 해보기
	public void someMethod2() {
		try {
			String message = null;
			int[] arr = { 1, 2, 3 };
			// Exception 발생
			// new NullPointerException 생성 후, 에러문구 출력
			// System.out.println(message.length());
			// System.out.println(10 / 0);
			// int[] arr = { 1, 2, 3 };
			System.out.println(arr[3]);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			System.out.println("인스턴스 생성 안됐슈!1");
			e.printStackTrace(); // 직접 디버깅할 때 사용 (콜스택 내용 출력)
			return ;
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());
			System.out.println("인스턴스 생성 안됐슈!2");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
			System.out.println("인스턴스 생성 안됐슈!3");
			e.printStackTrace();
		} catch (Exception e) {
			// catch 마지막엔 최상위 클래스인 Exception을 기재해 모든 예외를 처리한다.
			System.out.println(e.getMessage());
			System.out.println("인스턴스 생성 안됐슈!4");
			e.printStackTrace(); // 직접 디버깅할 때 사용 (콜스택 내용 출력)
		} finally {
			System.out.println("예외 발생 여부와 상관없이 항상 실행");
		}

	}

	// throws
	// 원하는 곳에 예외를 떠넘긴다. try-catch는 메서드마다 선언해야 하지만 throws는 중복을 줄일 수 있다.
	public void someMethod3() throws NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException {
		String message = null;
		System.out.println(message.length());
		System.out.println(10 / 0);
		int[] arr = { 1, 2, 3 };
		System.out.println(arr[3]);

	}

	public static void main(String[] args) {
		System.out.println("JVM에 의해 프로그램 시작...");
		Foo foo = new Foo();
//		foo.someMethod2();

//		try {
//			foo.someMethod3();
//		}catch (Exception e) {
//			System.out.println("메인에서 모든 예외 처리 완료");
//		}
		
		
		// 키보드에서 문자 읽어들이기
		try {
			int value = System.in.read();
			System.out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		System.out.println("프로그램 종료됨");
	}
}
