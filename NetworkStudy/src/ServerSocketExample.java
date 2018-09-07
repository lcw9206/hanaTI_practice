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
		InputStream in = null;
		OutputStream out = null;

		try {
			// 서버소켓 연결
			ServerSocket serverSocket = new ServerSocket(PORT);
			System.out.println(PORT + "포트에서 서버 실행");

			while (running) {
				// 소켓이 연결되지 않으면 accept 메서드에서 머물러있는다.
				socket = serverSocket.accept();
				System.out.println(socket.getInetAddress() + " 클라이언트가 연결해옴");
				in = socket.getInputStream();
				out = socket.getOutputStream();
//				
//				int data = in.read();
//				System.out.println("수신 데이터 : " + data);
//				out.write(data);

				PrintWriter pw = new PrintWriter(out, true);
				// 브릿지 삽입
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				boolean stop = false;
				
				while (!stop) {
					String clientMesseage = br.readLine();
					System.out.println("클라이언트 메세지 : " + clientMesseage); 
					if (clientMesseage.equalsIgnoreCase("quit")) {
						break;
					}
					pw.println(clientMesseage);
				}
//				pw.close();
//				br.close();
				socket.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
