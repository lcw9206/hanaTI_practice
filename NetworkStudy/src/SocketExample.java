import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP/IP 기반의 Socket 프로그래밍 원리 (클라이언트)
 * 
 * @author 이철우
 *
 */
public class SocketExample {
//	public static final String DOMAIN = "127.0.0.1";
	public static final String DOMAIN = "localhost";
//	public static final String DOMAIN = "192.168.0.123";
	public static final int PORT = 7777;

	public static void main(String[] args) {
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;

		try {
			socket = new Socket(DOMAIN, PORT);
			System.out.println("서버와 연결됨.");

			// 스트림 생성, 바이트!
			// 바이트를 지지고 볶아 맞춤형으로 데이터를 가공한다.
			in = socket.getInputStream();
			out = socket.getOutputStream();

			PrintWriter pw = new PrintWriter(out, true);
			// 브릿지 삽입
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			Scanner scanner = new Scanner(System.in);

			while (true) {
				String inputMessage = scanner.nextLine();
				
				pw.println(inputMessage);

				if (inputMessage.equalsIgnoreCase("quit")) {
					break;
				}

				String serverMessage = br.readLine();
				System.out.println("서버 메세지 : " + serverMessage);
			}
			socket.close();

		} catch (IOException e) {
			System.out.println("서버를 연결할 수 없습니다.");
		}
	}
}
