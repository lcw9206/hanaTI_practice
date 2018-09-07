import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {

	public static final int PORT = 7777;

	public static void main(String[] args) {
		boolean running = true;
		Socket socket = null;

		try {
			// 서버소켓 연결, 7777포트를 감시하고 있으며 소켓을 생성한다.
			// 
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println(PORT + "포트에서 서버 실행");

			while (running) {
				// 소켓이 연결되지 않으면 accept 메서드에서 머물러있는다.
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + " 클라이언트가 연결해옴");

				// client - socket : has-a 관계
				Client client = new Client(socket);
				client.start(); 
				// start 후, 객체를 해쉬테이블에 담아 일괄 관리하면 다중 채팅이 가능하다.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
