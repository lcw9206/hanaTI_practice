import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP/IP 기반의 Socket 프로그래밍 원리 (클라이언트)
 * 
 * @author 이철우
 *
 */
public class SocketExample {
//	public static final String DOMAIN = "127.0.0.1";
//	public static final String DOMAIN = "localhost";
	public static final String DOMAIN = "192.168.0.123";
	public static final int PORT = 7777;
	
	public static void main(String[] args) {
		Socket socket = null;
		InputStream in = null;
		OutputStream out = null;
		
		try {
//			Socket socket = new Socket(InetAddress.getByName(DOMAIN), PORT);
			socket = new Socket(DOMAIN, PORT);
			System.out.println("서버와 연결됨.");
			
			// 스트림 생성, 바이트!
			// 바이트를 지지고 볶아 맞춤형으로 데이터를 가공한다.
			in = socket.getInputStream();
			out = socket.getOutputStream();
			out.write(10);
			
			BufferedInputStream bin = new BufferedInputStream(in);
			
			System.out.println("서버에 데이터 전송.");
			int data = in.read();
			System.out.println("수신한 데이터 : " + data);
			
		} catch (IOException e) {
			System.out.println("서버를 연결할 수 없습니다.");
		} finally {
			try {
				// 생성된 순서 역순으로 닫는다.
//				out.close();
//				in.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
