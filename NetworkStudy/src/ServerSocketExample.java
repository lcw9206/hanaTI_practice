import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			
			while(running) {
				// 소켓이 연결되지 않으면 accept 메서드에서 머물러있는다.
				socket = serverSocket.accept();
				System.out.println("????클라이언트가 연결해옴");
				in = socket.getInputStream();
				out = socket.getOutputStream();
				
				int data = in.read();
				System.out.println("수신 데이터 : " + data);
				
				
				// 에코 서버 => 받은 것을 다시 되돌려주는 기본서버 
				out.write(data);
				out.close();
				in.close();
				socket.close();
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
