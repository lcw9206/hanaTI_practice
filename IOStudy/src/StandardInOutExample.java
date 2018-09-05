import java.io.IOException;

public class StandardInOutExample {

	public static void main(String[] args) throws IOException {
		System.out.println(System.in);
		System.out.println(System.out);
		
		System.out.print("당신의 이름 : ");
		byte[] buffer = new byte[100];
		// count를 이용해 배열의 실제 길이를 구한다.
		int count = System.in.read(buffer);
		
		// offset 지정을 통해 100개의 배열을 다 들여다보지 않아도 됨.
		// 문자 디코딩 처리
		// enter까지 입력되면 개행이 되므로 처리
		String name = new String(buffer, 0, count-1);
		
		
		System.out.println("당신의 입력 이름은 " + name + " 입니다...");
		
		
		System.out.print("당신의 나이 : ");
		count = System.in.read(buffer);
		String age = new String(buffer, 0, count-1);
		Integer.parseInt(age); 
		
		System.out.println("당신의 입력 나이는 " + age + " 입니다...");

	}

}
