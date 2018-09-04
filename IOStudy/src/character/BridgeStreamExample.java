package character;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BridgeStreamExample {

	public static void main(String[] args) throws IOException {
		System.out.println(System.in);
		System.out.println(System.out);
		
		System.out.println("당신의 이름 : ");
		// 브릿지 스트림
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String name = br.readLine();
		System.out.println(name);
	}

}
