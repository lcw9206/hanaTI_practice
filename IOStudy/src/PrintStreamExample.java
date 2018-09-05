import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;

public class PrintStreamExample {
	static final String path = "example4.dat";

	public static void main(String[] args) throws IOException {
		boolean flag = false;
		char c = '이';
		int age = 27;
		double weight = 73.2;
		String message = "입출력 프로그램입니다..";
		
		Calendar now = Calendar.getInstance();
		
		// 필터 스트림인데 유일하게 노드 스트림이 없어도 사용이 가능한 장점이 있다.
//		PrintStream out = new PrintStream(new FileOutputStream(path));
		PrintStream out = new PrintStream(path);
		
		out.println(flag);
		out.println(c);
		out.println(age);
		out.println(weight);
		out.println(message);
		// formatter로 객체 출력
		out.printf("%1$tF %1$tT", now);
		System.out.println("썼음");
		out.close();
	}	
}
