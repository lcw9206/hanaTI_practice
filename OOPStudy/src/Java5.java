import static java.lang.Math.max;
import static java.lang.System.out;

import java.util.Formatter;

public class Java5 {

	// 가변인자 => 애용하자
	// 내부적으로 배열처리된다.
	// int... arg와 int[] arg는 동일한 배열이기에 오버라이딩이 안된다. 
	public static int sum(int... arg) {
		int sum = 0;
		for (int i: arg) {
			sum += i;
		}
		return sum;
	}
	
	// 위에서 선언한 열거형 Direction 이외의 값을 입력하면 컴파일 에러
	public static class Some {
		public void move(Directions directions) {
			switch (directions) {
				case NORTH: System.out.println("NORTH"); break;
				case WEST: System.out.println("WEST");  break;
				case EAST: System.out.println("EAST");  break;
				case SOUTH: System.out.println("SOUTH");  break;
				
			}
		}
	}
	
	// Annotation
	public static class Bar {
		@Deprecated
		public void some() {
			System.out.println("타용");
		}
		
		// 오버라이딩을 목적으로 했으나, 표시가 없어 오버라이딩인지 확장인지 알 수 없다.
		// 따라서 아래와 같이 표시해 사용자와 컴파일러에게 메타데이터를 알린다.
		@Override
		public String toString() {
			System.out.println("어노테이션 테스트");
			return "";
		}
	}
	
	
	public static void main(String[] args) {
		Some some = new Some();
		some.move(Directions.EAST);
		// AutoBoxing
		Double obj = 78.56;
		System.out.println(obj);
		
		// UnBoxing
		double value = new Double(169.2);

		// StaticImportExample
		System.out.println(Math.max(10, 20));
		out.println(max(10, 20));
		
		// 가변인자 
		int[] values = {10, 20};
		System.out.println(sum(values));
		
		// Enum
		Directions[] list = Directions.values();
		for (Directions directions : list) {
			System.out.println(directions);
		}
	
		// Annotation
		Bar bar = new Bar();
		bar.some();
		
		// Formatter
		
		int number = 1234567;
		Formatter formatter = new Formatter();
		// Formatter format(String format,Object... args)
		// format : "%[출력인자순서$][출력옵션(-, +, (,,..)][출력자리수][.소수점이하자리수]출력데이터유형"
		// args : 포맷팅 하고자 하는 가변인자
		String formatedString = null;
		formatter.format("%d", number).toString();
		System.out.println(formatter.format("%d", number));

		formatter = new Formatter();
		// 우측정렬 후 정수로 출력
		System.out.println(formatter.format("%d", number));

		formatter = new Formatter();
		// 20자리확보하고, 3자리단위 콤마 찍고, 부호달고, 좌측정렬 후 정수로 포맷 => 여기만 알아도 됨.
		System.out.println(formatter.format("%,+-20d", number));

		double height = 23454.34343434356;
		formatter = new Formatter();
		// 20자리확보하고, 3자리단위 콤마 찍고, 부호달고, 좌측정렬 후 소수점 이하 2자리 실수로 포맷
		System.out.println(formatter.format("%,+-20.2f", height));

		String name = "김기정";
		formatter = new Formatter();
		// 10자리확보하고 우측정렬 후 문자열 포맷
		System.out.println(formatter.format("%10s", name));

		formatter = new Formatter();
		System.out.println(formatter.format("%o", 150)); //8진수 포맷
		System.out.println(formatter.format("%x", 458)); //16진수 포맷
		
		// String 클래스의 클래스메소드 활용
		String fs = String.format("%,20.2f\n", 198745.678);
		System.out.println(fs);
		System.out.printf("%1$,-10d와 %2$,10d\n", 1000, 2000);

		
		
		
		
	}

}
