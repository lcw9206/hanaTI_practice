import java.net.Socket;

/**
 * 클라이언트의 데이터 수신 및 처리
 * 
 * @author 이철우
 *
 */
public class Client extends Thread {
	private Socket socket;
	
	Client(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
	}
	
	
}
