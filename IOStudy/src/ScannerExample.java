import java.io.IOException;
import java.util.Scanner;

public class ScannerExample {

	public static void main(String[] args) throws IOException {
		System.out.print("당신의 이름 : ");
		
		// Scanner는 inputStream 타입을 인자로 받기에 어떤 타입이든 입력이 가능
		Scanner scanner = new Scanner(System.in);
		System.out.println(scanner.nextLine());
		
		
		System.out.print("당신의 나이 : ");
		System.out.println(scanner.nextLine());

	}

}
