
public class OuterClass {
	
	// enum도 클래스 안에 쓸 수 있다.
	enum Direction{
		A, B, C, D
	}

	class InnerClass{
		public void foo() {
			System.out.println("foo 호출");

		}
	}
	// new를 이용하지 않아도 사용할 수 있도록 만든 스태틱 내부 클래스 
	static class SInnerClass{
		public void bar() {
			System.out.println("bar 호출");
		}
	}
}
